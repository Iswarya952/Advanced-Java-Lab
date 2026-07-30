package parcelpackage;

import java.sql.*;

public class ScrollableUpdateable {

    Connection con;
    Statement stmt;
    ResultSet rs;
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
    public void scrollRecords() {

        try {

            stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            rs = stmt.executeQuery("SELECT * FROM parcel");

            System.out.println("First Record");
            rs.first();
            System.out.println(rs.getInt(1) + " " +
                               rs.getString(2) + " " +
                               rs.getString(3) + " " +
                               rs.getString(4));

            System.out.println("\nLast Record");
            rs.last();
            System.out.println(rs.getInt(1) + " " +
                               rs.getString(2) + " " +
                               rs.getString(3) + " " +
                               rs.getString(4));

            System.out.println("\nPrevious Record");
            rs.previous();
            System.out.println(rs.getInt(1) + " " +
                               rs.getString(2) + " " +
                               rs.getString(3) + " " +
                               rs.getString(4));

            System.out.println("\nAbsolute Position (2)");
            rs.absolute(2);
            System.out.println(rs.getInt(1) + " " +
                               rs.getString(2) + " " +
                               rs.getString(3) + " " +
                               rs.getString(4));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRecord() {

        try {

            stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            rs = stmt.executeQuery("SELECT * FROM parcel");

            rs.absolute(2);         
            rs.updateString("status", "Delivered");
            rs.updateRow();

            System.out.println("\nRecord Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayRecords() {

        try {

            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT * FROM parcel");

            System.out.println("\nParcel Details");
            System.out.println("--------------------------------");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("parcel_id") + " " +
                        rs.getString("sender") + " " +
                        rs.getString("receiver") + " " +
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