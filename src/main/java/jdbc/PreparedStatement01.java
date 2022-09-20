package jdbc;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc2022", "postgres", "4952");
        Statement st = con.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //1.Adım: Prepared statement query'sini olustur
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";

        //2.Adım: PreparedStatement objesini oluştur.
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3.Adım: set() methodları ile soru işaretleri için değer gir
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");

        //4.Adım: Execute query
        int updateRowSayisi = pst1.executeUpdate();
        System.out.println("updateRowSayisi = " + updateRowSayisi);

        String sql2 = "SELECT * FROM companies";
        ResultSet result1 = st.executeQuery(sql2);

        while (result1.next()) {

            System.out.println(result1.getInt(1) + " " +
                    result1.getString(2) + " " +
                    result1.getInt(3));

        }

        //GOOGLE için değişiklik
        pst1.setInt(1, 15000);
        pst1.setString(2, "GOOGLE");

        int updateRowSayisi2 = pst1.executeUpdate();
        System.out.println("updateRowSayisi = " + updateRowSayisi);

        sql2 = "SELECT * FROM companies";
        ResultSet result2 = st.executeQuery(sql2);

        while (result2.next()) {

            System.out.println(result2.getInt(1) + " " +
                    result2.getString(2) + " " +
                    result2.getInt(3));

        }
        System.out.println();
        //2. Örnek: "SELECT * FROM <table name>" query'sini prepared statement ile kullanın.
        read_data(con, "companies");
    }

    //Bir tablonun istenilen datasini prepared statement ile cagirmak icin kullanilan method
    public static void read_data(Connection con, String tableName) {

        try {
            String query = String.format("SELECT * FROM %s", tableName); // format() methodu dinamik String olusturmak icin kullanilir
            //SQL query'i calistir
            Statement statement = con.createStatement();
            ResultSet rS = statement.executeQuery(query); // Datayi cagirip ResultSet konteynirina koyuyoruz
            while (rS.next()) { // Tum datayi cagiralim

                System.out.println(rS.getInt(1) + " " +
                        rS.getString(2) + " " +
                        rS.getInt(3));

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
