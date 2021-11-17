Feature: cadastro de Professor
  Eu como administrador da plataforma
  Quero conseguir admistrar os professores cadastrados na plataforma
  Para poder cadastrar, excluir e atualizar os professores.

  Scenario Outline: Administrador insere um professor
    Given que eu como administrador acesse o SportFree
    When eu clico no botão cadastrar professor
    Then sou redirecionado para a tela de cadastro
    And Na tela de cadastro eu preencho "<NomeCompleto>", "<e-mail>", "<CPF>", "<RG>", "<CEP>", "<numero>", "<complemento>"
    And eu clico no botão Registrar
    Then o sistema retorna mensagem de cadastro realizado com sucesso.
    And Fecho o navegador

    Examples:
      | NomeCompleto      | e-mail            | CPF         | RG        | CEP       | numero | complemento |
      | Francisco fontana | fontana@teste.com | 41185963080 | 389480797 | 73301-586 | 20     | bloco b     |
#     | Daniel       | daniel@teste.com | 572.431.040-61 | 36.208.804-4 | 74305-120 | 10  |            |



  Scenario Outline: Administrador visualiza professor
    Given que eu como administrador acesse o SportFree
    When eu clico no botão visualizar do professor com id <id>
    Then eu consigo ver os dados do professor com o id <id>
    When eu clico no botão voltar
    Then sou redirecionado para a tela de professores
    And Fecho o navegador

    Examples:
      | id |  |
      | 25 |  |

  Scenario Outline: Administrador edita professor
    Given que eu como administrador acesse o SportFree
    When eu clico no botão editar do professor com id <id>
    Then eu consigo ver os dados para editar do professor com o id <id>
    And altero o campo "<campo>" com o valor "<valor>"
    When eu clico no botão Alterar
    Then o sistema retorna mensagem de edicao realizada com sucesso.
    And Fecho o navegador

    Examples:
      | id | campo        | valor               |
      | 20 | NomeCompleto | Jaqueline           |
      | 27 | email        | jaqueline@teste.com |
      | 98 | CPF          | 975.737.160-28      |
      | 98 | RG           | 134961109           |
      | 98 | CEP          | 65636-710           |
      | 98 | numero       | 16                  |
      | 98 | complemento  | teste 123           |

  Scenario Outline: Administrador exclui professor
    Given que eu como administrador acesse o SportFree
    When eu clico no botão excluir do professor com id <id>
    Then o sistema retorna mensagem de exclusao realizada com sucesso.
    And Fecho o navegador

    Examples:
      | id |  |
      | 29  |  |

