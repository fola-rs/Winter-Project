package hellofx;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    // Database file path
    private static final String DB_URL = "jdbc:sqlite:grocery.db";

    // Connection object
    private Connection conn;

    // Method to establish a database connection
    public Connection connect() {
        try {
            // Load the SQLite JDBC driver (if needed)
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the database
            conn = DriverManager.getConnection(DB_URL);

            System.out.println("Connected to the database successfully.");

            // Create the 'lists' table if it doesn't exist
            createTable();
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found.");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return conn;
    }

    // Method to close the database connection
    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing the database connection: " + e.getMessage());
        }
    }

    // Method to create the 'lists' table
    private void createTable() throws SQLException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS lists (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                content TEXT NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
            """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Table 'lists' created successfully.");
        }
    }

    // Method to insert a new list into the database
    public void saveList(String title, String content) {
        String insertSQL = "INSERT INTO lists (title, content) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.executeUpdate();
            System.out.println("List saved successfully.");
        } catch (SQLException e) {
            System.err.println("Error saving list: " + e.getMessage());
        }
    }

    // Method to load all lists from the database
    public List<Map<String, Object>> loadLists() {
        List<Map<String, Object>> lists = new ArrayList<>();
        String selectSQL = "SELECT * FROM lists ORDER BY created_at DESC";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                Map<String, Object> list = new HashMap<>();
                list.put("id", rs.getInt("id"));
                list.put("title", rs.getString("title"));
                list.put("content", rs.getString("content"));
                list.put("created_at", rs.getString("created_at"));
                lists.add(list);
            }
        } catch (SQLException e) {
            System.err.println("Error loading lists: " + e.getMessage());
        }
        return lists;
    }

    // Method to update an existing list
    public void updateList(int id, String title, String content) {
        String updateSQL = "UPDATE lists SET title = ?, content = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("List updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating list: " + e.getMessage());
        }
    }

    // Method to delete a list by ID
    public void deleteList(int id) {
        String deleteSQL = "DELETE FROM lists WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("List deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Error deleting list: " + e.getMessage());
        }
    }
}