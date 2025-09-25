package dev.java10x.CadastroDeNinjas.Missoes;

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
    public MissoesModel criarMissao(@RequestBody MissoesModel missao){
        return missoesService.criaMissao(missao);
    }

    @GetMapping("/listar")
    public List<MissoesModel> listarMissoes(){
        return missoesService.listarMissoes();
    }

    @GetMapping("/listar/{id}")
    public MissoesModel listarMissaoPorId(@PathVariable Long id){
        return missoesService.listarMissaoPorId(id);
    }

    @PutMapping("/atualizar")
    public String atualizarMissao(){
        return "Missão atualizada";
    }

    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missão deletada";
    }
}
