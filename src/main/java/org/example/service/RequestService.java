package org.example.service;

import org.example.dao.DAOException;
import org.example.dao.IRequestDAO;
import org.example.dao.RequestDAOMySql;
import org.example.entities.Request;

import java.util.ArrayList;

public class RequestService {
    private IRequestDAO requestDAO;

    public RequestService(){
        requestDAO = new RequestDAOMySql();
    }

    public void guardarRequest(Request request) throws ServiceException{
        try {
            requestDAO.guardar(request);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
    public void eliminarRequest(int id) throws ServiceException {
        try {
            requestDAO.eliminar(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public Request buscarServerRequest(int id) throws ServiceException {
        try {
            return requestDAO.buscar(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList buscarTodos() throws ServiceException {
        try {
            return requestDAO.buscarTodos();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarRequest(Request request) throws ServiceException {
        try {
            requestDAO.modificar(request);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }


}