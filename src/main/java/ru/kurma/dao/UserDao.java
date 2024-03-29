package ru.kurma.dao;

import ru.kurma.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUsers();
    User findUserById(Integer id);
    User findUserByLogin(String login);
    void createNewUser(String firstName, String lastName, String login, String password);
    void updateUser(User user);
    void deleteUser(User user);
}