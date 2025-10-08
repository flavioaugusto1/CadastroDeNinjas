package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<NinjaDTO> listarNinjas(){
       List<NinjaModel> ninjas = ninjasRepository.findAll();
       return ninjas.stream()
               .map(ninjaMapper::map)
               .collect(Collectors.toList());

    }

    public NinjaDTO listarNinjaPorID(Long id){
        Optional<NinjaModel> ninjaPorId = ninjasRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO){
        Optional<NinjaModel> ninjaExistente = ninjasRepository.findById(id);

        if(ninjaExistente.isPresent()){
            NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO);
            ninjaAtualizado.setId(id);
            NinjaModel ninjaSalvo = ninjasRepository.save(ninjaAtualizado);

            return  ninjaMapper.map(ninjaSalvo);
        }

        return null;
    }

    public void deletarNinja(Long id){
        ninjasRepository.deleteById(id);
    }
}
