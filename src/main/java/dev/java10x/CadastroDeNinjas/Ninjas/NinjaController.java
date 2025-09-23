package dev.java10x.CadastroDeNinjas.Ninjas;

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
    public String criarNinja(){
        return "Ninja criado";
    }

    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
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
