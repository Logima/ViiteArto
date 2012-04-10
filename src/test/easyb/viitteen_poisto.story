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

    given 'viite on listalla', {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys("väinö");
        element = driver.findElement(By.name("author"));
        element.sendKeys("linna");
        element = driver.findElement(By.name("lisays"));
        element.submit();
        driver.getPageSource().contains("väinö").shouldBe true
    }

    when 'viite löytyy listalta', {
        element = driver.findElement(By.name("Poista"));
        element.submit();
       
    }

    then 'viite on poistettu', {
       driver.getPageSource().contains("väinö").shouldBe false
    }
}

scenario "user can not login with incorrect password", {
    given 'command login selected'
    when 'a valid username and incorrect password are entered'
    then 'user will not be logged in to system'
}

scenario "nonexistent user can not login to ", {
    given 'command login selected'
    when 'a nonexistent username and some password are entered'
    then 'user will not be logged in to system'
}