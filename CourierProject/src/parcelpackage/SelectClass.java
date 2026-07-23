package parcelpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectClass {

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

    public void selectParcel() {

        try {

            String query = "SELECT * FROM parcel";

            pstmt = con.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            System.out.println("Parcel ID\tSender\tReceiver\tStatus");

            while (rs.next()) {

                System.out.println(rs.getInt("parcel_id") + "\t\t"+ rs.getString("sender") + "\t"+ rs.getString("receiver") + "\t"+ rs.getString("status"));

            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}