import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class HotelManagement {
   static final String url="jdbc:mysql://localhost:3306/";
   static final String user="root";
   static final String pass="root";
   static int n=0;
   static int pID;

    
    static void Reservation(Connection con,Statement state) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID");
        int ID=sc.nextInt();
        System.out.println("Enter name:");
        String name = sc.next();
        System.out.println("Enter Phone number");
        long phone_no = sc.nextLong();
        System.out.println("Enter Address");
        String address = sc.next();
        System.out.println("Enter table number");
        int tID=sc.nextInt(); 
        System.out.println("Data you entered is:");
        System.out.println(ID+","+name + "," + phone_no + "," + address+","+tID);
        try{
         String sql1="use hotelDB;";
         String sql2="insert into Customers(pID,name,phoneno,address,tID) values("+ID+","+"'"+name+"'"+","+"'"+phone_no+"'"+","+"'"+address+"'"+","+tID+");";
         String sql3="update statusTable SET "+"status='booked'"+"where tID="+tID;
         state.execute(sql1);
         state.execute(sql2);
         state.execute(sql3);
         System.out.println("Data Entered Successfully");
        }catch(SQLException e){
         e.printStackTrace();
        }
     }

     static void Update(Connection con,Statement state) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID");
        int ID=sc.nextInt();
        System.out.println("What to Update?");
        System.out.println("1.Name");
        System.out.println("2.Phone number");
        System.out.println("3.Address");
        int n1 = sc.nextInt();
        String n2;
        if (n1 == 1) {
         try{
           System.out.println("Enter new name");
           n2 = sc.next();
           String sql1="use hotelDB;";
           String sql2="update customers SET name='"+n2+"'"+" where pID="+ID+";";
           state.execute(sql1);
           state.execute(sql2);
           System.out.println("Updated name to " + n2);
        }catch(SQLException e){
         e.printStackTrace();
        }
      } else if (n1 == 2) {
         try{
           System.out.println("Enter new phone number");
            n2 = sc.next();
            String sql1="use hotelDB;";
           String sql2="update customers SET phoneno='"+n2+"'"+" where pID="+ID+";";
           state.execute(sql1);
           state.execute(sql2);
           System.out.println("Updated phone number to " + n2);
        }catch(SQLException e){
         e.printStackTrace();
        } }else if (n1 == 3) {
           try{
            System.out.println("Enter new address");
           n2 = sc.next();
            String sql1="use hotelDB;";
           String sql2="update customers SET address='"+n2+"'"+" where pID="+ID+";";
           state.execute(sql1);
           state.execute(sql2);
           System.out.println("Updated address to " + n2);
        }catch(SQLException e){
         e.printStackTrace();
        }
      }else {
           System.out.println("Invalid choice");
        }
  
     }

     static void availableTables(Connection con, Statement state){
        try{
         String sql1="use hotelDB;";
         String sql2="select tID from statusTable where status='NotBooked';";
         state.execute(sql1);
         ResultSet rs=state.executeQuery(sql2);
         //iterates through resultset rs
         System.out.println("Available tables:");
         while(rs.next()){
            int id=rs.getInt("tID");
            System.out.println(id);
         }
        }catch(SQLException e){
         e.printStackTrace();
        }
     }

     static void Registerations(Connection con,Statement state){
      try{
         String sql1="use hotelDB;";
         String sql2="select * from customers";
         state.execute(sql1);
         ResultSet rs=state.executeQuery(sql2);
         System.out.println("Registerations:");
         System.out.println("ID name  phoneno  address  tableID");

         while(rs.next()){
            int pID=rs.getInt("pID");
            String name=rs.getString("name");
            String phoneno=rs.getString("phoneno");
            String addr=rs.getString("address");
            int tableID=rs.getInt("tID");
            System.out.println(pID+" "+name+" "+phoneno+" "+addr+" "+tableID);
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
     }

     static void Delete(Connection con,Statement state){
      try{
      Scanner sc = new Scanner(System.in);
      String sql1="use hotelDB;";
      state.execute(sql1);
      System.out.println("Enter the tableID to remove the registeration");
      int tID=sc.nextInt();
      String sql2="delete from customers where tID="+tID;
      String sql3="update statustable SET status='NotBooked' where tID="+tID;
      state.execute(sql2);
      state.execute(sql3);
      System.out.println("Data Deleted Succesfully");
      }catch(SQLException e){
         e.printStackTrace();
      }
     }

     
     public static void main(String[] args) {
      do{
      try{
         Connection con=DriverManager.getConnection(url,user,pass);
         Statement state=con.createStatement();
      
         
        Scanner sc = new Scanner(System.in);
        System.out.println("Options:");
        System.out.println("1.Book table");
        System.out.println("2.Update booking");
        System.out.println("3.Remove booking");
        System.out.println("4.check available tables");
        System.out.println("5.check Registerations");
        System.out.println("6.Exit");
        n = sc.nextInt();
        switch (n) {
           case 1:
              System.out.println("You chose Book_Table");
                Reservation(con,state);
                break;
           case 2:
              System.out.println("You Chose Update Booking");
              Update(con,state);
              break;
           case 3:
              Delete(con,state);
              break;
           case 4:
              availableTables(con,state);
              break;
           case 5:
              Registerations(con,state);
              break;
           case 6:
              System.out.println("Exit");
              break;   
           default:
              System.out.println("Invalid Input!");
              break;
        }
  
     }catch(SQLException e){
      System.out.println(e);
   }

   }while(n != 6);
}
}
