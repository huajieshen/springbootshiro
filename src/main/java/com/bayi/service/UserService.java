package com.bayi.service;

import com.bayi.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User findByName(String name);

    public User findById(Integer id);
}
