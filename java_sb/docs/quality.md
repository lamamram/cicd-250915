# qualité

## formatter Java automatisé

* solution : plugin maven net.revelc.code.formatter.formatter-maven-plugin

* installation: `via pom.xml`
* configuration: `src/main/resources/formatter/formatter.xml`
* tester: `mvnw formatter:format`
* automatisation via un git hook `pre-commit`

---