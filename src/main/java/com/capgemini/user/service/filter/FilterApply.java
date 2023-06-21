package com.capgemini.user.service.filter;

import com.capgemini.user.repository.specifications.SearchCriteria;
import com.capgemini.user.service.model.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilterApply {


    private final List<FilterStrategy> strategies;

    @Autowired
    public FilterApply(List<FilterStrategy> strategies) {
        this.strategies = strategies;
    }

    public List<SearchCriteria> searchCriteria(UserFilter userFilter) {
        return strategies.stream()
                .filter(e -> e.apply(userFilter))
                .map(e -> e.search(userFilter))
                .collect(Collectors.toList());
    }
}
