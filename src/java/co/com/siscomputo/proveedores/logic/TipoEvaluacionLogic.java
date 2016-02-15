/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.proveedores.persistencia.TipoEvaluacionEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class TipoEvaluacionLogic {

    private Session sesion;//Variable de la sesión y conexión de la base de datos
    private Transaction tx;//Variable que almacena las consultas y las transacciones de la base de datos

    /**
     * Metodo para establecer una conexion con la base de datos
     *
     * @return
     */
    private String initOperation() {
        String respuesta;
        try {
            if (sesion == null) {
                sesion = HibernateUtil.getSessionFactory().openSession();
                tx = sesion.beginTransaction();
            }
            respuesta = "Ok";

        } catch (Exception e) {
            respuesta = "Error Conexión Hibernate " + e;
        }

        return respuesta;
    }

    /**
     * Método que trae el siguiente ID de una tabla
     *
     * @return
     */
    private int maxDocumento() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(IdTipoEvaluacion) FROM TipoEvaluacionEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo para insertar un tipo de evaluacion
     *
     * @param ObjTipoE
     * @return
     */
    public TipoEvaluacionEntity insertarTipoEvaluacion(TipoEvaluacionEntity ObjTipoE) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                ObjTipoE.setIdTipoEvaluacion(maxDocumento());
                sesion.save(ObjTipoE);
                tx.commit();
                ObjTipoE.setNumeroRespuesta(0);
                ObjTipoE.setTrazaRespuesta("Inserción correcta");
            } else {
                ObjTipoE = new TipoEvaluacionEntity();
                ObjTipoE.setNumeroRespuesta(0);
                ObjTipoE.setTrazaRespuesta(conexion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            ObjTipoE = new TipoEvaluacionEntity();
            ObjTipoE.setNumeroRespuesta(0);
            ObjTipoE.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return ObjTipoE;
    }

    /**
     * Metodo para actualizar el tipo de evaluacion
     *
     * @param ObjTipoE
     * @return
     */
    public TipoEvaluacionEntity actualizarTipoEvaluacion(TipoEvaluacionEntity ObjTipoE) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                sesion.update(ObjTipoE);
                tx.commit();
            } else {
                ObjTipoE = new TipoEvaluacionEntity();
                ObjTipoE.setNumeroRespuesta(0);
                ObjTipoE.setTrazaRespuesta("Error de conexion : " + conexion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ObjTipoE = new TipoEvaluacionEntity();
            ObjTipoE.setNumeroRespuesta(0);
            ObjTipoE.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ObjTipoE;
    }

    /**
     * Metodo para listar los tipos de evaluaciones
     *
     * @return
     */
    public ObjetoRetornaEntity listarTipoEvaluaciones() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();

        try {
            String Conexion = initOperation();
            if ("OK".equalsIgnoreCase(Conexion)) {
                Query sentencia = sesion.createQuery("From TipoEvaluacionEntity");
                retorno.setRetorna((ArrayList<Object>) sentencia.list());
                retorno.setTrazaRespuesta("Consulta tabla  de tipos de evaluacion exitosa");
                retorno.setNumeroRespuesta(1);
            } else {
                retorno.setNumeroRespuesta(0);
                retorno.setTrazaRespuesta("Error de conexion: " + Conexion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            retorno.setNumeroRespuesta(0);
            retorno.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retorno;
    }

}
