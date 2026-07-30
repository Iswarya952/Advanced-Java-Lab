
package parcelpackage;

import java.sql.*;
import java.util.ArrayList;
public class BatchUpdates {
    Connection con;
    PreparedStatement pstmt;
    public void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/courierdb","root","svecw@123");
            System.out.println("Connection Established");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void batchInsert() {
        ArrayList<String> students = new ArrayList<>();
        students.add("21,Honey,Vishnu,Pending");
        students.add("22,Rahul,Anitha,Shipped");
        students.add("23,Kiran,Priya,Delivered");
        try {
            String query = "INSERT INTO parcel(parcel_id,sender,receiver,status) VALUES(?,?,?,?)";
            pstmt = con.prepareStatement(query);
            for (String student : students) {
                String[] columns = student.split(",");
                pstmt.setInt(1, Integer.parseInt(columns[0]));
                pstmt.setString(2, columns[1]);
                pstmt.setString(3, columns[2]);
                pstmt.setString(4, columns[3]);

                pstmt.addBatch();
            }
            int[] result = pstmt.executeBatch();
            System.out.println("Records Inserted = " + result.length);
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void batchUpdate() {
        ArrayList<String> students = new ArrayList<>();
        students.add("1,Delivered");
        students.add("2,In Transit");
        students.add("3,Cancelled");
        try {
            String query = "UPDATE parcel SET status=? WHERE parcel_id=?";
            pstmt = con.prepareStatement(query);
            for (String student : students) {
                String[] columns = student.split(",");
                pstmt.setString(1, columns[1]);
                pstmt.setInt(2, Integer.parseInt(columns[0]));

                pstmt.addBatch();
            }
            int[] result = pstmt.executeBatch();
            System.out.println("Records Updated = " + result.length);
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    public void displayParcels() {
        try {
            String query = "SELECT * FROM parcel";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("\nParcel Details");
            System.out.println("-----------------------------------------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("parcel_id") + "  " +
                        rs.getString("sender") + "  " +
                        rs.getString("receiver") + "  " +
                        rs.getString("status"));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

   