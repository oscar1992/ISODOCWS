/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.UnidadDeNegocioEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class UnidadDeNegocioLogic {

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
                Query query = sesion.createQuery("SELECT MAX(idUnidadNegocio) FROM UnidadDeNegocio");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo para insertar una unidad de negocio
     *
     * @param obj
     * @return
     */
    public UnidadDeNegocioEntity insertarUnidadNegocio(UnidadDeNegocioEntity obj) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                obj.setIdUnidadNegocio(maxDocumento());
                sesion.save(obj);
                tx.commit();
                obj.setNumeroRespuesta(23);
                obj.setTrazaRespuesta("Insercion exitosa");
            } else {
                obj = new UnidadDeNegocioEntity();
                obj.setNumeroRespuesta(0);
                obj.setTrazaRespuesta("Error de conexion: " + conexion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            obj = new UnidadDeNegocioEntity();
            obj.setNumeroRespuesta(0);
            obj.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    /**
     * Metodo para actualizar unidad de negocio 
     *
     * @param obj
     * @return
     */
    public UnidadDeNegocioEntity actualizarUnidadNegocio(UnidadDeNegocioEntity obj) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                sesion.update(obj);
                tx.commit();
            } else {
                obj = new UnidadDeNegocioEntity();
                obj.setNumeroRespuesta(0);
                obj.setTrazaRespuesta("Erro de conexion: " + conexion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            obj.setNumeroRespuesta(0);
            obj.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    /**
     * Metodo para listar las unidades de negocio
     *
     * @return
     */
    public ObjetoRetornaEntity listarUnidades() {
        ObjetoRetornaEntity obj = new ObjetoRetornaEntity();
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                Query sentencia = sesion.createQuery("From UnidadDeNegocioEntity");
                obj.setRetorna((ArrayList<Object>) sentencia.list());
                obj.setTrazaRespuesta("Consulta tabla  de  unidad de negocio exitosa");
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
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

}
