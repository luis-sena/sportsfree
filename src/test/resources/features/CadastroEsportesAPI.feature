Feature: Criacao de Cursos
  Eu como professor da plataforma
  quero cadastrar aulas no site
  para disponibilizar aos alunos interessados


  @Positivo
    @Test
  Scenario Outline: Salvar esporte
    Given que eu uso o json
    """
        {
          "nome": "<nome>",
          "descricao": "<descricao>",
          "urlImagem": "<urlImagem>"
        }
    """
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/esporte"
    When eu envio a requisição POST
    Then Http response should be 202

    Examples:
      | nome        | descricao                                                                                                                                                                                                         | urlImagem                                                                 |
#      | Atletismo   | Atletismo é um conjunto de esportes constituído por varias modalidades: corrida, marcha, lançamentos e saltos. De modo geral, o atletismo é praticado em estádios, com exceção de algumas corridas de longa distância, praticadas em vias públicas ou no campo, como a maratona. | https://image.freepik.com/fotos-gratis/velocista-masculino-pronto-para-correr_342744-606.jpg |
#      | Futsal      | O futsal, também chamado de futebol de salão, é um esporte coletivo semelhante ao futebol de campo, porém possui suas peculiaridades.                                                                                                                                            | https://static.todamateria.com.br/upload/fu/ts/futsal_sorocaba_1.jpg                         |
      | Basquetebol | O basquetebol, ou simplesmente basquete, é um esporte coletivo praticado entre duas equipes. Ele é jogado com uma bola, onde o objetivo é inseri-la no cesto fixo que está localizado nas extremidades da quadra. | https://static.todamateria.com.br/upload/ba/sq/basquetebolpartida-cke.jpg |

  @Positivo
    @Test
  Scenario Outline: Editar esporte
    Given que eu uso o json
    """
        {
          "id": <id>,
          "nome": "<nome>",
          "descricao": "<descricao>",
          "urlImagem": "<urlImagem>"
        }
    """
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/esporte"
    When eu envio a requisição PUT
    Then Http response should be 200

    Examples:
      | id  | nome      | descricao                                                                                                                                                                                                                                                                        | urlImagem                                                                                                                                                              |
#      | 120 | Atletismo | Atletismo é a prática esportiva mais antiga, que é conhecida como esporte-base. Isso porque as suas modalidades compreendem os movimentos mais comuns para as pessoas desde a Antiguidade: corrida, lançamentos e saltos.                                                        | https://image.freepik.com/fotos-gratis/silhueta-do-homem-que-corre-correndo-na-estrada-fit-corredor-de-fitness-masculino-durante-o-exercicio-ao-ar-livre_38335-402.jpg |
      | 120 | Atletismo | Atletismo é um conjunto de esportes constituído por varias modalidades: corrida, marcha, lançamentos e saltos. De modo geral, o atletismo é praticado em estádios, com exceção de algumas corridas de longa distância, praticadas em vias públicas ou no campo, como a maratona. | https://image.freepik.com/fotos-gratis/silhueta-do-homem-que-corre-correndo-na-estrada-fit-corredor-de-fitness-masculino-durante-o-exercicio-ao-ar-livre_38335-402.jpg |


  @Positivo
    @Test
  Scenario Outline: Recuperar esporte
    Given que eu queira pesquisar por um esporte
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/esporte/<id>"
    When eu envio a requisição GET
    Then Http response should be 200

    Examples:
      | id  |  |
      | 120 |  |

  @Positivo
  @Test
  Scenario: Lista esportes
    Given que eu queira listar todos os esportes
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/esporte"
    When eu envio a requisição GET
    Then Http response should be 200

  @Positivo
    @Test
  Scenario Outline: Deletar esporte
    Given que eu queira deletar um esporte
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/esporte/<id>"
    When eu envio a requisição DELETE
    Then Http response should be 204

    Examples:
      | id  |  |
      | 120 |  |

  @Negativo
  @Test
  Scenario: Salvar esporte sem JSON
    Given que eu uso o json
    """

    """
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/esporte"
    When eu envio a requisição POST
    Then Http response should be 400

  @Negativo
    @Test
  Scenario Outline: Salvar esportes com dados inválidos
    Given que eu uso o json
    """
        {
          "id": <id>,
          "nome": "<nome>",
          "descricao": "<descricao>",
          "urlImagem": "<urlImagem>"
        }
    """
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/esporte"
    When eu envio a requisição POST
    Then Http response should be 400

    Examples:
      | nome      | descricao                                                                                                                                                                                                                                                                        | urlImagem                                                                                    |
      | &*&¨¨     | Atletismo é um conjunto de esportes constituído por varias modalidades: corrida, marcha, lançamentos e saltos. De modo geral, o atletismo é praticado em estádios, com exceção de algumas corridas de longa distância, praticadas em vias públicas ou no campo, como a maratona. | https://image.freepik.com/fotos-gratis/velocista-masculino-pronto-para-correr_342744-606.jpg |
      | Atletismo | ¨@@@@                                                                                                                                                                                                                                                                            | https://image.freepik.com/fotos-gratis/velocista-masculino-pronto-para-correr_342744-606.jpg |
      | Atletismo | Atletismo é um conjunto de esportes constituído por varias modalidades: corrida, marcha, lançamentos e saltos. De modo geral, o atletismo é praticado em estádios, com exceção de algumas corridas de longa distância, praticadas em vias públicas ou no campo, como a maratona. | imagem                                                                                       |


  @Negativo
    @Test
  Scenario Outline: Editar esporte com dados inválidos
    Given que eu uso o json
    """
        {
          "id": <id>,
          "nome": "<nome>",
          "descricao": "<descricao>",
          "urlImagem": "<urlImagem>"
        }
    """
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/esporte/<id>"
    When eu envio a requisição PUT
    Then Http response should be 400


    Examples:
      | id  | nome      | descricao     | urlImagem |
      | 111 | Atletismo | 7899......... | 78787878  |

  @Negativo
    @Test
  Scenario Outline: Recuperar esporte com id inexistente
    Given que eu queira pesquisar por um professor
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/esporte/<id>"
    When eu envio a requisição GET
    Then Http response should be 404

    Examples:
      | id     |  |
      | 999999 |  |

  @Positivo
    @Test
  Scenario Outline: Deletar esporte com id inexistente
    Given que eu queira deletar todos os professores
    And executo os testes no endpoint "DEVELOPMENT"
    And uso a rota "/esporte/<id>"
    When eu envio a requisição DELETE
    Then Http response should be 404

    Examples:
      | id    |  |
      | 99999 |  |