package jdbcPractise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Query04 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc2022",
                "postgres",
                "4952");
        PreparedStatement ps = con.prepareStatement("insert into ogrenciler values(?, ?, ?, ?)");
        /*
        Statement ve Prestatement arasindaki fark nedir?
        prestatement;
           1-daha dinamik
           2-daha az yer kapliyor
         */

        ps.setInt(1,200);
        ps.setString(2,"Veli Can");
        ps.setString(3,"12");
        ps.setString(4,"E");

        int veriSayisi = ps.executeUpdate();
        System.out.println(veriSayisi+" veri girisi yapildi.");


    }
}
