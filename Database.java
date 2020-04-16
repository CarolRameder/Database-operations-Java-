package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//singleton database class
public class Database {
    // static variable single_instance of type Database
    private static Database single_instance = null;

    // private constructor restricted to this class itself
    private Database() {
        //open connetion ?
    }

    // static method to create instance of Database class
    public static Database Database() {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new Database();
        }
        return single_instance;
    }

    //connection to database
    public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost/MusicAlbums";
        Connection con = null;
        try {
            con = DriverManager.getConnection(
                    url, "root", "bololobo");
            this.operate(con);
        } catch (SQLException e) {
            System.err.println("Cannot connect to DB: " + e);
        } finally {
            if (con != null) {
                System.out.println("Conexiune reusita !");
                con.close();
            }
        }
    }

    //functionality test
    //creating the chart albums and artists
    public void operate(Connection c) throws SQLException {
        Chart Ch1 = new Chart("Top 100 worldwide",c);

        Artist Ar1 = new Artist("Russ", "USA", c);
        Album Al3 = new Album("Shake the snowglobe",2,9,2020,c);
        Album Al4 = new Album("There is a wolf",5,9,2017,c);

        Artist Ar2 = new Artist("Eminem", "USA", c);
        Album Al1 = new Album("Music to be murdered by",4,10,2020,c);

        Artist Ar3 = new Artist("Travis Scott", "USA", c);
        Album Al2 = new Album("Astroworld",1,11,2018,c);

        Artist Ar4 = new Artist("A$AP Rocky", "USA", c);
        Album Al5 = new Album("Testing",3,12,2018,c);

        Ch1.add(Al3);
        Ch1.add(Al4);
        Ch1.add(Al2);
        Ch1.add(Al1);
        Ch1.add(Al5);
        System.out.println(Ch1);
    }

}