package br.co.zup.bootcamp.reservaquartos.quarto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface QuartoRepository extends JpaRepository<Quarto,Long> {


}
