package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    @PostMapping("/cadastrar")
    public String criarNinja(){
        return "Ninja criado";
    }

    @GetMapping("/listar")
    public String listarNinjas(){
        return "Ninjas";
    }

    @GetMapping("/listarPorId")
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
