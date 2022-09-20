package jdbc;

import java.sql.*;

public class CallableStatement01 {
    /*
    Java'da methodlar return type sahibi olsa da void olsa da method olarak adlandirilir
    SQL'de ise data return ediyorsa "function" denir. Return yapmiyorsa "procedure" diye adlandirilir
     */

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc2022", "postgres", "4952");
        Statement st = con.createStatement();

        //1. Örnek: İki parametre ile çalışıp bu parametreleri toplayarak return yapan bir fonksiyon oluşturun.

        //1. Adım: Fonksiyon kodunu yaz
        String sql1 = "CREATE OR REPLACE FUNCTION toplamaF(x NUMERIC, y NUMERIC)\n" +
                "RETURNs NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x + y;\n" +
                "\n" +
                "END\n" +
                "$$";

        //2. Adım: Fonksiyonu calistir
        st.execute(sql1);

        //3. Adım: Fonksiyonu cagir
        CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?, ?)}");

        //4. Adım: Return icin registerOutParameter() methodunu, parametreler icin set() methodlarindan uygun olanlari kullan
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2, 15);
        cst1.setInt(3, 25);

        //5. Adım: Calistirmak icin execute() methodunu kullan
        cst1.execute();

        //6. Adım: Sonucu cagirmak icin return data tipine gore "get()" methodlarindan uygun olani kullan
        System.out.println(cst1.getBigDecimal(1));

        //============================================================================================


        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.
        String sql2 = "CREATE OR REPLACE FUNCTION koniHacmi(r NUMERIC, h NUMERIC)\n" +
                "RETURNs NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14 * r * r * h / 3;\n" +
                "\n" +
                "END\n" +
                "$$";

        //2. Adım: Fonksiyonu calistir
        st.execute(sql2);

        //3. Adım: Fonksiyonu cagir
        CallableStatement cst2 = con.prepareCall("{? = call koniHacmi(?, ?)}");

        //4. Adım: Return icin registerOutParameter() methodunu, parametreler icin set() methodlarindan uygun olanlari kullan
        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2, 3);
        cst2.setInt(3, 5);

        //5. Adım: Calistirmak icin execute() methodunu kullan
        cst2.execute();

        //6. Adım: Sonucu cagirmak icin return data tipine gore "get()" methodlarindan uygun olani kullan
        System.out.println(cst2.getBigDecimal(1));
    }

}
