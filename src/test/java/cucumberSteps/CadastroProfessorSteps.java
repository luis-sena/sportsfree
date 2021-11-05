package cucumberSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;


public class CadastroProfessorSteps {

    WebDriver navegador;




    @Given("que eu como administrador acesse o SportFree")
    public void queEuComoAdministradorAcesseOSportFree() throws IOException {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        navegador = new ChromeDriver();
        HttpsURLConnection urlc = (HttpsURLConnection) new URL("https://sportsfree-dev.herokuapp.com/professor").openConnection();
        int code = urlc.getResponseCode();
        Assert.assertEquals(200, code);
        System.out.println("administrador acessa o site");
    }

    @When("eu clico no botão de login")
    public void euClicoNoBotaoDeLogin(){
        WebElement btn_login_home = navegador.findElement(By.name("login-home"));
        btn_login_home.click();
    }
    @Then("sou redirecionado para a tela de login")
    public void souRedirecionadoParaATelaDeLogin() {
        String page = navegador.getTitle();
        Assert.assertEquals("Login", page);
    }

    @And("insiro meu {string} e {string}")
    public void insiroMeuE(String login, String password) {
        WebElement txt_login = navegador.findElement(By.name("txtlogin"));
        WebElement txt_password = navegador.findElement(By.name("txt_password"));
        txt_login.sendKeys(login);
        txt_password.sendKeys(password);
    }

    @And("clico no botão login para entrar")
    public void clicoNoBotaoLoginParaEntrar() {
        WebElement btn_login = navegador.findElement(By.name("login"));
        btn_login.click();
    }

    @Then("sou redirecionado para a tela de perfil Administrador")
    public void redirecionadoParaATelaDePerfilAdministrador() {
        String title = navegador.getTitle();
        Assert.assertEquals("Perfil-Administrador", title);

    }

    @When("eu clico no botão cadastro")
    public void euClicoNoBotaoCadastro() {
        WebElement btn_cadastro = navegador.findElement(By.name("Cadastro"));
        btn_cadastro.click();
    }

    @Then("sou redirecionado para a tela de cadastro Novo Cliente")
    public void souRedirecionadoParaATelaDeCadastroNovoCliente() {
        String title = navegador.getTitle();
        Assert.assertEquals("NovoCliente", title);
    }

    @And("Na tela de cadastro eu preencho {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void naTelaDeCadastroEuPreencho(String tipoPessoa, String Nome, String Documento, String RG, String Telefone1, String Telefone2, String email, String CEP, String NumeroComplemento, String RedeSocial, String Observacoes ) {
        WebElement txt_tipoPessoa = navegador.findElement(By.name("tipoPessoa"));
        WebElement txt_Nome = navegador.findElement(By.name("Nome"));
        WebElement txt_Documento = navegador.findElement(By.name("Documento"));
        WebElement txt_RG = navegador.findElement(By.name("RG"));
        WebElement txt_Telefone1 = navegador.findElement(By.name("Telefone1"));
        WebElement txt_Telefone2 = navegador.findElement(By.name("Telefone2"));
        WebElement txt_email = navegador.findElement(By.name("email"));
        WebElement txt_CEP = navegador.findElement(By.name("CEP"));
        WebElement txt_NumeroComplemento = navegador.findElement(By.name("NumeroComplemento"));
        WebElement txt_RedeSocial = navegador.findElement(By.name("RedeSocial"));
        WebElement txt_Observacoes = navegador.findElement(By.name("Observacoes"));
        txt_tipoPessoa.sendKeys(tipoPessoa);
        txt_Nome.sendKeys(Nome);
        txt_Documento.sendKeys(Documento);
        txt_RG.sendKeys(RG);
        txt_Telefone1.sendKeys(Telefone1);
        txt_Telefone2.sendKeys(Telefone2);
        txt_email.sendKeys(email);
        txt_CEP.sendKeys(CEP);
        txt_NumeroComplemento.sendKeys(NumeroComplemento);
        txt_RedeSocial.sendKeys(RedeSocial);
        txt_Observacoes.sendKeys(Observacoes);

    }

    @And("eu escolho o perfil profissional")
    public void euEscolhoOPerfilProfissional() {
        WebElement btn_Profissional = navegador.findElement(By.name("Profissional"));
        btn_Profissional.click();
    }

    @And("eu clico no botão salvar")
    public void euClicoNoBotaoSalvar() {
        WebElement btn_salvarCadastro = navegador.findElement(By.name("SalvarCadastro"));
        btn_salvarCadastro.click();
    }

    @Then("o sistema retorna mensagem de cadastro realizado com sucesso.")
    public void oSistemaRetornaMensagemDeCadastroRealizadoComSucesso() {
        WebElement mensagemSucesso = navegador.findElement(By.id("msnSucesso"));
        String msn = mensagemSucesso.getText();
        Assert.assertEquals("Cadastro salvo com sucesso", msn);
    }

    @When("eu clico no menu profissionais")
    public void euClicoNoMenuProfissionais() {
        WebElement lnk_Profissionais = navegador.findElement(By.name("Profissionais"));
        lnk_Profissionais.click();
    }

    @Then("sou redirecionado para a tela de professores cadastrados")
    public void souRedirecionadoParaATelaDeProfessoresCadastrados() {
        String title = navegador.getTitle();
        Assert.assertEquals("Profissionais", title);
    }

    @And("eu clico no botão deletar da linha do cadastro a ser deletado")
    public void euClicoNoBotaoDeletarDaLinhaDoCadastroASerDeletado() {
        WebElement btn_delete = navegador.findElement(By.name("Delete professor"));
        btn_delete.click();
    }

    @And("eu clico no botão confirmar delete")
    public void euClicoNoBotaoConfirmarDelete() {
        WebElement btn_confirmar = navegador.findElement(By.name("Confirmar delete"));
        btn_confirmar.click();
    }

    @When("o sistema retorna mensagem de Profissional deletado com sucesso.")
    public void oSistemaRetornaMensagemDeProfissionalDeletadoComSucesso() {
        WebElement mensagemDelete = navegador.findElement(By.id("msnDelete"));
        String msn = mensagemDelete.getText();
        Assert.assertEquals("Professor deletado com sucesso", msn);
    }

    @And("eu clico no botão alterar da linha do cadastro a ser deletado")
    public void euClicoNoBotaoAlterarDaLinhaDoCadastroASerDeletado() {
        WebElement btn_deletar = navegador.findElement(By.name("Deletar professor"));
        btn_deletar.click();
    }

    @And("insiro no campo {string} o valor {string}")
    public void insiroNoCampoOValor(String campo, String valor) {
        WebElement txt_campoAlt = navegador.findElement(By.name("campo"));
        txt_campoAlt.sendKeys(valor);

    }

    @Then("o sistema retorna mensagem de alteracao realizado com sucesso")
    public void oSistemaRetornaMensagemDeAlteracaoRealizadoComSucesso() {
        WebElement mensagemAlteracao = navegador.findElement(By.id("msnAlteracao"));
        String msn = mensagemAlteracao.getText();
        Assert.assertEquals("Alteração realizada com sucesso", msn);
    }
}
