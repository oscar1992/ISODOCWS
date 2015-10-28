/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.RolesEntity;
import co.com.siscomputo.administracion.persistencia.SedeEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class SedeLogic {

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
     * Método que permite ingresar una sede nueva
     *
     * @param sede
     * @return
     */
    public SedeEntity ingresarSede(SedeEntity sede) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                sede.setNumeroRespuesta(3);
                sede.setTrazaRespuesta("Error de Conexión: " + validaConexion);
            } else {
                sede.setIdSede(maxSede());
                sesion.save(sede);
                tx.commit();
                sesion.close();
                sede.setTrazaRespuesta("Inserción de Sede exitosa");
                sede.setNumeroRespuesta(29);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sede = new SedeEntity();
            sede.setNumeroRespuesta(0);
            sede.setTrazaRespuesta(e.getMessage());
        }
        return sede;
    }

    /**
     * Método que trae el siguente ID de la tabla de sedes
     *
     * @return
     */
    public int maxSede() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idSede) FROM SedeEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }

        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que permite la actualización de una sede
     *
     * @param sede
     * @return
     */
    public SedeEntity actualizarSede(SedeEntity sede) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                sede.setNumeroRespuesta(3);
                sede.setTrazaRespuesta("Error de Conexión: " + validaConexion);
            } else {

                sesion.update(sede);
                tx.commit();
                sesion.close();
                sede.setTrazaRespuesta("Actualización de Sede exitosa");
                sede.setNumeroRespuesta(30);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sede = new SedeEntity();
            sede.setNumeroRespuesta(0);
            sede.setTrazaRespuesta(e.getMessage());
        }
        return sede;
    }

    /**
     * Método que consulta la lista de sedes disponibles
     *
     * @return
     */
    public ObjetoRetornaEntity listaSedes() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            initOperation();
            Query query = sesion.createQuery("FROM SedeEntity");
            retorna.setRetorna((ArrayList<Object>) query.list());
            retorna.setTrazaRespuesta("Carga de Sedes exitosa");
            retorna.setNumeroRespuesta(31);
            sesion.close();
        } catch (Exception e) {
            retorna.setNumeroRespuesta(3);
            retorna.setTrazaRespuesta("ERROR: " + e);
            e.printStackTrace();
        }
        return retorna;
    }

    /**
     * Método que trae una sede por ID
     *
     * @param idSede
     * @return
     */
    public SedeEntity sedePorID(int idSede) {
        SedeEntity sede = new SedeEntity();
        String validaConexion = initOperation();
        try {
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                sede.setNumeroRespuesta(3);
                sede.setTrazaRespuesta("Error de Conexión: " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM SedeEntity s WHERE s.idSede=:idS");
                query.setParameter("idS", idSede);
                sede=(SedeEntity) query.uniqueResult();
                sede.setTrazaRespuesta("Consulta de sede exitosa");
                sede.setNumeroRespuesta(35);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sede = new SedeEntity();
            sede.setNumeroRespuesta(0);
            sede.setTrazaRespuesta(e.getMessage());
        }
        return sede;
    }
}
