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
    Then Http response should be 202


    Examples:
      | nome     | email              | CPF            | RG           | CEP       | Cidade    | UF | Rua                                 | numero | bairro               | complemento |  |  |
      | Leonardo | Leonardo@teste.com | 758.008.780-22 | 68.994.268-4 | 24930-520 | Boa Vista | RR | Rua Vereador Manoel Joaquim Martins | 68     | Senador Hélio Campos |             |  |  |
      | cassio   | cassio@teste.com   | 341.105.430-96 | 10.602.286-4 | 59607-819 | Mossoró   | RN | Rua Antônio Maciel de Lima          | 68     | Aeroporto            |             |  |  |
      | marcia   | marcia@teste.com   | 950.631.770-91 | 18.885.057-8 | 52040-081 | Recife    | PE | 1ª Travessa Marquês de Baipendi     | 68     | Campo Grande         |             |  |  |
      | hellen   | hellen@teste.com   | 958.831.860-91 | 21.386.269-3 | 24930-520 | Maricá    | RJ | Rua das Orquídeas                   | 68     | Centro               |             |  |  |
      | paulo    | paulo@teste.com    | 399.180.340-36 | 30.720.549-9 | 24930-520 | Maricá    | RJ | Rua das Orquídeas                   | 68     | Centro               |             |  |  |


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
      |  | id | nome | email               | CPF | RG | CEP | Cidade | UF | Rua | numero | bairro | complemento |
      |  | 20 |      | Jaqueline@teste.com |     |    |     |        |    |     |        |        | BL3         |


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
      | 20 |  |

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
  Scenario Outline: Deletar Professor
    Given que eu queira deletar todos os professores
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/professor/<id>"
    When eu envio a requisição DELETE
    Then Http response should be 204

    Examples:
      | id |  |
      | 25  |  |

  @Negativo
  @Test

  Scenario: Salvar professor sem JSON
    Given que eu uso o json
    """

    """
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/professor"
    When eu envio a requisição POST
    Then Http response should be 400

#  @Negativo
#    @Test
#  Scenario Outline: Salvar professor com dados inválidos
#    Given que eu uso o json
#    """
#        {
#          "nome": "<nome>",
#          "email": "<email>",
#          "cpf": "<CPF>",
#          "rg": "<RG>",
#          "endereco": {
#            "cep": "<CEP>",
#            "cidade": "<Cidade>",
#            "uf": "<UF>",
#            "rua": "<Rua>",
#            "numero": "<numero>",
#            "bairro": "<bairro>",
#            "complemento": "<complemento>"
#          }
#        }
#    """
#    And executo os testes no endpoint "DEVELOPMENT"
#    And uso a rota "/professor"
#    When eu envio a requisição POST
#    Then Http response should be 400
#
#
#    Examples:
#      | nome     | email              | CPF            | RG           | CEP       | Cidade    | UF  | Rua                                 | numero | bairro               | complemento |  |  |
#      | !!       | Leonardo@teste.com | 758.008.780-22 | 68.994.268-4 | 24930-520 | Boa Vista | RR  | Rua Vereador Manoel Joaquim Martins | 68     | Senador Hélio Campos |             |  |  |
#      | cassio   | cassio.com         | 341.105.430-96 | 10.602.286-4 | 59607-819 | Mossoró   | RN  | Rua Antônio Maciel de Lima          | 68     | Aeroporto            |             |  |  |
#      | marcia   | marcia@teste.com   | abc            | 18.885.057-8 | 52040-081 | Recife    | PE  | 1ª Travessa Marquês de Baipendi     | 68     | Campo Grande         |             |  |  |
#      | hellen   | hellen@teste.com   | 958.831.860-91 | abc          | 24930-520 | Maricá    | RJ  | Rua das Orquídeas                   | 68     | Centro               |             |  |  |
#      | paulo    | paulo@teste.com    | 399.180.340-36 | 30.720.549-9 | abc       | Maricá    | RJ  | Rua das Orquídeas                   | 68     | Centro               |             |  |  |
#      | Leonardo | Leonardo@teste.com | 758.008.780-22 | 68.994.268-4 | 24930-520 | &&&       | RR  | Rua Vereador Manoel Joaquim Martins | 68     | Senador Hélio Campos |             |  |  |
#      | cassio   | cassio@teste.com   | 341.105.430-96 | 10.602.286-4 | 59607-819 | Mossoró   | qqq | Rua Antônio Maciel de Lima          | 68     | Aeroporto            |             |  |  |
#      | marcia   | marcia@teste.com   | abc            | 18.885.057-8 | 52040-081 | Recife    | PE  | ------                              | 68     | Campo Grande         |             |  |  |
#      | hellen   | hellen@teste.com   | 958.831.860-91 | abc          | 24930-520 | Maricá    | RJ  | Rua das Orquídeas                   | as     | Centro               |             |  |  |
#      | paulo    | paulo@teste.com    | 399.180.340-36 | 30.720.549-9 | 24930-520 | Maricá    | RJ  | Rua das Orquídeas                   | 68     | $$$                  |             |  |  |
#      | paulo    | paulo@teste.com    | 399.180.340-36 | 30.720.549-9 | 24930-520 | Maricá    | RJ  | Rua das Orquídeas                   | 68     | $$$                  | %%%%%%      |  |  |

#  @Negativo
#    @Test
#  Scenario Outline: Editar professor com dados inválidos
#    Given que eu uso o json
#    """
#        {
#          "id": <id>,
#          "nome": "<nome>",
#          "email": "<email>",
#          "cpf": "<CPF>",
#          "rg": "<RG>",
#          "endereco": {
#            "cep": "<CEP>",
#            "cidade": "<Cidade>",
#            "uf": "<UF>",
#            "rua": "<Rua>",
#            "numero": "<numero>",
#            "bairro": "<bairro>",
#            "complemento": "<complemento>"
#          }
#        }
#    """
#    And executo os testes no endpoint "DEVELOPMENT"
#    And uso a rota "/professor"
#    When eu envio a requisição PUT
#    Then Http response should be 400
#
#
#    Examples:
#      | id | nome   | email            | CPF            | RG  | CEP       | Cidade | UF | Rua               | numero | bairro | complemento |  |  |
#      | 94 | Ingrid | ingrid@teste.com | 058.831.860-91 | abc | 24930-520 | Maricá | RJ | Rua das Orquídeas | as     | Centro |             |  |  |

  @Positivo
    @Test
  Scenario Outline: Recuperar professor com id inexistente
    Given que eu queira pesquisar por um professor
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/professor/<id>"
    When eu envio a requisição GET
    Then Http response should be 404

    Examples:
      | id     |  |
      | 999999 |  |

  @Positivo
    @Test
  Scenario Outline: Deletar Professor com id inválido
    Given que eu queira deletar todos os professores
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/professor/<id>"
    When eu envio a requisição DELETE
    Then Http response should be 404

    Examples:
      | id    |  |
      | 99999 |  |