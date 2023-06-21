package com.capgemini.user.service;

import com.capgemini.user.service.model.User;
import com.capgemini.user.service.model.UserFilter;

import java.util.List;

public interface UserService {

    User create(User user);

    User getById(Long id);

    List<User> filter (UserFilter filter);

    void updateUserById(Long id, User user);

    void updateExistingUser(Long id, User user);

    void deleteById(Long id);
}
