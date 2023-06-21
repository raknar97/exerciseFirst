package com.capgemini.user.repository.specifications;

import com.capgemini.user.repository.entity.UserEntity;
import com.capgemini.user.repository.specifications.predicate.PredicateApply;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class UserSpecification implements Specification<UserEntity> {
    private final List<SearchCriteria> criterias = new ArrayList<>();
    private final PredicateApply predicateApply;

    public UserSpecification(PredicateApply predicateApply) {
        this.predicateApply=predicateApply;
    }

    public void addSearchCriterias(List<SearchCriteria> criterias) {
        this.criterias.addAll(criterias);
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = predicateApply.searchCriteria(criterias,root,builder);
        return builder.and(predicates.toArray(new Predicate[0]));
    }

}
