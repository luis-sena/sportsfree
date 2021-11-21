package cucumberSteps;

import io.cucumber.java.en.Given;

public class CadastroEsporteAPISteps {
    @Given("que eu queira pesquisar por um esporte")
    public void queEuQueiraPesquisarPorUmEsporte() {
        System.out.println("Pesquisar esportes");
    }

    @Given("que eu queira listar todos os esportes")
    public void queEuQueiraListarTodosOsEsportes() {
        System.out.println("Listar esportes");
    }

    @Given("que eu queira deletar um esporte")
    public void queEuQueiraDeletarUmEsporte() {
        System.out.println("Deletar um esporte");
    }
}
