/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.MetodoRecuperacionEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class MetodoRecuperacionLogic {
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
     * Método que inserta un Método de recuperación nuevo
     * @param objetoMetodoRecuperacion
     * @return 
     */
    public MetodoRecuperacionEntity insertarMetodoRecuperacion(MetodoRecuperacionEntity objetoMetodoRecuperacion){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoMetodoRecuperacion.setNumeroRespuesta(3);
                objetoMetodoRecuperacion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoMetodoRecuperacion.setIdMetodoRecuperacion(maxMetodo());
                sesion.save(objetoMetodoRecuperacion);
                tx.commit();

                objetoMetodoRecuperacion.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoMetodoRecuperacion.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoMetodoRecuperacion = new MetodoRecuperacionEntity();
            objetoMetodoRecuperacion.setNumeroRespuesta(0);
            objetoMetodoRecuperacion.setTrazaRespuesta(e.getMessage());
        }
        return objetoMetodoRecuperacion;
    }
    /**
     * Método que trae el siguiente ID de la tabla ADM_TRECU
     * @return 
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idMetodoRecuperacion) FROM MetodoRecuperacionEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método que actualiza un Método de Recupración
     * @param objetoMetodoRecuperacion
     * @return 
     */
    public MetodoRecuperacionEntity actualizarMetodoRecuperacion(MetodoRecuperacionEntity objetoMetodoRecuperacion){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoMetodoRecuperacion.setNumeroRespuesta(3);
                objetoMetodoRecuperacion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoMetodoRecuperacion);
                tx.commit();
                sesion.close();
                objetoMetodoRecuperacion.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoMetodoRecuperacion.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoMetodoRecuperacion = new MetodoRecuperacionEntity();
            objetoMetodoRecuperacion.setNumeroRespuesta(0);
            objetoMetodoRecuperacion.setTrazaRespuesta(e.getMessage());
        }
        return objetoMetodoRecuperacion;
    }
    /**
     * Método que trae una lista de Métodos de Recuperación
     * @return 
     */
    public ObjetoRetornaEntity listaMetodosRecuperacion(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM MetodoRecuperacionEntity d WHERE d.estadoMetodoRecuperacion<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla MetodoRecuperacion exitosa");
                retorna.setNumeroRespuesta(22);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna ;
    }
}
