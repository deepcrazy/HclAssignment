package com.deepanshu.hclassignment.service.impl;

import com.deepanshu.hclassignment.controller.UserController;
import com.deepanshu.hclassignment.exception.ResourceNotFoundException;
import com.deepanshu.hclassignment.model.Address;
import com.deepanshu.hclassignment.model.User;
import com.deepanshu.hclassignment.repository.UserRepository;
import com.deepanshu.hclassignment.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Override
    @Transactional
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "getUserByEmpIdFallback")
    public User getUserByEmpId(long empId) {
        return userRepository.findByEmpId(empId).orElseThrow(() -> new ResourceNotFoundException("User", "EmpId", empId));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "updateUserByEmpIdFallback")
    public User updateUserByEmpId(long empId, User updateUser) {
        User existingUser = userRepository.findByEmpId(empId).orElseThrow(() -> new ResourceNotFoundException("User", "EmpId", empId));

        Address existingUserAddress = existingUser.getAddress();
        BeanUtils.copyProperties(updateUser, existingUser, getNullPropertyNames(updateUser));

        if (updateUser.getAddress() != null) {
            BeanUtils.copyProperties(updateUser.getAddress(), existingUserAddress, getNullPropertyNames(updateUser.getAddress()));
            existingUser.setAddress(existingUserAddress);
        }

        return userRepository.save(existingUser);
    }

    // Function to get property names having null or zero values to exclude null
    // and zero value properties while using BeanUtils.copyProperties method.
    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || (srcValue instanceof Long && ((Long) srcValue).intValue() == 0L))
                emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    // Fallback methods for Circuit Breaker
    private User getUserByEmpIdFallback(long empId, Throwable t) {
        logger.error("Fallback triggered for getUserByEmpId. Cause: {}", t.getMessage());
        throw new ResourceNotFoundException("User", "EmpId", empId);
    }

    private User updateUserByEmpIdFallback(long empId, User updateUser, Throwable t) {
        logger.error("Fallback triggered for updateUserByEmpId. Cause: {}", t.getMessage());
        throw new ResourceNotFoundException("User", "EmpId", empId);
    }
}
