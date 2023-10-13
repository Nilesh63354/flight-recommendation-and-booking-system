package reco;

import java.util.ArrayList;
import java.sql.*;
public class Search {
    // ArrayList<Data> rows;

    public ArrayList<Data> getrows(String start,String dest,String choice){
        ArrayList<Data> rows=new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/scrach","root","Your_Password");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select PATH,MONEY,TIME from drool where START=\""+start+"\" and END=\""+dest+"\" order by "+choice);
            while(rs.next()){
                rows.add(new Data(rs.getString(1), rs.getFloat(2), rs.getInt(3)));
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return rows;
    }
}
