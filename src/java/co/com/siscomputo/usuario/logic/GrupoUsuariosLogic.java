
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.persistencia.GrupoUsuariosEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioGrupoUsuarioEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class GrupoUsuariosLogic {
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
     * Método que inserta un Grupo de Usuarios nuevo
     * @param objetoGrupoUsuarios
     * @return 
     */    public GrupoUsuariosEntity insertarGrupoUsuarios(GrupoUsuariosEntity objetoGrupoUsuarios){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoGrupoUsuarios.setNumeroRespuesta(3);
                objetoGrupoUsuarios.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoGrupoUsuarios.setIdGrupoUsuarios(maxMetodo());
                sesion.save(objetoGrupoUsuarios);
                tx.commit();

                objetoGrupoUsuarios.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoGrupoUsuarios.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoGrupoUsuarios = new GrupoUsuariosEntity();
            objetoGrupoUsuarios.setNumeroRespuesta(0);
            objetoGrupoUsuarios.setTrazaRespuesta(e.getMessage());
        }
        return objetoGrupoUsuarios;
    }

     /**
     * Método que trae el siguiente ID de la tabla ADM_TGRUP
     * @return 
     */    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idGrupoUsuarios) FROM GrupoUsuariosEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un Grupo de Usuarios
     * @param objetoGrupoUsuarios
     * @return 
     */ public GrupoUsuariosEntity actualizarGrupoUsuarios(GrupoUsuariosEntity objetoGrupoUsuarios){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoGrupoUsuarios.setNumeroRespuesta(3);
                objetoGrupoUsuarios.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {   
                sesion.update(objetoGrupoUsuarios);
                tx.commit();
                sesion.close();
                objetoGrupoUsuarios.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoGrupoUsuarios.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoGrupoUsuarios = new GrupoUsuariosEntity();
            objetoGrupoUsuarios.setNumeroRespuesta(0);
            objetoGrupoUsuarios.setTrazaRespuesta(e.getMessage());
        }
        return objetoGrupoUsuarios;
    }
     /**
     * Método Método para consultar la lista de Grupo de Usuarios
     */public ObjetoRetornaEntity listaGrupoUsuarios(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM GrupoUsuariosEntity d WHERE d.estadoGrupoUsuarios<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla GrupoUsuarios exitosa");
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
    /**
     * Método que retorna un grupo de usuario por ID
     * @param idGrupo
     * @return 
     */ 
    public GrupoUsuariosEntity GrupoPorId(int idGrupo){
         GrupoUsuariosEntity gu=new GrupoUsuariosEntity();
         try {
             String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                gu.setNumeroRespuesta(3);
                gu.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                
                Query query=sesion.createQuery("FROM GrupoUsuariosEntity d WHERE d.idGrupoUsuarios=:idG");
                query.setParameter("idG", idGrupo);
                gu=(GrupoUsuariosEntity) query.uniqueResult();
                gu.setTrazaRespuesta("Consulta GrupoUsuarios exitosa");
                gu.setNumeroRespuesta(22);
                sesion.close();
            }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return gu;
     }
    
}