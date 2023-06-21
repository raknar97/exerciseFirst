package com.capgemini.user.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFilter {
    private String name;
    private String surname;
    private String email;
}
