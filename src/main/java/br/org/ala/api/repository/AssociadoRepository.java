package br.org.ala.api.repository;

import br.org.ala.api.model.Associado;
import br.org.ala.api.model.Pessoa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    Page<Associado> findByNomeContaining(String nome, Pageable pageable);
    Optional<List<Associado>> findByAtivoTrue();
    Optional<List<Associado>> findByAtivoFalse();
}
