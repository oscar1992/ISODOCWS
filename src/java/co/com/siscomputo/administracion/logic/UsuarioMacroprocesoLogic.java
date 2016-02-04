/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioMacroprocesoEntity;
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
    public ObjetoRetornaEntity ingresaUsuarioMacroproceso(ArrayList<UsuarioMacroprocesoEntity> usumacro){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        ArrayList<Object> listaretorna=new ArrayList<>();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                int siguiente=maxMacro();
                for(UsuarioMacroprocesoEntity usuarioMacrop: usumacro){
                    UsuarioMacroprocesoEntity usuarioMacroprocesoEntity=usuarioMacrop;
                    usuarioMacroprocesoEntity.setIdUsuarioMacroproceso(siguiente);
                    siguiente++;
                    sesion.save(usuarioMacroprocesoEntity);
                    listaretorna.add(usuarioMacroprocesoEntity);
                }                
                tx.commit();
                 
                retorna.setTrazaRespuesta("Inserción de Usuario-Macroproceso Exitosa");
                retorna.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());            
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
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
                 
                usumacro.setTrazaRespuesta("Actualización de Usuario-Macroproceso Exitosa");
                usumacro.setNumeroRespuesta(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            usumacro=new UsuarioMacroprocesoEntity();
            usumacro.setNumeroRespuesta(0);
            usumacro.setTrazaRespuesta(e.getMessage());            
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return usumacro;
    }
    /**
     * Método que retorna una lista de relaciones Usuarios
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
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }
    /**
     * Método que retorna una lista de relaciones Usuarios-MacroProcesos
     * @return 
     */
    public ObjetoRetornaEntity listaMacroProcesosPorUsuario(int idUsuario){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("SELECT ump FROM UsuarioMacroprocesoEntity ump, UsuarioEntity u WHERE ump.idUsuario=u AND u.idUsuario=:idUsuario");
                query.setParameter("idUsuario", idUsuario);
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla usuario-Macroproceso exitosa");
                retorna.setNumeroRespuesta(21);
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }
    /**
     * Método que retorna una lista de relaciones Usuarios-MacroProcesos
     * @return 
     */
    public ObjetoRetornaEntity listaMacroProcesosPorUsuarioAccion(int idUsuario, int idAccion){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query = query=sesion.createQuery("SELECT ump FROM UsuarioMacroprocesoEntity ump, UsuarioEntity u, AccionEntity a WHERE ump.idUsuario=u AND ump.idAccion=a AND u.idUsuario=:idUsuario AND a.idAccion=:idAccion");
                query.setParameter("idUsuario", idUsuario);
                query.setParameter("idAccion", idAccion);
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla usuario-Macroproceso exitosa");
                retorna.setNumeroRespuesta(21);
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }
    /**
     * Método que retorna una lista de relaciones Usuarios-MacroProcesos
     * @return 
     */
    public ObjetoRetornaEntity listaMacroProcesosPorAccion( int idAccion){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query = query=sesion.createQuery("SELECT ump FROM UsuarioMacroprocesoEntity ump, UsuarioEntity u, AccionEntity a WHERE ump.idUsuario=u AND ump.idAccion=a AND a.idAccion=:idAccion");
                
                query.setParameter("idAccion", idAccion);
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla usuario-Macroproceso exitosa");
                retorna.setNumeroRespuesta(21);
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }
    /**
     * Método que retorna una lista de relaciones Usuarios-MacroProcesos
     * @return 
     */
    public ObjetoRetornaEntity listaProcesosPorUsuarioAccion(int idUsuario, int idAccion, int tipo){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query = null;
                switch(tipo){
                    case 1:
                        query=sesion.createQuery("SELECT ump FROM UsuarioMacroprocesoEntity ump, UsuarioEntity u, AccionEntity a WHERE ump.idUsuario=u AND ump.idAccion=a AND u.idUsuario=:idUsuario AND a.idAccion=:idAccion");
                    break;
                    case 2:
                        query=sesion.createQuery("SELECT ump FROM UsuarioMacroprocesoEntity ump, UsuarioEntity u, AccionEntity a WHERE ump.idUsuario=u AND ump.idAccion=a AND u.idUsuario=:idUsuario AND a.idAccion=:idAccion");
                    break;
                    case 3:
                        query=sesion.createQuery("SELECT ump FROM UsuarioMacroprocesoEntity ump, UsuarioEntity u, AccionEntity a WHERE ump.idUsuario=u AND ump.idAccion=a AND u.idUsuario=:idUsuario AND a.idAccion=:idAccion");
                    break;
                }
                
                query.setParameter("idUsuario", idUsuario);
                query.setParameter("idAccion", idAccion);
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla usuario-Macroproceso exitosa");
                retorna.setNumeroRespuesta(21);
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }
    /**
     * Método que limpia las asiganaciones que tiene un usuario antes de que
     * ingresen las nuevas asignaciones de macroprocesos     *
     * @param idUsuario
     * @param idAccion
     */
    public void limpia(int idUsuario, int idAccion) {
        String validaConexion = initOperation();
        
        try {
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                System.out.println("error conexión: " + validaConexion);
            } else {
                Query query = sesion.createQuery("delete UsuarioMacroprocesoEntity WHERE idUsuario.idUsuario=:idUsuario AND idAccion.idAccion=:idAccion");
                query.setParameter("idUsuario", idUsuario);
                query.setParameter("idAccion", idAccion);
                int result = query.executeUpdate();
                tx.commit();
                
                System.out.println("LIMPIA: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
    }
}
