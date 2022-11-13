package org.example.GUI;

import org.example.entities.Request;
import org.example.entities.ServerGETRequest;

import javax.swing.*;

public class PanelManager {
    private PanelListadoFavoritos panelListadoFavoritos;
    private PanelFormularioFavoritos panelFormularioFavoritos;
    private PanelRequestExecution panelRequestExecution;


    private JFrame frame;

    public void armarPanelManager(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelRequestExecution = new PanelRequestExecution();
        panelRequestExecution.armarRequestExecution(this);

        panelFormularioFavoritos = new PanelFormularioFavoritos();
        panelFormularioFavoritos.armarPanelFormularioFavoritos(this);

        panelListadoFavoritos = new PanelListadoFavoritos();
        panelListadoFavoritos.armarPanelListadoFavoritos(this);

        frame.setVisible(true);
        frame.pack();
    }

    public void mostrarRequestExecution() {
        panelRequestExecution.vaciarComponentes();
        mostrar(panelRequestExecution);
    }

    /*public void mostrarRequestExecution(ServerGETRequest serverGETRequest){
        panelRequestExecution.armarPanelRequestExecution(serverGETRequest);
        mostrar(panelRequestExecution);
    }*/
    public void mostrarFormularioFavoritos(){

        panelFormularioFavoritos.vaciarComponentes();
        mostrar(panelFormularioFavoritos);
    }

    public void mostrarFormularioFavoritos(Request request){
        panelFormularioFavoritos.armarPanelFormularioFavoritos(request);
        mostrar(panelFormularioFavoritos);
    }

    public void mostrarListadoFavoritos(){
        panelListadoFavoritos.refrezcarListado();
        mostrar(panelListadoFavoritos);
    }

    private void mostrar(JPanel panel){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        frame.pack();
    };
}
