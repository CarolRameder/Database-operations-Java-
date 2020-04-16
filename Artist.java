package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Artist {

    private String name;
    private String country;
    private Connection c;

    public Artist( String name, String country, Connection c) throws SQLException{
        this.name = name;
        this.country = country;
        this.c = c;
        this.create();
    }

    //an artist is added to the created database
    public void create() throws SQLException {
        String query="INSERT INTO artists (name ,country) VALUES ('" + name + "','" + country + "');" ;
        Statement st=c.createStatement();
        int count=st.executeUpdate(query);
        System.out.println(count+" number of rows were changed");
    }

//only for the compulsory part
    public void findByName() throws SQLException {
        String query="select * from artists where name = '" + name + "';" ;
        Statement st=c.createStatement();
        ResultSet rs=st.executeQuery(query);
        rs.next();
        String country=rs.getString(3);
        String id=rs.getString(1);
        System.out.println("The artist called " + name +" is from " + country + " and has id: " + id);
    }
    //

    @Override
    public String toString() {
        return this.name+" from " + this.country ;
    }
}

