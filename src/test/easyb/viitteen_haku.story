import ohtu.*
import ohtu.viitearto.*
import ohtu.viitearto.servlets.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.*

description 'Asiakas voi hakea viitteen'

scenario "asiakas hakee viitteen oikeilla syötteillä", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'viite on listalla', {
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
        element.sendKeys("apinakin osaa koodata");
        element = driver.findElement(By.name("author"));
        element.sendKeys("pelle peloton");
        element = driver.findElement(By.name("lisays"));
        element.submit();
    }

    when 'haun tiedot on syötetty', {

        WebElement element = driver.findElement(By.name("ekaKentta"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Author")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("tyyppi"));
        List<WebElement> options2 = element.findElements(By.tagName("option"));

        for(WebElement option : options2){
            if(option.getText().equals("Book")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("ekaSana"));
        element.sendKeys("pelle peloton");
        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'oikea viite löytyy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe true
       driver.getPageSource().contains("pelle peloton").shouldBe true
    }
}