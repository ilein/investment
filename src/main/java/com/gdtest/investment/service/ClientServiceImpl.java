package com.gdtest.investment.service;

import com.gdtest.investment.dao.ClientDao;
import com.gdtest.investment.model.Client;
import com.gdtest.investment.specification.ClientSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientDao clientDao;

    @Override
    public Client create(Client client) {
        return this.clientDao.save(client);
    }

    @Override
    public List<Client> readAll() {
        return this.clientDao.findAll();
    }

    @Override
    public List<Client> readAllOrdered(Integer pageNo,
                                       Integer pageSize,
                                       String sortBy,
                                       String direction) {

        Pageable paging = Utils.getPaging(pageSize, pageNo, sortBy, direction);

        Page<Client> pagedResult = clientDao.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Client> readAllFilter(Integer pageNo, Integer pageSize, String sortBy, String direction, String search) {
        Pageable paging = Utils.getPaging(pageSize, pageNo, sortBy, direction);
        Page<Client> pagedResult;
        if (!"".equals(search)) {
            Specification<Client> spec = Specification.where(
                    ClientSpecification.nameContain(search))
                    .or(ClientSpecification.shortNameContain(search))
                    .or(ClientSpecification.addressContain(search))
                    .or(ClientSpecification.legalFormEqual(search));


            pagedResult = clientDao.findAll(spec, paging);
        }
        else {
            pagedResult = clientDao.findAll(paging);
        }
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Client> read(int id) {
        return this.clientDao.findById(id);
    }

    @Override
    public boolean update(Client client, int id) {
        if (this.clientDao.existsById(id)) {
            client.setId(id);
            clientDao.save(client);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (this.clientDao.existsById(id)) {
            this.clientDao.deleteById(id);
            return true;
        }
        return false;
    }
}
