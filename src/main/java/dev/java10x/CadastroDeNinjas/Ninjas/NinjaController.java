package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @PostMapping("/cadastrar")
    public String criarNinja(){
        return "Ninja criado";
    }

    @GetMapping("/listar")
    public String listarNinjas(){
        return "Ninjas";
    }

    @GetMapping("/listarID")
    public String listarNinjaPorId(){
        return "Ninja por ID";
    }

    @PutMapping("/atualizarID")
    public String atualizarNinja(){
        return "Ninja atualizado por ID";
    }

    @DeleteMapping("/deletarID")
    public String deletarNinja(){
        return "Ninja deletado por ID";
    }

}
