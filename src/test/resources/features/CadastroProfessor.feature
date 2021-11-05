Feature: cadastro de Professor
  Eu como administrador da plataforma
  Quero conseguir admistrar os professores cadastrados na plataforma
  Para poder cadastrar, excluir e atualizar os professores.

  Scenario Outline: Administrador insere um professor
    Given que eu como administrador acesse o SportFree
    When eu clico no botão de login
    Then sou redirecionado para a tela de login
    And insiro meu "<loginADM>" e "<senhaADM>"
    And clico no botão login para entrar
    Then sou redirecionado para a tela de perfil Administrador
    When eu clico no botão cadastro
    Then sou redirecionado para a tela de cadastro Novo Cliente
    And Na tela de cadastro eu preencho "<tipoPessoa>", "<Nome>", "<Documento>", "<RG>", "<Telefone1>", "<Telefone2>", "<email>", "<CEP>", "<NumeroComplemento>", "<RedeSocial>", "<Observacoes>"
    And eu escolho o perfil profissional
    And eu clico no botão salvar
    Then o sistema retorna mensagem de cadastro realizado com sucesso.

    Examples:
      | loginADM | senhaADM | tipoPessoa | Nome        | Documento      | RG          | Telefone1   | Telefone2   | email                      | CEP       | NumeroComplemento | RedeSocial | Observacoes   |
      | PR123    | 123456   | PF         | Professor 1 | 111.111.111-11 | 11.111.11-1 | 11987563214 | 11987563278 | professor@professor.com.br | 11111-111 | 11                | @Professor | OBS Professor |


  Scenario Outline: Administrador Deleta um cadastro de professor
    Given que eu como administrador acesse o SportFree
    When eu clico no botão de login
    Then sou redirecionado para a tela de login
    And insiro meu "<loginADM>" e "<senhaADM>"
    And clico no botão login para entrar
    When sou redirecionado para a tela de perfil Administrador
    When eu clico no menu profissionais
    Then sou redirecionado para a tela de professores cadastrados
    And eu clico no botão deletar da linha do cadastro a ser deletado
    And eu clico no botão confirmar delete
    When o sistema retorna mensagem de Profissional deletado com sucesso.

    Examples:
      | loginADM | senhaADM |
      | PR123    | 123456   |


  Scenario Outline: Administrador altera um cadastro de professor
    Given que eu como administrador acesse o SportFree
    When eu clico no botão de login
    Then sou redirecionado para a tela de login
    And insiro meu "<loginADM>" e "<senhaADM>"
    And clico no botão login para entrar
    Then sou redirecionado para a tela de perfil Administrador
    When eu clico no menu profissionais
    Then sou redirecionado para a tela de professores cadastrados
    And eu clico no botão alterar da linha do cadastro a ser deletado
    And insiro no campo "<campo>" o valor "<vlor>"
    And eu clico no botão salvar
    Then o sistema retorna mensagem de alteracao realizado com sucesso

    Examples:
      | loginADM | senhaADM | campo             | vlor                    |
      | PR123    | 123456   | tipoPessoa        | PJ                      |
      | PR123    | 123456   | Nome              | Professor Ciclano       |
      | PR123    | 123456   | Documento         | 222.222.222-22          |
      | PR123    | 123456   | RG                | 22.222.222-2            |
      | PR123    | 123456   | Telefone1         | 16-222222222            |
      | PR123    | 123456   | Telefone2         | 16-333333333            |
      | PR123    | 123456   | email             | fulano@professor.com.br |
      | PR123    | 123456   | CEP               | 13561100                |
      | PR123    | 123456   | NumeroComplemento | 222                     |
      | PR123    | 123456   | RedeSocial        | @ciclano                |
      | PR123    | 123456   | Observacoes       | alterando professor     |


