/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class ConnectionFactory {
    private static final String PROPERTIES_FILE = "db.properties";


    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();

        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new RuntimeException("No se encontró el archivo " + PROPERTIES_FILE);
            }

            props.load(input);

            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String driver = props.getProperty("driver");

            Class.forName(driver);

            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            throw new RuntimeException("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
