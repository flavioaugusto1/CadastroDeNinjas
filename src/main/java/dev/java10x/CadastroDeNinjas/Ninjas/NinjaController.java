package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cria um novo ninja", description = "Rota para criar um novo ninja e inserir no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja")
    })
    public ResponseEntity<String> cadastrarNinja(
            @Parameter(description = "Recebe o ninja que com o usuário deseja cadastrar")
            @RequestBody NinjaDTO ninja
    ){
        NinjaDTO novoNinja = ninjaService.cadastraNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ninja criado com sucesso. Id: " + novoNinja.getId());
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas", description = "Rota para listagem de todos os ninjas cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninjas listados com sucesso")
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> listaDeNinjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(listaDeNinjas);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Listar ninja por ID", description = "Essa rota lista um ninja específico baseado no ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O ninja foi localizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "O ninja informado não foi encontrado")
    })
    public ResponseEntity<String> listarNinjaPorId(
            @Parameter(description = "Recebe o ID do ninja na rota da requisição")
            @PathVariable Long id
    ){
        NinjaDTO ninjaPorId = ninjaService.listarNinjaPorID(id);

        if(ninjaPorId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja não foi encontrado");
        }

        return ResponseEntity.ok("Ninja encontrado. Nome do ninja: " + ninjaPorId.getNome());
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar ninja", description = "Rota utilizada para atualização de ninja baseado no id e dados passados no corpo da requisição")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "O Ninja foi atualizado com sucesso"),
           @ApiResponse(responseCode = "404", description = "O ninja com o ID informado não foi encontrado")
   })
    public ResponseEntity<String> atualizarNinja(
            @Parameter(description = "Recebe na rota o ID do ninja que deseja atualizar")
            @PathVariable Long id,
            @Parameter(description = "Recebe um JSON no corpo da requisição os dados do ninja que serão atualizadas")
            @RequestBody NinjaDTO ninjaAtualizado){
        NinjaDTO ninjaExistente = ninjaService.listarNinjaPorID(id);

        if(ninjaExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja que deseja atualizar não foi encontrado");
        }

        ninjaService.atualizarNinja(id, ninjaAtualizado);

        return ResponseEntity.ok("O ninja de ID" + ninjaExistente.getId() + " foi atualizdao com sucesso");
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar ninja", description = "Rota utilizada para deletar ninja através do ID de um ninja existente")
    public ResponseEntity<String> deletarNinja(
            @Parameter(description = "Recebe na rota o ID do ninja que deseja deletar")
            @PathVariable Long id
    ){
        NinjaDTO ninjaExistente = ninjaService.listarNinjaPorID(id);

        if(ninjaExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Ninja informado não foi encontrado.");
        }

        ninjaService.deletarNinja(id);
        return ResponseEntity.ok("Ninja de id " + ninjaExistente.getId() + " deletado com sucesso");
    }

}
