Feature: Gestion de référentiel

Scenario Outline: Ajouter un nouveau user avec des informations valides
When le responsable crée un nouveau user avec le <nom> , le <prenom> ,le <email> et le <password>
Then le nouveau user avec le <nom> <prenom> est bien enregistré et apparaît dans la liste des users
Examples:
| nom  |prenom      |email            |password
| Zaid |  BOURAUOI  |zaid.C@gemail.com |kdclkmd
| Arwa |   ABDI     |arwa.A@gemail.com |j,cdjjc

Scenario Outline: Vérifier l'unicité du champ email
    Given Un user avec le email <email> existe déjà
    When le responsable ajoute un nouveau user avec le email <email>
    Then la société doit être rejetée en raison de la contrainte d'unicité de l'attribut eemail
    Examples

     | eemail
     | arwa.A@gemail.com

Scenario Outline: Activer un profil

Given le user <nom> <prenom> avec un email <email> existe déjà
When le responsable active l'attribut isAccountActivated pour ce user
Then l'update doit être effectué avec succes
Examples:
  | nom  |prenom      |eemail
  | Zaid |  BOURAUOI  |zaid.C@gemail.com
  | Arwa |   ABDI     |arwa.A@gemail.com

Scenario Outline: Archiver un profil
Given le user <nom> <prenom> avec un email <email> existe déjà
When le responsable desactive l'attribut isAccountActivated pour ce user
Then la société <Soc_nom> ne doit pas etre affiché dans la liste des societes disponibles
Examples:
  | nom  |prenom      |email
  | Zaid |  BOURAUOI  |zaid.C@gemail.com
  | Arwa |   ABDI     |arwa.A@gemail.com

Scenario Outline:Mis à jour du email
    Given le user <nom> <prenom> avec un email <email> existe déjà
    When le responsable modifie la valeur de l'attribut email pour ce user
    Then Mis à jour doit être effectué avec succes
    Examples:
      | nom  |prenom      |email
      | Zaid |  BOURAUOI  |zaid.C@gemail.com
      | Arwa |   ABDI     |arwa.A@gemail.com