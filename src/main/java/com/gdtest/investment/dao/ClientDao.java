package com.gdtest.investment.dao;

import com.gdtest.investment.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client> {
}
