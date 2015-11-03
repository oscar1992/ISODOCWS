/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioProcesoEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class UsuarioProcesoLogic {
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
     * Método que permite ingresar una relación usuario-proceso nueva
     * @param usuproceso
     * @return 
     */
    public UsuarioProcesoEntity ingresarUsuarioProceso(UsuarioProcesoEntity usuproceso){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                usuproceso.setNumeroRespuesta(3);
                usuproceso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                usuproceso.setIdUsuarioProceso(maxMacro());
                sesion.save(usuproceso);
                tx.commit();
                sesion.close();
                usuproceso.setTrazaRespuesta("Inserción de Usuario-Proceso Exitosa");
                usuproceso.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            usuproceso=new UsuarioProcesoEntity();
            usuproceso.setNumeroRespuesta(0);
            usuproceso.setTrazaRespuesta(e.getMessage());            
        }
        return usuproceso;
    }
    /**
     * Método que retorna el ID máximo de la tabla relacional usuario-proceso
     * @return 
     */
    private int maxMacro() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idUsuarioProceso) FROM UsuarioProcesoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    /**
     * Método que actualiza una relación entre usuarios-procesos
     * @param usuproceso
     * @return 
     */
    public UsuarioProcesoEntity actualizarUsuarioProceso(UsuarioProcesoEntity usuproceso){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                usuproceso.setNumeroRespuesta(3);
                usuproceso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                sesion.update(usuproceso);
                tx.commit();
                sesion.close();
                usuproceso.setTrazaRespuesta("Actualización de Usuario-Proceso Exitosa");
                usuproceso.setNumeroRespuesta(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            usuproceso=new UsuarioProcesoEntity();
            usuproceso.setNumeroRespuesta(0);
            usuproceso.setTrazaRespuesta(e.getMessage());            
        }
        return usuproceso;
    }
    /**
     * Método que retorna una lista de relaciones Usuarios_Procesos
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
                Query query=sesion.createQuery("FROM UsuarioProcesoEntity");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla usuario-Proceso exitosa");
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
