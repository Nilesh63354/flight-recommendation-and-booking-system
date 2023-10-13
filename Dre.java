package reco;

import java.util.function.DoubleToIntFunction;
import java.sql.*;
import java.util.HashMap;

public class Dre {
    public HashMap<Integer,String> codes;
    public HashMap<String,Integer> keys;
    public Dre(){
        codes=new HashMap<>();
        keys= new HashMap<>();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","Your_Password");
            Statement stmt=con.createStatement();
            int i=1;
            ResultSet rs=stmt.executeQuery("select CODE from port order by TSTAMP");
            while(rs.next()){
                codes.put(i,rs.getString(1));
                keys.put(rs.getString(1), i);
                i++;
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public HashMap getcodes(){
        return codes;
    }

    public HashMap getkeys(){
        return keys;
    }
}
