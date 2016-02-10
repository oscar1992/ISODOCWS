/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.administracion.entites.ObjetoLogin;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioGrupoUsuarioEntity;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.NamingException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */

public class UsuarioLogic {

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
     * Método que recibe el usuario y la contraseña
     *
     * @param usuario
     * @param pass
     * @return Devuelve el objeto de login
     */
    public ObjetoLogin login(String usuario, String pass) {
        ObjetoLogin objetoLogin = null;
        UsuarioEntity usuarioObjeto = null;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoLogin = new ObjetoLogin();
                objetoLogin.setAcceso(false);
                objetoLogin.setNumeroRespuesta(3);
                objetoLogin.setTrazaRespuesta(validaConexion);
            } else {
                Query query = sesion.createQuery("FROM UsuarioEntity u WHERE u.nombreUsuario=:nom AND u.clave=:cla");
                query.setParameter("nom", usuario);
                query.setParameter("cla", pass);
                usuarioObjeto = (UsuarioEntity) query.uniqueResult();
                if (usuarioObjeto == null) {
                    objetoLogin = new ObjetoLogin();
                    objetoLogin.setAcceso(false);
                    objetoLogin.setNumeroRespuesta(2);
                    objetoLogin.setTrazaRespuesta("Usuario/Clave Incorrecto");
                } else {
                    objetoLogin = new ObjetoLogin();
                    objetoLogin.setAcceso(true);
                    objetoLogin.setNumeroRespuesta(1);
                    objetoLogin.setTrazaRespuesta("Bienvenido");
                    objetoLogin.setIdUsuario(usuarioObjeto.getIdUsuario());
                    if(usuarioObjeto.getUltimoIngreso()==null){
                        objetoLogin.setTrazaRespuesta("InicioNuevo");
                    }else{
                        fechaUltimoIngreso(usuarioObjeto);
                    }
                    
                }
             
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoLogin = new ObjetoLogin();
            objetoLogin.setAcceso(false);
            objetoLogin.setNumeroRespuesta(3);
            objetoLogin.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                
                sesion.close();
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoLogin;
    }

    /**
     * Método que crea la lista de Módulos filtrada por Usuario
     *
     * @param idUsuario
     * @return Lista de módulos
     */
    public ObjetoRetornaEntity modulos(int idUsuario) {
        
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                
            } else {
                System.out.println("IDUSUARIO: "+idUsuario);
                Query query = sesion.createQuery("SELECT distinct m FROM UsuarioEntity u, UsuarioRolEntity ure, RolesEntity r,  RolPermisoEntity rpe, PermisosEntity p,  ModuloEntity m "
                        + "WHERE  u=ure.usuario AND ure.rol=r AND r=rpe.id_rol AND rpe.id_permiso=p AND p.id_modulo=m AND u.idUsuario=:idu ORDER BY m.orden");
                query.setParameter("idu", idUsuario);
                //listaModulos = (ArrayList<ModuloEntity>) query.list();
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setNumeroRespuesta(8);
                retorna.setTrazaRespuesta("Lista de Modulos Cargada Correctamente");
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorna;
    }

