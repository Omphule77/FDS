package com.fds.foodiexpress.dao;

import com.fds.foodiexpress.entity.Users;

public interface UserDAO {
    Users findByEmail(String email);
    void updateUser(Users user);
}
