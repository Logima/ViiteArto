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
    driver.get("http://localhost:8080/");
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
        element = driver.findElement(By.xpath("//a[@href='/PoistaViite?id" + id + "']"));
        element.click();
    }

    then 'viite on poistettu', {
       driver.getPageSource().contains(id).shouldBe false
    }
}