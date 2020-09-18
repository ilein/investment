package com.gdtest.investment.service;

import com.gdtest.investment.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    /**
     * Создает нового клиента
     * @param client
     * @return - возвращает клиента
     */
    Client create(Client client);

    /**
     * Читает всех клиентов
     * @return - возвращает список всех клиентов
     */
    List<Client> readAll();

    /**
     * Читает всех клиентов c пагинацией
     * @return - возвращает список всех клиентов
     */
    List<Client> readAllOrdered(Integer pageNo, Integer pageSize, String sortBy, String direction);

    /**
     * Читает всех клиентов с пагинацией и фильтром
     * @return - возвращает список всех клиентов
     */
    List<Client> readAllFilter(Integer pageNo, Integer pageSize, String sortBy, String direction, String search);

    /**
     * Возвращает клиента по ID
     * @param id - идентификатор клиента
     * @return - клиент
     */
    Optional<Client> read(int id);

    /**
     * Обновление информации по клиенту
     * @param id
     * @return
     */
    boolean update(Client client, int id);

    /**
     * Удаляет клиента по id
     * @param id
     * @return
     */
    boolean delete(int id);

}
