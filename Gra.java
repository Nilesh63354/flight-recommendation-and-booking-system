package reco;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Dictionary;
import java.util.List;
import java.sql.*;

public class Gra extends Dre{
    private int v;
    private ArrayList<Integer>[] adjList;
    public Gra()
    {
        this.v = codes.size();   
        initAdjList();
        addAllEdges();
        // System.out.println("Done");
    }

    private void initAdjList()
    {
        adjList = new ArrayList[v+1];
        for (int i = 0; i <=v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }
    private void addEdge(int u, int v)
    {
        adjList[u].add(v);
    }

    private void addAllEdges(){
        // k represents hashmap of keys and v represents hashmap of values

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","Your_Password");
            Statement stmt=con.createStatement();
            for(int i=1; i<=codes.size(); i++ ){
                String station=codes.get(i);
                String querry="select * from ".concat(station) ;
                ResultSet rs=stmt.executeQuery(querry);
                // System.out.println(i);
                while(rs.next()){
                    
                    int j=keys.get(rs.getString(1));
                    //System.out.println(rs.getString(1));
                    // System.out.println(i+"" +j);
                    addEdge(i, j);
                }

            }
            con.close();
        }catch(Exception e){
            System.out.println("The present interrupt is  {%s}".formatted(e));
        }

    }

    public void printgraph(){
        for(int i=0;i<=v;i++){
            System.out.printf("%d ",i);
            //System.out.println(adjList[i].size());
            for(int j=0;j<adjList[i].size();j++){
                System.out.printf("%d ",adjList[i].get(j));
            }
            System.out.println("");
        }
    }
    public void update(){
        try{
            System.out.println("Started the insertion");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","Your_Password");
            Statement stmt=con.createStatement();

            Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/scrach","root","Your_Password");
            Statement stmt1=con1.createStatement();
            stmt1.executeUpdate("delete from drool");

            for(int i=1;i<=v;i++){
                for(int j=1; j<=v; j++){
                    if(i!=j){
                        printAllPaths(i, j, stmt, stmt1); 
                    }
                }
            }
            con.close();
            con1.close();
            System.out.println("insertion completed");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    private void printAllPaths(int s, int d ,Statement stmt,Statement stmt1)
    {
        boolean[] isVisited = new boolean[v+1];
        ArrayList<Integer> pathList = new ArrayList<>();
        int count=0;
        pathList.add(s);
        
        printAllPathsUtil(s, d, isVisited, pathList,count,stmt,stmt1);
    }
 
    private void printAllPathsUtil(Integer u, Integer d,boolean[] isVisited,List<Integer> localPathList ,int count,Statement stmt,Statement stmt1)
    {
        if (u.equals(d) ) {
            float money=0;
            String prev=new String();
            String start=new String();
            String path=new String("");
            int time=0,stop;
            try{
                ResultSet rs;
                for(int i=0;i<localPathList.size();i++){
                    if(i==0){
                        start=codes.get(localPathList.get(i));
                    }
                    path=path+codes.get(localPathList.get(i))+" ";
                    if(i>0){
                        rs=stmt.executeQuery("select MONEY,TIME from ".concat(prev)+" where CODE=\"".concat(codes.get(localPathList.get(i)))+"\"");
                        while(rs.next()){
                            money=money+rs.getFloat(1);
                            time=time+rs.getInt(2);
                        }
                    }
                    prev=codes.get(localPathList.get(i));
                }
                stop=localPathList.size()-2;
                // System.out.printf("%f %d\n",money,time);
                // System.out.printf("%s %s \n",start,prev);
                // System.out.println("");
                //System.out.println("insert into drool values (\"".concat(start)+"\",\"".concat(prev)+"\",\"".concat(path)+"\","+stop+","+money+","+time+")");
                stmt1.executeUpdate("insert into drool values (\"".concat(start)+"\",\"".concat(prev)+"\",\"".concat(path)+"\","+stop+","+money+","+time+")");
                return;
            }catch(Exception e){
                System.out.println(e);
            }
            
        }

        if(count>1){
            return;
        }
        count++;
        isVisited[u] = true;
        for (Integer i : adjList[u]) {
            if (!isVisited[i]  ) {
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited , localPathList,count,stmt,stmt1);
                localPathList.remove(i);
            }
        }
        isVisited[u] = false;
    }
}
