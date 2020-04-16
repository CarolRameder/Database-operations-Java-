package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Database myDB = Database.Database();
        myDB.connect();

    }
}
