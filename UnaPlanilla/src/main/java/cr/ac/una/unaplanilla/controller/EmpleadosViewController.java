/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.unaplanilla.model.EmpleadoDto;
import cr.ac.una.unaplanilla.service.EmpleadoService;
import cr.ac.una.unaplanilla.util.BindingUtils;
import cr.ac.una.unaplanilla.util.FlowController;
import cr.ac.una.unaplanilla.util.Formato;
import cr.ac.una.unaplanilla.util.Mensaje;
import cr.ac.una.unaplanilla.util.Respuesta;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class EmpleadosViewController extends Controller implements Initializable {

    private JFXTextField txtID;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtCedula;
    @FXML
    private JFXRadioButton rdbMasculino;
    @FXML
    private ToggleGroup tggGenero;
    private JFXRadioButton rdbFemennino;
    @FXML
    private JFXCheckBox chkAdministrador;
    @FXML
    private JFXCheckBox chkActivo;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXPasswordField txtClave;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXRadioButton rdbFemenino;
    @FXML
    private JFXTextField txtPApellido;
    @FXML
    private JFXTextField txtSApellido;
    @FXML
    private JFXDatePicker dtpFIngreso;
    @FXML
    private JFXDatePicker dtpFSalida;
    
    EmpleadoDto empleado;
    List<Node> requeridos = new ArrayList<>(); 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rdbMasculino.setUserData("M");
        rdbFemenino.setUserData("F");
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtPApellido.setTextFormatter(Formato.getInstance().letrasFormat(15));
        txtSApellido.setTextFormatter(Formato.getInstance().letrasFormat(15));
        txtCedula.setTextFormatter(Formato.getInstance().cedulaFormat(40));
        txtCorreo.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        txtUsuario.setTextFormatter(Formato.getInstance().letrasFormat(15));
        txtClave.setTextFormatter(Formato.getInstance().maxLengthFormat(8));
        empleado = new EmpleadoDto();
        nuevoEmpleado();
        indicarRequeridos();
    }  
    
   public void indicarRequeridos(){
       requeridos.clear();
       requeridos.addAll(Arrays.asList(txtNombre, txtCedula, txtPApellido, dtpFIngreso));
   }
   
   private void bindEmpleado(Boolean nuevo) {
        if(!nuevo){
            txtId.textProperty().bind(empleado.id);
        }
        txtCedula.textProperty().bindBidirectional(empleado.cedula);
        txtNombre.textProperty().bindBidirectional(empleado.nombre);
        txtPApellido.textProperty().bindBidirectional(empleado.pApellido);
        txtSApellido.textProperty().bindBidirectional(empleado.sApellido);
        txtUsuario.textProperty().bindBidirectional(empleado.usuario);
        txtClave.textProperty().bindBidirectional(empleado.clave);
        txtCorreo.textProperty().bindBidirectional(empleado.correo);
        dtpFIngreso.valueProperty().bindBidirectional(empleado.fIngreso);
        dtpFSalida.valueProperty().bindBidirectional(empleado.fSalida);
        chkActivo.selectedProperty().bindBidirectional(empleado.estado);
        chkAdministrador.selectedProperty().bindBidirectional(empleado.administrador);
        BindingUtils.bindToggleGroupToProperty(tggGenero, empleado.genero);
    }

    private void unbindEmpleado() {
        txtId.textProperty().unbind();
        txtCedula.textProperty().unbindBidirectional(empleado.cedula);
        txtNombre.textProperty().unbindBidirectional(empleado.nombre);
        txtPApellido.textProperty().unbindBidirectional(empleado.pApellido);
        txtSApellido.textProperty().unbindBidirectional(empleado.sApellido);
        txtUsuario.textProperty().unbindBidirectional(empleado.usuario);
        txtClave.textProperty().unbindBidirectional(empleado.clave);
        txtCorreo.textProperty().unbindBidirectional(empleado.correo);
        dtpFIngreso.valueProperty().unbindBidirectional(empleado.fIngreso);
        dtpFSalida.valueProperty().unbindBidirectional(empleado.fSalida);
        chkActivo.selectedProperty().unbindBidirectional(empleado.estado);
        chkAdministrador.selectedProperty().unbindBidirectional(empleado.administrador);
        BindingUtils.unbindToggleGroupToProperty(tggGenero, empleado.genero);
    }

    private void nuevoEmpleado() {
        unbindEmpleado();
        empleado = new EmpleadoDto();
        bindEmpleado(true);
        validarAdministrador();
        txtId.clear();
        txtId.requestFocus();
    }
    
    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof JFXTextField && !((JFXTextField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && !((JFXPasswordField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXDatePicker && ((JFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXDatePicker) node).getAccessibleText();
                } else {
                    invalidos += "," + ((JFXDatePicker) node).getAccessibleText();
                }
                validos = false;
            } else if (node instanceof JFXComboBox && ((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((JFXComboBox) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }
    }
        private void validarAdministrador() {
        if (chkAdministrador.isSelected()) {
            requeridos.addAll(Arrays.asList(txtUsuario, txtClave));
            txtUsuario.setDisable(false);
            txtClave.setDisable(false);
        } else {
            requeridos.removeAll(Arrays.asList(txtUsuario, txtClave));
            txtUsuario.validate();
            txtClave.validate();
            txtUsuario.clear();
            txtUsuario.setDisable(true);
            txtClave.clear();
            txtClave.setDisable(true);
        }
    }

    @Override
    public void initialize() {
        
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar empleado", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevoEmpleado();
        }
    }

    @FXML
    private void onActionBtnBuscar(ActionEvent event) {
        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaEmpleados();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(),true);
        EmpleadoDto empleadoDto = (EmpleadoDto) busquedaController.getResultado();
        if (empleadoDto != null) {
            cargarEmpleado(empleadoDto.getId());
        }
    }

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {
        try {
            if (empleado.getId()== null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar empleado", getStage(), "Debe cargar el empleado que desea eliminar.");
            } else {

                EmpleadoService service = new EmpleadoService();
                Respuesta respuesta = service.eliminarEmpleado(empleado.getId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar empleado", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar empleado", getStage(), "Empleado eliminado correctamente.");
                    nuevoEmpleado();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadosViewController.class.getName()).log(Level.SEVERE, "Error eliminando el empleado.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar empleado", getStage(), "Ocurrio un error eliminando el empleado.");
        }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        try {
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empleado", getStage(), invalidos);
            } else {

                EmpleadoService service = new EmpleadoService();
                Respuesta respuesta = service.guardarEmpleado(empleado);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empleado", getStage(), respuesta.getMensaje());
                } else {
                    unbindEmpleado();
                    empleado = (EmpleadoDto) respuesta.getResultado("Empleado");
                    bindEmpleado(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar empleado", getStage(), "Empleado actualizado correctamente.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadosViewController.class.getName()).log(Level.SEVERE, "Error guardando el empleado.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empleado", getStage(), "Ocurrio un error guardando el empleado.");
        }
    }

    @FXML
    private void onActionChkAdministrador(ActionEvent event) {
        validarAdministrador();
    }

    @FXML
    private void onActionChkActivo(ActionEvent event) {
    }

    @FXML
    private void onKeyPressedTxtId(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtId.getText().isEmpty()) {
            cargarEmpleado(Long.valueOf(txtId.getText()));
        }
    }
    
    private void cargarEmpleado(Long id) {
        EmpleadoService service = new EmpleadoService();
        Respuesta respuesta = service.getEmpleado(id);

        if (respuesta.getEstado()) {
            unbindEmpleado();
            empleado = (EmpleadoDto) respuesta.getResultado("Empleado");
            bindEmpleado(false);
            validarAdministrador();
            validarRequeridos();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar empleado", getStage(), respuesta.getMensaje());
        }
    }
    
}
