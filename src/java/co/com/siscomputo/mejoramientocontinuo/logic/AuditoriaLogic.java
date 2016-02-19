/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.mejoramientocontinuo.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.mejoramientocontinuo.persistencia.AuditoriaEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class AuditoriaLogic implements AutoCloseable{

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
                Query query = sesion.createQuery("SELECT MAX(idAuditoria) FROM AuditoriaEntity");
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
     * @param objAudi
     * @return
     */
    public AuditoriaEntity insertarAuditoria(AuditoriaEntity objAudi) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                objAudi.setIdAuditoria(maxDocumento());
                sesion.save(objAudi);
                tx.commit();
                objAudi.setNumeroRespuesta(23);
                objAudi.setTrazaRespuesta("Insercion exitosa");
            } else {
                objAudi = new AuditoriaEntity();
                objAudi.setNumeroRespuesta(0);
                objAudi.setTrazaRespuesta("Error de conexion: " + conexion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            objAudi = new AuditoriaEntity();
            objAudi.setNumeroRespuesta(0);
            objAudi.setTrazaRespuesta(e.getMessage());
        }

        return objAudi;
    }

    /**
     * Metodo para actualizar una auditoria
     *
     * @param objAudi
     * @return
     */
    public AuditoriaEntity actualizarAuditoria(AuditoriaEntity objAudi) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                sesion.update(objAudi);
                tx.commit();
            } else {
                objAudi = new AuditoriaEntity();
                objAudi.setNumeroRespuesta(0);
                objAudi.setTrazaRespuesta("Erro de conexion: " + conexion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            objAudi.setNumeroRespuesta(0);
            objAudi.setTrazaRespuesta(e.getMessage());
        }
        return objAudi;
    }

    /**
     * Metodo para listar las auditorias
     *
     * @return
     */
    public ObjetoRetornaEntity listarAuditoria() {
        ObjetoRetornaEntity obj = new ObjetoRetornaEntity();
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                Query sentencia = sesion.createQuery("From AuditoriaEntity WHERE estadoAuditoria<>'E'");
                obj.setRetorna((ArrayList<Object>) sentencia.list());
                obj.setTrazaRespuesta("Consulta tabla  de auditorias exitosa");
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
