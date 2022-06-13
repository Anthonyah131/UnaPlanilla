/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.unaplanilla.service.EmpleadoService;
import cr.ac.una.unaplanilla.util.AppContext;
import cr.ac.una.unaplanilla.util.FlowController;
import cr.ac.una.unaplanilla.util.Mensaje;
import cr.ac.una.unaplanilla.util.Respuesta;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class LoginViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXPasswordField txtClave;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnIngresar;
    @FXML
    private ImageView imvFondo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imvFondo.fitHeightProperty().bind(root.heightProperty());
        imvFondo.fitWidthProperty().bind(root.widthProperty());
    }    

    @Override
    public void initialize() {
        txtClave.clear();
        txtUsuario.clear();
    }

    @FXML
    private void onActionBtnCancelar(ActionEvent event) {
        //getStage().close();
        ((Stage) btnCancelar.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnIgresar(ActionEvent event) {
        try {
           if(txtUsuario.getText()== null || txtUsuario.getText().isEmpty()){
               new Mensaje().showModal(Alert.AlertType.ERROR, "Validacion de ususario", getStage() , "Es necesario digitar un usuario para poder ingresar al sistema");
           }
           else if(txtClave.getText()== null || txtClave.getText().isEmpty()){
               new Mensaje().showModal(Alert.AlertType.ERROR, "Validacion de ususario", (Stage)btnIngresar.getScene().getWindow() , "Es necesario digitar una contraseña para poder ingresar al sistema");
               
           }
           else{        
               EmpleadoService empleadoService = new EmpleadoService();
               Respuesta respuesta = empleadoService.getUsuario(txtUsuario.getText(), txtClave.getText());
                if(respuesta.getEstado()){
                    AppContext.getInstance().set("Usuario", respuesta.getResultado("Empleado"));
                    FlowController.getInstance().goMain();
                    getStage().close();
                } else {
                    new Mensaje().show(Alert.AlertType.ERROR, "Validación Usuario", respuesta.getMensaje());
                }
           }
            
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error ingresando.", ex);
        }
    }
    
}
