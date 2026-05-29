package gabrielJose556695.AegisOrbit.controller;

import gabrielJose556695.AegisOrbit.dto.EventoAlertaDTO;
import gabrielJose556695.AegisOrbit.services.EventoAlertaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alertas")
public class EventoAlertaController {

    @Autowired
    private EventoAlertaService eventoAlertaService;

    @GetMapping
    public ResponseEntity<List<EventoAlertaDTO>> getAll() {

        List<EventoAlertaDTO> dto = eventoAlertaService.findAllAlertas();

        return (ResponseEntity.ok(dto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoAlertaDTO> getOneById(@PathVariable Long id) {

        EventoAlertaDTO dto = eventoAlertaService.findAlertaById(id);

        return (ResponseEntity.ok(dto));

    }

    @PostMapping
    public ResponseEntity<EventoAlertaDTO> createEventoAlerta(@Valid @RequestBody EventoAlertaDTO dto) {

        dto = eventoAlertaService.saveEventoAlerta(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return (ResponseEntity.created(uri).body(dto));

    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoAlertaDTO> updateEventoAlerta(@PathVariable Long id,
                                                                      @Valid @RequestBody EventoAlertaDTO dto) {

        dto = eventoAlertaService.updateEventoAlerta(id, dto);

        return (ResponseEntity.ok(dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventoAlerta(@PathVariable Long id) {

        eventoAlertaService.deleteEventoAlertaById(id);

        return (ResponseEntity.noContent().build());

    }

}//class
