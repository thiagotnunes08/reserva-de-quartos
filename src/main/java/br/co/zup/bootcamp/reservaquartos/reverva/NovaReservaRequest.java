package br.co.zup.bootcamp.reservaquartos.reverva;

import br.co.zup.bootcamp.reservaquartos.quarto.Quarto;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class NovaReservaRequest {

    @NotBlank
    private String reservadoPara;

    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkin;
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkout;

    public NovaReservaRequest(String reservadoPara, LocalDate checkin, LocalDate checkout) {
        this.reservadoPara = reservadoPara;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public NovaReservaRequest() {
    }

    public String getReservadoPara() {
        return reservadoPara;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public Reserva paraReserva(Quarto quarto) {
        if (quarto.isReservado()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);

        }
        return new Reserva(reservadoPara,quarto,checkin,checkout);
    }
}