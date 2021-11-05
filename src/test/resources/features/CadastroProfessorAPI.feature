Feature: cadastro de Professor API
  Eu como administrador da plataforma
  Quero conseguir admistrar os professores cadastrados na plataforma
  Para poder cadastrar, excluir e atualizar os professores.

  @Positivo
    @Test
  Scenario Outline: Salvar professor
    Given que eu uso o json
    """
        {
          "nome": "<nome>",
          "email": "<email>",
          "cpf": "<CPF>",
          "rg": "<RG>",
          "endereco": {
            "cep": "<CEP>",
            "cidade": "<Cidade>",
            "uf": "<UF>",
            "rua": "<Rua>",
            "numero": "<numero>",
            "bairro": "<bairro>",
            "complemento": "<complemento>"
          }
        }
    """
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/professor"
    When eu envio a requisição POST
    Then Http response should be 200


    Examples:
      | nome           | email            | CPF            | RG           | CEP       | Cidade | UF | Rua                | numero | bairro | complemento |  |  |
      | Murilo Pereira | murilo@teste.com | 833.818.610-42 | 46.174.758-3 | 69930-970 | Xapuri | AC | Rua Seis de Agosto | 68     | Centro | bl3         |  |  |


  @Positivo
    @Test
  Scenario Outline: Editar Professor
    Given que eu uso o json
    """
        {
          "id": <id>,
          "nome": "<nome>",
          "email": "<email>",
          "cpf": "<CPF>",
          "rg": "<RG>",
          "endereco": {
            "cep": "<CEP>",
            "cidade": "<Cidade>",
            "uf": "<UF>",
            "rua": "<Rua>",
            "numero": "<numero>",
            "bairro": "<bairro>",
            "complemento": "<complemento>"
         }
        }
    """
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/professor"
    When eu envio a requisição PUT
    Then Http response should be 200

    Examples:
      |  | id | nome | email                 | CPF            | RG           | CEP      | Cidade     | UF | Rua                      | numero | bairro              | complemento       |
      |  | 8  | Juca | juca@professor.com.br | 123.456.789-10 | 12.345.678-9 | 12345-67 | Araraquara | PC | Rua da casa do professor | 123    | Bairro do professor | Casa do professor |


  @Positivo
    @Test
  Scenario Outline: Recuperar professor
    Given que eu queira pesquisar por um professor
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/professor/<id>"
    When eu envio a requisição GET
    Then Http response should be 200

    Examples:
      | id |  |
      | 8  |  |

  @Positivo
    @Test
  Scenario Outline: Lista Professores
    Given que eu queira pesquisar por um professor
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/professor/<id>"
    When eu envio a requisição GET
    Then Http response should be 200

    Examples:
      | id |  |
      | 8  |  |

  @Positivo
    @Test
  Scenario: Lista Professores
    Given que eu queira listar todos os professores
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/professor"
    When eu envio a requisição GET
    Then Http response should be 200

  @Positivo
  @Test
  Scenario: Deletar Professor
    Given que eu queira listar todos os professores
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/professor"
    When eu envio a requisição DELETE
    Then Http response should be 200