package com.capgemini.user.controller.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFilterModelApi {
    private String name;
    private String surname;
    private String email;
}
