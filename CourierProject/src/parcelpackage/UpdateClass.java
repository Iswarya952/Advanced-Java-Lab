package parcelpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateClass {

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

    public void updation() {

        String status = "Delivery";
        int parcel_id = 3;

        try {

            String query = "UPDATE parcel SET status = ? WHERE parcel_id = ?";

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, status);
            pstmt.setInt(2, parcel_id);

            int rows = pstmt.executeUpdate();

            System.out.println("Rows Updated = " + rows);

            pstmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
