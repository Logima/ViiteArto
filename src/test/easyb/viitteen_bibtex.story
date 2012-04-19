import ohtu.*
import ohtu.viitearto.*
import ohtu.viitearto.servlets.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.*

description 'Asiakas voi tallentaa bibtex-tiedot'

scenario "asiakas lisää viitteen ja tallentaa bibtex-tiedot", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'viite on lisätty', {
        WebElement element = driver.findElement(By.name("viiteTyyppi"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Book")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("valinta"));
        element.submit();

        element = driver.findElement(By.name("title"));
        element.sendKeys("Helsinginkadun appro");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bileinsinöörit");
        element = driver.findElement(By.name("lisays"));
        element.submit();
        
    }

    when 'asiakas lataa bibtex-tiedot', {

        WebElement element = driver.findElement(By.name("Helsinginkadun appro"));
        element.submit();
        element = driver.findElement(By.name("BibTeX"));
        element.submit();

    }

    then 'viite näkyy sivulla', {
       driver.getPageSource().contains("Helsinginkadun Appro").shouldBe true
       driver.getPageSource().contains("Bileinsinöörit").shouldBe true
    }
}