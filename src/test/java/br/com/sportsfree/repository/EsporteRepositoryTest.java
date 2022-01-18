package br.com.sportsfree.repository;

import br.com.sportsfree.entity.DoadorEntity;
import br.com.sportsfree.entity.EsporteEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

import static br.com.sportsfree.utils.EsporteTesteUtils.criarEsporteEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@EnableJpaRepositories(repositoryBaseClass = AbstractRepositoryImpl.class)
@EntityScan(basePackageClasses = EsporteEntity.class)
class EsporteRepositoryTest {
 
    @Autowired
    private EsporteRepository repository;

    @Test
    @DisplayName("Deve salvar Um esporte")
    void deveSalvarUmEsporte() {
        EsporteEntity esporte = criarEsporteEntity();

        EsporteEntity esporteSalvo = this.repository.save(esporte);

        assertThat(esporteSalvo).isNotNull();
        assertThat(esporteSalvo.getId()).isNotNull();
        assertThat(esporteSalvo.getNome()).isEqualTo(esporte.getNome());
        assertThat(esporteSalvo.getDescricao()).isEqualTo(esporte.getDescricao());
        assertThat(esporteSalvo.getUrlImagem()).isEqualTo(esporte.getUrlImagem());

    }

    @Test
    @DisplayName("Deve atualizar Um esporte")
    void deveAtualizarUmEsporte() {
        EsporteEntity esporte = criarEsporteEntity();

        EsporteEntity esporteSalvo = this.repository.save(esporte);

        esporteSalvo.setNome("Esporte Atualizado");
        esporteSalvo.setDescricao("Descrição atualizada");
        esporteSalvo.setUrlImagem("https://urlimagem.com");

        EsporteEntity esporteAtualizado = this.repository.save(esporteSalvo);

        assertThat(esporteAtualizado).isNotNull();
        assertThat(esporteAtualizado.getId()).isNotNull();
        assertThat(esporteAtualizado.getNome()).isEqualTo(esporteSalvo.getNome());
        assertThat(esporteAtualizado.getDescricao()).isEqualTo(esporteSalvo.getDescricao());
        assertThat(esporteAtualizado.getUrlImagem()).isEqualTo(esporteSalvo.getUrlImagem());
    }

    @Test
    @DisplayName("Deve deletar Um esporte")
    void deveDeletarUmEsporte() {
        EsporteEntity esporte = criarEsporteEntity();

        EsporteEntity esporteSalvo = this.repository.save(esporte);

        this.repository.delete(esporteSalvo);

        Optional<EsporteEntity> optionalEsporte = this.repository.findById(esporteSalvo.getId());
        assertThat(optionalEsporte).isEmpty();
    }

    @Test
    @DisplayName("Deve recuperar Um esporte")
    void deveRecuperarUmEsporte() {
        EsporteEntity esporte = criarEsporteEntity();
        EsporteEntity esporteSalvo = this.repository.save(esporte);
        Long id = esporteSalvo.getId();

        Optional<EsporteEntity> optionalEsporte = this.repository.findById(id);

        assertThat(optionalEsporte)
                .isPresent()
                .contains(esporte);
    }

    @Test
    @DisplayName("Deve recuperar uma lista de esportes")
    void deveRecuperarUmaListaDeEsportes() {
        EsporteEntity esporte = criarEsporteEntity();
        this.repository.save(esporte);

        List<EsporteEntity> esportes = this.repository.findAll();

        assertThat(esportes)
                .isNotEmpty()
                .contains(esporte);
    }
    
}
