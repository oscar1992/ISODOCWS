/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.proveedores.persistencia.TemaEvaluacionEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class TemaEvaluacionLogic implements AutoCloseable{

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
                Query query = sesion.createQuery("SELECT MAX(idTemaEvaluacion) FROM TemaEvaluacionEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo para insertar un tema de evaluacion
     *
     * @param objent
     * @return
     */
    public TemaEvaluacionEntity insertarTemaEvaluacion(TemaEvaluacionEntity objent) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                objent.setIdTemaEvaluacion(maxDocumento());
                sesion.save(objent);
                tx.commit();
                objent.setNumeroRespuesta(23);
                objent.setTrazaRespuesta("Insercion correcta");
            } else {
                objent = new TemaEvaluacionEntity();
                objent.setNumeroRespuesta(0);
                objent.setTrazaRespuesta("Error de conexion :" + conexion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objent = new TemaEvaluacionEntity();
            objent.setNumeroRespuesta(0);
            objent.setTrazaRespuesta(e.getMessage());
        }

        return objent;
    }

    /**
     * Metodo para actualizar un tema de evaluacion
     *
     * @param objent
     * @return
     */
    public TemaEvaluacionEntity actualizarTemaEvaluacion(TemaEvaluacionEntity objent) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                sesion.update(objent);
                tx.commit();
                objent.setNumeroRespuesta(23);
                objent.setTrazaRespuesta("Actualizacion correcta");
            } else {
                objent = new TemaEvaluacionEntity();
                objent.setNumeroRespuesta(0);
                objent.setTrazaRespuesta("Error de conexion: " + conexion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objent = new TemaEvaluacionEntity();
            objent.setNumeroRespuesta(0);
            objent.setTrazaRespuesta(e.getMessage());
        }

        return objent;
    }

    /**
     * Metodo para listar los temas de evaluacion
     *
     * @return
     */
    public ObjetoRetornaEntity listaTemasEvaluacion() {
        ObjetoRetornaEntity obj = new ObjetoRetornaEntity();
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                Query sentecia = sesion.createQuery("From TemaEvaluacionLogic");
                obj.setNumeroRespuesta(23);
                obj.setRetorna((ArrayList<Object>) sentecia.list());
                obj.setTrazaRespuesta("Consulta exitosa");
            } else {
                obj.setNumeroRespuesta(0);
                obj.setTrazaRespuesta("Error de conexion " + conexion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            obj = new ObjetoRetornaEntity();
            obj.setNumeroRespuesta(0);
            obj.setTrazaRespuesta(e.getMessage());

        } 
        return obj;
    }

    @Override
    public void close() throws Exception {
        try {
            if (tx != null) {
                tx.commit();
            }
            if (sesion != null) {
                sesion.close();
                sesion = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
