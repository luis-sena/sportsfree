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

public class CursosSteps {

    WebDriver navegador;

    @Before
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        navegador = new ChromeDriver();
        HttpsURLConnection urlc = (HttpsURLConnection) new URL("https://sportsfree-dev.herokuapp.com/professor").openConnection();
        int code = urlc.getResponseCode();
        Assert.assertEquals(200, code);
    }

    @Given("que eu como professor acesse o site SportFree")
    public void queEuComoProfessorAcesseOSiteSportFree() {
        System.out.println("sou professor sem cadastro");
    }

    @Then("sou redirecionado para a tela de perfil de professor")
    public void souRedirecionadoParaATelaDePerfilDeProfessor() {
        String page = navegador.getTitle();
        Assert.assertEquals("Perfil-profissional", page);
    }

    @And("clico no botão Sim, desejo oferecer um curso")
    public void clicoNoBotaoSimDesejoOferecerUmCurso() {
        WebElement btn_simNovaAula = navegador.findElement(By.name("sim-nova-aula"));
        btn_simNovaAula.click();
    }

    @Then("sou redirecionado para a tela de grade horária de cursos ofertados")
    public void souRedirecionadoParaATelaDeGradeHorariaDeCursosOfertados() {
        String page = navegador.getTitle();
        Assert.assertEquals("Aulas", page);
    }

    @And("preencho os campos {string}, {string}, {string}, {string}")
    public void preenchoOsCampos(String curso, String horario, String local, String quantidadeVaga) {
        WebElement txt_curso = navegador.findElement(By.name("curso"));
        WebElement txt_horario = navegador.findElement(By.name("horario"));
        WebElement txt_local = navegador.findElement(By.name("local"));
        WebElement txt_quantidadeVaga = navegador.findElement(By.name("quantidadeVaga"));
        txt_curso.sendKeys(curso);
        txt_horario.sendKeys(horario);
        txt_local.sendKeys(local);
        txt_quantidadeVaga.sendKeys(quantidadeVaga);

    }

    @And("clico no botão Cadastrar novo curso")
    public void clicoNoBotaoCadastrarNovoCurso() {
        WebElement btn_cadastroNovaAula = navegador.findElement(By.name("cadastro-nova-aula"));
        btn_cadastroNovaAula.click();
    }

    @Then("uma nova linha na grade com o {string} e {string} e exibida")
    public void umaNovaLinhaNaGradeComOEEExibida(String aula, String horario) {
        WebElement label_novaAula = navegador.findElement(By.id(aula + "-" + horario));
        Assert.assertTrue(label_novaAula != null);
    }

    @Then("sou redirecionado para a tela do esporte escolhido")
    public void souRedirecionadoParaATelaDoEsporteEscolhido() {
        String title = navegador.getTitle();
        Assert.assertEquals("NovoCliente", title);
    }

    @And("clico no botão Seja um Professor")
    public void clicoNoBotaoSejaUmProfessor() {
        WebElement btn_sejaProfessor = navegador.findElement(By.name("seja-um-professor"));
        btn_sejaProfessor.click();
    }

    @When("eu clico na modalidade de esporte {string}")
    public void euClicoNaModalidadeDeEsporte(String esporte) {
        WebElement lnk_modalidade = navegador.findElement(By.name("esporte"));
        lnk_modalidade.click();
    }
}
