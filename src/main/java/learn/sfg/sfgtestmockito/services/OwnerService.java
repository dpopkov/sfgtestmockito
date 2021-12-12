package learn.sfg.sfgtestmockito.services;

import learn.sfg.sfgtestmockito.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
 }
