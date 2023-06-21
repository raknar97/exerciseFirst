package com.capgemini.user.service;

import com.capgemini.user.repository.UserRepository;
import com.capgemini.user.repository.entity.UserEntity;
import com.capgemini.user.repository.specifications.UserSpecification;
import com.capgemini.user.repository.specifications.predicate.PredicateApply;
import com.capgemini.user.service.exception.UserNotFoundException;
import com.capgemini.user.service.exception.UserUnexpectedException;
import com.capgemini.user.service.filter.FilterApply;
import com.capgemini.user.service.mapper.UserServiceMapper;
import com.capgemini.user.service.model.User;
import com.capgemini.user.service.model.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserServiceMapper userServiceMapper;

    private final FilterApply filterApply;
    private  final PredicateApply predicateApply;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserServiceMapper userServiceMapper, FilterApply filterApply, PredicateApply predicateApply) {
        this.userRepository = userRepository;
        this.userServiceMapper = userServiceMapper;
        this.filterApply = filterApply;
        this.predicateApply = predicateApply;
    }

    @Override
    public User create(User user) {
        try {
            UserEntity userEntity = userServiceMapper.map(user);
            UserEntity createdEntity = userRepository.save(userEntity);
            return userServiceMapper.map(createdEntity);
        } catch (DataAccessException dataAccessException) {
            throw new UserUnexpectedException(dataAccessException);
        }
    }

    @Override
    public User getById(Long id) {
        try {
            Optional<UserEntity> createdEntity = userRepository.findById(id);
            if (!createdEntity.isPresent()) {
                throw new UserNotFoundException("User Not found");
            }
            UserEntity userEntity = createdEntity.get();
            return userServiceMapper.map(userEntity);
        } catch (DataAccessException dataAccessException) {
            throw new UserUnexpectedException(dataAccessException);
        }
    }

    @Override
    public List<User> filter(UserFilter filter) {
        try {
            UserSpecification userSpecification = new UserSpecification(predicateApply);
            userSpecification.addSearchCriterias(filterApply.searchCriteria(filter));
            List<UserEntity> users = userRepository.findAll(userSpecification);
            return userServiceMapper.map(users);
        } catch (DataAccessException dataAccessException) {
            throw new UserUnexpectedException(dataAccessException);
        }
    }

    @Override
    @Transactional
    public void updateUserById(Long id, User user) {
        try {
            boolean existingUser = userRepository.existsById(id);
            if (!existingUser) {
                throw new UserNotFoundException("User Not found by this ID");
            }
            UserEntity newUser = userServiceMapper.map(user, id);
            userRepository.save(newUser);
        } catch (DataAccessException dataAccessException) {
            throw new UserUnexpectedException(dataAccessException);
        }
    }

    @Override
    @Transactional
    public void updateExistingUser(Long id, User user) {
        try {
            Optional<UserEntity> existingUser = userRepository.findById(id);
            if (!existingUser.isPresent()) {
                throw new UserNotFoundException("User Not found by this ID");
            }
            UserEntity finalUser = userServiceMapper.merge(existingUser.get(), user);
            userRepository.save(finalUser);
        } catch (DataAccessException dataAccessException) {
            throw new UserUnexpectedException(dataAccessException);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            boolean existingUser = userRepository.existsById(id);
            if (!existingUser) {
                throw new UserNotFoundException("User Not found by this ID");
            }
            userRepository.deleteById(id);
        } catch (DataAccessException dataAccessException) {
            throw new UserUnexpectedException(dataAccessException);
        }
    }
}
