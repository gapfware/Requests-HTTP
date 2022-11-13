package org.example.GUI;

import org.example.entities.ServerDELETERequest;
import org.example.entities.ServerGETRequest;
import org.example.entities.ServerPOSTRequest;
import org.example.entities.ServerPUTRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PanelRequestExecution extends JPanel {
    //Paneles
    private JPanel panelComponentes;
    private JPanel panelBotonera;
    private JPanel panelSeleccion;
    private PanelManager panelManager;

    //Componentes

    JLabel jLabelUrl;
    JLabel jLabelStatusCode;
    JLabel jLabelHeaderResponse;
    JLabel jLabelBodyResponse;

    JTextField jTextFieldUrl;
    JTextArea jTextAreaStatusCode;
    JTextArea jTextAreaHeaderResponse;
    JTextArea jTextAreaBodyResponse;
    JScrollPane jScrollPaneHeaderResponse;
    JScrollPane jScrollPaneBodyResponse;

    //Componentes Seleccion
    JComboBox jComboBoxSeleccion;

    //Componentes Botonera
    JButton jButtonEjecutar;
    JButton jButtonLimpiar;
    JButton jButtonFavoritos;

    public void vaciarComponentes() {
        jTextFieldUrl.setText("");
        jTextAreaStatusCode.setText("");
        jTextAreaBodyResponse.setText("");
        jTextAreaHeaderResponse.setText("");
    }

    public void armarRequestExecution(PanelManager panelManager) {
        this.panelManager = panelManager;
        setLayout(new BorderLayout());

//
        jLabelUrl = new JLabel("URL: ");
        jLabelStatusCode = new JLabel("STATUS CODE: ");
        jLabelHeaderResponse = new JLabel("HEADERS");
        jLabelBodyResponse = new JLabel("BODY:");
        jTextFieldUrl = new JTextField(10); //Maxima cantidad de caracteres permitidos en internet explorer
        jTextAreaStatusCode = new JTextArea();
        jTextAreaStatusCode.setEditable(false);

        jTextAreaHeaderResponse = new JTextArea(50, 50);
        jScrollPaneHeaderResponse = new JScrollPane(jTextAreaHeaderResponse);
        jScrollPaneHeaderResponse.setBounds(200, 200, 300, 350);

        jTextAreaStatusCode.setEditable(false);
        jTextAreaHeaderResponse.setEditable(false);

        jTextAreaBodyResponse = new JTextArea(80, 80);
        jTextAreaBodyResponse.setBounds(100, 100, 200, 20);
        jScrollPaneBodyResponse = new JScrollPane(jTextAreaBodyResponse);
        jScrollPaneBodyResponse.setBounds(100, 100, 300, 150);
        jTextAreaBodyResponse.setEditable(false);

        panelComponentes = new JPanel();
        panelComponentes.setLayout(new GridLayout(2, 2));

        panelComponentes.add(jLabelHeaderResponse);

        panelComponentes.add(jScrollPaneHeaderResponse);

        panelComponentes.add(jLabelBodyResponse);
        panelComponentes.add(jScrollPaneBodyResponse);

        this.setLayout(new BorderLayout());
        add(panelComponentes, BorderLayout.CENTER);

        panelBotonera = new JPanel();
        jButtonEjecutar = new JButton("Ejecutar");
        jButtonLimpiar = new JButton("Limpiar");
        jButtonFavoritos = new JButton("IR A FAVORITOS");
        panelBotonera.add(jButtonEjecutar);
        panelBotonera.add(jButtonLimpiar);
        panelBotonera.add(jButtonFavoritos);
        add(panelBotonera, BorderLayout.SOUTH);

        panelSeleccion = new JPanel();
        jComboBoxSeleccion = new JComboBox();
        jComboBoxSeleccion.addItem("GET");
        jComboBoxSeleccion.addItem("POST");
        jComboBoxSeleccion.addItem("PUT");
        jComboBoxSeleccion.addItem("DELETE");
        panelSeleccion.add(jComboBoxSeleccion);
        panelSeleccion.add(jLabelUrl);
        panelSeleccion.add(jTextFieldUrl);
        panelSeleccion.setLayout(new FlowLayout());
        add(panelSeleccion, BorderLayout.NORTH);

        jButtonEjecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String urlValue = jTextFieldUrl.getText(); //capturo lo que guardo en la variable
                //TODO PASAR A SWITCH
                String selectedMethod = String.valueOf(jComboBoxSeleccion.getSelectedItem());
                switch(selectedMethod){

                    case "GET":
                        ServerGETRequest serverGETRequest = new ServerGETRequest();
                        try {
                            serverGETRequest.sendGETRequest(urlValue);

                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null , "HA SUCEDIDO UN ERROR AL EJECUTAR SU PROGRAMA" + ex);
                            throw new RuntimeException(ex);
                        }
                        JOptionPane.showMessageDialog(null, "STATUS CODE: " + serverGETRequest.getStatusCodeResponse()); //Muestro Una Alerta
                        jTextAreaHeaderResponse.setText(serverGETRequest.getHeadersResponse());
                        jTextAreaBodyResponse.setText(serverGETRequest.getBodyResponse());
                        break;

                    case "POST":
                        ServerPOSTRequest postRequest = new ServerPOSTRequest();
                        try {
                            postRequest.sendPOSTRequest(urlValue);

                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null , "HA SUCEDIDO UN ERROR AL EJECUTAR SU PROGRAMA" + ex);
                            throw new RuntimeException(ex);
                        }
                        JOptionPane.showMessageDialog(null , "Status Code: " + postRequest.getStatusCodeResponse());
                        jTextAreaHeaderResponse.setText(postRequest.getHeadersResponse());
                        jTextAreaBodyResponse.setText(postRequest.getBodyResponse());
                        break;

                    case "PUT":
                        ServerPUTRequest putRequest = new ServerPUTRequest();
                        try{
                            putRequest.sendPUTRequest(urlValue);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null , "HA SUCEDIDO UN ERROR AL EJECUTAR SU PROGRAMA" + ex);
                            throw new RuntimeException(ex);
                        }
                        JOptionPane.showMessageDialog(null , "Status Code: " + putRequest.getStatusCodeResponse());
                        jTextAreaHeaderResponse.setText(putRequest.getHeadersResponse());
                        jTextAreaBodyResponse.setText(putRequest.getBodyResponse());
                        break;

                    case "DELETE":
                        ServerDELETERequest deleteRequest = new ServerDELETERequest();
                        try{
                            deleteRequest.sendDELETERequest(urlValue);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null , "HA SUCEDIDO UN ERROR AL EJECUTAR SU PROGRAMA" + ex);
                            throw new RuntimeException(ex);
                        }
                        JOptionPane.showMessageDialog(null , "Status Code: " + deleteRequest.getStatusCodeResponse());
                        jTextAreaHeaderResponse.setText(deleteRequest.getHeadersResponse());
                        jTextAreaBodyResponse.setText(deleteRequest.getBodyResponse());
                        break;

                }
            }

        });

        jButtonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldUrl.setText("");
                jTextAreaStatusCode.setText("");
                jTextAreaHeaderResponse.setText("");
                jTextAreaBodyResponse.setText("");
            }
        });

        PanelRequestExecution panel = this;
        jButtonFavoritos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarListadoFavoritos();

            }
        });
    }
}