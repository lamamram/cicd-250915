# job test interfaces

## composants

* on va utiliser le conteneur maven classique du pipeline
  - donc on a besoin des dépendances
* on va ajouter un **conteneur dynamique** selenium `selenium/standalone-firefox` en tant que serveur
* on a besoin en plus du `geckodriver` sur le client, entre le client et le serveur

## installation du serveur

*  en manuel
```bash
docker run \
       --name selenium \
       -d --restart unless-stopped \
       selenium/standalone-firefox:<tag>
```

* ou dans gitlab avec un **service** à la volée puisque le serveur est complètement
* configuré depuis le client

```yaml
services:
  - name: selenium/standalone-firefox:<tag>
    alias: <nom.de.domaine.du.conteneur.selenium>
```

## installation du driver

* avant de lancer les tests
  - décompresser le driver dans le conteneur
  - l'installer en exécution dans le PATH
  - côté client selenium => utiiser le nom du conteneur comme nom de domaine !
  - utiliser l'option **headless**


## création & exécution des scripts

exécution avec Junit (TDD) ou Cucumber (BDD)

* attention au scope: différencier les tests E2E et les tests Unit et IT