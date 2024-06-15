package com.primer_parcial.shop.service;

import com.primer_parcial.shop.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(Long id, User user);
    List<User> getAllUser();
    User getUserById(Long id);
}
