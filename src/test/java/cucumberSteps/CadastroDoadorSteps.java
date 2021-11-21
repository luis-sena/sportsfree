package cucumberSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CadastroDoadorSteps {

    WebDriver navegador;

    @And("eu escolho o perfil Doador")
    public void euEscolhoOPerfilDoador() {
        WebElement btn_Doador = navegador.findElement(By.name("Doador"));
        btn_Doador.click();
    }


}
