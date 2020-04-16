package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

//a chart contains the album, it's artist and the position
public class Chart {

    private String chartName;
    private Connection c;
    private Set<Album> albums = new TreeSet<>();
    public Chart(String name,Connection con) {
        this.chartName=name;
        this.c=con;
    }

    //a chart is added to the created database
    public void add(Album a) throws SQLException {
        albums.add(a);
        String query="INSERT INTO chart (pozitie ,AlbumName,ArtistName ) VALUES (" + a.getPosition() + ",'" + a.getName() +  "','" + a.getArtistName() +"');" ;
        Statement st=c.createStatement();
        int count=st.executeUpdate(query);
        System.out.println(count+" number of rows were changed");
    }


    @Override
    public String toString() {
        return "The chart " + this.chartName + " includes the following albums " + albums ;
    }
}
