package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja = ninjaService.cadastraNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ninja criado com sucesso. Id: " + novoNinja.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> listaDeNinjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(listaDeNinjas);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<String> listarNinjaPorId(@PathVariable Long id){
        NinjaDTO ninjaPorId = ninjaService.listarNinjaPorID(id);

        if(ninjaPorId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja não foi encontrado");
        }

        return ResponseEntity.ok("Ninja encontrado. Nome do ninja: " + ninjaPorId.getNome());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
        NinjaDTO ninjaExistente = ninjaService.listarNinjaPorID(id);

        if(ninjaExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja que deseja atualizar não foi encontrado");
        }

        ninjaService.atualizarNinja(id, ninjaAtualizado);

        return ResponseEntity.ok("O ninja de ID" + ninjaExistente.getId() + " foi atualizdao com sucesso");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinja(@PathVariable Long id){
        NinjaDTO ninjaExistente = ninjaService.listarNinjaPorID(id);

        if(ninjaExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Ninja informado não foi encontrado.");
        }

        ninjaService.deletarNinja(id);
        return ResponseEntity.ok("Ninja de id " + ninjaExistente.getId() + " deletado com sucesso");
    }

}
