package ie.mccormack.blygh.CRUD.domain;

import org.springframework.data.repository.CrudRepository;

//talks to DB, 
public interface RecordRepository extends CrudRepository<Record, Long> {
}
