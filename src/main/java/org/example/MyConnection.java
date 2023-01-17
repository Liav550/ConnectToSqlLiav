package org.example;

import java.sql.*;

public class MyConnection {
    private Connection connect()
    {
        String url = "jdbc:postgresql://localhost:5432/loginProject";
        String userName = "postgres";
        String password = "0542615224L";
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            return connection;
        } catch (SQLException e) {
            System.out.printf("Error!!");
            throw new RuntimeException(e);
        }
    }
    public void viewTable() {
        try
        {
            String sql = "SELECT id, \"userName\", gmail, \"phoneNumber\", \"firstName\", \"lastName\"\n" +
                    "\tFROM public.\"Users\";";
            Connection con = connect();
            System.out.println();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                System.out.println("ID: "+rs.getString("id"));
                System.out.println("UserName: "+rs.getString("userName"));
                System.out.println("Email: "+rs.getString("gmail"));
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
            String sql = String.format("INSERT INTO public.\"Users\"(\n" +
                            "\t\"userName\", gmail, \"phoneNumber\", \"firstName\", \"lastName\")\n" +
                            "\tVALUES ('%s', '%s', '%s', '%s', '%s');", userName,email,phoneNumber,
                    firstName,lastName);
            Connection con = connect();
            System.out.println();
            Statement st = con.createStatement();
            st.execute(sql);
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
            System.out.println();
            Statement st = con.createStatement();
            String sql = String.format("UPDATE public.\"Users\"\n" +
                    "\tSET \"%s\"= '%s'\n" +
                    "\tWHERE id=%s;",columnNameToUpdate,newData,Integer.toString(specialID));
            st.execute(sql);
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
            Statement st = con.createStatement();
            String sql = String.format("DELETE FROM public.\"Users\"\n" +
                    "\tWHERE id=%s;",Integer.toString(specialID));
            st.execute(sql);
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
