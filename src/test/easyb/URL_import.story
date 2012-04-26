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
        WebElement element = driver.findElement(By.name("import"));
        element.submit();
    }

    then 'viite näkyy sivulla', {
       driver.getPageSource().contains("Social networks generate interest in computer science").shouldBe true
    }
}

scenario "asiakas lisää viitteen väärää URL:ia käyttäen", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'URL-osoite annettu', {

        WebElement element = driver.findElement(By.name("url"));
        element.sendKeys("http://www.keijonkaljakauppa.fi");
        
    }

    when 'URL-osoite importataan', {        
        WebElement element = driver.findElement(By.name("import"));
        element.submit();
    }

    then 'virheilmoitus näkyy sivulla', {
       driver.getPageSource().contains("URL import epäonnistui").shouldBe true
    }
}

scenario "asiakas lisää useita viitteitä URL-importtia käyttäen", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'ensimmäinen URL-viite lisätty', {

        WebElement element = driver.findElement(By.name("url"));
        element.sendKeys("http://dl.acm.org/citation.cfm?id=1121341.1121477&coll=DL&dl=ACM&CFID=99384110&CFTOKEN=68077440");
        element = driver.findElement(By.name("import"));
        element.submit();

    }

    when 'lisätään toinen URL-viite', {        

        element = driver.findElement(By.name("url"));
        element.sendKeys("http://dl.acm.org/citation.cfm?id=971300.971343&coll=DL&dl=ACM&CFID=99384110&CFTOKEN=68077440");
        element = driver.findElement(By.name("import"));
        element.submit()

    }

    then 'viitteet näkyy sivulla', {
       System.out.println(driver.getPageSource());
       driver.getPageSource().contains("Social networks generate interest in computer science").shouldBe true
       driver.getPageSource().contains("The dream of a common language: the search for simplicity and stability in computer science education").shouldBe true
       driver.getPageSource().contains("URL import epäonnistui").shouldBe false
    }
}

scenario "asiakas lisää useita viitteitä URL-importtia käyttäen ja sitten hakee viitteen", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'URL-viitteet lisätty', {

        WebElement element = driver.findElement(By.name("url"));
        element.sendKeys("http://dl.acm.org/citation.cfm?id=1121341.1121477&coll=DL&dl=ACM&CFID=99384110&CFTOKEN=68077440");
        element = driver.findElement(By.name("import"));
        element.submit();

        element = driver.findElement(By.name("url"));
        element.sendKeys("http://dl.acm.org/citation.cfm?id=971300.971343&coll=DL&dl=ACM&CFID=99384110&CFTOKEN=68077440");
        element = driver.findElement(By.name("import"));
        element.submit()

    }

    when 'haetaan yhtä viitettä', {        

        WebElement element = driver.findElement(By.name("kentta"));
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
            if(option.getText().equals("inproceedings")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("hakuSanat"));
        element.sendKeys("Roberts");
        element = driver.findElement(By.name("haku"));
        element.submit();


    }

    then 'viitteet näkyy sivulla', {

       driver.getPageSource().contains("Hakutulokset").shouldBe true
       driver.getPageSource().contains("The dream of a common language: the search for simplicity and stability in computer science education").shouldBe true
       driver.getPageSource().indexOf("Roberts, Eric").shouldEqual driver.getPageSource().lastIndexOf("Roberts, Eric")
    }
}


