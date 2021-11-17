package cucumberSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;


public class CadastroProfessorSteps {

    WebDriver navegador;
    private String nomeProfessor;

    @Given("que eu como administrador acesse o SportFree")
    public void queEuComoAdministradorAcesseOSportFree() throws IOException {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.get("https://front-bootcamp.herokuapp.com/pages/professores/");
        Assert.assertTrue(navegador.getTitle().equals("Sportfree"));
    }

//    @When("eu clico no botão de login")
//    public void euClicoNoBotaoDeLogin(){
//        WebElement btn_login_home = navegador.findElement(By.name("login-home"));
//        btn_login_home.click();
//    }
//    @Then("sou redirecionado para a tela de login")
//    public void souRedirecionadoParaATelaDeLogin() {
//        String page = navegador.getTitle();
//        Assert.assertEquals("Login", page);
//    }

//    @And("insiro meu {string} e {string}")
//    public void insiroMeuE(String login, String password) {
//        WebElement txt_login = navegador.findElement(By.name("txtlogin"));
//        WebElement txt_password = navegador.findElement(By.name("txt_password"));
//        txt_login.sendKeys(login);
//        txt_password.sendKeys(password);
//    }
//
//    @And("clico no botão login para entrar")
//    public void clicoNoBotaoLoginParaEntrar() {
//        WebElement btn_login = navegador.findElement(By.name("login"));
//        btn_login.click();
//    }

    @Then("sou redirecionado para a tela de perfil Administrador")
    public void redirecionadoParaATelaDePerfilAdministrador() {
        String title = navegador.getTitle();
        Assert.assertEquals("Perfil-Administrador", title);

    }

    @When("eu clico no botão cadastro")
    public void euClicoNoBotaoCadastro() throws InterruptedException {
        WebElement btn_cadastro = navegador.findElement(By.xpath("//a[contains(text(),'Cadastrar professor')]"));
        navegador.wait(2000);
        btn_cadastro.click();
    }

    @Then("sou redirecionado para a tela de cadastro Novo Cliente")
    public void souRedirecionadoParaATelaDeCadastroNovoCliente() {
        String currentURL = navegador.getCurrentUrl();
        Assert.assertEquals("https://front-bootcamp.herokuapp.com/pages/professores/cadastro.php", currentURL);
    }

