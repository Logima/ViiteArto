import ohtu.*
import ohtu.viitearto.*
import ohtu.viitearto.servlets.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;



description 'Asiakas voi lisätä viitteen'

scenario "Asiakas voi lisätä viitteen", {
    given 'Viitteen tiedot täytetty', {
        WebDriver driver = new HtmlUnitDriver();
 
        driver.get("http://localhost:8080/ViiteArto/Viitteet");
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.name("Title:"));
        element.sendKeys("väinö");
        element = driver.findElement(By.name("Author:"));
        element.sendKeys("linna");
        
    }

    when 'kun vaadittavat kentät on syötetty', {
       element = driver.findElement(By.name("Lisää viite"));
       element.submit();
    }

    then 'viite näkyy sivulla', {
       driver.getPageSource().contains("väinö").shouldBe true
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