/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.mejoramientocontinuo.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.mejoramientocontinuo.persistencia.AccionesRapidasEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class AccionesRapidaLogic implements AutoCloseable {

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
                Query query = sesion.createQuery("SELECT MAX(idAccionesR) FROM AccionesRapidasEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo para insertar una auditoria
     *
     * @param obj
     * @return
     */
    public AccionesRapidasEntity insertarAcciones(AccionesRapidasEntity obj) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                obj.setIdAccionesR(maxDocumento());
                sesion.save(obj);
                tx.commit();
                obj.setNumeroRespuesta(23);
                obj.setTrazaRespuesta("Insercion exitosa");
            } else {
                obj = new AccionesRapidasEntity();
                obj.setNumeroRespuesta(0);
                obj.setTrazaRespuesta("Error de conexion: " + conexion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            obj = new AccionesRapidasEntity();
            obj.setNumeroRespuesta(0);
            obj.setTrazaRespuesta(e.getMessage());
        }
        return obj;
    }

    /**
     * Metodo para actualizar una accionRapida
     *
     * @param obj
     * @return
     */
    public AccionesRapidasEntity actualizarAcciones(AccionesRapidasEntity obj) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                sesion.update(obj);
                tx.commit();
            } else {
                obj = new AccionesRapidasEntity();
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
     * Metodo para listar las acciones rapidas
     *
     * @return
     */
    public ObjetoRetornaEntity listarAcciones() {
        ObjetoRetornaEntity obj = new ObjetoRetornaEntity();
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                Query sentencia = sesion.createQuery("From AccionesRapidasEntity  WHERE estadoAuditoria<>'E'");
                obj.setRetorna((ArrayList<Object>) sentencia.list());
                obj.setTrazaRespuesta("Consulta tabla  de acciones Rapidas exitosa");
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
