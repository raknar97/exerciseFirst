package com.capgemini.user.repository.specifications.predicate;

import com.capgemini.user.repository.entity.UserEntity;
import com.capgemini.user.repository.specifications.OperationCriteria;
import com.capgemini.user.repository.specifications.SearchCriteria;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
public class PredicateLike implements PredicateStrategy{

    @Override
    public boolean apply(SearchCriteria searchCriteria) {
        return searchCriteria.getOperation() == OperationCriteria.LIKE;
    }

    @Override
    public Predicate predicate(Root<UserEntity> root, CriteriaBuilder builder, SearchCriteria criteria) {
        return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
    }


}
