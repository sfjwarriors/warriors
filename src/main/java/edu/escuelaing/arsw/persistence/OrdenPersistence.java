package edu.escuelaing.arsw.persistence;

import edu.escuelaing.arsw.model.Orden;
import org.springframework.data.repository.CrudRepository;

public interface OrdenPersistence extends CrudRepository<Orden, Long> {
}
