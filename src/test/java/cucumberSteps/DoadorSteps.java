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

public class DoadorSteps {

    WebDriver navegador;

    @Before
    public void setup() throws IOException{
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        navegador = new ChromeDriver();
        HttpsURLConnection urlc = (HttpsURLConnection) new URL("https://sportsfree-dev.herokuapp.com/professor").openConnection();
        int code = urlc.getResponseCode();
        Assert.assertEquals(200, code);
    }

    @Given("que eu não tenha cadastro no site SportFree")
    public void queEuNaoTenhaCadastroNoSiteSportFree()  {

        System.out.println("não tenho cadastro no site");
    }

    @When("eu clico no menu Seja um Doador")
    public void euClicoNoMenuSejaUmDoador() {
        WebElement btn_sejaDoador = navegador.findElement(By.name("seja-um-doador"));
        btn_sejaDoador.click();
    }

    @And("clico no botão Clique Aqui por não ter cadastro")
    public void clicoNoBotaoCliqueAquiPorNaoTerCadastro() {
        WebElement btn_cliqueAquiDoador = navegador.findElement(By.name("clique-aqui-seja-doador"));
        btn_cliqueAquiDoador.click();
    }

    @And("clico no botão Login")
    public void clicoNoBotaoLogin(){
        WebElement btn_loginCadDoador = navegador.findElement(By.name("loginCadDoador"));
        btn_loginCadDoador.click();
    }

    @Then("sou redirecionado para a tela de perfil Doador")
    public void souRedirecionadoParaATelaDePerfilDoador() {
        String page = navegador.getTitle();
        Assert.assertEquals("Perfil-Doador", page);
    }

    @And("clico no radio button Financeira")
    public void clicoNoRadioButtonFinanceira() {
        WebElement rb_financeira = navegador.findElement(By.name("doacaoFinanceira"));
        rb_financeira.click();
    }

    @Then("sou redirecionado para a tela de efetuar doacao")
    public void souRedirecionadoParaATelaDeEfetuarDoacao() {
        String page = navegador.getTitle();
        Assert.assertEquals("doacao", page);
    }

    @And("preencho os campos {string}, {string}, {string}, {string}, {string}")
    public void preenchoOsCampos(String nome, String codIntituicao, String entidadeBeneficiada, String valorDoacao, String conferirValorDoacao) {
        WebElement txt_nome = navegador.findElement(By.name("nome"));
        WebElement txt_codIntituicao = navegador.findElement(By.name("codIntituicao"));
        WebElement txt_entidadeBeneficiada = navegador.findElement(By.name("entidadeBeneficiada"));
        WebElement txt_valorDoacao = navegador.findElement(By.name("valorDoacao"));
        WebElement txt_conferirValorDoacao = navegador.findElement(By.name("conferirValorDoacao"));
        txt_nome.sendKeys(nome);
        txt_codIntituicao.sendKeys(codIntituicao);
        txt_entidadeBeneficiada.sendKeys(entidadeBeneficiada);
        txt_valorDoacao.sendKeys(valorDoacao);
        txt_conferirValorDoacao.sendKeys(conferirValorDoacao);
    }

    @And("clico no botão Confirmar doacao")
    public void clicoNoBotaoConfirmarDoacao() {
        WebElement btn_confirmaDoacao = navegador.findElement(By.name("confirma-Doacao"));
        btn_confirmaDoacao.click();
    }

    @Then("recebo mensagem de sucesso")
    public void receboMensagemDeSucesso() {
        WebElement mensagemAlteracao = navegador.findElement(By.id("sucesso-cad-doador"));
        String msn = mensagemAlteracao.getText();
        Assert.assertEquals("Doador cadastrado com sucesso", msn);
    }

    @When("eu clico no menu cadastro")
    public void euClicoNoMenuCadastro() {
        WebElement btn_cadastroHome = navegador.findElement(By.name("cadastro-Home"));
        btn_cadastroHome.click();
    }

    @And("clico no o radio button Equipamentos")
    public void clicoNoORadioButtonEquipamentos() {
        WebElement rb_equipamento = navegador.findElement(By.name("doacao-equipamentos"));
        rb_equipamento.click();
    }

    @Given("que eu tenha cadastro no site SportFree")
    public void queEuTenhaCadastroNoSiteSportFree() {
        System.out.println("tenho cadastro no site");
    }


    @And("preencho os campos preencho os campos {string}, {string}, {string}, {string}, {string}")
    public void preenchoOsCamposPreenchoOsCamposQuantidade(String nome, String codIntituicao, String entidadeBeneficiada, String equipamento, String quantidade) {
        WebElement txt_nomeDoador = navegador.findElement(By.name("nome"));
        WebElement txt_codIntituicao = navegador.findElement(By.name("codIntituicao"));
        WebElement txt_entidadeBeneficiada = navegador.findElement(By.name("entidadeBeneficiada"));
        WebElement txt_equipamento = navegador.findElement(By.name("equipamento"));
        WebElement txt_quantidade = navegador.findElement(By.name("quantidade"));
        txt_nomeDoador.sendKeys(nome);
        txt_codIntituicao.sendKeys(codIntituicao);
        txt_entidadeBeneficiada.sendKeys(entidadeBeneficiada);
        txt_equipamento.sendKeys(equipamento);
        txt_quantidade.sendKeys(quantidade);

    }
}
