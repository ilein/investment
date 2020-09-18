package com.gdtest.investment.service;

import com.gdtest.investment.model.Client;
import com.gdtest.investment.model.Investment;

import java.util.List;
import java.util.Optional;

public interface InvestmentService {

    /**
     * Создание вклада
     * @param investment
     * @return
     */
    Investment create(Investment investment);

    /**
     * Список всех вкладов
     * @return
     */
    List<Investment> readAll();

    /**
     * Читает всех вкладов с пагинацией и фильтром
     * @return - возвращает список всех клиентов
     */
    List<Investment> readAllFilter(Integer pageNo, Integer pageSize, String sortBy, String direction, String search);


    /**
     * Получить вклад
     * @return
     */
    Optional<Investment> read(int id);

    /**
     * Изменение вклада по id
     * @param id
     * @return
     */
    boolean update(Investment investment, int id);

    /**
     * Удаление вклада по id
     * @param id
     * @return
     */
    boolean delete(int id);
}
