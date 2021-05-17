package gusi.springframework.springdata.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gusi.springframework.springdata.orm.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer>{

}
