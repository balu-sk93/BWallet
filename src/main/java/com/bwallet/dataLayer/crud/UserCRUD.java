package com.bwallet.dataLayer.crud;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bwallet.dataLayer.model.Userdetails;

@Repository
public interface UserCRUD extends CrudRepository<Userdetails, Long> {

}
