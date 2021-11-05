Feature: Cadastro de Instituição
  Eu como administrador da plataforma
  quero conseguir admistrar as instituições cadastrados na plataforma,
  para poder cadastrar, excluir e atualizar as instituições.


  Scenario Outline: Administrador insere instituição
    Given que eu como administrador acesse o SportFree
    When eu clico no botão de login
    Then sou redirecionado para a tela de login
    And insiro meu "<loginADM>" e "<senhaADM>"
    And clico no botão Login
    Then sou redirecionado para a tela de perfil Administrador
    When eu clico no menu Instituição
    Then sou redirecionado para a tela de instituições
    And eu clico no botão nova instituição
    And Na tela de cadastro eu preencho "<Nome>", "<Telefone1>", "<Telefone2>", "<CEP>", "<Numero>", "<RedeSocial>", "<Observacoes>"
    And eu clico no botão salvar
    Then o sistema retorna mensagem de cadastro realizado com sucesso

    Examples:
      | loginADM | senhaADM | Nome          | Telefone1  | Telefone2 | CEP      | Numero | RedeSocial    | Observacoes |
      | A D123   | 753698   | Instituicao 1 | 1198555555 | 119865555 | 13561100 | 22     | @instituicao1 | intituicao  |

  Scenario Outline: Administrador altera uma instituição
    Given que eu como administrador acesse o SportFree
    When eu clico no botão de login
    Then sou redirecionado para a tela de login
    And insiro meu "<loginADM>" e "<senhaADM>"
    And clico no botão Login
    Then sou redirecionado para a tela de perfil Administrador
    When eu clico no menu Instituição
    Then sou redirecionado para a tela de instituições
    And eu clico no botão alterar do cadastro a ser alterado
    Then eu apago o valor no campo da instituicao "<campo>"
    And insiro no campo da instituicao "<campo>" o valor "<valor>"
    And eu clico no botão salvar
    Then o sistema retorna mensagem de alteração realizada com sucesso

    Examples:
      | loginADM | senhaADM | campo | valor        |
      | adm123   | 147852   | nome  | instituicao9 |

  Scenario Outline:Administrador deleta uma instituição
    Given que eu como administrador acesse o SportFree
    When eu clico no botão de login
    Then sou redirecionado para a tela de login
    And insiro meu "<loginADM>" e "<senhaADM>"
    And clico no botão Login
    Then sou redirecionado para a tela de perfil Administrador
    When eu clico no menu Instituição
    Then sou redirecionado para a tela de instituições
    And eu clico no botão deletar do cadastro a ser deletado
    And eu clico no botão confirmar delete de instituicao
    Then o sistema retorna mensagem de cadastro deletado com sucesso

    Examples:
      | loginADM |senhaADM  |
