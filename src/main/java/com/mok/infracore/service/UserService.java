package com.mok.infracore.service;

import com.mok.infracore.domain.User;
import com.mok.infracore.domain.pagination.PaginationCriteria;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<User> findAll(PaginationCriteria paginationCriteria, int pageNumber);

}
