package com.primer_parcial.shop.serviceImp;

import com.primer_parcial.shop.exceptions.AlreadyExistsException;
import com.primer_parcial.shop.exceptions.NotFoundException;
import com.primer_parcial.shop.model.User;
import com.primer_parcial.shop.model.enums.ErrorMessage;
import com.primer_parcial.shop.model.enums.Role;
import com.primer_parcial.shop.repository.UserRepository;
import com.primer_parcial.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User createUser(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if (optionalUser.isPresent()){

            throw  new AlreadyExistsException(ErrorMessage.USER_EMAIL_EXIST.getMessage());
        }
        user.setRole(Role.USER);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> userId = userRepository.findById(id);

        if(userId.isEmpty()){
            throw new NotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage());
        }

        Optional<User> userEmail = userRepository.findByEmailAndIdNot(user.getEmail(), id);

        if(userEmail.isPresent()){
            throw new AlreadyExistsException(ErrorMessage.USER_EMAIL_EXIST.getMessage());
        }

        userId.get().setEmail(user.getEmail());
        userId.get().setName(user.getName());
        userId.get().setAddress(user.getAddress());
        userId.get().setPassword(user.getPassword());
        userId.get().setLastname(user.getLastname());

        return userRepository.save(userId.get());
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userId = userRepository.findById(id);

        if(userId.isEmpty()){
            throw  new NotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage());
        }

        return  userId.get();
    }
}
