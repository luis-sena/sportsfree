#noinspection GherkinBrokenTableInspection
Feature: Criacao de Cursos
  Eu como professor da plataforma
  quero cadastrar aulas no site
  para disponibilizar aos alunos interessados

  Scenario Outline: Criar uma aula nova com professor ainda não cadastrado no site
    Given que eu como professor acesse o site SportFree
    When eu clico no menu cadastro
    Then sou redirecionado para a tela de cadastro Novo Cliente
    And Na tela de cadastro eu preencho "<tipoPessoa>", "<Nome>", "<Documento>", "<RG>", "<Telefone1>", "<Telefone2>", "<email>", "<CEP>", "<NumeroComplemento>", "<RedeSocial>", "<Observacoes>"
    And eu escolho o perfil profissional
    And eu clico no botão salvar
    Then sou redirecionado para a tela de login
    And insiro meu "<login>" e "<senha>"
    And clico no botão Login
    Then sou redirecionado para a tela de perfil de professor
    And clico no botão Sim, desejo oferecer um curso
    Then sou redirecionado para a tela de grade horária de cursos ofertados
    And preencho os campos "<curso>", "<horario>", "<local>", "<quantidadeVagas>"
    And clico no botão Cadastrar novo curso
    Then uma nova linha na grade com o "<curso>" e "<horario>" e exibida

    Examples:
      | curso   | horario | local        | quantidadeVagas |
      | natacao | 07:00   | multi Sports | 5               |


  Scenario Outline: Criar um curso novo com professor já cadastrado no site
    Given que eu como professor acesse o site SportFree
    When eu clico no botão de login
    Then sou redirecionado para a tela de login
    And insiro meu "<login>" e "<senha>"
    And clico no botão Login
    Then sou redirecionado para a tela de perfil de professor
    And clico no botão Sim, desejo oferecer um curso
    Then sou redirecionado para a tela de grade horária de cursos ofertados
    And preencho os campos "<curso>", "<horario>", "<local>", "<quantidadeVagas>"
    And clico no botão Cadastrar novo curso
    Then uma nova linha na grade com o "<curso>" e "<horario>" e exibida

    Examples:
      | login | senha  | curso   | horario | local        | quantidadeVagas |  |
      | PR123 | 123456 | natacao | 07:00   | multi Sports | 5               |  |


  Scenario Outline: Criar um curso novo com professor já cadastrado via modalidade de esporte
    Given que eu como professor acesse o site SportFree
    When eu clico na modalidade de esporte "<esporte>"
    Then sou redirecionado para a tela do esporte escolhido
    And clico no botão Seja um Professor
    Then sou redirecionado para a tela de login
    And insiro meu "<login>" e "<senha>"
    And clico no botão Login
    Then sou redirecionado para a tela de perfil de professor
    And clico no botão Sim, desejo oferecer um curso
    Then sou redirecionado para a tela de grade horária de cursos ofertados
    And preencho os campos "<curso>", "<horario>", "<local>", "<quantidadeVagas>"
    And clico no botão Cadastrar novo curso
    Then uma nova linha na grade com o "<curso>" e "<horario>" e exibida


    Examples:
      | login | senha  | curso   | horario | local        | quantidadeVagas | esporte |
      | PR123 | 123456 | natacao | 07:00   | multi Sports | 5               | natacao |
