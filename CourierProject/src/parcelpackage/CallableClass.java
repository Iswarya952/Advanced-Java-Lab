package parcelpackage;
import java.sql.*;
public class CallableClass
{
    Connection con;
    CallableStatement cst;
    ResultSet rs;
    public CallableClass()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/courierdb","root","svecw@123");
            System.out.println("Connection Established");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void getParcelDetails()
    {
        try
        {
            cst = con.prepareCall("{call get_parcel_details(?,?)}");

            cst.setInt(1,4);

            cst.registerOutParameter(2,Types.VARCHAR);

            cst.execute();

            System.out.println("Procedure Status : "+cst.getString(2));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void getStatusFunction()
    {
        try
        {
            cst = con.prepareCall("{? = call get_status_from_parcel(?)}");

            cst.registerOutParameter(1,Types.VARCHAR);

            cst.setInt(2,4);

            cst.execute();

            System.out.println("Function Status : "+cst.getString(1));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}