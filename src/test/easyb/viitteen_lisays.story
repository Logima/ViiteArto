import ohtu.*
import ohtu.viitearto.*
import ohtu.viitearto.servlets.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Asiakas voi lisätä viitteen'

scenario "asiakas lisää viitteen oikeilla syötteillä", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'viitteen pakolliset tiedot täytetty', {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys("väinö");
        element = driver.findElement(By.name("author"));
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
    driver.get("http://localhost:8080/");

    given 'viitteen kaikki tiedot täytetty', {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys("väiski");
        element = driver.findElement(By.name("author"));
        element.sendKeys("linnanherra");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2000");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("otava");
        
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

scenario "asiakas lisää viitteen vain Titlellä", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen title osa täytetty', {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys("TäytinVainTämänTest");
        
    }

    when 'kun Title täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla', {
       driver.getPageSource().contains("TäytinVainTämänTest").shouldBe false
        }
}

scenario "asiakas lisää viitteen vain Authorilla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Author osa täytetty', {
        WebElement element = driver.findElement(By.name("author"));
        element.sendKeys("TäytinVainTämänTest");
        
    }

    when 'kun Author täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla', {
       driver.getPageSource().contains("TäytinVainTämänTest").shouldBe false
        }
}

scenario "asiakas lisää viitteen vain vuosiluvulla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Year osa täytetty', {
        WebElement element = driver.findElement(By.name("year"));
        element.sendKeys("TäytinVainTämänTest");
        
    }

    when 'kun Year täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla', {
       driver.getPageSource().contains("TäytinVainTämänTest").shouldBe false
        }
}

scenario "asiakas lisää viitteen vain julkaisija-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Publisher osa täytetty', {
        WebElement element = driver.findElement(By.name("publisher"));
        element.sendKeys("TäytinVainTämänTest");
        
    }

    when 'kun Publisher täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla', {
       driver.getPageSource().contains("TäytinVainTämänTest").shouldBe false
        }
}