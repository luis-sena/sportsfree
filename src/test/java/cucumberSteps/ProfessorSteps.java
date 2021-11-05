package cucumberSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;


public class ProfessorSteps {

    WebDriver navegador;

    @Before
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        navegador = new ChromeDriver();
        HttpsURLConnection urlc = (HttpsURLConnection) new URL("https://sportsfree-dev.herokuapp.com/professor").openConnection();
        int code = urlc.getResponseCode();
        Assert.assertEquals(200, code);
    }

    @When("clico no botão clique aqui para não cadastrados")
    public void clicoNoBotaoCliqueAquiParaNaoCadastrados() {
        WebElement btn_cliqueAqui = navegador.findElement(By.name("clique-aqui-novo-cadastro"));
        btn_cliqueAqui.click();
    }

    @Then("o sistema retorna a mensagem de documento já existente.")
    public void oSistemaRetornaAMensagemDeDocumentoJaExistente() {
        WebElement msnCadastroExistente = navegador.findElement(By.id("msnDelete"));
        String msn = msnCadastroExistente.getText();
        Assert.assertEquals("Documento já possui cadastro", msn);
    }

    @And("eu clico no botão cancelar")
    public void euClicoNoBotaoCancelar() {
        WebElement btn_cancelarCurso = navegador.findElement(By.name("cancela-curso"));
        btn_cancelarCurso.click();
    }

    @Then("sou redirecionado para a tela home do site")
    public void souRedirecionadoParaATelaHomeDoSite() {
        String title = navegador.getTitle();
        Assert.assertEquals("Home SportsFree", title);
    }

    @And("clico no botão Sim desejo cancelar")
    public void clicoNoBotaoSimDesejoCancelar() {
        WebElement btn_simCancelar = navegador.findElement(By.name("sim-cancela-curso"));
        btn_simCancelar.click();
    }

    @Then("sou redirecionado para a tela de grade horária dos meus cursos")
    public void souRedirecionadoParaATelaDeGradeHorariaDosMeusCursos() {
        String title = navegador.getTitle();
        Assert.assertEquals("Meus Cursos", title);
    }

    @And("confirmo no botão confirmar do popup")
    public void confirmoNoBotaoConfirmarDoPopup() {
    }

    @Then("o curso é cancelado e o sistema retorna mensagem de curso cancelado com sucesso.")
    public void oCursoECanceladoEOSistemaRetornaMensagemDeCursoCanceladoComSucesso() {
    }

    @When("eu clico no botao cancelar da linha do curso {string}")
    public void euClicoNoBotaoCancelarDaLinhaDoCurso(String arg0) {
        WebElement btn_CancelarCurso = navegador.findElement(By.name("nome-curso-cancelado"));
        btn_CancelarCurso.click();
    }
}
