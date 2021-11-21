Feature: cadastro de Doador
  Eu como administrador da plataforma
  Quero conseguir admistrar os doadores cadastrados na plataforma
  Para poder cadastrar, excluir e atualizar os doadores.

  Scenario Outline: Administrador insere um Doador
    Given que eu como administrador acesse o SportFree
    When eu clico no botão de login
    Then sou redirecionado para a tela de login
    And insiro meu "<loginADM>" e "<senhaADM>"
    And clico no botão login para entrar
    Then sou redirecionado para a tela de perfil Administrador
    When eu clico no botão cadastro
    Then sou redirecionado para a tela de cadastro Novo Cliente
    And Na tela de cadastro eu preencho "<tipoPessoa>", "<Nome>", "<Documento>", "<RG>", "<Telefone1>", "<Telefone2>", "<email>", "<CEP>", "<NumeroComplemento>", "<RedeSocial>", "<Observacoes>"
    And eu escolho o perfil Doador
    And eu clico no botão salvar
    Then o sistema retorna mensagem de cadastro realizado com sucesso.

    Examples:
      | loginADM | senhaADM | tipoPessoa | Nome     | Documento      | RG          | Telefone1   | Telefone2   | email                      | CEP       | NumeroComplemento | RedeSocial | Observacoes   |
      | DO123    | 123456   | PF         | Doador 1 | 111.111.111-11 | 11.111.11-1 | 11987563214 | 11987563278 | professor@professor.com.br | 11111-111 | 11                | @Professor | OBS Professor |
