package br.org.ala.api.repository;

import br.org.ala.api.model.Pessoa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<List<Pessoa>> findByNomeContaining(String nome);

}
