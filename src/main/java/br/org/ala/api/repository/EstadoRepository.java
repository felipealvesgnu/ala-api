package br.org.ala.api.repository;

import br.org.ala.api.model.Estado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Integer> {

}
