package cucumberSteps;

import io.cucumber.java.en.And;
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

public class CadastroInstituicaoSteps {

    WebDriver navegador;

    @Before
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        navegador = new ChromeDriver();
        HttpsURLConnection urlc = (HttpsURLConnection) new URL("https://sportsfree-dev.herokuapp.com/professor").openConnection();
        int code = urlc.getResponseCode();
        Assert.assertEquals(200, code);
    }

    @When("eu clico no menu Instituição")
    public void euClicoNoMenuInstituicao() {
        WebElement btn_instituicao = navegador.findElement(By.name("seja-um-doador"));
        btn_instituicao.click();
    }

    @Then("sou redirecionado para a tela de instituições")
    public void souRedirecionadoParaATelaDeInstituicoes() {
        String page = navegador.getTitle();
        Assert.assertEquals("Perfil-Doador", page);
    }

    @And("eu clico no botão nova instituição")
    public void euClicoNoBotaoNovaInstituicao() {
        WebElement btn_novaInstituicao = navegador.findElement(By.name("seja-um-doador"));
        btn_novaInstituicao.click();
    }

//    @And("Na tela de cadastro eu preencho {string}, {string}, {string}, {string}, {string}, {string}, {string}")
//    public void naTelaDeCadastroEuPreencho(String nome, String Telefone1, String Telefone2, String CEP, String Numero, String RedeSocial, String Observacoes) {
//        WebElement txt_nome = navegador.findElement(By.name("nome"));
//        WebElement txt_Telefone1 = navegador.findElement(By.name("Telefone1"));
//        WebElement txt_Telefone2 = navegador.findElement(By.name("Telefone2"));
//        WebElement txt_CEP = navegador.findElement(By.name("CEP"));
//        WebElement txt_numero = navegador.findElement(By.name("Numero"));
//        WebElement txt_RedeSocial = navegador.findElement(By.name("RedeSocial"));
//        WebElement txt_Observacoes = navegador.findElement(By.name("Observacoes"));
//        txt_nome.sendKeys(nome);
//        txt_Telefone1.sendKeys(Telefone1);
//        txt_Telefone2.sendKeys(Telefone2);
//        txt_CEP.sendKeys(CEP);
//        txt_numero.sendKeys(Numero);
//        txt_RedeSocial.sendKeys(RedeSocial);
//        txt_Observacoes.sendKeys(Observacoes);
//    }

    @Then("o sistema retorna mensagem de cadastro realizado com sucesso")
    public void oSistemaRetornaMensagemDeCadastroRealizadoComSucesso() {
        WebElement mensagemCadastro = navegador.findElement(By.id("sucesso-cad-doador"));
        String msn = mensagemCadastro.getText();
        Assert.assertEquals("Instituição cadastrada com sucesso", msn);
    }

    @And("eu clico no botão alterar do cadastro a ser alterado")
    public void euClicoNoBotaoAlterarDoCadastroASerAlterado() {
        WebElement btn_alterarInstituicao = navegador.findElement(By.name("alteracao-instituicao"));
        btn_alterarInstituicao.click();
    }

    @Then("o sistema retorna mensagem de alteração realizada com sucesso")
    public void oSistemaRetornaMensagemDeAlteracaoRealizadaComSucesso() {
        WebElement mensagemAlteracao = navegador.findElement(By.id("sucesso-alteracao-instituicao"));
        String msn = mensagemAlteracao.getText();
        Assert.assertEquals("Instituição alterada com sucesso", msn);
    }

    @And("insiro no campo da instituicao {string} o valor {string}")
    public void insiroNoCampoDaInstituicaoOValor(String campo, String valor) {
        WebElement txt_campoInstituicao = navegador.findElement(By.name(campo));
        txt_campoInstituicao.sendKeys(valor);
    }

    @Then("eu apago o valor no campo da instituicao {string}")
    public void euApagoOValorNoCampoDaInstituicao(String campo) {
        WebElement txt_campoInstituicao = navegador.findElement(By.name(campo));
        txt_campoInstituicao.clear();
    }

    @And("eu clico no botão deletar do cadastro a ser deletado")
    public void euClicoNoBotaoDeletarDoCadastroASerDeletado() {
        WebElement btn_deletarInstituicao = navegador.findElement(By.name("deleta-instituicao"));
        btn_deletarInstituicao.click();
    }

    @And("eu clico no botão confirmar delete de instituicao")
    public void euClicoNoBotaoConfirmarDeleteDeInstituicao() {
        WebElement btn_confirmaDeleteInstituicao = navegador.findElement(By.name("confirma-delete-instituicao"));
        btn_confirmaDeleteInstituicao.click();
    }

    @Then("o sistema retorna mensagem de cadastro deletado com sucesso")
    public void oSistemaRetornaMensagemDeCadastroDeletadoComSucesso() {
        WebElement mensagemDelete = navegador.findElement(By.id("sucesso-delete-instituicao"));
        String msn = mensagemDelete.getText();
        Assert.assertEquals("Instituição removida com sucesso", msn);
    }

   }
