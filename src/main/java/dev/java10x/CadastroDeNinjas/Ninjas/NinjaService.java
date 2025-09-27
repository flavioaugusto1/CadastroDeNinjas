package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private NinjasRepository ninjasRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjasRepository ninjasRepository, NinjaMapper ninjaMapper) {
        this.ninjasRepository = ninjasRepository;
        this.ninjaMapper = ninjaMapper;
    }

    public NinjaDTO cadastraNinja(NinjaDTO ninjaDTO){
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjasRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    public List<NinjaModel> listarNinjas(){
        return ninjasRepository.findAll();
    }

    public NinjaModel listarNinjaPorID(Long id){
        Optional<NinjaModel> ninjaPorId = ninjasRepository.findById(id);
        return ninjaPorId.orElse(null);
    }

    public NinjaModel atualizarNinja(Long id, NinjaModel ninjaAtualizado){
        if (ninjasRepository.existsById(id)){
            ninjaAtualizado.setId(id);
            return ninjasRepository.save(ninjaAtualizado);
        }
        return null;
    }

    public void deletarNinja(Long id){
        ninjasRepository.deleteById(id);
    }
}
