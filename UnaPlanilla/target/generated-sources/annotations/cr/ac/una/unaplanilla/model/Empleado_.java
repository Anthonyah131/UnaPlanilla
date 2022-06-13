package cr.ac.una.unaplanilla.model;

import cr.ac.una.unaplanilla.model.TipoPlanilla;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-06-13T12:46:11", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, String> administrador;
    public static volatile SingularAttribute<Empleado, String> clave;
    public static volatile SingularAttribute<Empleado, String> estado;
    public static volatile ListAttribute<Empleado, TipoPlanilla> tiposPlanilla;
    public static volatile SingularAttribute<Empleado, String> cedula;
    public static volatile SingularAttribute<Empleado, String> nombre;
    public static volatile SingularAttribute<Empleado, Long> version;
    public static volatile SingularAttribute<Empleado, Date> fechaIngreso;
    public static volatile SingularAttribute<Empleado, String> sApellido;
    public static volatile SingularAttribute<Empleado, String> genero;
    public static volatile SingularAttribute<Empleado, String> correo;
    public static volatile SingularAttribute<Empleado, Date> fechaSalida;
    public static volatile SingularAttribute<Empleado, String> pApellido;
    public static volatile SingularAttribute<Empleado, String> usuario;
    public static volatile SingularAttribute<Empleado, Long> id;

}