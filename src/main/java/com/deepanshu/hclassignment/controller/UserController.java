package com.deepanshu.hclassignment.controller;

import com.deepanshu.hclassignment.exception.InvalidEmpIdException;
import com.deepanshu.hclassignment.model.User;
import com.deepanshu.hclassignment.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userdetails")
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{emp_id}")
    public ResponseEntity<User> getUserByEmpId(@PathVariable("emp_id") String empIdStr) {
        logger.info("Invoked getUserByEmpId method with empId: {}", empIdStr);

        // This validation could have been done outside of controller using HandleInterceptor Interface's
        // method: `preHandle`. However, have gone with this approach due to time constraints.
        long empId = validateNumericEmpId(empIdStr);
        return new ResponseEntity<>(userService.getUserByEmpId(empId), HttpStatus.OK);
    }

    @PutMapping("/{emp_id}")
    public ResponseEntity<User> updateUserByEmpId(@PathVariable("emp_id") String empIdStr, @RequestBody User updateUser) {
        System.out.println("Entered updateUserById API");
        System.out.println(empIdStr);
        logger.info("Invoked updateUserByEmpId method with empId: {} and updatedUser: {}", empIdStr, updateUser);
        long empId = validateNumericEmpId(empIdStr);
        return new ResponseEntity<>(userService.updateUserByEmpId(empId, updateUser), HttpStatus.OK);
    }

    private long validateNumericEmpId(String empIdStr) {
        try {
            long empId = Long.parseLong(empIdStr);
            if (empId > 0L) return empId;
            else throw new InvalidEmpIdException("Invalid user ID. Must be non-zero numeric.");
        } catch (NumberFormatException e) {
            throw new InvalidEmpIdException("Invalid user ID. Must be non-zero numeric.");
        }
    }
}
