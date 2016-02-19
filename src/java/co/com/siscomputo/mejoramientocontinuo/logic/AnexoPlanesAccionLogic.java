/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.mejoramientocontinuo.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.mejoramientocontinuo.persistencia.AnexoPlanesAccionEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class AnexoPlanesAccionLogic implements AutoCloseable {

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
            if (!"OK".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idTablaAnexo) FROM AnexoPlanesAccionEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo para insertar un plan de accion
     *
     * @param obj
     * @return
     */
    public AnexoPlanesAccionEntity insertarAnexoAcciones(AnexoPlanesAccionEntity obj) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                obj.setIdTablaAnexo(maxDocumento());
                sesion.save(obj);
                tx.commit();
                obj.setNumeroRespuesta(23);
                obj.setTrazaRespuesta("Insercion exitosa");
            } else {
                obj = new AnexoPlanesAccionEntity();
                obj.setNumeroRespuesta(0);
                obj.setTrazaRespuesta("Error de conexion: " + conexion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            obj = new AnexoPlanesAccionEntity();
            obj.setNumeroRespuesta(0);
            obj.setTrazaRespuesta(e.getMessage());
        } 
        return obj;
    }

    /**
     * Metodo para actualizar un plan de accion
     *
     * @param obj
     * @return
     */
    public AnexoPlanesAccionEntity actualizarAnexoAcciones(AnexoPlanesAccionEntity obj) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                sesion.update(obj);
                tx.commit();
            } else {
                obj = new AnexoPlanesAccionEntity();
                obj.setNumeroRespuesta(0);
                obj.setTrazaRespuesta("Erro de conexion: " + conexion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            obj.setNumeroRespuesta(0);
            obj.setTrazaRespuesta(e.getMessage());
        } 
        return obj;
    }

    /**
     * Metodo para listar las anexo planes de accion
     *
     * @return
     */
    public ObjetoRetornaEntity listarAnexoAcciones() {
        ObjetoRetornaEntity obj = new ObjetoRetornaEntity();
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                Query sentencia = sesion.createQuery("From AnexoPlanesAccionEntity");
                obj.setRetorna((ArrayList<Object>) sentencia.list());
                obj.setTrazaRespuesta("Consulta tabla  de  anexo plan de acciones exitosa");
                obj.setNumeroRespuesta(1);
            } else {
                obj = new ObjetoRetornaEntity();
                obj.setNumeroRespuesta(0);
                obj.setTrazaRespuesta("Error de conexion :" + conexion);
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
