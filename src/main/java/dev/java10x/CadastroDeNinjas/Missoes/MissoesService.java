package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {
    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    public MissoesModel criaMissao(MissoesModel missao){
        return missoesRepository.save(missao);
    }

    public List<MissoesModel> listarMissoes(){
        return missoesRepository.findAll();
    }

    public MissoesModel listarMissaoPorId(Long id){
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id);
        return missaoPorId.orElse(null);
    }

    public void deletarMissao(Long id){
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id);

        if(missaoPorId.isPresent()){
            missoesRepository.deleteById(id);
        }
    }

    public MissoesModel atualizarMissao(Long id, MissoesModel missaoAtualizada){
        if(missoesRepository.existsById(id)){
            missaoAtualizada.setId(id);
            return missoesRepository.save(missaoAtualizada);
        }

        return null;
    }
}
