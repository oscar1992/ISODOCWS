/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.MacroprocesosEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class MacroProcesoLogic {
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
     * Método que ingresa un macro proceso nuevo
     * @param macro
     * @return 
     */
    public MacroprocesosEntity ingresaMacroproceso(MacroprocesosEntity macro){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                macro.setNumeroRespuesta(3);
                macro.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                macro.setIdMacroproceso(maxMacro());
                sesion.save(macro);
                tx.commit();
                sesion.close();
                macro.setTrazaRespuesta("Inserción de MacroProceso Exitosa");
                macro.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            macro=new MacroprocesosEntity();
            macro.setNumeroRespuesta(0);
            macro.setTrazaRespuesta(e.getMessage());            
        }
        return macro;
    }
    /**
     * Método que trael el siguiente ID
     * @return 
     */
    private int maxMacro() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idMacroproceso) FROM MacroprocesosEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método que actualiza un macro proceso
     * @param macro
     * @return 
     */
    public MacroprocesosEntity actualizarMacroproceso(MacroprocesosEntity macro){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                macro.setNumeroRespuesta(3);
                macro.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                sesion.update(macro);
                tx.commit();
                sesion.close();
                macro.setTrazaRespuesta("Actualización de MacroProceso Exitosa");
                macro.setNumeroRespuesta(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            macro=new MacroprocesosEntity();
            macro.setNumeroRespuesta(0);
            macro.setTrazaRespuesta(e.getMessage());            
        }
        return macro;
    }
    /**
     * Método que trae la lista de macro procesos
     * @return 
     */
    public ObjetoRetornaEntity listaMacroProcesos(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM MacroprocesosEntity p WHERE p.estadoMacroproceso<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla MacroProceso exitosa");
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
     * Método que trae un macro proceso por ID
     * @param idMacro
     * @return 
     */
    public MacroprocesosEntity macroPorID(int idMacro){
        MacroprocesosEntity macro = new MacroprocesosEntity();
        String validaConexion = initOperation();
        try {
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                macro.setNumeroRespuesta(3);
                macro.setTrazaRespuesta("Error de Conexión: " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM MacroprocesosEntity p WHERE p.idMacroproceso=:idS");
                query.setParameter("idS", idMacro);
                macro=(MacroprocesosEntity) query.uniqueResult();
                macro.setTrazaRespuesta("Consulta de macro exitosa");
                macro.setNumeroRespuesta(35);
            }
        } catch (Exception e) {
            e.printStackTrace();
            macro = new MacroprocesosEntity();
            macro.setNumeroRespuesta(0);
            macro.setTrazaRespuesta(e.getMessage());
        }
        return macro;
    }
}
