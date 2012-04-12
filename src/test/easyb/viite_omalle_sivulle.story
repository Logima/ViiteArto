import ohtu.*
import ohtu.viitearto.*
import ohtu.viitearto.servlets.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Asiakas voi avata viitteen omalle sivulleen ja palata takaisin etusivulle'

scenario "asiakas valitsee listasta viitteen, joka avautuu omalle sivulleen", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");
    WebElement element;
    String id;

    given 'viite on listalla', {
        element = driver.findElement(By.name("title"));
        element.sendKeys("Avaudun");
        element = driver.findElement(By.name("author"));
        element.sendKeys("OmalleSivulleTesti");
        element = driver.findElement(By.name("lisays"));
        element.submit();
    }

    when 'viite löytyy listalta', {
        element = driver.findElement(By.linkText("Avaudun"));
        element.click();
    }

    then 'viite avautuu omalle sivulleen', {
       driver.getPageSource().contains("Viitteen tiedot").shouldBe true
       driver.getPageSource().contains("Avaudun").shouldBe true
    }
}


scenario "asiakas palaa viitteen sivulta takaisin etusivulle", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/");
    WebElement element;
    String id;

    given 'viite on omalla sivulla', {
        element = driver.findElement(By.name("title"));
        element.sendKeys("PalaanTakaisin");
        element = driver.findElement(By.name("author"));
        element.sendKeys("EtusivulleTest");
        element = driver.findElement(By.name("lisays"));
        element.submit();
        element = driver.findElement(By.linkText("Avaudun"));
        element.click();
    }

    when 'viite on avattuna omalle sivulleen', {
        element = driver.findElement(By.linkText("Etusivu"))
        element.click();
    
        
    }

    then 'palataan etusivulle', {
       driver.getPageSource().contains("ViiteArto").shouldBe true
       driver.getPageSource().contains("Lisää viite").shouldBe true
    }
}