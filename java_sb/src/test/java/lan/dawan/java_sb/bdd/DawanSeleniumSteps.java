package lan.dawan.java_sb.bdd;

import static org.assertj.core.api.Assertions.assertThat;
import java.net.URI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DawanSeleniumSteps {
  private WebDriver driver;
  private HomePage hp;
  private SearchResultsPage srp;

  @Before
  public void connect() {
    FirefoxOptions options = new FirefoxOptions();
    // configuration du moteur de navigateur dans le serveur Selenium
    // headless => pas de GUI => car pas besoin et pas de serveur X sur un conteneur
    options.addArguments("-headless");
    try {
      URI uri = new URI("http://selenium-server:4444/wd/hub");
      driver = new RemoteWebDriver(uri.toURL(), options);
    } catch (Exception e) {
      System.exit(1);
    }
  }

  @After
  public void close() {
    driver.close();
  }

  @Given("^connexion sur la page d acceuil$")
  public void setupScenario1() {
    hp = new HomePage(driver);
  }

  @When("^j entre (\\w+) dans le moteur$")
  public void act1Scenario1(String kw) {
    hp.searchKeyword(kw);
  }

  @And("^j appuie sur le bouton de recherche$")
  public void act2Scenario1() {
    hp.goSearch();
  }

  @Then("^les prix (\\d+) sont les bons$")
  public void assertStep(int price) {
    srp = new SearchResultsPage(driver);
    for (String p : srp.getResultsPrices()) {
      // à refaire avec une regex ET dans la Page conernée dans un map !!!
      int pivot = p.indexOf("€");
      p = p.charAt(pivot - 6) + p.substring(pivot - 4, pivot - 1);
      assertThat(p).contains(String.valueOf(price));
    }
  }
}
