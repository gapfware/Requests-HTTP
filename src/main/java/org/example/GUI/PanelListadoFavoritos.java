package org.example.GUI;

import org.example.entities.Request;
import org.example.service.RequestService;
import org.example.service.ServiceException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelListadoFavoritos extends JPanel {
    private JTable jtable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane scrollPane;
    private JPanel panelBotonera;

    JButton jButtonEliminar;
    JButton jButtonModificar;
    JButton jButtonNuevo;
    private PanelManager panelManager;

    public void armarPanelListadoFavoritos(PanelManager panelManager){
        this.panelManager = panelManager;
        setLayout(new BorderLayout());

        contenidoTabla = new DefaultTableModel();
        jtable = new JTable(contenidoTabla);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jtable);

        contenidoTabla.addColumn("ID");
        contenidoTabla.addColumn("URL");
        contenidoTabla.addColumn("METODO");

        RequestService requestService = new RequestService();

        ArrayList listaRequest = null;
        try {
            listaRequest = requestService.buscarTodos();
        } catch (ServiceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Ha sucedido un error al traer las Request. Por favor contactar al administrador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        for(Object obj: listaRequest){
            Request request = (Request) obj;
            Object [] row = new Object[3];
            row[0] = request.getId();
            row[1] = request.getUrl();
            row[2] = request.getMethod();
            contenidoTabla.addRow(row);
        }

        //Botonera
        panelBotonera = new JPanel();
        jButtonNuevo = new JButton("Nuevo");
        jButtonEliminar = new JButton("Eliminar");
        jButtonModificar = new JButton("Modificar");
        panelBotonera.add(jButtonNuevo);
        panelBotonera.add(jButtonModificar);
        panelBotonera.add(jButtonEliminar);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotonera, BorderLayout.SOUTH);


        jButtonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RequestService requestService1 = new RequestService();

                int idEliminar = (int) jtable.getValueAt(jtable.getSelectedRow(), 0);

                try {
                    requestService1.eliminarRequest(idEliminar);
                    refrezcarListado();

                } catch (ServiceException serviceException) {
                    serviceException.printStackTrace();
                    //Dialog
                    JOptionPane.showMessageDialog(null, "Ha sucedido un error al eliminar un URL request. Por favor contactar al administrador: " + serviceException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }
            }});
        jButtonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int idModificar = (int) jtable.getValueAt(jtable.getSelectedRow(), 0);
                RequestService requestService1 = new RequestService();

                Request request = null;
                try {
                    //lo buscamos
                    request = requestService1.buscarServerRequest(idModificar);
                } catch (ServiceException serviceException) {
                    serviceException.printStackTrace();
                    //Dialog
                    JOptionPane.showMessageDialog(null, "Ha sucedido un error al traer un request para modificarlo. Por favor contactar al administrador: " + serviceException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }
                panelManager.mostrarFormularioFavoritos(request);
            }
        });

        PanelListadoFavoritos panel = this;
        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarFormularioFavoritos();

            }
        });


    }
    public void refrezcarListado() {
        removeAll();
        armarPanelListadoFavoritos(panelManager);
        validate();
        repaint();

    }
}
