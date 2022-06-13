/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanilla.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author ANTHONY
 */
@Entity
@Table(name = "PLAM_EMPLEADOS", schema = "UNA")
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findById", query = "SELECT e FROM Empleado e WHERE e.id = :id"),
    @NamedQuery(name = "Empleado.findByUsuClave", query = "SELECT e FROM Empleado e WHERE e.usuario = :usuario AND e.clave = :clave"),
    @NamedQuery(name = "Empleado.findByCedNomPApe", query = "SELECT e FROM Empleado e WHERE UPPER(e.nombre) like :nombre and UPPER(e.cedula) like :cedula and UPPER(e.pApellido) like :pApellido and UPPER(e.sApellido) like :sApellido", hints = @QueryHint(name = "eclipselink.refresh", value = "true")),
})
    /*@NamedQuery(name = "Empleado.findByEmpNombre", query = "SELECT e FROM Empleado e WHERE e.empNombre = :empNombre"),
    @NamedQuery(name = "Empleado.findByEmpPapellido", query = "SELECT e FROM Empleado e WHERE e.empPapellido = :empPapellido"),
    @NamedQuery(name = "Empleado.findByEmpSapellido", query = "SELECT e FROM Empleado e WHERE e.empSapellido = :empSapellido"),
    @NamedQuery(name = "Empleado.findByEmpCedula", query = "SELECT e FROM Empleado e WHERE e.empCedula = :empCedula"),
    @NamedQuery(name = "Empleado.findByEmpGenero", query = "SELECT e FROM Empleado e WHERE e.empGenero = :empGenero"),
    @NamedQuery(name = "Empleado.findByEmpCorreo", query = "SELECT e FROM Empleado e WHERE e.empCorreo = :empCorreo"),
    @NamedQuery(name = "Empleado.findByEmpAdministrador", query = "SELECT e FROM Empleado e WHERE e.empAdministrador = :empAdministrador"),
    @NamedQuery(name = "Empleado.findByEmpClave", query = "SELECT e FROM Empleado e WHERE e.empClave = :empClave"),
    @NamedQuery(name = "Empleado.findByEmpFingreso", query = "SELECT e FROM Empleado e WHERE e.empFingreso = :empFingreso"),
    @NamedQuery(name = "Empleado.findByEmpFsalida", query = "SELECT e FROM Empleado e WHERE e.empFsalida = :empFsalida"),
    @NamedQuery(name = "Empleado.findByEmpEstado", query = "SELECT e FROM Empleado e WHERE e.empEstado = :empEstado"),
    @NamedQuery(name = "Empleado.findByEmpVersion", query = "SELECT e FROM Empleado e WHERE e.empVersion = :empVersion")*/
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PLAM_EMPLEADOS_EMP_ID_GENERATOR", sequenceName = "una.PLAM_EMPLEADOS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAM_EMPLEADOS_EMP_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "EMP_ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "EMP_NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "EMP_PAPELLIDO")
    private String pApellido;
    @Basic(optional = false)
    @Column(name = "EMP_SAPELLIDO")
    private String sApellido;
    @Basic(optional = false)
    @Column(name = "EMP_CEDULA")
    private String cedula;
    @Basic(optional = false)
    @Column(name = "EMP_GENERO")
    private String genero;
    @Column(name = "EMP_CORREO")
    private String correo;
    @Basic(optional = false)
    @Column(name = "EMP_ADMINISTRADOR")
    private String administrador;
    @Column(name = "EMP_USUARIO")
    private String usuario;
    @Column(name = "EMP_CLAVE")
    private String clave;
    @Basic(optional = false)
    @Column(name = "EMP_FINGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "EMP_FSALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @Basic(optional = false)
    @Column(name = "EMP_ESTADO")
    private String estado;
    @Version
    @Basic(optional = false)
    @Column(name = "EMP_VERSION")
    private Long version;
    @ManyToMany(mappedBy = "empleados", fetch = FetchType.LAZY)
    private List<TipoPlanilla> tiposPlanilla;

    public Empleado(EmpleadoDto empleadoDto) {
        this.id = empleadoDto.getId();
        actualizar(empleadoDto);
    }

    public Empleado() {
        
    }

    public Empleado(Long empId) {
        this.id = empId;
    }

    public void actualizar(EmpleadoDto empleadoDto) {
        this.nombre = empleadoDto.getNombre();
        this.pApellido = empleadoDto.getPApellido();
        this.sApellido = empleadoDto.getSApellido();
        this.cedula = empleadoDto.getCedula();
        this.genero = empleadoDto.getGenero();
        this.administrador = empleadoDto.getAdministrador();
        this.fechaIngreso = Date.from(empleadoDto.getFIngreso().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (empleadoDto.getFSalida()!= null) {
            this.fechaSalida = Date.from(empleadoDto.getFSalida().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        this.correo = empleadoDto.getCorreo();
        this.usuario = empleadoDto.getUsuario();
        this.clave = empleadoDto.getClave();
        this.estado = empleadoDto.getEstado();
        this.version = empleadoDto.getVersion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPApellido() {
        return pApellido;
    }

    public void setPApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    public String getSApellido() {
        return sApellido;
    }

    public void setSApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<TipoPlanilla> getTiposPlanilla() {
        return tiposPlanilla;
    }

    public void setTiposPlanilla(List<TipoPlanilla> tiposPlanilla) {
        this.tiposPlanilla = tiposPlanilla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.unaplanilla.model.Empleado[ empId=" + id + " ]";
    }
    
}
