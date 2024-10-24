package dci.j24e01.pc_games;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionManager implements DBConnectionManager {
    @Override
    public Connection getConnection() {

        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project_test",
                    "root",
                    "dudeWTF?"
            );

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
