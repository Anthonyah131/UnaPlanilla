/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla.service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import cr.ac.una.unaplanilla.model.TipoPlanillaDto;
import cr.ac.una.unaplanilla.model.Empleado;
import cr.ac.una.unaplanilla.model.EmpleadoDto;
import cr.ac.una.unaplanilla.model.TipoPlanilla;
import cr.ac.una.unaplanilla.util.Respuesta;
import cr.ac.una.unaplanilla.util.EntityManagerHelper;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
public class TipoPlanillaService {
    
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public Respuesta getTipoPlanilla(Long id) {
        try {
            Query qryTipoPlanilla = em.createNamedQuery("TipoPlanilla.findById", TipoPlanilla.class);
            qryTipoPlanilla.setParameter("id", id);

            TipoPlanilla tipoPlanilla = (TipoPlanilla) qryTipoPlanilla.getSingleResult();
            TipoPlanillaDto tipoPlanillaDto = new TipoPlanillaDto(tipoPlanilla);
            
            for (Empleado emp : tipoPlanilla.getEmpleados()) {
                tipoPlanillaDto.getEmpleados().add(new EmpleadoDto(emp));
            }
            return new Respuesta(true, "", "", "TipoPlanilla", tipoPlanillaDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un tipo de planilla con el código ingresado.", "getTipoPlanilla NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el tipo de planilla.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el tipo de planilla.", "getTipoPlanilla NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el planilla.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el tipo de planilla.", "getTipoPlanilla " + ex.getMessage());
        }
    }
    public Respuesta getTipoPlanillas(String codigo,String descripcion,String planillaMes) {
        try {
            Query query = em.createNamedQuery("TipoPlanilla.findByCodigoDescripcionPlanillasPorMes",TipoPlanilla.class);
            query.setParameter("codigo", codigo);
            query.setParameter("descripcion", descripcion);
            query.setParameter("planillasMes", planillaMes);
            List<TipoPlanilla> tipoPlanilla = (List<TipoPlanilla>) query.getResultList();
            List<TipoPlanillaDto> tipoPlanillaDto = new ArrayList<>();
            for (TipoPlanilla tpl : tipoPlanilla) {
                tipoPlanillaDto.add(new TipoPlanillaDto(tpl));
            }
            return new Respuesta(true, "", "", "Planillas", tipoPlanillaDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existen planillas con los criterios ingresados.", "getPlanillas NoResultException");
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error obteniendo planillas.", ex);
            return new Respuesta(false, "Error obteniendo planillas.", "getPlanillas " + ex.getMessage());
        }
    }
    
    public Respuesta getTipoPlanillasIDyCed(String idemp, String cedula) {
        try {
            Query query = em.createNamedQuery("TipoPlanilla.findByCedulaIDEmp",TipoPlanilla.class);
            query.setParameter("id", idemp);
            query.setParameter("cedula", cedula);
            List<TipoPlanilla> tipoPlanilla = (List<TipoPlanilla>) query.getResultList();
            List<TipoPlanillaDto> tipoPlanillaDto = new ArrayList<>();
            for (TipoPlanilla tpl : tipoPlanilla) {
                tipoPlanillaDto.add(new TipoPlanillaDto(tpl));
            }
            return new Respuesta(true, "", "", "Planillas", tipoPlanillaDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existen planillas con los criterios ingresados.", "getPlanillas NoResultException");
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error obteniendo planillas.", ex);
            return new Respuesta(false, "Error obteniendo planillas.", "getPlanillas " + ex.getMessage());
        }
    }
    
    public Respuesta guardarTipoPlanilla(TipoPlanillaDto tipoPlanillaDto) {
        try {
            et = em.getTransaction();
            et.begin();
            TipoPlanilla tipoPlanilla;
            if (tipoPlanillaDto.getId() != null && tipoPlanillaDto.getId() > 0) {
                tipoPlanilla = em.find(TipoPlanilla.class, tipoPlanillaDto.getId());
                if (tipoPlanilla == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró el tipo de planilla a modificar.", "guardarTipoPlanilla NoResultException");
                }
                tipoPlanilla.actualizarTipoPlanilla(tipoPlanillaDto);
                for (EmpleadoDto emp : tipoPlanillaDto.getEmpleadosEliminados()) {
                    tipoPlanilla.getEmpleados().remove(new Empleado(emp.getId()));
                }
                if (!tipoPlanillaDto.getEmpleados().isEmpty()) {
                    for (EmpleadoDto emp : tipoPlanillaDto.getEmpleados()) {
                        if (emp.getModificado()) {
                            Empleado empleado = em.find(Empleado.class, emp.getId());
                            empleado.getTiposPlanilla().add(tipoPlanilla);
                            tipoPlanilla.getEmpleados().add(empleado);
                        }
                    }
                }
                tipoPlanilla = em.merge(tipoPlanilla);
            } else {
                tipoPlanilla = new TipoPlanilla(tipoPlanillaDto);
                em.persist(tipoPlanilla);
            }
            et.commit();
            tipoPlanillaDto = new TipoPlanillaDto(tipoPlanilla);
            for (Empleado emp : tipoPlanilla.getEmpleados()) {
                tipoPlanillaDto.getEmpleados().add(new EmpleadoDto(emp));
            }
            return new Respuesta(true, "", "", "TipoPlanilla", tipoPlanillaDto);
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el tipo de planilla.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el tipo de planilla.", "guardarTipoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta eliminarTipoPlanilla(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            TipoPlanilla tipoPlanilla;
            if (id != null && id > 0) {
                tipoPlanilla = em.find(TipoPlanilla.class, id);
                if (tipoPlanilla == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el tipo de planilla a eliminar.", "eliminarTipoPlanilla NoResultException");
                }
                em.remove(tipoPlanilla);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar el tipo de planilla a eliminar.", "eliminarTipoPlanilla NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar el tipo de planilla porque tiene relaciones con otros registros.", "eliminarTipoPlanilla " + ex.getMessage());
            }
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el tipo de planilla.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el tipo de planilla.", "eliminarTipoPlanilla " + ex.getMessage());
        }
    }

}
