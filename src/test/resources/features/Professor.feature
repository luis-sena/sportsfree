Feature: Professores

  Scenario Outline: Criar cadastro a partir da tela de login de Documento já existente

    Given que eu como professor acesse o site SportFree
    When eu clico no botão de login
    Then sou redirecionado para a tela de login
    When clico no botão clique aqui para não cadastrados
    Then sou redirecionado para a tela de cadastro Novo Cliente
    And Na tela de cadastro eu preencho "<tipoPessoa>", "<Nome>", "<Documento>", "<RG>", "<Telefone1>", "<Telefone2>", "<email>", "<CEP>", "<NumeroComplemento>", "<RedeSocial>", "<Observacoes>"
    And eu escolho o perfil profissional
    And eu clico no botão salvar
    Then o sistema retorna a mensagem de documento já existente.

    Examples:
      |  |  | tipoPessoa | Nome        | Documento      | RG          | Telefone1   | Telefone2   | email                      | CEP       | NumeroComplemento | RedeSocial | Observacoes   |
      |  |  | PF         | Professor 1 | 111.111.111-11 | 11.111.11-1 | 11987563214 | 11987563278 | professor@professor.com.br | 11111-111 | 11                | @Professor | OBS Professor |




  Scenario Outline: Cancelar um cadastro de professor em andamento
    Given que eu como professor acesse o site SportFree
    When eu clico no botão de login
    Then sou redirecionado para a tela de login
    When clico no botão clique aqui para não cadastrados
    Then sou redirecionado para a tela de cadastro Novo Cliente
    And Na tela de cadastro eu preencho "<tipoPessoa>", "<Nome>", "<Documento>", "<RG>", "<Telefone1>", "<Telefone2>", "<email>", "<CEP>", "<NumeroComplemento>", "<RedeSocial>", "<Observacoes>"
    And eu escolho o perfil profissional
    And eu clico no botão cancelar
    Then sou redirecionado para a tela home do site

    Examples:
      | loginADM | senhaADM | tipoPessoa | Nome        | Documento      | RG          | Telefone1   | Telefone2   | email                      | CEP       | NumeroComplemento | RedeSocial | Observacoes   |
      | PR123    | 123456   | PF         | Professor 1 | 111.111.111-11 | 11.111.11-1 | 11987563214 | 11987563278 | professor@professor.com.br | 11111-111 | 11                | @Professor | OBS Professor |


  Scenario: professor cancela um curso que oferece
    Given que eu como professor acesse o site SportFree
    When eu clico no botão de login
    Then sou redirecionado para a tela de login
    And insiro meu "<loginADM>" e "<senhaADM>"
    And clico no botão login para entrar
    Then sou redirecionado para a tela de perfil de professor
    And clico no botão Sim desejo cancelar
    Then sou redirecionado para a tela de grade horária dos meus cursos
    When eu clico no botao cancelar da linha do curso "<cursoParaCancelar>"
    And confirmo no botão confirmar do popup
    Then o curso é cancelado e o sistema retorna mensagem de curso cancelado com sucesso.
