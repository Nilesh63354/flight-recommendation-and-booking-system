package reco;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Changes {
    
    public  void addnode(String name, String code, String state,int num){
        try{
            String q;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","Your_Password");
            Statement stmt=con.createStatement();
            q="insert into port(NAME,CODE,STATE) values (\""+name+"\",\""+code+"\",\""+state+"\")";
            stmt.executeUpdate(q);
            stmt.executeUpdate("create table "+code+" ( CODE varchar(10) Primary Key, TIME int, MONEY float(9,3) )");
            addedge(stmt, num,code);
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private  void addedge(Statement stmt,int num,String code){
        Scanner sc =new Scanner(System.in);
        String p;
        float mon;
        int tme;
        try{
            for(int i=1;i<=num;i++){
                System.out.println("please enter the "+(i)+" edge of the vertice");
                p=sc.next();
                System.out.println("please enter the cost of travel in "+p+" edge");
                mon=sc.nextFloat();
                System.out.println("please enter the time taken to travel to "+p+" edge");
                tme=sc.nextInt();
                stmt.executeUpdate("insert into "+code+" values(\""+p+"\","+tme+","+mon+")");
                stmt.executeUpdate("insert into "+p+" values(\""+code+"\","+tme+","+mon+")");
                // System.out.println("inset into "+code+" values(\""+p+"\","+tme+","+mon+")");
                // System.out.println("inset into "+p+" values(\""+code+"\","+tme+","+mon+")");
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        Gra graph=new Gra();
        graph.update();

    }

    public void updateMoney(String a,String b,float mon){
        try{
            String q;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","Your_Password");
            Statement stmt=con.createStatement();
            q="update "+a+" set MONEY="+mon+" where CODE=\""+b+"\"";
            stmt.executeUpdate(q);
            //System.out.println("update "+b+" set MONEY="+mon+" where CODE=\""+a+"\"");
            stmt.executeUpdate("update "+b+" set MONEY="+mon+" where CODE=\""+a+"\"");
            System.out.println("DONE");
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        Gra graph=new Gra();
        graph.update();
    }

    public void updateTime(String a,String b,int time){
        try{
            String q;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","Your_Password");
            Statement stmt=con.createStatement();
            q="update "+a+" set TIME="+time+" where CODE=\""+b+"\"";
            stmt.executeUpdate(q);
            //System.out.println("update "+b+" set MONEY="+mon+" where CODE=\""+a+"\"");
            stmt.executeUpdate("update "+b+" set TIME="+time+" where CODE=\""+a+"\"");
            System.out.println("DONE");
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        Gra graph=new Gra();
        graph.update();
    }

    public void removeEdge(String a,String b){
        try{
            String q;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","Your_Password");
            Statement stmt=con.createStatement();
            q="delete from "+a+" where CODE=\""+b+"\"";
            stmt.executeUpdate(q);
            // System.out.println(q);
            // System.out.println("delete from "+b+" where CODE=\""+a+"\"");
            stmt.executeUpdate("delete from "+b+" where CODE=\""+a+"\"");
            System.out.println("DONE");
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        Gra graph=new Gra();
        graph.update();
    }

    public void deleteVertice(String ver){
        try{
            ArrayList<String> ab=new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","Your_Password");
            Statement stmt=con.createStatement();
            stmt.executeUpdate("delete from port where CODE=\""+ver+"\"");
            ResultSet rs = stmt.executeQuery("select CODE from "+ver);
            while(rs.next()){
                ab.add(rs.getString(1));
            }
            for(int i=0;i<ab.size();i++){
                stmt.executeUpdate("delete from "+ab.get(i)+" where CODE=\""+ver+"\"");
            }
            stmt.executeUpdate("drop table "+ver);
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        Gra graph=new Gra();
        graph.update();
    }

}
