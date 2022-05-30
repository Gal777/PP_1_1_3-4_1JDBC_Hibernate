package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {



    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Гарри", "Потер", (byte) 14);
        userService.saveUser("Робинзон", "Крузо", (byte) 35);
        userService.saveUser("Кинг", "Конг", (byte) 25);
        userService.saveUser("Мария", "Лопес", (byte) 40);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}