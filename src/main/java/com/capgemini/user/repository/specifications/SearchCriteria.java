package com.capgemini.user.repository.specifications;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private OperationCriteria operation;
    private Object value;
}
