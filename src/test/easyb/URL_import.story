import ohtu.*
import ohtu.viitearto.*
import ohtu.viitearto.servlets.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.*

description 'Asiakas voi lisätä viitteen käyttäen URL-importtia'

scenario "asiakas lisää viitteen URL-importtia käyttäen", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'URL-osoite annettu', {

        WebElement element = driver.findElement(By.name("url"));
        element.sendKeys("http://dl.acm.org/citation.cfm?id=1121341.1121477&coll=DL&dl=ACM&CFID=99384110&CFTOKEN=68077440");
        
    }

    when 'URL-osoite importataan', {
        WebElement element = driver.findElement(By.value("Import"));
        element.submit();
    }

    then 'viite näkyy sivulla', {
       driver.getPageSource().contains("Social networks generate interest in computer science").shouldBe true
    }
}