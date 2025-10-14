package dev.java10x.CadastroDeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cadastro de missões", description = "Rota para cadastro de missões")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão cadastrada com sucesso")
    })
    public ResponseEntity<MissoesDTO> criarMissao(
            @Parameter(description = "Essa rota recebe um JSON com os dados de cadastro da missão")
            @RequestBody MissoesDTO missaoDTO
    ){
        MissoesDTO missao = missoesService.criaMissao(missaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(missao);
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar missões cadastradas", description = "Rota para listagem de todas as missões cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missões listadas com sucesso")
    })
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        List<MissoesDTO> missoesCadastradas = missoesService.listarMissoes();
        return ResponseEntity.ok(missoesCadastradas);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Listar missão específica", description = "Rota para listagem de missão específica baseada no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não localizada")
    })
    public ResponseEntity<?> listarMissaoPorId(
            @Parameter(description = "Recebe o ID na rota da requisição")
            @PathVariable Long id
    ){
        MissoesDTO missaoPorId = missoesService.listarMissaoPorId(id);

        if (missaoPorId == null) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missão informada não foi encontrada");
        }

        return ResponseEntity.ok(missaoPorId);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar missão", description = "Rota para atualização de missões através do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "A missão não foi encontrada")
        }
    )
    public ResponseEntity<?> atualizarMissao(
            @Parameter(description = "Recebe o ID na rota da requisição")
            @PathVariable Long id,
            @Parameter(description = "Recebe um JSON no body na requisição com as informação para atualizar")
            @RequestBody MissoesDTO missaoAtualizada
    ){
        MissoesDTO missaoPorId = missoesService.listarMissaoPorId(id);

        if (missaoPorId != null) {
            MissoesDTO missaoAposAtualizacao = missoesService.atualizarMissao(id, missaoAtualizada);
            return ResponseEntity.ok(missaoAposAtualizacao);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missão informada não foi encontrada.");
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar missão", description = "Rota para deletar missão através do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não foi encontrada")
    })
    public ResponseEntity<?> deletarMissao(
            @Parameter(description = "Recebe na rota o ID da missão que deseja deletar")
            @PathVariable Long id
    ){
        MissoesDTO missaoPorId = missoesService.listarMissaoPorId(id);

        if (missaoPorId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missão informada não foi encontrada");
        }

        missoesService.deletarMissao(id);

        return ResponseEntity.ok("Missão deletada com sucesso");
    }
}
