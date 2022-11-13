package org.example.GUI;

import org.example.entities.Request;
import org.example.service.RequestService;
import org.example.service.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFormularioFavoritos extends JPanel {
    private JPanel panelComponentes;
    private JPanel panelBotonera;
    private PanelManager panelManager;
    private boolean modificacion;

    JLabel jLabelUrl;
    JLabel jLabelMetodo;
    JLabel jLabelId;
    JTextField jTextFieldId;
    JTextField jTextFieldUrl;
    JTextField jTextFieldMetodo;
    JButton jButtonGuardar;
    JButton jButtonCancelar;


    public void armarPanelFormularioFavoritos(Request request){
        jTextFieldId.setText(String.valueOf(request.getId()));
        jTextFieldUrl.setText(String.valueOf(request.getUrl()));
        jTextFieldMetodo.setText(String.valueOf(request.getMethod()));
        modificacion = true;
    }

    public void vaciarComponentes(){
        jTextFieldId.setText("");
        jTextFieldUrl.setText("");
        jTextFieldMetodo.setText("");
    }

    public void armarPanelFormularioFavoritos(PanelManager panelManager){
        jLabelId = new JLabel("ID");
        jTextFieldId = new JTextField();

        jLabelUrl = new JLabel("Ingrese URL");
        jTextFieldUrl = new JTextField();

        jLabelMetodo = new JLabel("Metodo");
        jTextFieldMetodo = new JTextField();

        panelComponentes = new JPanel();
        panelComponentes.setLayout(new GridLayout(4, 10));


        panelComponentes.add(jLabelId);
        panelComponentes.add(jTextFieldId);

        panelComponentes.add(jLabelUrl);
        panelComponentes.add(jTextFieldUrl);

        JComboBox jComboBox = new JComboBox<>();
        jComboBox.addItem("GET");
        jComboBox.addItem("POST");
        jComboBox.addItem("PUT");
        jComboBox.addItem("DELETE");
        //jComboBox.getSelectedItem(); probar para la entrega final

        jTextFieldMetodo.setText(jTextFieldMetodo.getText().toUpperCase());
        panelComponentes.add(jLabelMetodo);
        panelComponentes.add(jComboBox);

        this.setLayout(new BorderLayout());
        add(panelComponentes, BorderLayout.CENTER);

        panelBotonera = new JPanel();
        jButtonGuardar = new JButton("Guardar ");
        jButtonCancelar = new JButton("Cancelar");
        panelBotonera.add(jButtonGuardar);
        panelBotonera.add(jButtonCancelar);
        add(panelBotonera, BorderLayout.SOUTH);

        jButtonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Request request = new Request();
                request.setId( Integer.parseInt(jTextFieldId.getText()));
                request.setUrl( jTextFieldUrl.getText() ); //AGARRO LA URL DEL TEXTFIELD
                request.setMethod((String) jComboBox.getSelectedItem()); //AGARRO METODO TEXTFIELD

                RequestService requestService = new RequestService();

                try {
                    if(modificacion)
                        requestService.modificarRequest(request);
                    else
                        requestService.guardarRequest(request);

                } catch (ServiceException serviceException) {
                    //Dialog
                    JOptionPane.showMessageDialog(null,"Ha sucedido un error al guardar o modificar un request. Por favor contactar al administrador: " + serviceException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                panelManager.mostrarListadoFavoritos();
            }
        });


        jButtonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
                panelManager.mostrarListadoFavoritos();
            }
        });
    }
}