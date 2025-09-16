package lan.dawan.java_sb.bdd;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
// tag des fichiers gherkin .feature
@IncludeTags({"E2E"})
@IncludeEngines("cucumber")
// depuis src/test/resources/
@SelectClasspathResource("bdd")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "lan.dawan.java_sb.bdd")
// + gestion du rapport cucumber dans src/test/resources/junit-platform.properties
public class CucumberTest {

}
