import ohtu.*
import ohtu.viitearto.*
import ohtu.viitearto.servlets.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.*

description 'Asiakas voi muokata olemassa olevaa viitettä'

scenario "asiakas lisää viitteen (kirja) ja sen jälkeen muokkaa sitä", {

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
        element.clear();
        element.sendKeys("Elämäni kankkunen");
        element = driver.findElement(By.name("author"));
        element.clear();
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

scenario "asiakas lisää viitteen (artikkeli) pakollisilla tiedoilla ja sen jälkeen täydentää viitettä", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'viite on lisätty', {
        WebElement element = driver.findElement(By.name("viiteTyyppi"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Article")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("valinta"));
        element.submit();

        element = driver.findElement(By.name("title"));
        element.sendKeys("Koodia");
        element = driver.findElement(By.name("author"));
        element.sendKeys("your next President, mr. Eronen");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("your next President, mr. Eronen");

        element = driver.findElement(By.name("lisays"));
        element.submit();
        
    }

    when 'asiakas täydentää viitteen tietoja', {

        WebElement element = driver.findElement(By.linkText("Koodia"));
        element.click();
        element = driver.findElement(By.name("muokkaus"));
        element.submit();

        element = driver.findElement(By.name("address"));
        element.sendKeys("valkoinen talo");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");

        element = driver.findElement(By.name("tallennus"));
        element.submit();
    }

    then 'viite on muuttunut', {
       driver.getPageSource().contains("Koodia").shouldBe true
       driver.getPageSource().contains("valkoinen talo").shouldBe true
       driver.getPageSource().contains("2012").shouldBe true
    }
}
scenario "muokatessa asiakas ei pysty jättämään pakollisia tietoja tyhjiksi", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'viite on lisätty', {
        WebElement element = driver.findElement(By.name("viiteTyyppi"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Article")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("valinta"));
        element.submit();

        element = driver.findElement(By.name("title"));
        element.sendKeys("Uusielämä");
        element = driver.findElement(By.name("author"));
        element.sendKeys("KARHU III");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("tsapadapaduu");
        element = driver.findElement(By.name("lisays"));
        element.submit();
        
    }

    when 'muokatessa jätetään pakollinen kenttä tyhjäksi', {

        WebElement element = driver.findElement(By.linkText("Uusielämä"));
        element.click();
        element = driver.findElement(By.name("muokkaus"));
        element.submit();

        element = driver.findElement(By.name("title"));
        element.clear();

        element = driver.findElement(By.name("tallennus"));
        element.submit();
    }

    then 'viitteen pakolliset tiedot eivät ole muuttuneet', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Uusielämä").shouldBe true
    }
}
scenario "muokatessa pakollisen tiedon jättäminen tyhjäksi estää muiden tietojen muuttumisen", {

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
        element.sendKeys("LifeIsShort");
        element = driver.findElement(By.name("author"));
        element.sendKeys("TakeItEasy");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1989");
        element = driver.findElement(By.name("lisays"));
        element.submit();
        
    }

    when 'muokatessa jätetään pakollinen kenttä tyhjäksi, mutta muokataan valinnaista', {

        WebElement element = driver.findElement(By.linkText("LifeIsShort"));
        element.click();
        element = driver.findElement(By.name("muokkaus"));
        element.submit();

        element = driver.findElement(By.name("title"));
        element.clear();

        element = driver.findElement(By.name("year"));
        element.sendKeys("1");
        element = driver.findElement(By.name("tallennus"));
        element.submit();
    }

    then 'viitteen tiedot eivät ole muuttuneet', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("LifeIsShort").shouldBe true
       driver.getPageSource().contains("19891").shouldBe false
    }
}
scenario "viitettä muokatessa numerotietoihin ei voi syöttää kirjaimia", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'viite on lisätty', {
        WebElement element = driver.findElement(By.name("viiteTyyppi"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Article")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("valinta"));
        element.submit();

        element = driver.findElement(By.name("title"));
        element.sendKeys("Elastinen");
        element = driver.findElement(By.name("author"));
        element.sendKeys("elämää");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1989");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("Elastisen erälehti");

        element = driver.findElement(By.name("lisays"));
        element.submit();
        
    }

    when 'muokatessa tukitaan numerokenttään kirjaimia', {

        WebElement element = driver.findElement(By.linkText("LifeIsShort"));
        element.click();
        element = driver.findElement(By.name("muokkaus"));
        element.submit();

        element = driver.findElement(By.name("year"));
        element.sendKeys("ASD");

        element = driver.findElement(By.name("tallennus"));
        element.submit();
    }

    then 'viitteen tiedot eivät ole muuttuneet', {
       driver.getPageSource().contains("1989").shouldBe true
       driver.getPageSource().contains("ASD").shouldBe false
      
    }
}
