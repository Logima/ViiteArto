import ohtu.*
import ohtu.viitearto.*
import ohtu.viitearto.servlets.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.*

description 'Asiakas voi hakea olemassa olevan viitteen'

scenario "asiakas hakee olemassa olevan viitteen oikeilla syötteillä", {

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

scenario "asiakas hakee olemassa olevan viitteen väärillä syötteillä", {

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
        element.sendKeys("japa");
        element = driver.findElement(By.name("author"));
        element.sendKeys("snafu");
        element = driver.findElement(By.name("lisays"));
        element.submit();
    }

    when 'haun väärät tiedot on syötetty', {

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
        element.sendKeys("ei olemassa");
        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'viitettä ei löydy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe false

    }
}

scenario "asiakas hakee olematonta viitettä", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'haun tiedot täytetty', {

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
        element.sendKeys("ei olemassa");

    }

    when 'haetaan olematonta viitettä', {

        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'viitettä ei löydy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe false
       driver.getPageSource().contains("ei olemassa").shouldBe false

    }
}

scenario "asiakas hakee olemassa olevan viitteen kahdella (JA) syötteillä", {

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

        element = driver.findElement(By.name("tokaKentta"));
        List<WebElement> options2 = element.findElements(By.tagName("option"));

        for(WebElement option : options2){
            if(option.getText().equals("Title")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("tyyppi"));
        List<WebElement> options3 = element.findElements(By.tagName("option"));

        for(WebElement option : options3){
            if(option.getText().equals("Book")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("ekaSana"));
        element.sendKeys("pelle peloton");
        element = driver.findElement(By.name("tokaSana"));
        element.sendKeys("apinakin osaa koodata");
        element = driver.findElement(By.name("AND"));
        element.submit();
        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'oikea viite löytyy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe true
       driver.getPageSource().contains("pelle peloton").shouldBe true
       driver.getPageSource().contains("apinakin osaa koodata").shouldBe true
    }
}

scenario "asiakas hakee olemassa olevan viitteen kahdella (JA) syötteillä joista toinen syöte on väärä", {

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

        element = driver.findElement(By.name("tokaKentta"));
        List<WebElement> options2 = element.findElements(By.tagName("option"));

        for(WebElement option : options2){
            if(option.getText().equals("Title")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("tyyppi"));
        List<WebElement> options3 = element.findElements(By.tagName("option"));

        for(WebElement option : options3){
            if(option.getText().equals("Book")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("ekaSana"));
        element.sendKeys("pelle peloton");
        element = driver.findElement(By.name("tokaSana"));
        element.sendKeys("apina ei osaa koodata");
        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'viitettä ei löydy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe false

    }
}

scenario "asiakas hakee olemassa olevia viitteitä kahdella (TAI) syötteillä", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'viitteet on listalla', {
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
        element.sendKeys("testien maailma");
        element = driver.findElement(By.name("author"));
        element.sendKeys("myllyrinne");
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

        element = driver.findElement(By.name("tokaKentta"));
        List<WebElement> options2 = element.findElements(By.tagName("option"));

        for(WebElement option : options2){
            if(option.getText().equals("Title")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("tyyppi"));
        List<WebElement> options3 = element.findElements(By.tagName("option"));

        for(WebElement option : options3){
            if(option.getText().equals("Article")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("ekaSana"));
        element.sendKeys("pelle peloton");
        element = driver.findElement(By.name("tokaSana"));
        element.sendKeys("testien maailma");
        element = driver.findElement(By.name("OR"));
        element.submit();
        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'oikea viite löytyy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe true
       driver.getPageSource().contains("testien maailma").shouldBe true

    }
}

scenario "asiakas hakee olemassa olevia viitteitä kahdella (TAI) syötteillä, joista kumpikaan ei ole oikea", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'viitteet on listalla', {
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
        element.sendKeys("testien maailma");
        element = driver.findElement(By.name("author"));
        element.sendKeys("myllyrinne");
        element = driver.findElement(By.name("lisays"));
        element.submit();

    }

    when 'haun tiedot on syötetty (vääriin kenttiin)', {

        WebElement element = driver.findElement(By.name("ekaKentta"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Author")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("tokaKentta"));
        List<WebElement> options2 = element.findElements(By.tagName("option"));

        for(WebElement option : options2){
            if(option.getText().equals("Title")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("tyyppi"));
        List<WebElement> options3 = element.findElements(By.tagName("option"));

        for(WebElement option : options3){
            if(option.getText().equals("Article")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("ekaSana"));
        element.sendKeys("testien maailma");
        element = driver.findElement(By.name("tokaSana"));
        element.sendKeys("pelle peloton");
        element = driver.findElement(By.name("OR"));
        element.submit();
        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'viitettä ei löydy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe false

    }
}