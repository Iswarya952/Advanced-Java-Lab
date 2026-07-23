package parcelpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertClass {

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

    public void insertParcel() {

        int parcel_id = 4;
        String sender = "Hima";
        String receiver = "Vishu";
        String status = "Delivered";

        try {

            String query = "INSERT INTO parcel(parcel_id, sender, receiver, status) VALUES(?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);

            pstmt.setInt(1, parcel_id);
            pstmt.setString(2, sender);
            pstmt.setString(3, receiver);
            pstmt.setString(4, status);

            int rows = pstmt.executeUpdate();

            System.out.println("Rows Inserted = " + rows);

            pstmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}