package parcelpackage;

import java.sql.*;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class CachedRowSetClass {

    Connection con;
    CachedRowSet crs;

    public CachedRowSetClass() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/courierdb",
                    "root",
                    "svecw@123");

            con.setAutoCommit(false);

            System.out.println("Connection Established");

        } catch (Exception e) {

            System.out.println(e);

        }

    }

    public void cachedRowSetDemo() {

        try {

            crs = RowSetProvider.newFactory().createCachedRowSet();

            crs.setUrl("jdbc:mysql://localhost:3306/courierdb");
            crs.setUsername("root");
            crs.setPassword("svecw@123");

            crs.setCommand("SELECT * FROM parcel");

            crs.execute();

            System.out.println("\nParcel Details\n");

            while (crs.next()) {

                System.out.println("Parcel ID : " + crs.getInt("parcel_id"));
                System.out.println("Sender    : " + crs.getString("sender"));
                System.out.println("Receiver  : " + crs.getString("receiver"));
                System.out.println("Status    : " + crs.getString("status"));
                System.out.println("-----------------------------");

            }

            if (crs.absolute(2)) {

                System.out.println("\nSecond Row");

                System.out.println("Parcel ID : " + crs.getInt("parcel_id"));
                System.out.println("Sender    : " + crs.getString("sender"));
                System.out.println("Receiver  : " + crs.getString("receiver"));
                System.out.println("Status    : " + crs.getString("status"));

                crs.updateString("status", "Delivered");
                crs.updateRow();

                System.out.println("\nSecond Row Updated");

            }

            crs.moveToInsertRow();

            crs.updateInt("parcel_id", 106);
            crs.updateString("sender", "Ravi");
            crs.updateString("receiver", "Priya");
            crs.updateString("status", "In Transit");

            crs.insertRow();

            crs.moveToCurrentRow();

            crs.acceptChanges(con);

            con.commit();

            System.out.println("\nRecord Updated and Inserted Successfully");

            crs.close();
            con.close();

        } catch (Exception e) {

            try {

                con.rollback();

            } catch (Exception ex) {

                System.out.println(ex);

            }

            System.out.println(e);

        }

    }

}