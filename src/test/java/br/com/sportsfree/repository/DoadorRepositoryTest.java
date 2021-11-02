package br.com.sportsfree.repository;

import br.com.sportsfree.entity.DoadorEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static br.com.sportsfree.utils.DoadorTesteUtils.criarDoadorEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DoadorRepositoryTest {

    @Autowired
    private DoadorRepository repository;

    @Test
    @DisplayName("Deve salvar Um doador")
    void deveSalvarUmDoador() {
        DoadorEntity doador = criarDoadorEntity();

        DoadorEntity doadorSalvo = this.repository.save(doador);

        assertThat(doadorSalvo).isNotNull();
        assertThat(doadorSalvo.getId()).isNotNull();
        assertThat(doadorSalvo.getNome()).isEqualTo(doador.getNome());
        assertThat(doadorSalvo.getCpfCnpj()).isEqualTo(doador.getCpfCnpj());
        assertThat(doadorSalvo.getRg()).isEqualTo(doador.getRg());
        assertThat(doadorSalvo.getContato()).isEqualTo(doador.getContato());
        assertThat(doadorSalvo.getEndereco()).isEqualTo(doador.getEndereco());
    }

    @Test
    @DisplayName("Deve atualizar Um doador")
    void deveAtualizarUmDoador() {
        DoadorEntity doador = criarDoadorEntity();

        DoadorEntity doadorSalvo = this.repository.save(doador);

        doadorSalvo.setNome("Doador Atualizado");

        DoadorEntity doadorAtualizado = this.repository.save(doadorSalvo);

        assertThat(doadorAtualizado).isNotNull();
        assertThat(doadorAtualizado.getId()).isNotNull();
        assertThat(doadorAtualizado.getNome()).isEqualTo(doadorSalvo.getNome());
        assertThat(doadorAtualizado.getCpfCnpj()).isEqualTo(doadorSalvo.getCpfCnpj());
        assertThat(doadorAtualizado.getRg()).isEqualTo(doadorSalvo.getRg());
        assertThat(doadorAtualizado.getContato()).isEqualTo(doadorSalvo.getContato());
        assertThat(doadorAtualizado.getEndereco()).isEqualTo(doadorSalvo.getEndereco());
    }

    @Test
    @DisplayName("Deve deletar Um doador")
    void deveDeletarUmDoador() {
        DoadorEntity doador = criarDoadorEntity();

        DoadorEntity doadorSalvo = this.repository.save(doador);

        this.repository.delete(doadorSalvo);

        Optional<DoadorEntity> optionalDoador = this.repository.findById(doadorSalvo.getId());
        assertThat(optionalDoador).isEmpty();
    }

    @Test
    @DisplayName("Deve recuperar Um doador")
    void deveRecuperarUmDoador() {
        DoadorEntity doador = criarDoadorEntity();
        DoadorEntity doadorSalvo = this.repository.save(doador);
        Long id = doadorSalvo.getId();

        Optional<DoadorEntity> optionalDoador = this.repository.findById(id);

        assertThat(optionalDoador)
                .isPresent()
                .contains(doador);
    }

    @Test
    @DisplayName("Deve recuperar uma lista de doadores")
    void deveRecuperarUmaListaDeDoadores() {
        DoadorEntity doador = criarDoadorEntity();
        this.repository.save(doador);

        List<DoadorEntity> doadores = this.repository.findAll();

        assertThat(doadores)
                .isNotEmpty()
                .contains(doador);
    }

}