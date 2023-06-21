package com.capgemini.user.controller.mapper;

import com.capgemini.user.controller.model.UserFilterModelApi;
import com.capgemini.user.controller.model.UserModelApi;
import com.capgemini.user.service.model.User;
import com.capgemini.user.service.model.UserFilter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserControllerMapper {

    User map(UserModelApi userModelApi);

    UserModelApi map(User user);

    UserFilterModelApi map(UserFilter userFilter);

    UserFilter map(UserFilterModelApi userFilterModelApi);

    List<UserModelApi> map(List<User> userList);
}
