package com.suresh.bookstore.catlog.domain;

import java.util.List;

public record PagedResult<T>(
        List<T> data,
        Long totalElements,
        int pageNumber,
        int totalPages,
        boolean isFirst,
        boolean isLast,
        boolean hasNext,
        boolean hasPrevious) {}
