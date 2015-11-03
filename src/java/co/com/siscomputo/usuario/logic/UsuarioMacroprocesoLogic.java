/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioMacroprocesoEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class UsuarioMacroprocesoLogic {
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
     * Método que permite insertar una relación usuario-macroproceso nueva
     * @param usumacro
     * @return 
     */
    public UsuarioMacroprocesoEntity ingresaUsuarioMacroproceso(UsuarioMacroprocesoEntity usumacro){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                usumacro.setNumeroRespuesta(3);
                usumacro.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                usumacro.setIdUsuarioMacroproceso(maxMacro());
                sesion.save(usumacro);
                tx.commit();
                sesion.close();
                usumacro.setTrazaRespuesta("Inserción de Usuario-Macroproceso Exitosa");
                usumacro.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            usumacro=new UsuarioMacroprocesoEntity();
            usumacro.setNumeroRespuesta(0);
            usumacro.setTrazaRespuesta(e.getMessage());            
        }
        return usumacro;
    }
    /**
     * Mpetodo que retorna el ID máximo de la tabla Usuario-MacroProceso
     * @return 
     */
    private int maxMacro() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idUsuarioMacroproceso) FROM UsuarioMacroprocesoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método que permite actualizar una relación Usuario-Macroproceso
     * @param usumacro
     * @return 
     */
    public UsuarioMacroprocesoEntity actualizarUsuarioMacroproceso(UsuarioMacroprocesoEntity usumacro){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                usumacro.setNumeroRespuesta(3);
                usumacro.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                sesion.update(usumacro);
                tx.commit();
                sesion.close();
                usumacro.setTrazaRespuesta("Actualización de Usuario-Macroproceso Exitosa");
                usumacro.setNumeroRespuesta(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            usumacro=new UsuarioMacroprocesoEntity();
            usumacro.setNumeroRespuesta(0);
            usumacro.setTrazaRespuesta(e.getMessage());            
        }
        return usumacro;
    }
    /**
     * Método que retorna una lista de relaciones Usuarios-MacroProcesos
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
                Query query=sesion.createQuery("FROM UsuarioMacroprocesoEntity");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla usuario-Macroproceso exitosa");
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
}
