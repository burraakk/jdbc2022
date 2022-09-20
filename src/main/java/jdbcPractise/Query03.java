package jdbcPractise;

import java.sql.*;

public class Query03 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc2022",
                "postgres",
                "4952");
        PreparedStatement ps = con.prepareStatement("select * from ogrenciler");
        //PreparedStatement, Statement'a göre daha dinamik ve hafızada daha az yer kaplıyor !!!!!!!!!!!!!
        /*
        Statement ve Prestatement arasindaki fark nedir?
        prestatement;
           1-daha dinamik
           2-daha az yer kapliyor
         */

        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();

        System.out.println("Sutun Sayisi : " + rsmd.getColumnCount());

        System.out.println(rsmd.getColumnLabel(2));

        System.out.println("1. Sutun Ismi : " + rsmd.getColumnName(1));
        System.out.println("2. Sutun Ismi : " + rsmd.getColumnName(2));
        System.out.println("3. Sutun Ismi : " + rsmd.getColumnName(3));
        System.out.println("4. Sutun Ismi : " + rsmd.getColumnName(4));

        System.out.println("1. Sutun Data Tipi : " + rsmd.getColumnTypeName(1));
        System.out.println("2. Sutun Data Tipi : " + rsmd.getColumnTypeName(2));
        System.out.println("3. Sutun Data Tipi : " + rsmd.getColumnTypeName(3));
        System.out.println("4. Sutun Data Tipi : " + rsmd.getColumnTypeName(4));

        System.out.println("Table name : " + rsmd.getTableName(1));

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.println(i + ". sutun :" + rsmd.getColumnName(i));
        }

    }
}
