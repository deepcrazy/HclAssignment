package com.deepanshu.hclassignment.service;

import com.deepanshu.hclassignment.model.User;

import java.util.Optional;

public interface UserService {
    public User getUserByEmpId(long empId);
    public User saveUser(User user);
    public User updateUserByEmpId(long empId, User updateUser);
}
