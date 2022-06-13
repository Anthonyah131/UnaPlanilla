package cr.ac.una.unaplanilla.model;

import cr.ac.una.unaplanilla.model.Empleado;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-06-13T12:46:11", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(TipoPlanilla.class)
public class TipoPlanilla_ { 

    public static volatile SingularAttribute<TipoPlanilla, String> descripcion;
    public static volatile SingularAttribute<TipoPlanilla, String> codigo;
    public static volatile SingularAttribute<TipoPlanilla, Integer> numUltPla;
    public static volatile SingularAttribute<TipoPlanilla, String> estado;
    public static volatile SingularAttribute<TipoPlanilla, Integer> mesUltPla;
    public static volatile SingularAttribute<TipoPlanilla, Integer> planillasMes;
    public static volatile ListAttribute<TipoPlanilla, Empleado> empleados;
    public static volatile SingularAttribute<TipoPlanilla, Long> id;
    public static volatile SingularAttribute<TipoPlanilla, Integer> anoUltPla;
    public static volatile SingularAttribute<TipoPlanilla, Long> version;

}