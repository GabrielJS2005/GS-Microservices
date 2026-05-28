package gabrielJose556695.AegisOrbit.dto;

import gabrielJose556695.AegisOrbit.entities.RegiaoMonitorada;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegiaoMonitoradaDTO {

    private Long id;

    @NotBlank(message = "O nome da Região é requerido")
    @Size(min = 3, max = 120, message = "O nome da região deve ter entre 3 a 100 caracteres")
    private String nomeRegiao;

    @NotBlank(message = "O nome do continente é requerido")
    @Size(min = 5, max = 150, message = "O nome do continete deve ter entre 5 a 150 caracteres")
    private String continente;

    @NotBlank(message = "O tipo da área é requerido")
    @Size(min = 5, max = 150, message = "O tipo da área deve ter entre 5 a 150 caracteres")
    private String tipoArea;

    @NotNull(message = "A extensão é requerida")
    @Positive(message = "A estensão deve ser um número positivo")
    private Double extensaoKm2;

    public RegiaoMonitoradaDTO(RegiaoMonitorada regiaoMonitorada) {

        id = regiaoMonitorada.getId();
        nomeRegiao = regiaoMonitorada.getNomeRegiao();
        continente = regiaoMonitorada.getContinente();
        tipoArea = regiaoMonitorada.getTipoArea();
        extensaoKm2 = regiaoMonitorada.getExtensaoKm2();

    }

}// class
