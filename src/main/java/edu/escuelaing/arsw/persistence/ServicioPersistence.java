package edu.escuelaing.arsw.persistence;

import edu.escuelaing.arsw.model.Servicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioPersistence extends CrudRepository<Servicio, Long> {
    public List<Servicio> findByStatusAndFkStoreService(String status, Long fkStoreService);

}
