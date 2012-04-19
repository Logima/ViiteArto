import ohtu.*
import ohtu.viitearto.*
import ohtu.viitearto.servlets.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.*

description 'Asiakas voi muokata olemassa olevaa viitettä'

scenario "asiakas lisää viitteen ja sen jälkeen muokkaa sitä", {

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

    when 'asiakas muokkaa viitettä', {

        WebElement element = driver.findElement(By.linkText("Helsinginkadun appro"));
        element.click();
        element = driver.findElement(By.name("muokkaus"));
        element.submit();
        element = driver.findElement(By.name("title"));
        element.sendKeys("Elämäni kankkunen");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Snafu");
        element = driver.findElement(By.name("tallennus"));
        element.submit();
        

    }

    then 'viite on muuttunut', {
       driver.getPageSource().contains("Snafu").shouldBe true
       driver.getPageSource().contains("Elämäni kankkunen").shouldBe true
       driver.getPageSource().contains("Bileinsinöörit").shouldBe false
    }
}
