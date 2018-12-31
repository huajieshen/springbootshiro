package com.bayi.mapper;

import com.bayi.pojo.User;

public interface UserMapper {
    public User findByName(String name);
    public User findById(Integer id);
}
