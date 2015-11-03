/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;


import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.ProcesosEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class ProcesosLogic {
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
     * Ingresa
     * @param procesos
     * @return 
     */
    public ProcesosEntity ingresaProcesos(ProcesosEntity procesos){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                procesos.setNumeroRespuesta(3);
                procesos.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                procesos.setIdProcesos(maxProcesos());
                sesion.save(procesos);
                tx.commit();
                sesion.close();
                procesos.setTrazaRespuesta("Inserción de Permiso Exitosa");
                procesos.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            procesos=new ProcesosEntity();
            procesos.setNumeroRespuesta(0);
            procesos.setTrazaRespuesta(e.getMessage());            
        }
        return procesos;
    }
    
    /**
     * MaxProcesos
     * @return 
     */
    private int maxProcesos() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idProcesos) FROM ProcesosEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Actualiza
     * @param procesos
     * @return 
     */
    public ProcesosEntity actualizarProcesos(ProcesosEntity procesos){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                procesos.setNumeroRespuesta(3);
                procesos.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                sesion.update(procesos);
                tx.commit();
                sesion.close();
                procesos.setTrazaRespuesta("Actualización de procesos Exitosa");
                procesos.setNumeroRespuesta(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            procesos=new ProcesosEntity();
            procesos.setNumeroRespuesta(0);
            procesos.setTrazaRespuesta(e.getMessage());            
        }
        return procesos;
    }
    /**
     * Consulta
     * @return 
     */
    public ObjetoRetornaEntity listaProcesos(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM ProcesosEntity p WHERE p.estadoProceso<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Procesos exitosa");
                retorna.setNumeroRespuesta(21);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna;
    }
    /**
     * PorID
     * @param idProceso
     * @return 
     */
    public ProcesosEntity procesoPorID(int idProceso){
        ProcesosEntity procesos = new ProcesosEntity();
        String validaConexion = initOperation();
        try {
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                procesos.setNumeroRespuesta(3);
                procesos.setTrazaRespuesta("Error de Conexión: " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM ProcesosEntity p WHERE p.idProcesos=:idS");
                query.setParameter("idS", idProceso);
                procesos=(ProcesosEntity) query.uniqueResult();
                procesos.setTrazaRespuesta("Consulta de procesos exitosa");
                procesos.setNumeroRespuesta(35);
            }
        } catch (Exception e) {
            e.printStackTrace();
            procesos = new ProcesosEntity();
            procesos.setNumeroRespuesta(0);
            procesos.setTrazaRespuesta(e.getMessage());
        }
        return procesos;
    }
}
