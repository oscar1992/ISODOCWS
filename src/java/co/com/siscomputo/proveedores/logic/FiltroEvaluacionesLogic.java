/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.proveedores.persistencia.EvaluacionesEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Personal
 */
public class FiltroEvaluacionesLogic {

    private Session sesion;//Variable de la sesión y conexión de la base de datos
    private Transaction tx;//Variable que almacena las consultas y las transacciones de la base de datos

    /**
     * Metodo que establece la conexión a la base de datos, previa validación de
     * que la sesión no exista o este nula
     *
     * @throws HibernateException
     */
    private String initOperation() {
        String retorno;
        try {
            if (sesion == null) {
                sesion = HibernateUtil.getSessionFactory().openSession();
                tx = sesion.beginTransaction();
            }
            retorno = "Ok";
        } catch (Error e) {
            retorno = "Error Conexión Hibernate " + e;
        }
        return retorno;
    }

    public ObjetoRetornaEntity filtrarEvaluaciones(Integer empresa, Integer proveedor, String nit, String anio, Integer tipoEvaluacion, Integer liderImplementador, String fechaEvaluacion, Integer estado) {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        String conexion = initOperation();

        try {
            if ("OK".equalsIgnoreCase(conexion)) {
                Criteria criteria = sesion.createCriteria(EvaluacionesEntity.class);
                if (empresa == 1) {
                    criteria.add(Restrictions.eq("empresaEvaluaciones", empresa));
                }
                if (proveedor == 1) {
                    criteria.add(Restrictions.eq("ProveedoresEntity.idProveedor", proveedor));
                }
                if (nit != null) {
                    criteria.add(Restrictions.eq("documentoEvaluaciones", nit));
                }
                if (anio != null) {
                    criteria.add(Restrictions.eq("AnioEvaluaciones", anio));
                }
                if (tipoEvaluacion == 1) {
                    criteria.add(Restrictions.eq("TipoEvaluacionEntity.idTemaEvaluacion", tipoEvaluacion));
                }
                if (fechaEvaluacion != null) {
                    criteria.add(Restrictions.eq("fechaEvaluaciones", fechaEvaluacion));
                }
                if (estado == 1) {
                    criteria.add(Restrictions.eq("estadoProveedorEvaluaciones", estado));
                }
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Carga exitosa de documentos filtrados");
                retorna.setNumeroRespuesta(99);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna.setNumeroRespuesta(3);
            retorna.setTrazaRespuesta("Error de Conexión " + conexion);
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
                retorna = new ObjetoRetornaEntity();
                retorna.setNumeroRespuesta(0);
                retorna.setTrazaRespuesta(e.getMessage());
            }

        }
        return retorna;
    }
}
