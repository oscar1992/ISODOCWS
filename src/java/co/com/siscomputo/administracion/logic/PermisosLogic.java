/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.entites.ListaAsignaPermisosModulo;
import co.com.siscomputo.administracion.entites.ListaAsignaPermisosPermiso;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.ModuloEntity;
import co.com.siscomputo.administracion.persistencia.PermisosEntity;
import co.com.siscomputo.administracion.persistencia.RolPermisoEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class PermisosLogic {

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
     * Método para insertar un permiso nuevo
     *
     * @param rolPermiso
     * @return
     */
    public RolPermisoEntity insertarRolPermiso(RolPermisoEntity rolPermiso) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                rolPermiso.setNumeroRespuesta(3);
                rolPermiso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                rolPermiso.setId_rol_permiso(maxRolPermiso());
                sesion.save(rolPermiso);
                tx.commit();
                
                rolPermiso.setTrazaRespuesta("Inserción de RolPermiso Exitosa");
                rolPermiso.setNumeroRespuesta(15);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rolPermiso = new RolPermisoEntity();
            rolPermiso.setNumeroRespuesta(0);
            rolPermiso.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return rolPermiso;
    }

    /**
     * Método para encontrar el sihuiente id de la tabla rol-permisos
     *
     * @return
     */
    public int maxRolPermiso() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(id_rol_permiso) FROM RolPermisoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método para actualizar un Rol-Perimiso
     *
     * @param rolPermiso
     */
    public void actualizaRolPermiso(RolPermisoEntity rolPermiso) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                rolPermiso.setNumeroRespuesta(3);
                rolPermiso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                sesion.update(rolPermiso);
                tx.commit();
                
                rolPermiso.setTrazaRespuesta("Actualización de RolPermiso Exitosa");
                rolPermiso.setNumeroRespuesta(16);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rolPermiso = new RolPermisoEntity();
            rolPermiso.setNumeroRespuesta(0);
            rolPermiso.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
    }

    /**
     * Método que retorna un objeto con varias listas para la construción de la
     * lista de permisos del sistema
     *
     * @return
     */
    public ArrayList<ListaAsignaPermisosModulo> listaRolPermiso() {
        ArrayList<ListaAsignaPermisosModulo> listaRetorna = new ArrayList<>();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                ArrayList<ListaAsignaPermisosModulo> listaGeneral = new ArrayList<>();
                ArrayList<ModuloEntity> listaModulos = new ArrayList<>();
                Query query = sesion.createQuery("FROM ModuloEntity");
                listaModulos = (ArrayList<ModuloEntity>) query.list();
                for (ModuloEntity modulo : listaModulos) {
                    ListaAsignaPermisosModulo objetoListaGeneral = new ListaAsignaPermisosModulo();
                    objetoListaGeneral.setModulo(modulo);
                    ArrayList<ListaAsignaPermisosPermiso> listaN1 = new ArrayList<>();
                    ArrayList<PermisosEntity> listaPermisosN1 = new ArrayList<>();
                    Query query1 = sesion.createQuery("SELECT p FROM ModuloEntity m, PermisosEntity p WHERE p.id_modulo=m AND m.id_modulo=:idModulo AND p.asociadoNivel=1");
                    query1.setParameter("idModulo", modulo.getId_modulo());
                    listaPermisosN1 = (ArrayList<PermisosEntity>) query1.list();
                    for (PermisosEntity permisos : listaPermisosN1) {
                        ListaAsignaPermisosPermiso objetoPermisoN1 = new ListaAsignaPermisosPermiso();
                        objetoPermisoN1.setPermiso(permisos);
                        ArrayList<ListaAsignaPermisosPermiso> listaN2 = new ArrayList<>();
                        ArrayList<PermisosEntity> listaPermisosN2 = new ArrayList<>();
                        Query query2 = sesion.createQuery("SELECT p FROM ModuloEntity m, PermisosEntity p WHERE p.id_modulo=m AND m.id_modulo=:idModulo AND p.asociadoNivel=2 AND p.asociadoMenu=:asociadoMenu");
                        query2.setParameter("idModulo", modulo.getId_modulo());
                        query2.setParameter("asociadoMenu", permisos.getId_permiso());
                        listaPermisosN2 = (ArrayList<PermisosEntity>) query2.list();
                        for (PermisosEntity permisos2 : listaPermisosN2) {
                            ListaAsignaPermisosPermiso objetoPermisoN2 = new ListaAsignaPermisosPermiso();
                            objetoPermisoN2.setPermiso(permisos2);
                            ArrayList<ListaAsignaPermisosPermiso> listaN3 = new ArrayList<>();
                            ArrayList<PermisosEntity> listaPermisosN3 = new ArrayList<>();
                            Criteria criteria = sesion.createCriteria(PermisosEntity.class);
                            criteria.add(Restrictions.eq("id_modulo", modulo));
                            criteria.add(Restrictions.eq("asociadoNivel", new Integer(3)));
                            criteria.add(Restrictions.eq("asociadoMenu", new Integer(permisos2.getId_permiso())));
                            //Query query3 = sesion.createQuery("SELECT p FROM ModuloEntity m, PermisosEntity p WHERE p.id_modulo=m AND m.id_modulo=:idModulo AND p.asociadoNivel=3 AND p.asociadoMenu=:asociadoMenu");
                            //query3.setParameter("idModulo", modulo.getId_modulo());
                            //query3.setParameter("asociadoMenu", permisos2.getId_permiso());
                            listaPermisosN3 = (ArrayList<PermisosEntity>) criteria.list();
                            //System.out.println("TAMA: "+ permisos2.getAsociadoMenu());
                            if (listaPermisosN3 != null) {
                                for (PermisosEntity permisos3 : listaPermisosN3) {
                                    //System.out.println("tt: "+permisos3.getNombre_permiso()+" - "+permisos3.getId_permiso());
                                    ListaAsignaPermisosPermiso objetoPermisoN3 = new ListaAsignaPermisosPermiso();
                                    objetoPermisoN3.setPermiso(permisos3);
                                    listaN3.add(objetoPermisoN3);
                                }
                            } else {
                                System.out.println("nuevo");
                                listaPermisosN3 = new ArrayList<PermisosEntity>();
                            }

                            ListaAsignaPermisosPermiso objetoPermisoN3Aux = new ListaAsignaPermisosPermiso();
                            objetoPermisoN3Aux.setPermiso(permisos2);

                            listaN3.add(objetoPermisoN3Aux);
                            objetoPermisoN2.setListaS(listaN3);
                            listaN2.add(objetoPermisoN2);
                        }
                        objetoPermisoN1.setListaS(listaN2);
                        listaN1.add(objetoPermisoN1);
                    }
                    objetoListaGeneral.setPermisoNivel1(listaN1);
                    listaGeneral.add(objetoListaGeneral);
                }
                
                listaRetorna = listaGeneral;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaRetorna;
    }
    /**
     * Método que tra la lista de permisos filtrados por un Rol
     * @param idRol
     * @return 
     */
    public ObjetoRetornaEntity listaRolPermisoPorRol(int idRol) {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query = sesion.createQuery("SELECT rpe FROM RolPermisoEntity rpe, RolesEntity r WHERE rpe.id_rol=r AND r.id_rol=:idRol");
                query.setParameter("idRol", idRol);
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Procesos exitosa");
                retorna.setNumeroRespuesta(21);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna = new ObjetoRetornaEntity();
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
}
