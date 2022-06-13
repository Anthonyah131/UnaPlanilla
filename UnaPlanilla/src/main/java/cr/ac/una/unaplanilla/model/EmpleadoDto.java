/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Carlos
 */
public class EmpleadoDto {

    public SimpleStringProperty id;
    public SimpleStringProperty nombre;
    public SimpleStringProperty pApellido;
    public SimpleStringProperty sApellido;
    public SimpleStringProperty cedula;
    public ObjectProperty<String> genero;
    public SimpleStringProperty correo;
    public SimpleBooleanProperty administrador;
    public SimpleStringProperty usuario;
    public SimpleStringProperty clave;
    public ObjectProperty<LocalDate> fIngreso;
    public ObjectProperty<LocalDate> fSalida;
    public SimpleBooleanProperty estado;
    public Long version;
    private Boolean modificado;
    
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public EmpleadoDto(Empleado empleado) {
        this();
        this.id.set(empleado.getId().toString());
        this.nombre.set(empleado.getNombre());
        this.pApellido.set(empleado.getPApellido());
        this.sApellido.set(empleado.getSApellido());
        this.cedula.set(empleado.getCedula());
        this.genero.set(empleado.getGenero());
        this.correo.set(empleado.getCorreo());
        this.administrador.setValue(empleado.getAdministrador().equalsIgnoreCase("S"));
        this.usuario.set(empleado.getUsuario());
        this.clave.set(empleado.getClave());
        this.fIngreso.set(empleado.getFechaIngreso().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        if (empleado.getFechaSalida() != null) {
	    this.fSalida.set(empleado.getFechaSalida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}else{
	    this.fSalida.set(null);
	}
        this.estado.setValue(empleado.getEstado().equalsIgnoreCase("A"));
        this.version = empleado.getVersion();
    }
    

    public EmpleadoDto() {
        this.modificado = false;
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.pApellido = new SimpleStringProperty();
        this.sApellido = new SimpleStringProperty();
        this.cedula = new SimpleStringProperty();
        this.genero = new SimpleObjectProperty("M");
        this.correo = new SimpleStringProperty();
        this.administrador = new SimpleBooleanProperty(false);
        this.usuario = new SimpleStringProperty();
        this.clave = new SimpleStringProperty();
        this.fIngreso = new SimpleObjectProperty();
        this.fSalida = new SimpleObjectProperty();
        this.estado = new SimpleBooleanProperty(true);

    }

    public Long getId() {
        if(id.get()!=null && !id.get().isEmpty())
            return Long.valueOf(id.get());
        else
            return null;
    }

    public void setId(Long empId) {
        this.id.set(empId.toString());
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String empNombre) {
        this.nombre.set(empNombre);
    }

    public String getPApellido() {
        return pApellido.get();
    }

    public void setPApellido(String empPApellido) {
        this.pApellido.set(empPApellido);
    }

    public String getSApellido() {
        return sApellido.get();
    }

    public void setSApellido(String empSApellido) {
        this.sApellido.set(empSApellido);
    }

    public String getCedula() {
        return cedula.get();
    }

    public void setCedula(String empCedula) {
        this.cedula.set(empCedula);
    }

    public String getGenero() {
        return genero.get();
    }

    public void setGenero(String empGenero) {
        this.genero.set(empGenero);
    }

    public String getCorreo() {
        return correo.get();
    }

    public void setCorreo(String empCorreo) {
        this.correo.set(empCorreo);
    }

    public String getAdministrador() {
        return administrador.getValue() ? "S" : "N";
    }

    public void setAdministrador(String empAdministrador) {
        this.administrador.setValue(empAdministrador.equalsIgnoreCase("S"));
    }

    public String getUsuario() {
        return usuario.get();
    }

    public void setUsuario(String empUsuario) {
        this.usuario.set(empUsuario);
    }

    public String getClave() {
        return clave.get();
    }

    public void setClave(String empClave) {
        this.clave.set(empClave);
    }

    public LocalDate getFIngreso() {
        return fIngreso.get();
    }

    public void setEmpFIngreso(LocalDate empFingreso) {
        this.fIngreso.set(empFingreso);
    }

    public LocalDate getFSalida() {
        return fSalida.get();
    }

    public void setFSalida(LocalDate empFsalida) {
        this.fSalida.set(empFsalida);
    }

    public String getEstado() {
        return estado.getValue()?"A":"I";
    }

    public void setEstado(String empEstado) {
        this.estado.setValue(empEstado.equalsIgnoreCase("A"));
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "EmpleadoDto{" + "empId=" + id + ", empNombre=" + nombre + ", empPapellido=" + pApellido + ", empSapellido=" + sApellido + ", empCedula=" + cedula + ", empGenero=" + genero + ", empCorreo=" + correo + ", empAdministrador=" + administrador + ", empUsuario=" + usuario + ", empClave=" + clave + ", empFingreso=" + fIngreso + ", empFsalida=" + fSalida + ", empEstado=" + estado + '}';
    }
    
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        final EmpleadoDto other = (EmpleadoDto) obj;
        return Objects.equals(this.id.get(), other.id.get()) && Objects.equals(this.nombre.get(), other.nombre.get());
    }

}
