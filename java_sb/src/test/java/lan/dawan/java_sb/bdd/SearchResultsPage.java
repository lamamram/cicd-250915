package lan.dawan.java_sb.bdd;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {
  private WebDriver driver;
  private By resultPrice = By.xpath("/html/body/section/main/section//p");

  public SearchResultsPage(WebDriver driver) {
    this.driver = driver;
  }

  public List<String> getResultsPrices() {
    return this.driver.findElements(resultPrice).stream()
        .filter(item -> item.getText().contains("€")).map(item -> item.getText())
        .collect(Collectors.toList());
  }
}
