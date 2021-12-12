package learn.sfg.sfgtestmockito.repositories;


import learn.sfg.sfgtestmockito.model.Owner;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
