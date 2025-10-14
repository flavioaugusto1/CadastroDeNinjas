package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<MissoesDTO> criarMissao(@RequestBody MissoesDTO missaoDTO){
        MissoesDTO missao = missoesService.criaMissao(missaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(missao);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        List<MissoesDTO> missoesCadastradas = missoesService.listarMissoes();
        return ResponseEntity.ok(missoesCadastradas);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoPorId(@PathVariable Long id){
        MissoesDTO missaoPorId = missoesService.listarMissaoPorId(id);

        if (missaoPorId == null) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missão informada não foi encontrada");
        }

        return ResponseEntity.ok(missaoPorId);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarMissao(@PathVariable Long id, @RequestBody MissoesDTO missaoAtualizada){
        MissoesDTO missaoPorId = missoesService.listarMissaoPorId(id);

        if (missaoPorId != null) {
            MissoesDTO missaoAposAtualizacao = missoesService.atualizarMissao(id, missaoAtualizada);
            return ResponseEntity.ok(missaoAposAtualizacao);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missão informada não foi encontrada.");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarMissao(@PathVariable Long id){
        MissoesDTO missaoPorId = missoesService.listarMissaoPorId(id);

        if (missaoPorId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missão informada não foi encontrada");
        }

        missoesService.deletarMissao(id);

        return ResponseEntity.ok("Missão deletada com sucesso");
    }
}
