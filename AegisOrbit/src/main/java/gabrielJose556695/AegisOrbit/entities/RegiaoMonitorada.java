package gabrielJose556695.AegisOrbit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_evento_alerta")
public class RegiaoMonitorada {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_regiao")
    private Long id;

    @Column(name = "nome_regiao", nullable = false, length = 100)
    private String nomeRegiao;

    @Column(name = "continente", nullable = false, length = 80)
    private String continente;

    @Column(name = "tipo_area", nullable = false, length = 80)
    private String tipoArea;

    @Column(name = "extensao_km2", nullable = false)
    private Double extensaoKm2;

    @OneToMany(mappedBy = "regiao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EventoAlerta> eventos;


}// class
