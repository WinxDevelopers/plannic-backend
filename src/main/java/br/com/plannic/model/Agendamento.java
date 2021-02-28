    package br.com.plannic.model;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.ToString;

    import javax.persistence.*;
    import java.time.Duration;
    import java.time.LocalTime;
    import java.time.temporal.ChronoUnit;
    import java.util.Date;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "agendamento")
    public class Agendamento {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idagendamento")
        private int idAgendamento;

        @Column(name = "idmateria")
        private int idMateria;

        @Column(name = "idusuario")
        private int idUsuario;

        @Column(name = "recorrenciainicio")
        private Date recorrenciaInicio;

        @Column(name = "recorrenciafim")
        private Date recorrenciaFim;

        @Column(name = "recorrencia")
        private String recorrencia;

        @Column(name = "tipoestudo")
        private String tipoEstudo;

        @Column(name = "horainicio")
        private LocalTime horaInicio;

        @Column(name = "horafim")
        private LocalTime horaFim;

        @ToString.Exclude
        @ManyToOne
        @JoinColumn(name = "idusuario", insertable = false, updatable = false)
        @JsonBackReference
        private Usuario usuario;

        public Agendamento(String tipoEstudo) {
            this.tipoEstudo = tipoEstudo;
        }

        public long getMinEstudo(){

            long semanas = Duration.between(recorrenciaFim.toInstant(), recorrenciaInicio.toInstant()).toDays() / 7;

            long diferencaMinutos = Duration.between(horaFim, horaInicio).toMinutes();

            long totalMinutos = diferencaMinutos * semanas;

            return totalMinutos;
        }
    }
