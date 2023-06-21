package com.capgemini.user.service.filter;

import com.capgemini.user.repository.specifications.SearchCriteria;
import com.capgemini.user.service.model.UserFilter;

public interface FilterStrategy {
    SearchCriteria search(UserFilter userFilter);
    boolean apply(UserFilter userFilter);
}
