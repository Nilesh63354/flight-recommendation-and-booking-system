package reco;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter 1 for Searching and booking ");
        System.out.println("please enter 2 for checking for your booking");
        System.out.println("Please enter 3 for adding a new node ");
        System.out.println("please enter 4 for Deleting an edge");
        System.out.println("Please enter 5 for updating cost between two edges");
        System.out.println("Please enter 6 for updating time between two edges");
        System.out.println("please enter 7 fro deleting a verice");
        int a=sc.nextInt();
        switch(a){
            case 1:
            String depart;
            String arri;
            String filter;
            String phone;
            String name;
            ArrayList<Data> c;
            sc.nextLine();
            System.out.println("Please enter your departure");
            depart=sc.nextLine();
            System.out.println("Please enter your arrival");
            arri=sc.nextLine();
            System.out.println("Please enter your filter type MONEY OR TIME");
            filter=sc.nextLine();
            Search serch= new Search();
            c=serch.getrows(depart, arri, filter);
            System.out.println("\n\n");
            for(int i=0;i<c.size();i++){
                System.out.println((i+1)+" "+c.get(i).path+" "+c.get(i).money+" "+c.get(i).time);
            }
            System.out.println("\nPlease select the flight");
            int ch=sc.nextInt()-1;
            Booking booki=new Booking();
            System.out.println("\nPlease enter your PHONE Number");
            sc.nextLine();
            phone=sc.nextLine();
            System.out.println("Please enter Your Name");
            name=sc.nextLine();
            booki.book(depart, arri, c.get(ch).path, c.get(ch).money, name, phone);
            serch=null;
            booki=null;
            break;

            case 2:
            String nam;
            String pho;
            System.out.println("Please enter your registered name");
            sc.nextLine();
            nam=sc.nextLine();
            System.out.println("please enter your registered phone number");
            pho=sc.nextLine();
            Booking b= new Booking();
            b.check(nam, pho);
            b=null;
            break;

            case 3:
            String namep;
            String codep;
            String state;
            int count;
            System.out.println("Please enter the name of the airport");
            sc.nextLine();
            namep=sc.nextLine();
            System.out.println("Please enter the code of airport");
            codep=sc.nextLine();
            System.out.println("Please enter the state of Airport");
            state=sc.nextLine();
            System.out.println("please the number of edges");
            count=sc.nextInt();
            System.out.println(namep+codep+state+count);
            Changes cha=new Changes();
            cha.addnode(namep, codep, state,count);
            cha=null;
            break;

            case 4:
            String d;
            String e;
            System.out.println("Please enter the node from where edge start");
            sc.nextLine();
            d=sc.nextLine();
            System.out.println("Please enter the node from where edge ends");
            e=sc.nextLine();
            Changes chu=new Changes();
            chu.removeEdge(d, e);
            System.out.println("Sucessfully removed the edge");
            chu=null;
            break;

            case 5:
            String a1;
            String b1;
            float c1;
            System.out.println("Please enter the node from where edge start");
            sc.nextLine();
            a1=sc.nextLine();
            System.out.println("Please enter the node from where edge ends");
            b1=sc.nextLine();
            System.out.println("Please enter updated cost");
            c1=sc.nextFloat();
            Changes ch1=new Changes();
            ch1.updateMoney(a1, b1, c1);
            System.out.println("Sucessfully updated");
            ch1=null;
            break;

            case 6:
            String a2;
            String b2;
            int c2;
            System.out.println("Please enter the node from where edge start");
            sc.nextLine();
            a2=sc.nextLine();
            System.out.println("Please enter the node from where edge ends");
            b2=sc.nextLine();
            System.out.println("Please enter updated time");
            c2=sc.nextInt();
            Changes ch2=new Changes();
            ch2.updateTime(a2, b2, c2);
            ch2=null;
            break;

            case 7:
            String s;
            System.out.println("please enter the edge you want to delete");
            sc.nextLine();
            s=sc.nextLine();
            Changes ch3=new Changes();
            ch3.deleteVertice(s);
            ch3=null;
            break;

            default:
            System.out.println("Please enter the write input ");


        }
        sc.close();
        // ArrayList<Data> c;
        // Search b=new Search();
        // c=b.getrows("DEL", "BOM", "TIME");
        // for(int i=0;i<c.size();i++){
        //     System.out.println(c.get(i).path+" "+c.get(i).money+" "+c.get(i).time);
        // }

    }
}
