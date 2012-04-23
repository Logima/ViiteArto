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
            if(option.getText().equals("Book")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("hakuSanat"));
        element.sendKeys("pelle peloton");
        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'oikea viite löytyy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe true
       driver.getPageSource().contains("pelle peloton").shouldBe true
       driver.getPageSource().indexOf("pelle peloton").shouldNotEqual driver.getPageSource().lastIndexOf("pelle peloton")

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

        WebElement element = driver.findElement(By.name("kentta"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Valitse kaikki")){
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

        element = driver.findElement(By.name("hakuSanat"));
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
            if(option.getText().equals("Book")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("hakuSanat"));
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


scenario "asiakas hakee olemassa olevia viitteitä kahdella syötteellä, joista toinen syöte on väärä", {

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
        element.sendKeys("Juhlaa!");
        element = driver.findElement(By.name("author"));
        element.sendKeys("koodiorjat");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("HY");

        element = driver.findElement(By.name("lisays"));
        element.submit();
    }

    when 'haun tiedot on syötetty', {

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
            if(option.getText().equals("Book")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("hakuSanat"));
        element.sendKeys("pelle peloton,koodiorjat");

        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'viitettä ei löydy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe true
       driver.getPageSource().contains("pelle peloton").shouldBe true
       driver.getPageSource().indexOf("pelle peloton").shouldNotEqual driver.getPageSource().lastIndexOf("pelle peloton")
       driver.getPageSource().indexOf("koodiorjat").shouldEqual driver.getPageSource().lastIndexOf("koodiorjat")
       

    }
}

scenario "asiakas hakee olemassa olevia viitteitä kahdella syötteellä, joista kumpikin löytyy", {

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
            if(option.getText().equals("Book")){
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

        WebElement element = driver.findElement(By.name("kentta"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Valitse kaikki")){
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

        element = driver.findElement(By.name("hakuSanat"));
        element.sendKeys("pelle peloton,testien maailma");
        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'oikea viite löytyy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe true
       driver.getPageSource().contains("testien maailma").shouldBe true
       driver.getPageSource().indexOf("pelle peloton").shouldNotEqual driver.getPageSource().lastIndexOf("pelle peloton")
       driver.getPageSource().contains("pelle peloton").shouldBe true
       driver.getPageSource().indexOf("testien maailma").shouldNotEqual driver.getPageSource().lastIndexOf("testien maailma")
    }
}

scenario "asiakas hakee olemassa olevia viitteitä kahdella syötteellä, joista kumpikaan ei ole oikea", {

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
        element = driver.findElement(By.name("journal"));
        element.sendKeys("linkki");

        element = driver.findElement(By.name("lisays"));
        element.submit();

    }

    when 'haun tiedot on syötetty (olematon viite)', {

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
            if(option.getText().equals("Article")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("hakuSanat"));
        element.sendKeys("testien maailma,pelle peloton");

        element = driver.findElement(By.name("haku"));
        element.submit();
    }

    then 'viitettä ei löydy', { 
       driver.getPageSource().contains("Hakutulokset").shouldBe false
    }
}

scenario "asiakas muokkaa viitettä ja hakee sen tietokannasta", {

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
        element.sendKeys("PostiKulkee");
        element = driver.findElement(By.name("author"));
        element.sendKeys("pelle peloton");

        element = driver.findElement(By.name("lisays"));
        element.submit();
    }

    when 'viitettä on muokattu', {

        WebElement element = driver.findElement(By.linkText("PostiKulkee"));
        element.click();

        element = driver.findElement(By.name("muokkaus"));
        element.submit();
        element = driver.findElement(By.name("title"));
        element.clear();
        element.sendKeys("KustiPolkee");
        element = driver.findElement(By.name("author"));
        element.clear();
        element.sendKeys("Snafu");

        element = driver.findElement(By.name("tallennus"));
        element.submit();

        element = driver.findElement(By.linkText("Etusivu"));
        element.click();
    }

    then 'viite löytyy haulla sen uusilla tiedoilla', {

        WebElement element = driver.findElement(By.name("kentta"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Title")){
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

        element = driver.findElement(By.name("hakuSanat"));
        element.sendKeys("KustiPolkee");
        element = driver.findElement(By.name("haku"));
        element.submit();

        driver.getPageSource().contains("Hakutulokset").shouldBe true
    }
}

scenario "asiakas hakee viitteitä kahdella syötteellä, eikä tuloslistassa ole duplikaatteja", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'viitteet on listalla', {
        WebElement element = driver.findElement(By.name("viiteTyyppi"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Inproceedings")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("valinta"));
        element.submit();

        element = driver.findElement(By.name("title"));
        element.sendKeys("Extreme Apprenticeship Method in Teaching Programming for Beginners");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Vihavainen, Arto and Paksula, Matti and Luukkainen, Matti");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("SIGCSE '11: Proceedings of the 42nd SIGCSE technical symposium on Computer science education");

        element = driver.findElement(By.name("lisays"));
        element.submit();

        element = driver.findElement(By.name("viiteTyyppi"));
        List<WebElement> options2 = element.findElements(By.tagName("option"));

        for(WebElement option : options2){
            if(option.getText().equals("Inproceedings")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("valinta"));
        element.submit();

        element = driver.findElement(By.name("title"));
        element.sendKeys("Extreme Programming Explained: Embrace Change (2nd Edition)");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Beck, Kent and Andres, Cynthia");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("Addison-Wesley");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("-");

        element = driver.findElement(By.name("lisays"));
        element.submit();

    }

    when 'haun tiedot on syötetty', {

        WebElement element = driver.findElement(By.name("kentta"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Valitse kaikki")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("tyyppi"));
        List<WebElement> options2 = element.findElements(By.tagName("option"));

        for(WebElement option : options2){
            if(option.getText().equals("Inproceedings")){
                option.click();
                break;
            }
        }


        element = driver.findElement(By.name("hakuSanat"));
        element.sendKeys("Kent,Extreme");
        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'oikeat viitteet löytyvät ja niitä on vain yksi kappale tuloslistassa', {
       driver.getPageSource().contains("Hakutulokset").shouldBe true
       driver.getPageSource().contains("Extreme Apprenticeship").shouldBe true
       driver.getPageSource().contains("Extreme Programming").shouldBe true
       driver.getPageSource().indexOf("Addison-Wesley").shouldEqual driver.getPageSource().lastIndexOf("Addison-Wesley")
    }
}