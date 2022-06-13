/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanilla.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ANTHONY
 */
public class TipoPlanillaDto {
    public SimpleStringProperty id;
    public SimpleStringProperty codigo;
    public SimpleStringProperty descripcion;
    public SimpleStringProperty planillasPorMes;
    private Integer anoUltimaPlanilla;
    private Integer mesUltimaPlanilla;
    private Integer numeroUltimaPlanilla;
    public SimpleBooleanProperty estado;
    private Boolean modificado;
    ObservableList<EmpleadoDto> empleados;
    List<EmpleadoDto> empleadosEliminados;

    public TipoPlanillaDto() {
        this.modificado = false;
        this.id = new SimpleStringProperty();
        this.codigo = new SimpleStringProperty();
        this.descripcion = new SimpleStringProperty();
        this.planillasPorMes = new SimpleStringProperty();
        this.estado = new SimpleBooleanProperty(true);
        empleados = FXCollections.observableArrayList();
        empleadosEliminados = new ArrayList<>();
    }

    public TipoPlanillaDto(TipoPlanilla tipoPlanilla) {
        this();
        this.id.set(tipoPlanilla.getId().toString());
        this.codigo.set(tipoPlanilla.getCodigo());
        this.descripcion.set(tipoPlanilla.getDescripcion());
        this.planillasPorMes.set(tipoPlanilla.getPlanillasMes().toString());
        this.anoUltimaPlanilla = tipoPlanilla.getAnoUltPla();
        this.mesUltimaPlanilla = tipoPlanilla.getMesUltPla();
        this.numeroUltimaPlanilla = tipoPlanilla.getNumUltPla();
        this.estado.setValue(tipoPlanilla.getEstado().equalsIgnoreCase("A"));
    }

    public Long getId() {
        if(id.get()!=null && !id.get().isEmpty())
            return Long.valueOf(id.get());
        else
            return null;
    }

    public void setId(Long tplaId) {
        this.id.set(tplaId.toString());
    }

    public String getCodigo() {
        return codigo.get();
    }

    public void setCodigo(String tplaCodigo) {
        this.codigo.set(tplaCodigo);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String tplaDescripcion) {
        this.descripcion.set(tplaDescripcion);
    }

    public Integer getPlanillasPorMes() {
        if(planillasPorMes.get()!=null && !planillasPorMes.get().isEmpty())
            return Integer.valueOf(planillasPorMes.get());
        else
            return null;
    }

    public void setPlanillasPorMes(Integer tplaPlaxmes) {
        this.planillasPorMes.set(tplaPlaxmes.toString());
    }

    public Integer getAnoUltimaPlanilla() {
        return anoUltimaPlanilla;
    }

    public void setAnoUltimaPlanilla(Integer anoUltimaPlanilla) {
        this.anoUltimaPlanilla = anoUltimaPlanilla;
    }

    public Integer getMesUltimaPlanilla() {
        return mesUltimaPlanilla;
    }

    public void setMesUltimaPlanilla(Integer mesUltimaPlanilla) {
        this.mesUltimaPlanilla = mesUltimaPlanilla;
    }

    public Integer getNumeroUltimaPlanilla() {
        return numeroUltimaPlanilla;
    }

    public void setNumeroUltimaPlanilla(Integer numeroUltimaPlanilla) {
        this.numeroUltimaPlanilla = numeroUltimaPlanilla;
    }

    public String getEstado() {
        return estado.getValue()?"A":"I";
    }

    public void setEstado(String tplaEstado) {
        this.estado.setValue(tplaEstado.equalsIgnoreCase("A"));
    }
    
    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public ObservableList<EmpleadoDto> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ObservableList<EmpleadoDto> empleados) {
        this.empleados = empleados;
    }

    public List<EmpleadoDto> getEmpleadosEliminados() {
        return empleadosEliminados;
    }

    public void setEmpleadosEliminados(List<EmpleadoDto> empleadosEliminados) {
        this.empleadosEliminados = empleadosEliminados;
    }

    @Override
    public String toString() {
        return "TipoPlanillaDto{" + "id=" + id.get() + ", codigo=" + codigo.getName() + '}';
    }
    
}
