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

       driver.getPageSource().contains("TäytinVainTämän").shouldBe false
       driver.getPageSource().contains("Tähdellä merkityt tiedot ovat pakollisia.").shouldBe true

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
        element.sendKeys("1923");
        
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

scenario "asiakas lisää viitteen vain journal-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Journal osa täytetty', {
        WebElement element = driver.findElement(By.name("journal"));
        element.sendKeys("TäytinVainTämänTest");
        
    }

    when 'kun Journal täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla', {
       driver.getPageSource().contains("TäytinVainTämänTest").shouldBe false

        }
}

scenario "asiakas lisää viitteen vain booktitle-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Booktitle osa täytetty', {
        WebElement element = driver.findElement(By.name("booktitle"));
        element.sendKeys("TäytinVainTämänTest");
        
    }

    when 'kun booktitle täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla', {
       driver.getPageSource().contains("TäytinVainTämänTest").shouldBe false

        }
}

scenario "asiakas lisää viitteen vain sivu-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Pages osa täytetty', {
        WebElement element = driver.findElement(By.name("pages"));
        element.sendKeys("TäytinVainTämänTest");
        
    }

    when 'kun Pages täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla', {
       driver.getPageSource().contains("TäytinVainTämänTest").shouldBe false

        }
}

scenario "asiakas lisää viitteen vain osoite-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Address osa täytetty', {
        WebElement element = driver.findElement(By.name("address"));
        element.sendKeys("TäytinVainTämänTest");
        
    }

    when 'kun Address täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla', {
       driver.getPageSource().contains("TäytinVainTämänTest").shouldBe false

        }
}

scenario "asiakas lisää viitteen vain volyme-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Volyme osa täytetty', {
        WebElement element = driver.findElement(By.name("volyme"));
        element.sendKeys("TäytinVainTämänTest");
        
    }

    when 'kun Volyme täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla', {
       driver.getPageSource().contains("TäytinVainTämänTest").shouldBe false

        }
}

scenario "asiakas lisää viitteen vain numero-tiedolla", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Number osa täytetty', {
        WebElement element = driver.findElement(By.name("number"));
        element.sendKeys("TäytinVainTämänTest");
        
    }

    when 'kun Number täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'viite ei näy sivulla', {
       driver.getPageSource().contains("TäytinVainTämänTest").shouldBe false

        }
}

scenario "virheilmoitus kun pelkkä Title täytetty", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Title osa täytetty', {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys("TuleekoVirhe_Test");
        
    }

    when 'kun Title täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'ilmoittaa virheilmoituksen', {
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Vain numerot sallittuja!").shouldBe false
        }
}

scenario "virheilmoitus kun pelkkä Author täytetty", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Author osa täytetty', {
        WebElement element = driver.findElement(By.name("author"));
        element.sendKeys("TuleekoVirhe_Test");
        
    }

    when 'kun Author täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'ilmoittaa virheilmoituksen', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Vain numerot sallittuja!").shouldBe false
        }
}


scenario "virheilmoitus kun pelkkä vuosiluku täytetty", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Year osa täytetty', {
        WebElement element = driver.findElement(By.name("year"));
        element.sendKeys("1923");
        
    }

    when 'kun Year täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'ilmoittaa virheilmoituksen', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Vain numerot sallittuja!").shouldBe false
        }
}

scenario "virheilmoitus kun pelkkä julkaisija täytetty", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Publisher osa täytetty', {
        WebElement element = driver.findElement(By.name("publisher"));
        element.sendKeys("hehee");
        
    }

    when 'kun Publisher täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'ilmoittaa virheilmoituksen', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Vain numerot sallittuja!").shouldBe false
        }
}

scenario "virheilmoitus kun pelkkä journal täytetty", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Journal osa täytetty', {
        WebElement element = driver.findElement(By.name("journal"));
        element.sendKeys("TuleekoVirhe_Test");
        
    }

    when 'kun Journal täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'ilmoittaa virheilmoituksen', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Vain numerot sallittuja!").shouldBe false
        }
}

scenario "virheilmoitus kun pelkkä booktitle täytetty", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Booktitle osa täytetty', {
        WebElement element = driver.findElement(By.name("booktitle"));
        element.sendKeys("TuleekoVirhe_Test");
        
    }

    when 'kun Booktitle täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'ilmoittaa virheilmoituksen', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Vain numerot sallittuja!").shouldBe false
        }
}

scenario "virheilmoitus kun pelkkä sivut täytetty", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Pages osa täytetty', {
        WebElement element = driver.findElement(By.name("pages"));
        element.sendKeys("TuleekoVirhe_Test");
        
    }

    when 'kun Pages täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'ilmoittaa virheilmoituksen', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Vain numerot sallittuja!").shouldBe false
        }
}

scenario "virheilmoitus kun pelkkä osoite täytetty", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Address osa täytetty', {
        WebElement element = driver.findElement(By.name("address"));
        element.sendKeys("TuleekoVirhe_Test");
        
    }

    when 'kun Address täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'ilmoittaa virheilmoituksen', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Vain numerot sallittuja!").shouldBe false
        }
}

scenario "virheilmoitus kun pelkkä volyme täytetty", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Volyme osa täytetty', {
        WebElement element = driver.findElement(By.name("volyme"));
        element.sendKeys("TuleekoVirhe_Test");
        
    }

    when 'kun Volyme täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'ilmoittaa virheilmoituksen', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Vain numerot sallittuja!").shouldBe false
        }
}

scenario "virheilmoitus kun pelkkä numero täytetty", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");

    given 'vain vitteen Number osa täytetty', {
        WebElement element = driver.findElement(By.name("number"));
        element.sendKeys("TuleekoVirhe_Test");
        
    }

    when 'kun Number täytetty', {
       WebElement element = driver.findElement(By.name("lisays"));
       element.submit();
    }

    then 'ilmoittaa virheilmoituksen', {
       driver.getPageSource().contains("Title ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Author ei saa olla tyhjä!").shouldBe true
       driver.getPageSource().contains("Vain numerot sallittuja!").shouldBe false
        }
}