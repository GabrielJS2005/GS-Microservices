package gabrielJose556695.AegisOrbit.controller;

import gabrielJose556695.AegisOrbit.dto.RegiaoMonitoradaDTO;
import gabrielJose556695.AegisOrbit.services.RegiaoMonitoradaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/regioes")
public class RegiaoMonitoradaController {

    @Autowired
    private RegiaoMonitoradaService regiaoMonitoradaService;

    @GetMapping
    public ResponseEntity<List<RegiaoMonitoradaDTO>> getAll() {

        List<RegiaoMonitoradaDTO> dto = regiaoMonitoradaService.findAllByRegioes();

        return (ResponseEntity.ok(dto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<RegiaoMonitoradaDTO> getOneById(@PathVariable Long id) {

        RegiaoMonitoradaDTO dto = regiaoMonitoradaService.findRegiaoById(id);

        return (ResponseEntity.ok(dto));

    }

    @PostMapping
    public ResponseEntity<RegiaoMonitoradaDTO> createRegiaoMonitorada(@Valid @RequestBody RegiaoMonitoradaDTO dto) {

        dto = regiaoMonitoradaService.saveRegiaoMonitorada(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return (ResponseEntity.created(uri).body(dto));

    }

    @PutMapping("/{id}")
    public ResponseEntity<RegiaoMonitoradaDTO> updateRegiaoMonitorada(@PathVariable Long id,
                                                                      @Valid @RequestBody RegiaoMonitoradaDTO dto) {

        dto = regiaoMonitoradaService.updateRegiaomonitorada(id, dto);

        return (ResponseEntity.ok(dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegiaoMonitorada(@PathVariable Long id) {

        regiaoMonitoradaService.deleteregiaoMonitoradaById(id);

        return (ResponseEntity.noContent().build());

    }

}// class
