package jdbcPractise;

import java.sql.*;

public class Query01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Driver yükle
        Class.forName("org.postgresql.Driver");

        //2. Bağlantı oluştur
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc2022",
                                                     "postgres",
                                                     "4952");
        //3. Statement oluştur
        Statement st = con.createStatement();

        //4. ResultSet
        ResultSet veri = st.executeQuery("select * from ogrenciler");

        //5. Sonuçları al
        while (veri.next()) {

            //System.out.println(veri.getString(1)+" - "+veri.getString(2)+
            //                   " - "+veri.getString(3)+" - "+veri.getString(1));

            //Sutun ismi kullanarak getirme
            System.out.printf("%-6d %-15.15s %-8s %-8s\n", veri.getInt(1), veri.getString(2), veri.getString(3), veri.getString(4));
        }

        //6. Kapatma
        con.close();
        st.close();
        veri.close();

    }
}
