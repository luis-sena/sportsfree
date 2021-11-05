Feature: Doador faz seu cadastro
  Como doador,
  Quero poder me cadastrar no site,
  Para fazer doações.

  Scenario Outline: Doação Financeira com Doador sem cadastro iniciando no menu Seja um doador
    Given que eu não tenha cadastro no site SportFree
    When eu clico no menu Seja um Doador
    And clico no botão Clique Aqui por não ter cadastro
    And Na tela de cadastro eu preencho "<tipoPessoa>", "<Nome>", "<Documento>", "<RG>", "<Telefone1>", "<Telefone2>", "<email>", "<CEP>", "<NumeroComplemento>", "<RedeSocial>", "<Observacoes>"
    And eu escolho o perfil Doador
    And eu clico no botão salvar
    Then sou redirecionado para a tela de login
    And insiro meu "<login>" e "<senha>"
    And clico no botão Login
    Then sou redirecionado para a tela de perfil Doador
    And clico no radio button Financeira
    Then sou redirecionado para a tela de efetuar doacao
    And preencho os campos "<Nome>", "<codIntituicao>", "<entidadeBeneficiada>", "<valorDoacao>", "<conferirValorDoacao>"
    And clico no botão Confirmar doacao
    Then recebo mensagem de sucesso

    Examples:
      | tipoPessoa | Nome     | Documento      | RG          | Telefone1   | Telefone2   | email                 | CEP       | NumeroComplemento | RedeSocial | Observacoes | login | senha  | codIntituicao | entidadeBeneficiada            | valorDoacao | conferirValorDoacao |  |
      | PF         | Doador 1 | 111.111.111-11 | 11.111.11-1 | 11987563214 | 11987563278 | doador@doador.com.br  | 11111-111 | 11                | @doador    | OBS Doador  | DO123 | 123456 | 789654        | Centro Esportivo de São Carlos | 1000.00     | 1000.00             |  |
      | PF         | Doador 2 | 222.111.111-11 | 11.222.11-1 | 11987563111 | 11987563222 | doador2@doador.com.br | 11111-222 | 22                | @doador2   | OBS Doador2 | DO456 | 123411 | 789622        | Centro Esportivo de São Carlos | 1000.00     | 2000.00             |  |

    Scenario Outline: Doador sem cadastro iniciando pelo menu Cadastro
      Given que eu não tenha cadastro no site SportFree
      When eu clico no menu cadastro
      Then sou redirecionado para a tela de cadastro Novo Cliente
      And Na tela de cadastro eu preencho "<tipoPessoa>", "<Nome>", "<Documento>", "<RG>", "<Telefone1>", "<Telefone2>", "<email>", "<CEP>", "<NumeroComplemento>", "<RedeSocial>", "<Observacoes>"
      And eu escolho o perfil Doador
      And eu clico no botão salvar
      Then sou redirecionado para a tela de login
      And insiro meu "<login>" e "<senha>"
      And clico no botão Login
      Then sou redirecionado para a tela de perfil Doador
      And clico no o radio button Equipamentos
      Then sou redirecionado para a tela de efetuar doacao
      And preencho os campos "<nome>", "<codIntituicao>", "<entidadeBeneficiada>", "<valorDoacao>", "<conferirValorDoacao>"
      And clico no botão Confirmar doacao
      Then recebo mensagem de sucesso

      Examples:
        | tipoPessoa | Nome     | Documento      | RG          | Telefone1   | Telefone2   | email                 | CEP       | NumeroComplemento | RedeSocial | Observacoes | login | senha  | codIntituicao | entidadeBeneficiada            | valorDoacao | conferirValorDoacao |  |
        | PF         | Doador 1 | 111.111.111-11 | 11.111.11-1 | 11987563214 | 11987563278 | doador@doador.com.br  | 11111-111 | 11                | @doador    | OBS Doador  | DO123 | 123456 | 789654        | Centro Esportivo de São Carlos | 1000.00     | 1000.00             |  |
        | PF         | Doador 2 | 222.111.111-11 | 11.222.11-1 | 11987563111 | 11987563222 | doador2@doador.com.br | 11111-222 | 22                | @doador2   | OBS Doador2 | DO456 | 123411 | 789622        | Centro Esportivo de São Carlos | 1000.00     | 2000.00             |  |

    Scenario Outline: Doador sem cadastro iniciando pelo login
      Given que eu não tenha cadastro no site SportFree
      When eu clico no botão de login
      Then sou redirecionado para a tela de login
      When clico no botão Clique Aqui por não ter cadastro
      Then sou redirecionado para a tela de cadastro Novo Cliente
      And Na tela de cadastro eu preencho "<tipoPessoa>", "<Nome>", "<Documento>", "<RG>", "<Telefone1>", "<Telefone2>", "<email>", "<CEP>", "<NumeroComplemento>", "<RedeSocial>", "<Observacoes>"
      And eu escolho o perfil Doador
      And eu clico no botão salvar
      Then sou redirecionado para a tela de login
      And insiro meu "<login>" e "<senha>"
      And clico no botão Login
      Then sou redirecionado para a tela de perfil Doador
      And clico no radio button Financeira
      Then sou redirecionado para a tela de efetuar doacao
      And preencho os campos "<nome>", "<codIntituicao>", "<entidadeBeneficiada>", "<valorDoacao>", "<conferirValorDoacao>"
      And clico no botão Confirmar doacao
      Then recebo mensagem de sucesso

      Examples:
        | tipoPessoa | Nome     | Documento      | RG          | Telefone1   | Telefone2   | email                 | CEP       | NumeroComplemento | RedeSocial | Observacoes | login | senha  | codIntituicao | entidadeBeneficiada            | valorDoacao | conferirValorDoacao |  |
        | PF         | Doador 1 | 111.111.111-11 | 11.111.11-1 | 11987563214 | 11987563278 | doador@doador.com.br  | 11111-111 | 11                | @doador    | OBS Doador  | DO123 | 123456 | 789654        | Centro Esportivo de São Carlos | 1000.00     | 1000.00             |  |
        | PF         | Doador 2 | 222.111.111-11 | 11.222.11-1 | 11987563111 | 11987563222 | doador2@doador.com.br | 11111-222 | 22                | @doador2   | OBS Doador2 | DO456 | 123411 | 789622        | Centro Esportivo de São Carlos | 1000.00     | 2000.00             |  |

   Scenario Outline:Doação de equipamentos com doador já cadastrado
     Given que eu tenha cadastro no site SportFree
     When eu clico no menu Seja um Doador
     Then sou redirecionado para a tela de login
     And insiro meu "<login>" e "<senha>"
     And clico no botão Login
     Then sou redirecionado para a tela de perfil Doador
     And clico no o radio button Equipamentos
     Then sou redirecionado para a tela de efetuar doacao
     And preencho os campos preencho os campos "<nome>", "<codIntituicao>", "<entidadeBeneficiada>", "<equipamento>", "<quantidade>"
     And clico no botão Confirmar doacao
     Then recebo mensagem de sucesso

     Examples:

       | login | senha  | nome    | codIntituicao | entidadeBeneficiada | equipamento | quantidade |
       | DO123 | 456987 | doador1 | 789           | Multi Sports        | bola        | 20         |