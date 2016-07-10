package x.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Map;
import java.util.TreeSet;

/**
 * Connector to SQLite embedded database.
 */
public class DbConnect {
  private static final Logger LOGGER = LoggerFactory.getLogger(DbConnect.class);
  /**
   * Singleton
   */
  private static final DbConnect INSTANCE = new DbConnect();

  public static DbConnect getInstance() {
    return INSTANCE;
  }

  private DbConnect() {
    initDB();
  }


  private void createDomainTable() {
    try (Connection conn = createConnection()) {
      Statement stmt = conn.createStatement();
      stmt.execute("CREATE TABLE IF NOT EXISTS  domains (domain text, count int)");
    } catch (SQLException e) {
      LOGGER.debug("Table domains problem");
    } catch (Exception e) {
      LOGGER.error("DB problem", e);
    }
  }

  private Connection createConnection()
      throws ClassNotFoundException, SQLException {
    Class.forName("org.sqlite.JDBC");
    return DriverManager.getConnection("jdbc:sqlite:storm.db");
  }

  private void initDB() {
    createDomainTable();
  }

  public void saveDomainCounts(Map<String, Integer> counts) {
    // this will create multiple entries per domain, 
    // but in NoSQL this would be just right
    try (Connection conn = createConnection()) {
      String sql = "INSERT INTO domains (domain, count) values(?, ?)";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      TreeSet<String> countSet = new TreeSet(counts.keySet());
      for (String domain : countSet) {
        int index = 0;
        pstmt.setString(++index, domain);
        pstmt.setInt(++index, counts.get(domain));
        pstmt.executeUpdate();
      }
    } catch (Exception e) {
      LOGGER.error("DB problem", e);
    }
  }
  public int getNumberEntries() {
    int entries = 0;
    try (Connection conn = createConnection()) {
      String sql = "SELECT COUNT (*) FROM domains";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        entries = rs.getInt(1);
      }
    } catch (Exception e) {
      LOGGER.error("DB problem", e);
    }
    return entries;
  }
}
