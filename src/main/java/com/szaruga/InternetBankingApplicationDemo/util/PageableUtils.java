package com.szaruga.InternetBankingApplicationDemo.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {
    public static Pageable buildPageable(int pageNumber, int pageSize, String sort) {
        if (sort != null) {
            return PageRequest.of(pageNumber, pageSize, Sort.DEFAULT_DIRECTION, sort);
        } else {
            return PageRequest.of(pageNumber, pageSize);
        }
    }
}