package reco;
import java.sql.*;
public class Booking {
    public void book(String sou,String des,String path,float mon ,String name,String phone){
        try{
            String q;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/passenger","root","Your_Password");
            Statement stmt=con.createStatement();
            q="insert into bk values (\""+sou+"\",\""+des+"\",\""+path+"\","+mon+",\""+name+"\",\""+phone+"\")";
            stmt.executeUpdate(q);
            System.out.println("Booking Completed");
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void check(String name,String phone){
        try{
            String q;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/passenger","root","Your_Password");
            Statement stmt=con.createStatement();
            q="select * from bk where NAME=\""+name+"\" and PHONE=\""+phone+"\"";
            System.out.println(q);
            ResultSet rs=stmt.executeQuery(q);
            while(rs.next()){
                System.out.println("Source = "+rs.getString(1));
                System.out.println("Destination = "+rs.getString(2));
                System.out.println("Path = "+rs.getString(3));
                System.out.println("Cost = "+rs.getFloat(4));
                System.out.println("NAME = "+rs.getString(5));
                System.out.println("Phone = "+rs.getString(6));
                System.out.println("");
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Data not found");
        }
    }
}
