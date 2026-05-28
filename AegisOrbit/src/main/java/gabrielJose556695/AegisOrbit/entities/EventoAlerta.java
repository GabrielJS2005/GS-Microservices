package gabrielJose556695.AegisOrbit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_evento_alerta")
public class EventoAlerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long id;

    @Column(name = "tipo_evento", nullable = false, length = 80)
    private String tipoEvento;

    @Column(name = "severidade", nullable = false)
    private Integer severidade;

    @Column(name = "impacto_estimado", nullable = false)
    private Double impactoEstimado;

    @Column(name = "data_observacao", nullable = false)
    private LocalDate dataObservacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_regiao", nullable = false)
    private RegiaoMonitorada regiao;

}
