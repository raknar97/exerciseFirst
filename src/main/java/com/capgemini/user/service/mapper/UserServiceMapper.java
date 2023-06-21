package com.capgemini.user.service.mapper;

import com.capgemini.user.repository.entity.UserEntity;
import com.capgemini.user.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE ,componentModel = "spring")
public interface UserServiceMapper {
    UserEntity map(User user);

    User map(UserEntity userEntity);

    List<User> map(List<UserEntity> userEntities);

    UserEntity map(User user, Long id);

    UserEntity merge(@MappingTarget UserEntity target, User source);
}
