package com.example.jwt_auth.service;


import com.example.jwt_auth.domain.Role;
import com.example.jwt_auth.domain.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;



@Service
public class UserService {

    private final List<User> users;

    public UserService(@Value("${customers.user1.login}") String user1_login, @Value("${customers.user1.password}") String user1_password,
                       @Value("${customers.admin.login}") String admin_login, @Value("${customers.admin.password}") String admin_password) {

        this.users = Collections.unmodifiableList(Arrays.asList(
                new User(user1_login, user1_password, "John", "Doe", Collections.singleton(Role.USER)),
                new User(admin_login, admin_password, "Nessia", "Adminate", Collections.singleton(Role.ADMIN))));
  }

    public Optional<User> getByLogin(@NonNull String login) {
        return users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }

}

