package org.example.dao;

import org.example.entities.Request;

import java.sql.*;
import java.util.ArrayList;

public class RequestDAOMySql implements IRequestDAO {

    private String DB_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost:3306/?user=root/";
    private String DB_USER = "root";
    private String DB_PASSWORD = "twister2003";


    @Override
    public void guardar(Request request) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("INSERT INTO POSTMANLAB.FAVORITOS (ID, URL, METODO) VALUES(?,?,?)");
            preparedStatement.setInt(1, request.getId());
            preparedStatement.setString(2, request.getUrl());
            preparedStatement.setString(3, request.getMethod());

            //3 Ejecutar la sentencia
            int i = preparedStatement.executeUpdate();

            //4 Evaluar resultados
            System.out.println("Registros insertados: " + i);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            try {
                //5 Cerrar conexion
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
                throw new DAOException(e2.getMessage());
            }
        }

    }

    @Override
    public void modificar(Request request) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("UPDATE POSTMANLAB.FAVORITOS SET URL = ?, METODO = ? WHERE ID = ? ");
            preparedStatement.setString(1, request.getUrl());
            preparedStatement.setString(2, request.getMethod());
            preparedStatement.setInt(3, request.getId());

            //3 Ejecutar la sentencia
            int i = preparedStatement.executeUpdate();

            //4 Evaluar resultados
            System.out.println("Registros modificados: " + i);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            try {
                //5 Cerrar conexion
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
                throw new DAOException(e2.getMessage());
            }
        }

    }

    @Override
    public void eliminar(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("DELETE FROM POSTMANLAB.FAVORITOS WHERE ID=?");
            preparedStatement.setInt(1, id);


            //3 Ejecutar la sentencia
            int i = preparedStatement.executeUpdate();

            //4 Evaluar resultados
            System.out.println("Registros eliminados: " + i);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            try {
                //5 Cerrar conexion
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
                throw new DAOException(e2.getMessage());
            }
        }
    }

    @Override
    public Request buscar(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Request request = null;

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("SELECT * FROM POSTMANLAB.FAVORITOS WHERE id=?");
            preparedStatement.setInt(1, id);

            //3 Ejecutar la sentencia
            ResultSet rs = preparedStatement.executeQuery();

            //4 Evaluamos resultados del result set
            while (rs.next()) {
                request = new Request();
                request.setId(rs.getInt("ID"));
                request.setUrl(rs.getString("URL"));
                request.setMethod(rs.getString("METODO"));
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            try {
                //5 Cerrar conexion
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
                throw new DAOException(e2.getMessage());
            }
        }

        return request;
    }

    @Override
    public ArrayList buscarTodos() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Request request = null;
        ArrayList listaRequests = new ArrayList();

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //2 crear sentencia SQL
            preparedStatement = connection.prepareStatement("SELECT * FROM POSTMANLAB.FAVORITOS");


            //3 Ejecutar la sentencia
            ResultSet rs = preparedStatement.executeQuery();

            //4 Evaluamos resultados del result set
            while (rs.next()) {
                request = new Request();
                request.setMethod(rs.getString("METODO"));
                request.setId(rs.getInt("ID"));
                request.setUrl(rs.getString("URL"));
                listaRequests.add(request);

            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            try {
                //5 Cerrar conexion
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
                throw new DAOException(e2.getMessage());
            }
        }
        return listaRequests;

    }
}