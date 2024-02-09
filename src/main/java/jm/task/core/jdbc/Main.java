package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    static UserDaoHibernateImpl usr = new UserDaoHibernateImpl();
    public static void main(String[] args) {

        usr.createUsersTable();
        usr.saveUser("vasiya", "pupkov", (byte) 7);
        usr.saveUser("busiya", "truth", (byte) 80);
        usr.saveUser("gerz", "pups", (byte) 25);
        usr.saveUser("piu", "piu", (byte) 40);
        usr.removeUserById(3);
        System.out.println(usr.getAllUsers());




        // реализуйте алгоритм здесь
    }
}
