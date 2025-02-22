package com.example.ch4.bd;

import java.sql.*;

public class JDBCBasicExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/mydb";
        String user = "root";
        String password = "mfmf135llch";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("연결 성공");

            String sqlInsert = "INSERT INTO user(name, email) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sqlInsert);

            pstmt.setString(1, "Alice");
            pstmt.setString(2, "alice@example.com");
            pstmt.executeUpdate();

            pstmt.setString(1, "Bob");
            pstmt.setString(2, "bob@example.com");
            pstmt.executeUpdate();

            pstmt.setString(1, "Charlie");
            pstmt.setString(2, "charlie@example.com");
            pstmt.executeUpdate();

            System.out.println("데이터 INSERT 완료");

            String sqlSelect = "SELECT * FROM user";
            pstmt = conn.prepareStatement(sqlSelect);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println(id + " : " + name + " : " + email);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("종료");
        }
    }
}
