package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("John", "Doe", (byte) 25);
        user.saveUser("Jane", "Doe", (byte) 30);
        user.saveUser("Bob", "Smith", (byte) 40);
        user.saveUser("Alice", "Smith", (byte) 36);
        List<User> userList = user.getAllUsers();
        for (User users : userList) {
            System.out.println(users);
        }
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}





