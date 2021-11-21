Feature: cadastro de Doador API
  Eu como administrador da plataforma
  Quero conseguir admistrar os doadores cadastrados na plataforma
  Para poder cadastrar, excluir e atualizar os doadores.


  @Positivo
    @Test
  Scenario Outline: Administrador insere um doador
    Given que eu uso o json
    """
        {
          "nome": "<nomeDoador>",
          "cpfCnpj": "<cpfCnpj>",
          "rg": "<rg>",
          "endereco": {
            "cep": "<cep>",
            "cidade": "<cidade>",
            "uf": "<uf>",
            "rua": "<rua>",
            "numero": "<numero>",
            "bairro": "<bairroDoador>",
            "complemento": "<complemento>"
             },
          "contato": {
            "email":"<email>",
            "redeSocial": "<redeSocial>",
            "telefones": [
              "<telefones>"
              ]
            }
        }
    """
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/doador"
    When eu envio a requisição POST
    Then Http response should be 202

    Examples:
      | nomeDoador | cpfCnpj        | rg           | cep       | cidade    | uf | rua                                 | numero | bairroDoador         | complemento | email                  | redeSocial | telefones   |
      | Leonardo   | 858.008.780-22 | 69.994.268-4 | 24930-520 | Boa Vista | RR | Rua Vereador Manoel Joaquim Martins | 42     | Senador Hélio Campos | compl1      | leonardo@doador.com.br | @leonardo  | 11913345678 |
      | cassio     | 441.105.430-96 | 11.602.286-4 | 59607-819 | Mossoró   | RN | Rua Antônio Maciel de Lima          | 43     | Aeroporto            | compl2      | cassio@doador.com.br   | @cassio    | 11912445678 |
      | marcia     | 050.631.770-91 | 19.885.057-8 | 52040-081 | Recife    | PE | 1ª Travessa Marquês de Baipendi     | 152    | Campo Grande         | compl3      | marcia@doador.com.br   | @marcia    | 11912355678 |
      | hellen     | 058.831.860-91 | 22.386.269-3 | 24930-520 | Maricá    | RJ | Rua das Orquídeas                   | 17     | Centro               | compl4      | hellen@doador.com.br   | @hellen    | 11912475678 |
      | paulo      | 499.180.340-36 | 31.720.549-9 | 24930-520 | Maricá    | RJ | Rua das Orquídeas                   | 8      | Centro               | compl5      | paulo@doador.com.br    | @paulo     | 11912115678 |


  @Positivo
    @Test
  Scenario Outline: Adminsitrador Edita Doador
    Given que eu uso o json
    """
        {
        "id": <id>,
          "nome": "<nomeDoador>",
          "cpfCnpj": "<cpfCnpj>",
          "rg": "<rg>",
          "endereco": {
            "cep": "<cep>",
            "cidade": "<cidade>",
            "uf": "<uf>",
            "rua": "<rua>",
            "numero": "<numero>",
            "bairro": "<bairroDoador>",
            "complemento": "<complemento>"
             },
          "contato": {
            "email":"<email>",
            "redeSocial": "<redeSocial>",
            "telefones": [
              "<telefones>"
              ]
            }
        }
    """
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/doador"
    When eu envio a requisição PUT
    Then Http response should be 200

    Examples:
      | id  | nomeDoador | cpfCnpj        | rg           | cep       | cidade    | uf | rua                                 | numero | bairroDoador | complemento | email                  | redeSocial | telefones   |
      | 128 | Leonardo   | 858.008.780-22 | 69.994.268-4 | 24930-521 | Boa Vista | RR | Rua Vereador Manoel Joaquim Martins | 42     | Centro       | compl1      | leonardo@doador.com.br | @leonardo  | 11913345678 |


  @Positivo
    @Test
  Scenario Outline: Administrador recupera doador
    Given que eu queira pesquisar por um doador
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/doador/<id>"
    When eu envio a requisição GET
    Then Http response should be 200

    Examples:
      | id  |  |
      | 128 |  |

  @Positivo
  @Test
  Scenario: Administrador Lista doadores
    Given que eu queira listar todos os doadores
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/doador"
    When eu envio a requisição GET
    Then Http response should be 200

  @Positivo
    @Test
  Scenario Outline: Deletar doador
    Given que eu queira deletar um doador
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/doador/<id>"
    When eu envio a requisição DELETE
    Then Http response should be 204

    Examples:
      | id  |  |
      | 137 |  |

  @Negativo
  @Test

  Scenario: Salvar doador sem JSON
    Given que eu uso o json
    """

    """
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/doador"
    When eu envio a requisição POST
    Then Http response should be 400