    /**
     * Método que crea la lista de Permisos filtrada por Usuario
     *
     * @param idUsuario
     * @return Lista de Permisos
     */
    public ObjetoRetornaEntity permisos(int idUsuario) {
        
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT distinct p FROM UsuarioEntity u,  UsuarioRolEntity ure, RolesEntity r,  RolPermisoEntity rpe, PermisosEntity p, ModuloEntity m "
                        + "WHERE  u=ure.usuario AND ure.rol=r AND r=rpe.id_rol AND rpe.id_permiso=p AND p.id_modulo=m AND u.idUsuario=:idu ");
                query.setParameter("idu", idUsuario);
                //listaModulos = (ArrayList<PermisosEntity>) query.list();
                retorna.setRetorna((ArrayList<Object>)query.list());                
                retorna.setNumeroRespuesta(9);
                retorna.setTrazaRespuesta("Carga lista de Permisos Correcta");
                
            }

        } catch (Exception e) {
            //System.out.println("ERROR LITSA PERMISOS: " + e.getMessage());
        }finally{
            try {
                //sesion.close();  
                sesion.close();
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }

    /**
     * Método que devuelve un único Usuario filtrado por ID
     *
     * @param id
     * @return Un usuario
     */
    public UsuarioEntity obtenerUsuario(int id) {
        UsuarioEntity usuario = new UsuarioEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                usuario.setTrazaRespuesta("Error Conexión: "+validaConexion);
                usuario.setNumeroRespuesta(3);
            } else {
                Query query = sesion.createQuery("FROM UsuarioEntity u WHERE u.idUsuario=:idu");
                query.setParameter("idu", id);
                usuario = (UsuarioEntity) query.uniqueResult();
                
                usuario.setTrazaRespuesta("Obtención de usuario correcto");
                usuario.setNumeroRespuesta(4);
            }

        } catch (Exception e) {
            //System.out.println("ERRROR: " + e);
        }
        finally{
            try {
                //sesion.close();  
                sesion.close();
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return usuario;
    }

    /**
     * Método que devuelve una lista de Usuarios
     *
     * @return Lista de objetos UsuarioEntity
     */
    public ObjetoRetornaEntity listaUsuarios() throws NamingException {


        
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                //System.out.println("ERROR DE CONEXIÓN");
            } else {
                Query query = sesion.createQuery("FROM UsuarioEntity u WHERE u.estado<>'E'");
                
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setNumeroRespuesta(7);
                retorna.setTrazaRespuesta("Lista de Usuarios Cargada Correctamente");
                
            }
        } catch (Exception e) {
            //System.out.println("Error Lista Usuarios: " + e.getMessage());
            e.printStackTrace();
            retorna.setTrazaRespuesta("Error: "+e);
        }finally{
            try {
                //sesion.close();  
                sesion.close();
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        
        
        return retorna;
    }

    /**
     * Método que permite actualizar un Usuario
     *
     *
     * @param usu
     * @return El usuario que se ha actualizado
     */
    public UsuarioEntity actualizarUsuario(UsuarioEntity usu) {

        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                usu.setTrazaRespuesta("Error Conexión: "+validaConexion );
                usu.setNumeroRespuesta(3);
            } else {
                usu.setTrazaRespuesta("Actualización Correcta");
                usu.setNumeroRespuesta(5);
                usu.setUltimoIngreso(null);
                usu.setIdActualizador(0);
                usu.setIdCreador(0);
                fechaUltimoIngreso(usu);
                sesion.update(usu);
               
            }
        } catch (Exception e) {
            usu = null;
            e.printStackTrace();
        }finally{
            try {
                //sesion.close();  
                sesion.close();
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return usu;
    }

    /**
     * Metodo que recibe e inserta un usuario nuevo
     *
     * @param usu
     * @return Devuelve el usuario ingresado
     */
    public UsuarioEntity ingresarUsuario(UsuarioEntity usu) {

        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                usu.setTrazaRespuesta("Error Conexión: "+validaConexion );
                usu.setNumeroRespuesta(3);
            } else {
                usu.setTrazaRespuesta("Inserción Correcta");
                usu.setNumeroRespuesta(6);
                usu.setIdUsuario(maxUsuario());
                sesion.save(usu);
                tx.commit();
                
            }

        } catch (Exception e) {
            e.printStackTrace();
            usu = (UsuarioEntity) new UsuarioEntity();
            usu.setNumeroRespuesta(0);
            usu.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                //sesion.close();  
                sesion.close();
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return usu;
    }

    /**
     * Método que devuelve el Siguiente Id de la tabla Usuarios al relizar una
     * inserción nueva
     *
     * @return Id del nuevo ingreso
     */
    public int maxUsuario() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idUsuario) FROM UsuarioEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }

        } catch (Exception e) {
            ret = -2;
        }
        return ret;
    }

    /**
     * Método que crea la fecha del último ingreso de un usuario
     *
     * @param usu
     */
    public void fechaUltimoIngreso(UsuarioEntity usu) {
        try {
            Date fecha = new java.sql.Date(System.currentTimeMillis());
            usu.setUltimoIngreso(fecha);
            sesion.update(usu);
            tx.commit();
            
        } catch (Exception e) {
            //System.out.println("Error ingresando fecha: " + e);
        }
    }

    /**
     * Método que crea una lista de permisos filtrados por usuario
     *
     * @param idUsuario
     * @return Lista de PermisosEntity
     */
    public ObjetoRetornaEntity permisosFiltrados(int idUsuario) {
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                
            } else {
                Query query = sesion.createQuery("SELECT p FROM PermisosEntity p, RolPermisoEntity rpe, RolesEntity re WHERE re=rpe.id_rol AND rpe.id_permiso=p.id_permiso AND re.id_rol=:idu");
                query.setParameter("idu", idUsuario);
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setNumeroRespuesta(10);
                retorna.setTrazaRespuesta("Carga de permisos Fltrados");
                
            }
        } catch (Exception e) {
            retorna.setNumeroRespuesta(3);
            retorna.setTrazaRespuesta("ERROR: "+e);
            e.printStackTrace();
        }
        return retorna;
    }
    /**
     * Método que trae un Usuario por su ID
     * @param idUsuario
     * @return 
     */
    public UsuarioEntity usuarioPorId(int idUsuario){
        UsuarioEntity usuarioObjeto=new UsuarioEntity();
        try{
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                
            } else {
                Query query=sesion.createQuery("FROM UsuarioEntity u WHERE u.idUsuario=:idu");
                query.setParameter("idu", idUsuario);
                usuarioObjeto=(UsuarioEntity) query.uniqueResult();
                usuarioObjeto.setTrazaRespuesta("Usuario Retornado con Éxito");
                usuarioObjeto.setNumeroRespuesta(11);
               
            }
        }catch(Exception e){
            usuarioObjeto.setNumeroRespuesta(3);
            usuarioObjeto.setTrazaRespuesta("ERROR: "+e);
        }finally{
            try {
                //sesion.close();  
                sesion.close();
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return usuarioObjeto;
    }
    
    public ObjetoRetornaEntity listaUsuariosPorAccion(int idAccion){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                
            } else {
                Query query = sesion.createQuery("SELECT DISTINCT  m.idUsuario  FROM UsuarioEntity u, AccionEntity a, UsuarioMacroprocesoEntity m WHERE m.idAccion=a AND a.idAccion=:idA");
                query.setParameter("idA", idAccion);
                
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setNumeroRespuesta(10);
                retorna.setTrazaRespuesta("Carga de permisos Fltrados");
                
            }
        } catch (Exception e) {
            retorna.setNumeroRespuesta(3);
            retorna.setTrazaRespuesta("ERROR: "+e);
            e.printStackTrace();
        }
        finally{
            try {
                //sesion.close();  
                sesion.close();
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }
    /**
     * 
     * @param idGrupo
     * @return 
     */
    public ObjetoRetornaEntity listaUsuariosPorGrupo(int idGrupo){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                
            } else {
                //System.out.println("id GRupo: "+idGrupo);
                Criteria criteria=sesion.createCriteria(UsuarioGrupoUsuarioEntity.class)
                        .add(Restrictions.eq("grupoUsuario.idGrupoUsuarios", idGrupo));
                criteria.setProjection(Projections.groupProperty("usuario"));
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setNumeroRespuesta(100);
                retorna.setTrazaRespuesta("Carga de usuario Por grupo");
              
            }
        } catch (Exception e) {
            retorna.setNumeroRespuesta(3);
            retorna.setTrazaRespuesta("ERROR: "+e);
            e.printStackTrace();
        }finally{
            try {
                //sesion.close();  
                sesion.close();
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }
}
