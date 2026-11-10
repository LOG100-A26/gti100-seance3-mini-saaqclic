#!/usr/bin/env bash
# Lance les tests du dépôt.
#   bash verifier.sh 2       teste la mission 2
#   bash verifier.sh boss    teste la mission boss
#   bash verifier.sh         lance tous les tests
#   bash verifier.sh points  calcule votre score sur 100
set -u
export LC_ALL=C.UTF-8

MISSIONS=(
  "Mission 0 : Transaction abstraite|Mission0Test|10"
  "Mission 1 : interfaces|Mission1Test|20"
  "Mission 2 : trier|Mission2Test|20"
  "Mission 3 : exceptions|Mission3Test|25"
  "Mission 4 : génériques|Mission4Test|15"
  "Boss : charger un fichier|BossTest|10"
)

resume() {
  echo "$1"
  if [ -n "${GITHUB_STEP_SUMMARY:-}" ]; then
    echo "$1" >> "$GITHUB_STEP_SUMMARY"
  fi
}

calculer_points() {
  journal=$(mktemp)
  if ! mvn -q -ntp test-compile > "$journal" 2>&1; then
    cat "$journal"
    rm -f "$journal"
    resume "**COMPILATION ERROR : aucun test n'a pu tourner. Score : 0 / 100**"
    return 1
  fi
  rm -f "$journal"

  total=0
  resume "| Mission | Résultat | Points |"
  resume "| --- | --- | --- |"
  for mission in "${MISSIONS[@]}"; do
    IFS='|' read -r nom classe max <<< "$mission"
    if mvn -q -ntp test -Dtest="$classe" > /dev/null 2>&1; then
      total=$((total + max))
      resume "| $nom | Vert | $max / $max |"
    else
      resume "| $nom | Rouge | 0 / $max |"
    fi
  done
  resume ""
  resume "**SCORE : $total / 100**"
  if [ -n "${GITHUB_ACTIONS:-}" ]; then
    echo "::notice title=Score::$total / 100"
  fi
}

cible="${1:-tout}"
case "$cible" in
  0|1|2|3|4) classe="Mission${cible}Test"; titre="Mission $cible" ;;
  boss|Boss)   classe="BossTest";            titre="Mission boss" ;;
  tout)        classe="";                    titre="Toutes les missions" ;;
  points)      calculer_points; exit $? ;;
  *) echo "Usage : bash verifier.sh [0|1|2|3|4|boss|points]"; exit 2 ;;
esac

if [ -n "$classe" ]; then
  mvn -q -ntp test -Dtest="$classe"
else
  mvn -q -ntp test
fi
code=$?

echo
if [ "$code" -eq 0 ]; then
  echo "VERT : $titre, tous les tests passent. Faites commit et push."
else
  echo "ROUGE : $titre, au moins un test ne passe pas."
  echo "Cherchez plus haut la ligne [ERROR] qui commence par MissionXTest : elle dit ce qui était attendu."
  echo "« COMPILATION ERROR » veut dire que Java ne comprend pas le code : aucun test n'a pu tourner."
fi
exit "$code"
