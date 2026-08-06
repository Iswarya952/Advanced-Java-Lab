package parcelpackage;

import java.sql.*;

public class TransactionManagement {

    Connection con;

    public void createConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/courierdb",
                    "root",
                    "svecw@123");

            System.out.println("Connection Established");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateParcelStatus(int parcel_id, String status) {

        try {

            String query = "UPDATE parcel SET status=? WHERE parcel_id=?";

            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, status);
            ps.setInt(2, parcel_id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                con.commit();
                System.out.println("Parcel Status Updated");
            } else {
                con.rollback();
                System.out.println("Transaction Rollback");
            }

        } catch (Exception e) {

            try {
                con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    
}