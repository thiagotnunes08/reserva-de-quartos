package br.co.zup.bootcamp.reservaquartos.reverva;

import br.co.zup.bootcamp.reservaquartos.quarto.Quarto;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reservadoPara;

    @ManyToOne
    private Quarto quarto;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkin;
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkout;

    public Reserva(String reservadoPara, Quarto quarto, LocalDate checkin, LocalDate checkout) {
        this.reservadoPara = reservadoPara;
        this.quarto = quarto;
        this.checkin = checkin;
        this.checkout = checkout;
        this.quarto.reservar();

    }

    @Deprecated // uso interno da api
    public Reserva() {
    }

    public Long getId() {
        return id;
    }
}
