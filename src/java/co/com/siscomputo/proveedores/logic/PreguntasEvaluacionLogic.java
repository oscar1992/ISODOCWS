/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.proveedores.persistencia.LineaEntity;
import co.com.siscomputo.proveedores.persistencia.PreguntasEvaluacionEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omg.CORBA.OBJECT_NOT_EXIST;

/**
 *
 * @author Felipe
 */
public class PreguntasEvaluacionLogic {

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
                Query query = sesion.createQuery("SELECT MAX(idPregunta) FROM PreguntasEvaluacionEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que inserta una pregunta nueva
     *
     * @param objetoPre
     * @return
     */
    public PreguntasEvaluacionEntity insertarPregunta(PreguntasEvaluacionEntity objetoPre) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                objetoPre.setIdPregunta(maxDocumento());
                sesion.save(objetoPre);
                tx.commit();
                objetoPre.setNumeroRespuesta(23);
                objetoPre.setTrazaRespuesta("Insercion exitosa");
            } else {
                objetoPre = new PreguntasEvaluacionEntity();
                objetoPre.setNumeroRespuesta(0);
                objetoPre.setTrazaRespuesta("Error de conexion " + conexion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            objetoPre = new PreguntasEvaluacionEntity();
            objetoPre.setIdPregunta(0);
            objetoPre.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objetoPre;
    }

    /**
     * Metodo para actualizar una pregunta
     *
     * @param objPre
     * @return
     */
    public PreguntasEvaluacionEntity actualizarPreguntaEvaluacion(PreguntasEvaluacionEntity objPre) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                sesion.update(objPre);
                tx.commit();
                objPre.setNumeroRespuesta(23);
                objPre.setTrazaRespuesta("Actualizacion correcta");
            } else {
                objPre = new PreguntasEvaluacionEntity();
                objPre.setNumeroRespuesta(0);
                objPre.setTrazaRespuesta("Error de conexion" + conexion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            objPre = new PreguntasEvaluacionEntity();
            objPre.setNumeroRespuesta(0);
            objPre.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objPre;
    }

    /**
     * Metodo para listar preguntas
     *
     * @return
     */
    public ObjetoRetornaEntity listarPreguntas() {
        ObjetoRetornaEntity obj = new ObjetoRetornaEntity();
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                Query sentencia = sesion.createQuery("FROM PreguntasEvaluacionEntity");
                obj.setNumeroRespuesta(23);
                obj.setRetorna((ArrayList<Object>) sentencia.list());
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
