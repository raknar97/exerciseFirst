package com.capgemini.user.controller.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserModelApi {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String email;
}
