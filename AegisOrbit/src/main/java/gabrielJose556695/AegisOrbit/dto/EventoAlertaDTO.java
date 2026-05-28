package gabrielJose556695.AegisOrbit.dto;

import gabrielJose556695.AegisOrbit.entities.EventoAlerta;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EventoAlertaDTO {

    private Long id;

    @NotBlank(message = "O campo tipo evento é requerido")
    @Size(min = 5, max = 150, message = "O tipo de evento deve ter entre 5 a 150 caracteres")
    private String tipoEvento;

    @NotNull(message = "A severidade é requerida")
    @Positive(message = "A severidade deve ser um numero positivo")
    private Integer severidade;

    @NotNull(message = "O impacto estimado é requerido")
    @Positive(message = "O impacto estimado deve ser um número positivo")
    private Double impactoEstimado;

    @NotNull(message = "Data de observação é requerida")
    @FutureOrPresent(message = "A data da observação deve ser presente ou futura")
    private LocalDate dataObservacao;

    @NotNull(message = "O campo região monitorada é requerido")
    private RegiaoMonitoradaDTO regiao;

    public EventoAlertaDTO(EventoAlerta eventoAlerta) {

        id = eventoAlerta.getId();
        tipoEvento = eventoAlerta.getTipoEvento();
        severidade = eventoAlerta.getSeveridade();
        impactoEstimado = eventoAlerta.getImpactoEstimado();
        dataObservacao = eventoAlerta.getDataObservacao();
        regiao = new RegiaoMonitoradaDTO(eventoAlerta.getRegiao());

    }

}// class
