package ru.kurma.service;

import ru.kurma.dao.UserDao;
import ru.kurma.dao.UserDaoImplJDBC;
import ru.kurma.model.User;

import java.util.List;

public class UserService {

    private static UserService instance = null;

    private UserDao dao = new UserDaoImplJDBC();

    public static UserService getInstance() {
        return instance == null ? instance = new UserService() : instance;
    }

    public List<User> getAllUsers() {
        return dao.findAllUsers();
    }

    public User getUserByIs(Integer id) {
        return dao.findUserById(id);
    }

    public User getUserByLogin(String login) {
        return dao.findUserByLogin(login);
    }


}
