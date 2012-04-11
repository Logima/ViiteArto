import ohtu.*
import ohtu.viitearto.*
import ohtu.viitearto.servlets.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'Asiakas voi poistaa viitteen'

scenario "asiakas voi poistaa viitteen listasta", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/ViiteArto");
    WebElement element;
    String id;

    given 'viite on listalla', {
        element = driver.findElement(By.name("title"));
        element.sendKeys("pirkko");
        element = driver.findElement(By.name("author"));
        element.sendKeys("väinölä");
        element = driver.findElement(By.name("lisays"));
        element.submit();
    }

    when 'viite löytyy listalta', {
        element = driver.findElement(By.linkText("pirkko"));
        String link = element.getAttribute("href");
        id = link.substring(link.indexOf('='));
        element = driver.findElement(By.xpath("//a[@href='/ViiteArto/PoistaViite?id" + id + "']"));
        element.click();
    }

    then 'viite on poistettu', {
       driver.getPageSource().contains(id).shouldBe false
    }
}

scenario "asiakas voi poistaa viitteen sen omalta sivulta", {

    WebDriver driver = new HtmlUnitDriver();
    driver.get("http://localhost:8080/ViiteArto");
    WebElement element;
    String id;

    given 'olemassaolevan viitteen linkkiä klikataan', {
        element = driver.findElement(By.name("title"));
        element.sendKeys("pirkko");
        element = driver.findElement(By.name("author"));
        element.sendKeys("väinölä");
        element = driver.findElement(By.name("lisays"));
        element.submit();
        
        element = driver.findElement(By.linkText("pirkko"));
        String link = element.getAttribute("href");
        id = link.substring(link.indexOf('='));
        element.click();
    }

    when 'viite poistetaan sen omalla sivulla', {
        element = driver.findElement(By.name("poisto"));
        element.submit();
    }

    then 'palataan etusivulle, jossa poistettua viitettä ei näy', {
        driver.getPageSource().contains(id).shouldBe false
    }
}

scenario "nonexistent user can not login to ", {
    given 'command login selected'
    when 'a nonexistent username and some password are entered'
    then 'user will not be logged in to system'
}