package com.capgemini.user.service.filter;

import com.capgemini.user.repository.specifications.OperationCriteria;
import com.capgemini.user.repository.specifications.SearchCriteria;
import com.capgemini.user.service.model.UserFilter;
import org.springframework.stereotype.Component;

@Component
public class FilterNameStrategy implements FilterStrategy {

    @Override
    public SearchCriteria search(UserFilter userFilter) {
        return new SearchCriteria("name", OperationCriteria.LIKE, userFilter.getName());
    }

    @Override
    public boolean apply(UserFilter userFilter) {
        return userFilter.getName() != null;
    }
}
