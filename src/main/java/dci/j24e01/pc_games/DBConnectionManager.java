package dci.j24e01.pc_games;

import java.sql.Connection;

public interface DBConnectionManager {
    Connection getConnection();
}