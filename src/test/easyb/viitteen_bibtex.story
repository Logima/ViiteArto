import ohtu.*
import ohtu.viitearto.*
import ohtu.viitearto.servlets.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.*

description 'Asiakas voi tallentaa bibtex-tiedot'

scenario "asiakas lisää viitteen ja tallentaa oikean viitteen bibtex-tiedot", {

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

        WebElement element = driver.findElement(By.linkText("Helsinginkadun appro"));
        element.click();
        element = driver.findElement(By.linkText("BibTeX"));
        element.click();

    }

    then 'viitteen bibtex-tiedot latautuu', {
       driver.getPageSource().contains("Helsinginkadun appro").shouldBe true
       driver.getPageSource().contains("Bileinsinöörit").shouldBe true
    }
}

scenario "asiakas lisää useita viitteitä ja tallentaa yhden viitteen bibtex-tiedot", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'viitteet on lisätty', {
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

        element = driver.findElement(By.name("viiteTyyppi"));
        List<WebElement> options2 = element.findElements(By.tagName("option"));

        for(WebElement option : options2){
            if(option.getText().equals("Article")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("valinta"));
        element.submit();

        element = driver.findElement(By.name("title"));
        element.sendKeys("Krapula");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Johtaja Eronen");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("Mikälie");

        element = driver.findElement(By.name("lisays"));
        element.submit();
        

        element = driver.findElement(By.name("viiteTyyppi"));
        List<WebElement> options3 = element.findElements(By.tagName("option"));

        for(WebElement option : options3){
            if(option.getText().equals("Book")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("valinta"));
        element.submit();

        element = driver.findElement(By.name("title"));
        element.sendKeys("snafu");
        element = driver.findElement(By.name("author"));
        element.sendKeys("alkoholin orja");

        element = driver.findElement(By.name("lisays"));
        element.submit();
        
        
    }

    when 'asiakas lataa yhden viitteen bibtex-tiedot', {

        WebElement element = driver.findElement(By.linkText("Krapula"));
        element.click();
        element = driver.findElement(By.linkText("BibTeX"));
        element.click();

    }

    then 'oikean viitteen bibtex-tiedot latautuu', {
       driver.getPageSource().contains("Krapula").shouldBe true
       driver.getPageSource().contains("Johtaja Eronen").shouldBe true
       driver.getPageSource().contains("Mikälie").shouldBe true
       driver.getPageSource().contains("snafu").shouldBe false
       driver.getPageSource().contains("Bileinsinöörit").shouldBe false
    }
}

scenario "asiakas lisää useita viitteitä ja tallentaa kaikkien bibtex-tiedot", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'viitteet on lisätty', {
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

        element = driver.findElement(By.name("viiteTyyppi"));
        List<WebElement> options2 = element.findElements(By.tagName("option"));

        for(WebElement option : options2){
            if(option.getText().equals("Article")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("valinta"));
        element.submit();

        element = driver.findElement(By.name("title"));
        element.sendKeys("Krapula");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Johtaja Eronen");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("Mikälie");

        element = driver.findElement(By.name("lisays"));
        element.submit();
        

        element = driver.findElement(By.name("viiteTyyppi"));
        List<WebElement> options3 = element.findElements(By.tagName("option"));

        for(WebElement option : options3){
            if(option.getText().equals("Book")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("valinta"));
        element.submit();

        element = driver.findElement(By.name("title"));
        element.sendKeys("snafu");
        element = driver.findElement(By.name("author"));
        element.sendKeys("alkoholin orja");

        element = driver.findElement(By.name("lisays"));
        element.submit();
        
        
    }

    when 'asiakas lataa kaikkien viitteiden bibtex-tiedot', {

        element = driver.findElement(By.linkText("BibTeX"));
        element.click();

    }

    then 'oikean viitteen bibtex-tiedot latautuu', {
       driver.getPageSource().contains("Krapula").shouldBe true
       driver.getPageSource().contains("Johtaja Eronen").shouldBe true
       driver.getPageSource().contains("Mikälie").shouldBe true
       driver.getPageSource().contains("snafu").shouldBe true
       driver.getPageSource().contains("Bileinsinöörit").shouldBe true
    }
}