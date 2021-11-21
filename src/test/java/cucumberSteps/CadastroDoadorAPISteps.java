package cucumberSteps;

import io.cucumber.java.en.Given;

public class CadastroDoadorAPISteps {
    @Given("que eu queira listar todos os doadores")
    public void queEuQueiraListarTodosOsDoadores() {
        System.out.println("listar doadores");
    }

    @Given("que eu queira pesquisar por um doador")
    public void queEuQueiraPesquisarPorUmDoador() {
        System.out.println("Buscar um doador");
    }

    @Given("que eu queira deletar um doador")
    public void queEuQueiraDeletarUmDoador() {
        System.out.println("deletar doador");
    }
}
