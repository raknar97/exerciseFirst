package com.capgemini.user.repository.specifications.predicate;

import com.capgemini.user.repository.entity.UserEntity;
import com.capgemini.user.repository.specifications.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PredicateApply {
    private final List<PredicateStrategy> predicates;

    @Autowired
    public PredicateApply(List<PredicateStrategy> predicates) {
        this.predicates = predicates;
    }

    public List<Predicate> searchCriteria(List<SearchCriteria> searchCriteria, Root<UserEntity> root, CriteriaBuilder builder) {
        return searchCriteria.stream()
                .flatMap(s -> predicates.stream().filter(p -> p.apply(s)).map(p -> p.predicate(root, builder, s)))
                .collect(Collectors.toList());
    }
}
