package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//the albums are compared depending on their position in the chart
public class Album implements Comparable<Album>{

    private String name;
    private int artist_id ;
    private int release_year;
    private int position;
    private Connection c;

    public Album(String name, int position, int artist_id, int release_year, Connection c) throws SQLException{
        this.name = name;
        this.position=position;
        this.artist_id = artist_id;
        this.release_year = release_year;
        this.c = c;
        this.create();
    }

    public String getName() {
        return name;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public int getPosition() {
        return position;
    }

    public int getRelease_year() {
        return release_year;
    }

    public Connection getC() {
        return c;
    }

    //the album it's initialized only with artist id
    //this function return the artist name from the specific table in the database
    public String getArtistName () throws SQLException{
        String query="select name from artists where id = " + artist_id  + ";" ;
        Statement st=c.createStatement();
        ResultSet rs=st.executeQuery(query);
        rs.next();
        String Nume=rs.getString(1);
        return Nume;
    }

    //an album is added to the created database
    public void create() throws SQLException {
        String query="INSERT INTO albums (name ,artist_id,release_year ) VALUES ('" + name + "','" + artist_id  +  "','" + release_year +"');" ;
        Statement st=c.createStatement();
        int count=st.executeUpdate(query);
        System.out.println(count+" number of rows were changed");
    }

    //only for compulsory part
    public void findByArtist() throws SQLException {
        String query="select * from albums where artist_id = " + artist_id  + ";" ;
        Statement st=c.createStatement();
        ResultSet rs=st.executeQuery(query);
        rs.next();
        String name=rs.getString(2);
        int releaseYear=rs.getInt(4);
        System.out.println("The album called " + name +" was released in " + releaseYear );
    }

    @Override
    public int compareTo(Album o) {
        return this.getPosition()-o.getPosition();
    }


    @Override
    public String toString() {
        String artistName= null;
        try {
            artistName = this.getArtistName();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "No. " + this.position + " : " + this.name +" by "+artistName +" released in " + this.release_year ;
    }
}