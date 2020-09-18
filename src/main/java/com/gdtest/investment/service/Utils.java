package com.gdtest.investment.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Utils {

    public static Sort getSort(String sortBy, String direction) {
        Sort sort;
        switch (direction.toLowerCase()) {
            case "desc":
                sort = Sort.by(sortBy).descending();
                break;
            case "asc":
                sort = Sort.by(sortBy).ascending();
                break;
            default:
                sort = Sort.unsorted();
        }
        return sort;
    }

    public static Pageable getPaging(Integer pageSize, Integer pageNo, String sortBy, String direction) {
        Sort sort = Utils.getSort(sortBy, direction);
        return PageRequest.of(pageNo, pageSize, sort);
    }
}
