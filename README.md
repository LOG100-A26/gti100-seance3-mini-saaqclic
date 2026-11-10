# Mini-SAAQclic, séance 3

GTI100 : classes abstraites, interfaces, tri, exceptions et génériques.

Vous reprenez le noyau de la séance 2. Il encaisse maintenant de vrais paiements, refuse proprement,
trie ses transactions et lit des dossiers. Ce dépôt contient une solution de la séance 2 comme point de départ.
Les tarifs, les numéros et les règles sont fictifs et simplifiés pour l'exercice.

**Règle du cours : aucun outil d'IA pour générer le code.**

## Démarrer

1. Sur la page de ce dépôt, cliquez **Use this template**, puis **Create a new repository**.
2. Choisissez votre compte comme propriétaire et la visibilité **Private**.
3. Dans votre nouveau dépôt, ouvrez **Settings**, **Collaborators**, **Add people**, puis invitez `imenBen`.
4. Cliquez **Code**, onglet **Codespaces**, **Create codespace on main**.

Travaillez toujours dans votre dépôt, jamais dans le dépôt du cours.
Supprimez le Codespace de la séance 2 si vous avez poussé votre travail : il consomme votre quota.

## La boucle de travail

1. Lisez la mission, puis ouvrez le fichier indiqué dans `src/main/java/saaqclic`.
2. Écrivez votre code.
3. Dans le terminal : `bash verifier.sh 1` (remplacez 1 par le numéro de la mission).
4. Vert : **Commit** et **Sync Changes**. Rouge : lisez le message et corrigez.
5. Sur GitHub, l'onglet **Actions** affiche votre score. Vous pouvez aussi lancer `bash verifier.sh points`.

Faites les missions dans l'ordre. Du code qui ne compile pas donne zéro à toutes les missions.

| Mission | Fichiers | Points |
| --- | --- | --- |
| 0. Transaction abstraite | `Transaction` | 10 |
| 1. Interfaces | `CarteCredit`, `Interac`, `Permis`, `Transaction`, `Guichet` | 20 |
| 2. Trier | `Transaction`, `Tris`, `Caisse` | 20 |
| 3. Exceptions | `PaiementRefuseException`, `Paiement`, `CarteCredit`, `Interac`, `Caisse` | 25 |
| 4. Génériques | `Registre`, `Util`, `Caisse` | 15 |
| Boss | `ChargeurPermis` | 10 |

### Mission 0 : Transaction abstraite

Une `Transaction` sans forme précise n'a aucun sens. Rendez la classe abstraite, ainsi que `montant()` et
`description()`. `recu()` reste concrète.

### Mission 1 : interfaces

- `CarteCredit` et `Interac` respectent le contrat `Paiement`.
- `payer` retourne par exemple `Carte : 55.0 $` ou `Interac : 30.0 $`. `Interac` débite son solde.
- `Permis` et `Transaction` respectent le contrat `Imprimable`.
- `imprimer()` retourne `Permis ` suivi de `toString()`, ou `Reçu ` suivi de `recu()`.
- `Guichet.imprimerTout` produit une ligne par document et saute les cases `null`.

### Mission 2 : trier

- `Transaction` respecte `Comparable<Transaction>`, en comparant les montants.
- `Caisse.triees` retourne une nouvelle liste triée par montant.
- `Tris.parReference` utilise une classe anonyme.
- `Tris.parDescription` utilise une lambda.

### Mission 3 : exceptions

Procédez dans cet ordre et lisez chaque erreur de compilation :

1. Faites de `PaiementRefuseException` une exception vérifiée.
2. Dans `CarteCredit` et `Interac`, lancez-la au bon moment, avec les messages indiqués dans le code.
3. Un montant nul ou négatif lance `IllegalArgumentException`.
4. Déclarez l'exception là où le compilateur l'exige, y compris dans l'interface.
5. `Caisse.encaisser` retourne le résultat de `payer`, ou `Refusé : ` suivi du message.
6. Chaque appel à `encaisser` compte dans `tentatives`, même si une autre exception traverse la méthode.

### Mission 4 : génériques

- `Registre` devient `Registre<T>`. Il n'y a plus de `Object` dans ses signatures.
- `Util.max` devient une méthode générique bornée.
- `Caisse.totalListe` accepte une liste de n'importe quel enfant de `Transaction`.

### Boss : charger un fichier

`ChargeurPermis.charger` lit un fichier comme `donnees/permis.csv` et retourne un `Registre<Permis>`.

- Le fichier s'ouvre avec try-with-resources.
- Une ligne erronée est ignorée, et la lecture continue.
- Un fichier absent laisse remonter `IOException`.
