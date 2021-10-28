package br.com.sportsfree.controllers;

import br.com.sportsfree.dto.SportDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("sports")
public class SportsController {

    @GetMapping
    public ResponseEntity<Object> listAllSports(){
        
        //Criar uma camada Service ou Repository para carregar a lista de esportes
        List<SportDTO> listSportDTO = new ArrayList<SportDTO>();
        
        return new ResponseEntity<>(listSportDTO, HttpStatus.OK);
    }

    @GetMapping("/{sportId}")
    public ResponseEntity<SportDTO> findById(@PathVariable("sportId") Long sportId){
        SportDTO sportDTO = null; //service.findById(sportId);
        return new ResponseEntity<>(sportDTO, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody SportDTO sportDTO) {
        //service.save(sportDTO);
        return new ResponseEntity<>("Esporte Cadastrado com sucesso ou retornar o objeto?",
                HttpStatus.OK);
    }

    @PutMapping("/update/{sportId}")
    public ResponseEntity<SportDTO> update(@PathVariable("sportId") Long sportId, @RequestBody SportDTO sportDTO){
        //service.update(sportId, sportDTO);
        return new ResponseEntity<>(sportDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{sportId}")
    public ResponseEntity<Object> delete(@PathVariable("sportId") Long sportId) {

        String mensagem = "Não foi possivel excluir o Esporte com ID = " + sportId;

        if(Boolean.FALSE) { //service.delete(sportId)) {
            mensagem = "Esporte com ID = " + sportId + " excluido com sucesso";
        }
        
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

    
}
