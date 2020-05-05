package br.org.ala.api.repository;

import br.org.ala.api.model.Cidade;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends CrudRepository<Cidade, Long> {

    List<Cidade> findByEstadoId(Integer id);

}
