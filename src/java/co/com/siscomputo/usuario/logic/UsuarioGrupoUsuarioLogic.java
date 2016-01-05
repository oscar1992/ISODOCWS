package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.persistencia.UsuarioGrupoUsuarioEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.GrupoUsuariosEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class UsuarioGrupoUsuarioLogic {
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
     * Método que inserta un relacion entre Usuarios y Grupos de Usuarios nuevo
     *
     * @param objetoUsuarioGrupoUsuario
     * @return
     */
    public ObjetoRetornaEntity insertarUsuarioGrupoUsuario(ArrayList<UsuarioGrupoUsuarioEntity> lista) {
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        ArrayList<Object> listaObjeto=new ArrayList<>();
        
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                int siguiente=maxMetodo();
                for(UsuarioGrupoUsuarioEntity ugu:lista){
                    UsuarioGrupoUsuarioEntity usuarioGrupoUsuarioEntity=ugu;
                    usuarioGrupoUsuarioEntity.setIdUsuarioGrupoUsuario(siguiente);
                    
                    siguiente++;
                    
                    sesion.save(usuarioGrupoUsuarioEntity);
                    listaObjeto.add(usuarioGrupoUsuarioEntity);
                }
                tx.commit();
                retorna.setTrazaRespuesta("Inserción de usuario - grupousuario exitoso");
                retorna.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna = new UsuarioGrupoUsuarioEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna;
    }

    /**
     * Método que trae el siguiente ID de la tabla ADM_TUSGR
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idUsuarioGrupoUsuario) FROM UsuarioGrupoUsuarioEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un relacion entre Usuarios y Grupos de Usuarios
     *
     * @param objetoUsuarioGrupoUsuario
     * @return
     */
    public UsuarioGrupoUsuarioEntity actualizarUsuarioGrupoUsuario(UsuarioGrupoUsuarioEntity objetoUsuarioGrupoUsuario) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoUsuarioGrupoUsuario.setNumeroRespuesta(3);
                objetoUsuarioGrupoUsuario.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoUsuarioGrupoUsuario);
                tx.commit();
                sesion.close();
                objetoUsuarioGrupoUsuario.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoUsuarioGrupoUsuario.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoUsuarioGrupoUsuario = new UsuarioGrupoUsuarioEntity();
            objetoUsuarioGrupoUsuario.setNumeroRespuesta(0);
            objetoUsuarioGrupoUsuario.setTrazaRespuesta(e.getMessage());
        }
        return objetoUsuarioGrupoUsuario;
    }

    /**
     * Método Método para consultar la lista de relacion entre Usuarios y Grupos
     * de Usuarios
     * @return 
     */
    public ObjetoRetornaEntity listaUsuarioGrupoUsuario() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query = sesion.createQuery("FROM UsuarioGrupoUsuarioEntity d WHERE d.estadoUsuarioGrupoUsuario<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla UsuarioGrupoUsuario exitosa");
                retorna.setNumeroRespuesta(22);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna = new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna;
    }
    /**
     * Método Método para consultar la lista de relacion entre Usuarios y Grupos
     * de Usuarios
     * @return 
     */
    public ObjetoRetornaEntity listaUsuarioGrupoUsuarioPorGrupo(int idGrupo) {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query = sesion.createQuery("SELECT u FROM UsuarioEntity u, UsuarioGrupoUsuarioEntity ugu, GrupoUsuariosEntity g WHERE ugu.grupoUsuario=g AND ugu.usuario=u AND g.idGrupoUsuarios=:idG");
                query.setParameter("idG", idGrupo);
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla UsuarioGrupoUsuario exitosa");
                retorna.setNumeroRespuesta(22);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna = new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }
        return retorna;
    }
    /**
     * Método que limpia las asiganaciones que tiene un usuario antes de que
     * ingresen las nuevas asignaciones de grupos de Usuario     *
     * @param idUsuario
     */
    public void limpia(int idGrupo) {
        String validaConexion = initOperation();
        
        if (!"Ok".equalsIgnoreCase(validaConexion)) {
            System.out.println("error conexión: "+validaConexion);
        } else {
            Query query = sesion.createQuery("delete UsuarioGrupoUsuarioEntity WHERE grupoUsuario.idGrupoUsuarios=:idG");
            query.setParameter("idG", idGrupo);
            int result = query.executeUpdate();
            tx.commit();
            
            System.out.println("LIMPIA: " + result);
        }
    }
    
    /**
     * Método que retorna un grupo de usuario por id de Usuario
     * @param idGrupo
     * @return 
     */ 
    public ObjetoRetornaEntity GrupoPorUsuario(int idUsuario){
         ObjetoRetornaEntity gu=new ObjetoRetornaEntity();
         try {
             String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                gu.setNumeroRespuesta(3);
                gu.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria=sesion.createCriteria(UsuarioGrupoUsuarioEntity.class);
                criteria.add(Restrictions.eq("usuario.idUsuario", idUsuario));
                
                gu.setRetorna((ArrayList<Object>) criteria.list());
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