#  @Negativo
#    @Test
#  Scenario Outline: Salvar doador com dados inválidos
#    Given que eu uso o json
#    """
#        {
#        "id": <id>,
#          "nome": "<nomeDoador>",
#          "cpfCnpj": "<cpfCnpj>",
#          "rg": "<rg>",
#          "endereco": {
#            "cep": "<cep>",
#            "cidade": "<cidade>",
#            "uf": "<uf>",
#            "rua": "<rua>",
#            "numero": "<numero>",
#            "bairro": "<bairroDoador>",
#            "complemento": "<complemento>"
#             },
#          "contato": {
#            "email":"<email>",
#            "redeSocial": "<redeSocial>",
#            "telefones": [
#              "<telefones>"
#              ]
#            }
#        }
#    """
#    And executo os testes no endpoint "DEVELOPMENT"
#    And uso a rota "/doador"
#    When eu envio a requisição POST
#    Then Http response should be 400
#
#    Examples:
#      | nomeDoador | cpfCnpj        | rg           | cep       | cidade    | uf | rua                                 | numero | bairroDoador         | complemento | email                  | redeSocial | telefones   |
#      | *****      | 858.008.780-22 | 69.994.268-4 | 24930-520 | Boa Vista | RR | Rua Vereador Manoel Joaquim Martins | 42     | Senador Hélio Campos | compl1      | leonardo@doador.com.br | @leonardo  | 11913345678 |
#      | cassio     | ******         | 11.602.286-4 | 59607-819 | Mossoró   | RN | Rua Antônio Maciel de Lima          | 43     | Aeroporto            | compl2      | cassio@doador.com.br   | @cassio    | 11912445678 |
#      | marcia     | 050.631.770-91 | *****        | 52040-081 | Recife    | PE | 1ª Travessa Marquês de Baipendi     | 152    | Campo Grande         | compl3      | marcia@doador.com.br   | @marcia    | 11912355678 |
#      | hellen     | 058.831.860-91 | 22.386.269-3 | ****      | ***       | ** | ***                                 | **     | Centro               | compl4      | hellen@doador.com.br   | @hellen    | 11912475678 |
#      | paulo      | 499.180.340-36 | 31.720.549-9 | 24930-520 | Maricá    | RJ | Rua das Orquídeas                   | 8      | ***                  | compl5      | paulo@doador.com.br    | @paulo     | 11912115678 |
#      | cassio     | 441.105.430-96 | 11.602.286-4 | 59607-819 | Mossoró   | RN | Rua Antônio Maciel de Lima          | 43     | Aeroporto            | ***         | cassio@doador.com.br   | @cassio    | 11912445678 |
#      | marcia     | 050.631.770-91 | 19.885.057-8 | 52040-081 | Recife    | PE | 1ª Travessa Marquês de Baipendi     | 152    | Campo Grande         | compl3      | marcia****             | @marcia    | 11912355678 |
#      | hellen     | 058.831.860-91 | 22.386.269-3 | 24930-520 | Maricá    | RJ | Rua das Orquídeas                   | 17     | Centro               | compl4      | hellen@doador.com.br   | ***        | 11912475678 |
#      | paulo      | 499.180.340-36 | 31.720.549-9 | 24930-520 | Maricá    | RJ | Rua das Orquídeas                   | 8      | Centro               | compl5      | paulo@doador.com.br    | @paulo     | ****        |

#
#  @Negativo
#    @Test
#  Scenario Outline: Editar doador com dados inválidos
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
#    And uso a rota "/doador/<id>"
#    When eu envio a requisição PUT
#    Then Http response should be 400
#
#
#    Examples:
#      | id  | nome   | email            | CPF            | RG  | CEP       | Cidade | UF | Rua               | numero | bairro | complemento |  |  |
#      | 117 | Ingrid | ingrid@teste.com | 058.831.860-91 | abc | 24930-520 | Maricá | RJ | Rua das Orquídeas | as     | Centro |             |  |  |

  @Positivo
    @Test
  Scenario Outline: Recuperar doador com id inexistente
    Given que eu queira pesquisar por um doador
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/doador/<id>"
    When eu envio a requisição GET
    Then Http response should be 404

    Examples:
      | id     |  |
      | 999999 |  |

  @Positivo
    @Test
  Scenario Outline: Deletar doador com id inválido
    Given que eu queira deletar um doador
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/doador/<id>"
    When eu envio a requisição DELETE
    Then Http response should be 404

    Examples:
      | id    |  |
      | 99999 |  |