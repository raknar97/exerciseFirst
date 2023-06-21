package com.capgemini.user.repository.specifications.predicate;

import com.capgemini.user.repository.entity.UserEntity;
import com.capgemini.user.repository.specifications.SearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface PredicateStrategy {

    boolean apply(SearchCriteria searchCriteria);
    Predicate predicate(Root<UserEntity> root, CriteriaBuilder builder, SearchCriteria criteria);

}
