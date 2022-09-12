package br.co.zup.bootcamp.reservaquartos.quarto;

import br.co.zup.bootcamp.reservaquartos.reverva.Reserva;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Quarto {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;

    @Column(nullable = false, length =  200)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal precoDiaria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoQuarto tipo;

    @Column(nullable = false)
    private boolean reservado=false;

    @OneToMany(mappedBy = "quarto", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Reserva> reservas = new ArrayList<>();

    @Version
    private int versao;

    public Quarto(String descricao, BigDecimal precoDiaria, TipoQuarto tipo){
        this.descricao=descricao;
        this.precoDiaria=precoDiaria;
        this.tipo=tipo;
    }

    @Deprecated
    public Quarto(){
    }

    public Long getId(){
        return id;
    }

    public void adicionar(Reserva reserva){
        this.reservas.add(reserva);
    }

    public boolean isReservado(){
        return reservado;
    }

    public void reservar(){
        this.reservado=true;
    }
}
