package br.co.zup.bootcamp.reservaquartos.quarto;


import br.co.zup.bootcamp.reservaquartos.reverva.NovaReservaRequest;
import br.co.zup.bootcamp.reservaquartos.reverva.Reserva;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class ReservarUmQuartoController {

    private final QuartoRepository repository;

    public ReservarUmQuartoController(QuartoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/quartos/{id}/reservas")
    @Transactional
    public ResponseEntity<?> reservar(@PathVariable Long id, @RequestBody @Valid NovaReservaRequest request, UriComponentsBuilder uriComponentsBuilder) {

        Quarto quarto = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Quarto não encontrado."));

        Reserva reserva = request.paraReserva(quarto);

        quarto.adicionar(reserva);



        repository.save(quarto);

        URI location = uriComponentsBuilder.path("/quartos/{id}/reservas/{idReserva}")
                .buildAndExpand(quarto.getId(), reserva.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
