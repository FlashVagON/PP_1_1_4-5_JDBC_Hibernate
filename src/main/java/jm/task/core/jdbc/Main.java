package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    static UserDaoJDBCImpl usr = new UserDaoJDBCImpl();
    public static void main(String[] args) {

        usr.createUsersTable();
        usr.saveUser("vasiya", "pupkov", (byte) 7);
        usr.saveUser("busiya", "truth", (byte) 80);
        usr.saveUser("gerz", "pups", (byte) 25);
        usr.saveUser("piu", "piu", (byte) 40);




        // реализуйте алгоритм здесь
    }
}