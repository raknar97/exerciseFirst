package com.capgemini.user.controller;

import com.capgemini.user.controller.mapper.UserControllerMapper;
import com.capgemini.user.controller.model.UserFilterModelApi;
import com.capgemini.user.controller.model.UserModelApi;
import com.capgemini.user.controller.util.FieldErrorUtil;
import com.capgemini.user.service.UserService;
import com.capgemini.user.service.model.User;
import com.capgemini.user.service.model.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserControllerMapper userControllerMapper;
    private final UserService userService;

    private final FieldErrorUtil fieldErrorUtil;


    @Autowired
    public UserController(UserControllerMapper userControllerMapper, UserService userService, FieldErrorUtil fieldErrorUtil) {
        this.userControllerMapper = userControllerMapper;
        this.userService = userService;
        this.fieldErrorUtil = fieldErrorUtil;
    }

    @PostMapping()
    public long create(@Valid @RequestBody UserModelApi userModelApi, BindingResult bindingResult) {
        fieldErrorUtil.checkErrors(bindingResult);
        User user = userControllerMapper.map(userModelApi);
        return userService.create(user).getId();
    }

    @GetMapping("/{id}")
    public UserModelApi getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userControllerMapper.map(user);
    }

    @GetMapping("/")
    public List<UserModelApi> findByFilter(UserFilterModelApi filter) {
        UserFilter userFilter = userControllerMapper.map(filter);
        List<User> userList = userService.filter(userFilter);
        List<UserModelApi> userModelApi = userControllerMapper.map(userList);
        return userModelApi;
    }

    @PutMapping("/{id}")
    public void updateUserById(@PathVariable Long id, @Valid @RequestBody UserModelApi userModelApi, BindingResult bindingResult) {
        fieldErrorUtil.checkErrors(bindingResult);
        User user = userControllerMapper.map(userModelApi);
        userService.updateUserById(id, user);
    }

    @PatchMapping("/{id}")
    public void updateExistingUser(@PathVariable Long id, @RequestBody UserModelApi userModelApi) {
        User user = userControllerMapper.map(userModelApi);
        userService.updateExistingUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteById(id);
    }

}
