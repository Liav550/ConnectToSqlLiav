package org.example;

import java.sql.*;

/**
 * @author liavb
 * name: Liav Bengayev
 * id: 325364537
 * date: 18/01/2023
 */
public class MyConnection {
    private Connection connect()
    {
        String url = "jdbc:postgresql://localhost:5432/loginProject";
        String userName = "postgres";
        String password = "0542615224L";
        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            return con;
        } catch (SQLException e) {
            System.err.println("Error!!");
            throw new RuntimeException(e);
        }
    }
    public void viewTable() {
        try
        {
            String sql = "SELECT id, \"userName\", email, \"phoneNumber\", \"firstName\", \"lastName\"\n" +
                    "\tFROM public.\"Users\";";
            Connection con = connect();
            System.out.println();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                System.out.println("ID: "+rs.getString("id"));
                System.out.println("UserName: "+rs.getString("userName"));
                System.out.println("Email: "+rs.getString("email"));
                System.out.println("Phone Number: "+rs.getString("phoneNumber"));
                System.out.println("First Name: "+rs.getString("firstName"));
                System.out.println("Last Name: "+rs.getString("lastName"));
                System.out.println();
            }
            st.close();
            con.close();
        }
        catch (SQLException e)
        {
            System.out.println("SQL exception: "+e);
        }
    }
    public void insert(String userName, String email, String phoneNumber, String firstName, String lastName )
    {
        try
        {
            String sql = "INSERT INTO public.\"Users\"(\n" +
                            "\t\"userName\", email, \"phoneNumber\", \"firstName\", \"lastName\")\n" +
                            "\tVALUES (?,?,?,?,?);";
            Connection con = connect();
            PreparedStatement st = con.prepareStatement(sql);
            con.setAutoCommit(false);

            st.setString(1,userName);
            st.setString(2,email);
            st.setString(3,phoneNumber);
            st.setString(4,firstName);
            st.setString(5,lastName);

            st.executeUpdate();
            con.commit();
            System.out.println("Insert Worked");
            st.close();
            con.close();
        }
        catch (SQLException e)
        {
            System.out.println("SQL exception: "+e);
        }
    }
    public void update(String columnNameToUpdate, String newData, int specialID)
    {
        try
        {
            Connection con = connect();
            String sql = String.format("UPDATE public.\"Users\"\n" +
                    "\tSET \"%s\"=?\n" +
                    "\tWHERE id=?;",columnNameToUpdate);
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,newData);
            st.setInt(2,specialID);
            st.executeUpdate();
            con.commit();
            System.out.println("update saved!");
            st.close();
            con.close();
        }
        catch (SQLException e)
        {
            System.out.println("SQL exception: "+e);
        }
    }
    public void delete(int specialID)
    {
        try
        {
            Connection con = connect();
            System.out.println();
            String sql ="DELETE FROM public.\"Users\"\n" +
                    "\tWHERE id=?;";
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,specialID);
            st.executeUpdate();
            con.commit();
            System.out.println("Deleted successfully");
            st.close();
            con.close();
        }
        catch (SQLException e)
        {
            System.out.println("SQL exception: "+e);
        }
    }
}
