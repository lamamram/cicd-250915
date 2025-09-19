package lan.dawan.java_sb.bdd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
  private WebDriver driver;
  private String pageUrl = "https://dawan.fr";
  private By searchInputId = By.id("motsclefs");
  private By searchButtonId = By.id("search-btn");
  // private By searchButtonXpath = By.xpath("//button[@class='pre-menu-search-submit
  // border-form']");

  public HomePage(WebDriver driver) {
    this.driver = driver;
    driver.get(pageUrl);
  }

  public void searchKeyword(String keyword) {
    this.driver.findElement(searchInputId).sendKeys(keyword);
  }

  public void goSearch() {
    this.driver.findElement(searchButtonId).click();
  }
}
