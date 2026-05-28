package gabrielJose556695.AegisOrbit.services;

import gabrielJose556695.AegisOrbit.dto.RegiaoMonitoradaDTO;
import gabrielJose556695.AegisOrbit.entities.RegiaoMonitorada;
import gabrielJose556695.AegisOrbit.exceptions.DatabaseException;
import gabrielJose556695.AegisOrbit.exceptions.ResourceNotFoundException;
import gabrielJose556695.AegisOrbit.repositories.RegiaoMonitoradaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegiaoMonitoradaService {

    @Autowired
    private RegiaoMonitoradaRepository regiaoMonitoradaRepository;

    @Transactional(readOnly = true)
    public List<RegiaoMonitoradaDTO> findAllByRegioes() {

        return (regiaoMonitoradaRepository.findAll().stream().map(RegiaoMonitoradaDTO::new)
                .toList());

    }

    @Transactional(readOnly = true)
    public RegiaoMonitoradaDTO findRegiaoById(Long id) {

        RegiaoMonitorada regiaoMonitorada = regiaoMonitoradaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id));

        return (new RegiaoMonitoradaDTO(regiaoMonitorada));

    }

    @Transactional
    public RegiaoMonitoradaDTO saveRegiaoMonitorada(RegiaoMonitoradaDTO regiaoMonitoradaDTO) {

        RegiaoMonitorada regiaoMonitorada = new RegiaoMonitorada();
        mapDtoToRegiaoMonitorada(regiaoMonitoradaDTO, regiaoMonitorada);
        regiaoMonitorada = regiaoMonitoradaRepository.save(regiaoMonitorada);

        return (new RegiaoMonitoradaDTO(regiaoMonitorada));

    }

    @Transactional
    public RegiaoMonitoradaDTO updateRegiaomonitorada(Long id, RegiaoMonitoradaDTO regiaoMonitoradaDTO) {

        try {

            RegiaoMonitorada regiaoMonitorada = regiaoMonitoradaRepository.getReferenceById(id);
            mapDtoToRegiaoMonitorada(regiaoMonitoradaDTO, regiaoMonitorada);
            regiaoMonitorada = regiaoMonitoradaRepository.save(regiaoMonitorada);

            return (new RegiaoMonitoradaDTO(regiaoMonitorada));

        } catch (EntityNotFoundException e) {

            throw new ResourceNotFoundException("Recurso não Encontrado. ID: " + id);

        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteregiaoMonitoradaById(Long id) {

        if (!regiaoMonitoradaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);

        }

        try {

            regiaoMonitoradaRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {

            throw new DatabaseException("Não foi possível excluir a Região Monitorada. Existem Alertas associados a ela");

        }

    }

    private void mapDtoToRegiaoMonitorada(RegiaoMonitoradaDTO regiaoMonitoradaDTO, RegiaoMonitorada regiaoMonitorada) {

        regiaoMonitorada.setNomeRegiao(regiaoMonitoradaDTO.getNomeRegiao());
        regiaoMonitorada.setContinente(regiaoMonitoradaDTO.getContinente());
        regiaoMonitorada.setTipoArea(regiaoMonitoradaDTO.getTipoArea());
        regiaoMonitorada.setExtensaoKm2(regiaoMonitoradaDTO.getExtensaoKm2());

    }

}// class