    @And("Na tela de cadastro eu preencho {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void naTelaDeCadastroEuPreencho(String nomeCompleto, String email, String CPF, String RG, String CEP, String numero, String complemento ) {
        WebElement txt_nomeCompleto = navegador.findElement(By.id("nome"));
        WebElement txt_email = navegador.findElement(By.id("email"));
        WebElement txt_CPF = navegador.findElement(By.id("cpf"));
        WebElement txt_RG = navegador.findElement(By.id("rg"));
        WebElement txt_CEP = navegador.findElement(By.id("cep"));
        WebElement txt_numero = navegador.findElement(By.id("numero"));
        WebElement txt_complemento = navegador.findElement(By.id("complemento"));
        txt_nomeCompleto.sendKeys(nomeCompleto);
        txt_email.sendKeys(email);
        txt_CPF.sendKeys(CPF);
        txt_RG.sendKeys(RG);
        txt_CEP.sendKeys(CEP);
        txt_numero.sendKeys(numero);
        txt_complemento.sendKeys(complemento);

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
    public void oSistemaRetornaMensagemDeCadastroRealizadoComSucesso() throws InterruptedException {
        Thread.sleep(2000);
        Alert alert = navegador.switchTo().alert();
        String text = alert.getText();
        Assert.assertEquals("Professor cadastrado com sucesso!", text);
        alert.accept();
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

    @When("eu clico no botão cadastrar professor")
    public void euClicoNoBotãoCadastrarProfessor() {
        WebElement btn_cadProfessor = navegador.findElement(By.xpath("//a[contains(text(),'Cadastrar professor')]"));
        btn_cadProfessor.click();
    }

    @Then("sou redirecionado para a tela de cadastro")
    public void souRedirecionadoParaATelaDeCadastro() throws InterruptedException {
        WebElement pg_cadastro = navegador.findElement(By.xpath("//h2[contains(text(),'Cadastro')]"));
        String texto_elemento = pg_cadastro.getText();
        Assert.assertEquals("Cadastro", texto_elemento );
    }

    @And("eu clico no botão Registrar")
    public void euClicoNoBotaoRegistrar() throws InterruptedException {
        WebElement btn_registrar = navegador.findElement(By.xpath("//button[contains(text(),'Registrar')]"));
        btn_registrar.submit();
    }

    @And("Fecho o navegador")
    public void fechoONavegador() {
        navegador.close();
    }


    @When("eu clico no botão visualizar do professor com id {int}")
    public void euClicoNoBotaoVisualizarDoProfessorComIdId(int id) throws InterruptedException {
        Thread.sleep(3000);
        WebElement btn_visualizar = navegador.findElement(By.xpath("//tr/td/a[@href = \"visualizar.php?id="+ id +"\"]"));
        btn_visualizar.click();
    }

    @When("eu clico no botão voltar")
    public void euClicoNoBotaoVoltar() {
        WebElement btn_voltar = navegador.findElement(By.xpath("//button[contains(text(),'Voltar')]"));
        btn_voltar.click();
    }

    @Then("sou redirecionado para a tela de professores")
    public void souRedirecionadoApraATelaDeProfessores() {
        Assert.assertEquals(navegador.getCurrentUrl(), "https://front-bootcamp.herokuapp.com/pages/professores/");
    }

    @Then("eu consigo ver os dados do professor com o id {int}")
    public void euConsigoVerOsDadosDoProfessorComOIdId(int id) throws InterruptedException {
        Assert.assertEquals("https://front-bootcamp.herokuapp.com/pages/professores/visualizar.php?id=" + id, navegador.getCurrentUrl());
        Thread.sleep(3000);
    }

    @When("eu clico no botão Alterar")
    public void euClicoNoBotaoAlterar() {
        WebElement btn_alterar = navegador.findElement(By.xpath("//button[contains(text(),'Alterar')]"));
        btn_alterar.submit();
    }


    @When("eu clico no botão editar do professor com id {int}")
    public void euClicoNoBotaoEditarDoProfessorComId(int id) throws InterruptedException {
        Thread.sleep(3000);
        WebElement btn_editar = navegador.findElement(By.xpath("//tr/td/a[@href = \"editar.php?id="+id+"\"]"));
        btn_editar.click();
    }

    @And("altero o campo {string} com o valor {string}")
    public void alteroOCampoComOValor(String campo, String valor) {
        switch (campo) {
            case "NomeCompleto":
                WebElement txt_nomeCompleto = navegador.findElement(By.id("nome"));
                txt_nomeCompleto.click();
                txt_nomeCompleto.clear();
                txt_nomeCompleto.sendKeys(valor);
                break;

            case "email":
                WebElement txt_email = navegador.findElement(By.id("email"));
                txt_email.click();
                txt_email.clear();
                txt_email.sendKeys(valor);
                break;

            case "CPF":
                WebElement txt_CPF = navegador.findElement(By.id("cpf"));
                txt_CPF.click();
                txt_CPF.clear();
                txt_CPF.sendKeys(valor);
                break;

            case "RG":
                WebElement txt_RG = navegador.findElement(By.id("rg"));
                txt_RG.click();
                txt_RG.clear();
                txt_RG.sendKeys(valor);
                break;

            case "CEP":
                WebElement txt_CEP = navegador.findElement(By.id("cep"));
                txt_CEP.click();
                txt_CEP.clear();
                txt_CEP.sendKeys(valor);
                break;

            case "numero":
                WebElement txt_numero = navegador.findElement(By.id("numero"));
                txt_numero.click();
                txt_numero.clear();
                txt_numero.sendKeys(valor);
                break;

            case "complemento":
                WebElement txt_complemento = navegador.findElement(By.id("complemento"));
                txt_complemento.click();
                txt_complemento.clear();
                txt_complemento.sendKeys(valor);
                break;
        }
    }

    @Then("o sistema retorna mensagem de edicao realizada com sucesso.")
    public void oSistemaRetornaMensagemDeEdicaoRealizadaComSucesso() throws InterruptedException {
        Thread.sleep(3000);
        Alert alert = navegador.switchTo().alert();
        String text = alert.getText();
        Assert.assertEquals("Professor alterado com sucesso!", text);
        alert.accept();
    }

    @Then("eu consigo ver os dados para editar do professor com o id {int}")
    public void euConsigoVerOsDadosParaEditarDoProfessorComOId(int id) throws InterruptedException {
        Assert.assertEquals("https://front-bootcamp.herokuapp.com/pages/professores/editar.php?id=" + id, navegador.getCurrentUrl());
        Thread.sleep(3000);
    }


    @When("eu clico no botão excluir do professor com id {int}")
    public void euClicoNoBotaoExcluirDoProfessorComId(int id) throws InterruptedException {
        Thread.sleep(3000);
        WebElement btn_excluir = navegador.findElement(By.xpath("//button[@class='btn btn-main btn-round-full excluirProfessor' and @onclick='excluirProfessor(\""+id+"\")']"));
        btn_excluir.click();
    }

    @Then("o sistema retorna mensagem de exclusao realizada com sucesso.")
    public void oSistemaRetornaMensagemDeExclusaoRealizadaComSucesso() throws InterruptedException {
        Thread.sleep(3000);
        Alert alert = navegador.switchTo().alert();
        String text = alert.getText();
        Assert.assertEquals("excluido com sucesso", text);
        alert.accept();
    }
}


