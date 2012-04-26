import ohtu.*
import ohtu.viitearto.*
import ohtu.viitearto.servlets.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.*

description 'Asiakas voi lisätä viitteen'

scenario "asiakas lisää viitteen oikeilla syötteillä", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'viitteen pakolliset tiedot täytetty', {
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

        element = driver.findElement(By.name("field.title"));
        element.sendKeys("väinö");
        element = driver.findElement(By.name("field.author"));
        element.sendKeys("linna");
        
    }

    when 'vaadittavat kentät on syötetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite näkyy sivulla', {
       driver.getPageSource().contains("väinö").shouldBe true
    }
}

 scenario "asiakas lisää viitteen kaikilla syötteillä", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'viitteen kaikki tiedot täytetty', {

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

        element = driver.findElement(By.name("field.title"));
        element.sendKeys("väiski");
        element = driver.findElement(By.name("field.author"));
        element.sendKeys("linnanherra");
        element = driver.findElement(By.name("field.year"));
        element.sendKeys("2000");
        element = driver.findElement(By.name("field.publisher"));
        element.sendKeys("otava");
        element = driver.findElement(By.name("field.address"));
        element.sendKeys("FIN");
        
    }

    when 'kaikki kentät on syötetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite näkyy sivulla', {
       driver.getPageSource().contains("väiski").shouldBe true
       driver.getPageSource().contains("linnanherra").shouldBe true
    }
}

scenario "asiakas lisää viitteen vain titlellä", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'vain viitteen title-osa täytetty', {
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

        element = driver.findElement(By.name("field.title"));
        element.sendKeys("pelkkatitle");
    }

    when 'title täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla ja tulee virheilmoitus', {

       driver.getPageSource().contains("pelkkatitle").shouldBe false
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true

        }
}

scenario "asiakas lisää viitteen vain authorilla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'vain viitteen author-osa täytetty', {
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

        element = driver.findElement(By.name("field.author"));
        element.sendKeys("pelkkaAuthor");
        
    }

    when 'author täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla ja tulee virheilmoitus', {
       driver.getPageSource().contains("pelkkaAuthor").shouldBe false
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
        }
}

scenario "asiakas lisää viitteen vain vuosiluvulla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'vain viitteen year-osa täytetty', {
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

        element = driver.findElement(By.name("field.year"));
        element.sendKeys("1923");
        
    }

    when 'year täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla ja tulee virheilmoitus', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
        }
}

scenario "asiakas lisää viitteen vain julkaisija-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'vain viitteen publisher-osa täytetty', {
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

        element = driver.findElement(By.name("field.publisher"));
        element.sendKeys("Harakka");
        
    }

    when 'publisher täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla ja tulee virheilmoitus', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
        }
}

scenario "asiakas lisää viitteen vain journal-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'vain viitteen journal-osa täytetty', {
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

        element = driver.findElement(By.name("field.journal"));
        element.sendKeys("Diary");
    }

    when 'journal täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla ja tulee virheilmoitus', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
        }
}

scenario "asiakas lisää viitteen vain booktitle-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'vain viitteen Booktitle osa täytetty', {
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

        element = driver.findElement(By.name("field.booktitle"));
        element.sendKeys("Diary");
    }

    when 'booktitle täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla ja tulee virheilmoitus', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
        }
}

scenario "asiakas lisää viitteen vain pages-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'vain viiteen pages-osa täytetty', {
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

        element = driver.findElement(By.name("field.pages"));
        element.sendKeys("200-250");
    }

    when 'pages täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla ja tulee virheilmoitus', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
        }
}

scenario "asiakas lisää viitteen vain address-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'vain viitteen address-osa täytetty', {
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

        element = driver.findElement(By.name("field.address"));
        element.sendKeys("FIN");
    }

    when 'address täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla ja tulee virheilmoitus', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
        }
}

scenario "asiakas lisää viitteen vain volume-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'vain viitteen volume-osa täytetty', {
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

        element = driver.findElement(By.name("field.volume"));
        element.sendKeys("4");
    }

    when 'volume täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla ja tulee virheilmoitus', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
        }
}

scenario "asiakas lisää viitteen vain numero-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'vain viitteen number-osa täytetty', {
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

        element = driver.findElement(By.name("field.number"));
        element.sendKeys("4");
    }

    when 'number täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla ja tulee virheilmoitus', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
        }
}
