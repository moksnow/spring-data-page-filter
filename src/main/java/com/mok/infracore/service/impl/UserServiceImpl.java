package com.mok.infracore.service.impl;

import com.mok.infracore.domain.User;
import com.mok.infracore.domain.pagination.PaginationCriteria;
import com.mok.infracore.domain.pagination.SortBy;
import com.mok.infracore.domain.pagination.SortOrder;
import com.mok.infracore.repository.UserRepository;
import com.mok.infracore.repository.specification.SearchCriteria;
import com.mok.infracore.repository.specification.SearchOperation;
import com.mok.infracore.repository.specification.UserSpecification;
import com.mok.infracore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> findAll(PaginationCriteria paginationCriteria, int pageNumber) {
        Pageable pageRequest;

        SortBy sorts = paginationCriteria.getSortBy();
        UserSpecification userSpecification = null;

        if (paginationCriteria.getFilterBy().getMapOfFilters().size() > 0) {
            userSpecification = new UserSpecification();
            String value = paginationCriteria.getFilterBy().getMapOfFilters().entrySet().iterator().next().getValue();
            if (isNumeric(value)) {
                userSpecification.add(new SearchCriteria("id", Double.parseDouble(value), SearchOperation.EQUAL));
                userSpecification.add(new SearchCriteria("age", Double.parseDouble(value), SearchOperation.EQUAL));
            } else {
                userSpecification.add(new SearchCriteria("firstname", value, SearchOperation.MATCH));
                userSpecification.add(new SearchCriteria("lastname", value, SearchOperation.MATCH));
            }

        }

        String keySorterField = "id";
        String sortMethod = SortOrder.DESC.value();

        for (Map.Entry<String, SortOrder> entry : sorts.getSortBys().entrySet()) {
            keySorterField = entry.getKey();
            sortMethod = entry.getValue().value();
        }

        if (sortMethod.equals(SortOrder.ASC.value())) {
            pageRequest = PageRequest.of(pageNumber, paginationCriteria.getPageSize(), Sort.by(keySorterField).ascending());

        } else {
            pageRequest = PageRequest.of(pageNumber, paginationCriteria.getPageSize(), Sort.by(keySorterField).descending());

        }

        Page<User> applicationLogEntityPage;

        if (userSpecification != null)
            applicationLogEntityPage = userRepository.findAll(userSpecification, pageRequest);
        else
            applicationLogEntityPage = userRepository.findAll(pageRequest);


        return applicationLogEntityPage;
    }

    /**
     * check value is number or not
     * @param strNum input parameter for check
     * @return true / false
     */
    private boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
