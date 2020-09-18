package com.gdtest.investment.service;

import com.gdtest.investment.model.Bank;
import com.gdtest.investment.model.Client;

import java.util.List;
import java.util.Optional;

public interface BankService {
    /**
     * Создает новый банк
     * @param bank - банк для создания
     */
    Bank create(Bank bank);

    /**
     * Возвращает список всех имеющихся банков
     * @return список клиентов
     */
    List<Bank> readAll();

    /**
     * Читает все банки с пагинацией и фильтром
     * @return - возвращает список всех клиентов
     */
    List<Bank> readAllFilter(Integer pageNo, Integer pageSize, String sortBy, String direction, String search);


    /**
     * Возвращает банк по его ID
     * @param id - ID бинка
     * @return - объект банк с заданным ID
     */
    Optional<Bank> read(int id);

    /**
     * Обновляет банк с заданным ID,
     * в соответствии с переданным банком
     * @param bank - банк в соответсвии с которым нужно обновить данные
     * @param id - id банка который нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Bank bank, int id);

    /**
     * Удаляет банк с заданным ID
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(int id);
}
