package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.junit.Assert;

public class Main {
    public static void main(String[] args) {
 /*       UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("John", "Doe", (byte) 25);
        user.saveUser("Jane", "Doe", (byte) 30);
        user.saveUser("Bob", "Smith", (byte) 40);
        user.saveUser("Alice", "Smith", (byte) 36);
        user.removeUserById(1L);
        List<User> userList = user.getAllUsers();
        for (User users : userList) {
            System.out.println(users);
 /*    }
      user.cleanUsersTable();
      user.dropUsersTable();
  */
        final UserService userService = new UserServiceImpl();
        final String testName = "Ivan";
        final String testLastName = "Ivanov";
        final byte testAge = 5;
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser(testName, testLastName, testAge);
        userService.removeUserById(1L);
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser(testName, testLastName, testAge);
        if (userService.getAllUsers().size() != 1) {
            Assert.fail("Проверьте корректность работы метода сохранения пользователя/удаления или создания таблиц");
        }




    }
}






