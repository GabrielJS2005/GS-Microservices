package gabrielJose556695.AegisOrbit.services;

import gabrielJose556695.AegisOrbit.dto.EventoAlertaDTO;
import gabrielJose556695.AegisOrbit.entities.EventoAlerta;
import gabrielJose556695.AegisOrbit.entities.RegiaoMonitorada;
import gabrielJose556695.AegisOrbit.exceptions.DatabaseException;
import gabrielJose556695.AegisOrbit.exceptions.ResourceNotFoundException;
import gabrielJose556695.AegisOrbit.repositories.EventoAlertaRepository;
import gabrielJose556695.AegisOrbit.repositories.RegiaoMonitoradaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoAlertaService {

    @Autowired
    private EventoAlertaRepository eventoAlertaRepository;

    @Autowired
    private RegiaoMonitoradaRepository regiaoMonitoradaRepository;

    @Transactional(readOnly = true)
    public List<EventoAlertaDTO> findAllAlertas() {

        return (eventoAlertaRepository.findAll().stream().map(EventoAlertaDTO::new)
                .toList());

    }

    @Transactional(readOnly = true)
    public EventoAlertaDTO findAlertaById(Long id) {

        EventoAlerta eventoAlerta = eventoAlertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id));

        return (new EventoAlertaDTO(eventoAlerta));

    }

    @Transactional
    public EventoAlertaDTO saveEventoAlerta(EventoAlertaDTO eventoAlertaDTO) {

        try {

            EventoAlerta eventoAlerta = new EventoAlerta();
            mapDtoToEventoAlerta(eventoAlertaDTO, eventoAlerta);
            eventoAlerta = eventoAlertaRepository.save(eventoAlerta);

            return (new EventoAlertaDTO(eventoAlerta));

        } catch (DataIntegrityViolationException e) {

            throw new DatabaseException("Não foi possível salvar o alerta. Região Monitorada inexistente. ID: "
                    + eventoAlertaDTO.getRegiao().getId());

        }

    }

    @Transactional
    public EventoAlertaDTO updateEventoAlerta(Long id, EventoAlertaDTO eventoAlertaDTO) {

        try {

            EventoAlerta eventoAlerta = eventoAlertaRepository.getReferenceById(id);
            mapDtoToEventoAlerta(eventoAlertaDTO, eventoAlerta);
            eventoAlerta = eventoAlertaRepository.save(eventoAlerta);

            return (new EventoAlertaDTO(eventoAlerta));

        } catch (EntityNotFoundException e) {

            throw new ResourceNotFoundException("Recurso não Encontrado. ID: " + id);

        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteEventoAlertaById(Long id) {

        if (!eventoAlertaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);

        }

        try {

            eventoAlertaRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {

            throw new DatabaseException("Não foi possível excluir a Região Monitorada. Existem Alertas associados a ela");

        }

    }

    private void mapDtoToEventoAlerta(EventoAlertaDTO eventoAlertaDTO, EventoAlerta eventoAlerta) {

        eventoAlerta.setTipoEvento(eventoAlertaDTO.getTipoEvento());
        eventoAlerta.setSeveridade(eventoAlertaDTO.getSeveridade());
        eventoAlerta.setImpactoEstimado(eventoAlertaDTO.getImpactoEstimado());
        eventoAlerta.setDataObservacao(eventoAlertaDTO.getDataObservacao());

        RegiaoMonitorada regiaoMonitorada = regiaoMonitoradaRepository
                .getReferenceById(eventoAlertaDTO.getRegiao().getId());

        eventoAlerta.setRegiao(regiaoMonitorada);

    }

}
