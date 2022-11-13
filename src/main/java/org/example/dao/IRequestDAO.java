package org.example.dao;

import org.example.entities.Request;

import java.util.ArrayList;

public interface IRequestDAO {
    public void guardar(Request request) throws DAOException;
    public void modificar(Request request) throws DAOException;
    public void eliminar(int id) throws DAOException;
    public Request buscar(int id) throws DAOException;
    public ArrayList buscarTodos() throws DAOException;
}
