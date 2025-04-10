/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dbconecction;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author alumno
 */
public class DBConnection {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/seguridad_db";
        String username = "root";
        String password = "root";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            
            System.out.println("Pueden Comenzar las operaciones con la base de datos.");
            
            System.out.print("Introduce tu nombre de usuario: ");
            String user = sc.nextLine();

            System.out.print("Introduce tu password: ");
            String pass = sc.nextLine();
                
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Inicio de sesion correcto.");
            } else {
               System.out.println("Usuario o password incorrectos.");
            }
            
                                
                
        } catch (SQLException ex) {
            System.err.println("Error al realizar la consulta: " +
            ex.getMessage());
        }
        
    }
    
}
