import ohtu.*
import ohtu.viitearto.*
import ohtu.viitearto.servlets.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.*

description 'Asiakas voi hakea viitteitä tagilla'

scenario "asiakas hakee viitettä tagilla pelkistä kirjoista", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'lisätään kirja tagilla', {
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
        element.sendKeys("henkka ei");
        element = driver.findElement(By.name("tags"));
        element.sendKeys("hemulia");

        element = driver.findElement(By.name("lisays"));
        element.submit();
    }

    when 'haun tiedot on syötetty', {

        WebElement element = driver.findElement(By.name("kentta"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Tag")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("hakuSanat"));
        element.sendKeys("hemulia");
        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'oikea viite löytyy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe true
       driver.getPageSource().indexOf("henkka").shouldNotEqual driver.getPageSource().lastIndexOf("henkka")
    }
}



scenario "asiakas hakee viitettä tagilla inproceedingeistä", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'lisätään inproceeding tagilla', {
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
        element.sendKeys("apinakin osaa koodata");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("TKTL");
        element = driver.findElement(By.name("author"));
        element.sendKeys("henkka ei");
        element = driver.findElement(By.name("tags"));
        element.sendKeys("hemulia");

        element = driver.findElement(By.name("lisays"));
        element.submit();
    }

    when 'haun tiedot on syötetty', {

        WebElement element = driver.findElement(By.name("kentta"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Tag")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("hakuSanat"));
        element.sendKeys("hemulia");
        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'oikea viite löytyy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe true
       driver.getPageSource().indexOf("henkka").shouldNotEqual driver.getPageSource().lastIndexOf("henkka")
    }
}
scenario "asiakas hakee viitettä tagilla articlesta", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'lisätään article tagilla', {
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
        element.sendKeys("apinakin osaa koodata");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("TKTL");
        element = driver.findElement(By.name("author"));
        element.sendKeys("henkka ei");
        element = driver.findElement(By.name("tags"));
        element.sendKeys("hemulia");

        element = driver.findElement(By.name("lisays"));
        element.submit();
    }

    when 'haun tiedot on syötetty', {

        WebElement element = driver.findElement(By.name("kentta"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Tag")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("hakuSanat"));
        element.sendKeys("hemulia");
        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'oikea viite löytyy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe true
       driver.getPageSource().indexOf("henkka").shouldNotEqual driver.getPageSource().lastIndexOf("henkka")
    }
}

scenario "asiakas hakee viitettä väärällä tagilla kirjoista", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:7190/");

    given 'lisätään kirja tagilla', {
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
        element.sendKeys("henkka ei");
        element = driver.findElement(By.name("tags"));
        element.sendKeys("hemulia");

        element = driver.findElement(By.name("lisays"));
        element.submit();
    }

    when 'haun väärät tiedot on syötetty', {

        WebElement element = driver.findElement(By.name("kentta"));
        List<WebElement> options = element.findElements(By.tagName("option"));

        for(WebElement option : options){
            if(option.getText().equals("Tag")){
                option.click();
                break;
            }
        }

        element = driver.findElement(By.name("hakuSanat"));
        element.sendKeys("hemulia ei osaa koodata");
        element = driver.findElement(By.name("haku"));
        element.submit();

    }

    then 'viitettä ei löydy', {
       
       driver.getPageSource().contains("Hakutulokset").shouldBe false
    }
}