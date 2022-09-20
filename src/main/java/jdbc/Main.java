package jdbc;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        // DBWork objesi olustur
        DBWork db = new DBWork();

        // Connection methodunu cagir
        Connection con = db.connect_to_db("jdbc2022","postgres","4952");

        //Yeni table olusturma methodunu cagir
        db.createTable(con, "employees");

    }
}
