package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    @PostMapping("/cadastrar")
    public String criarMissao(){
        return "Missão criada";
    }

    @GetMapping("/listar")
    public String listarMissoes(){
        return "Missões listadas";
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
