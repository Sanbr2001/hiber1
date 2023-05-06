package jm.task.core.jdbc.dao;

import org.hibernate.Session;
import jm.task.core.jdbc.model.User;

import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;




public class UserDaoHibernateImpl implements UserDao {

    private final Session session;

    public UserDaoHibernateImpl() {
        session =  Util.getSessionFactory().openSession();
    }

    @Override
    public void createUsersTable() {
        try {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS user (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "last_name VARCHAR(50) NOT NULL," +
                    "age TINYINT NOT NULL" +
                    ")").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table 'users' successfully created.");
        } catch (HibernateException e) {
            System.err.println("Error creating table 'users': " + e.getMessage());
        }
    }



    @Override
    public void dropUsersTable() {
        try {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS user").executeUpdate();
            transaction.commit();
            System.out.println("User table dropped successfully");
        } catch (HibernateException e) {
            System.err.println("Error dropping User table: " + e.getMessage());
            throw e;
        }
    }




    @Override
    public void saveUser(String name, String lastName, byte age) {
        session.save(new User(name, lastName, age));
        System.out.println("User with name " + name + " has been added to the database");
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            System.out.println("User with id " + id + " has been successfully removed from the database");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error deleting user with id " + id + ": " + e.getMessage());
        }
    }





    public List<User> getAllUsers() {
        try {
            List<User> users = session.createQuery("FROM User", User.class).getResultList();
            System.out.println("The list of users has been successfully retrieved from the database");
            return users;
        } catch (HibernateException e) {
            System.err.println("An error occurred while retrieving the list of users from the database: " + e.getMessage());
            return Collections.emptyList();
        }
    }




    @Override
    public void cleanUsersTable() {
        try {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
            System.out.println("User table has been successfully cleaned up");
        } catch (HibernateException e) {
            System.err.println("Error cleaning up User table: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        session.close();
    }
}
