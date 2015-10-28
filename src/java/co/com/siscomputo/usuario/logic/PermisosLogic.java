/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.ListaAsignaPermisosModulo;
import co.com.siscomputo.administracion.persistencia.ModuloEntity;
import co.com.siscomputo.administracion.persistencia.PermisosEntity;
import co.com.siscomputo.administracion.persistencia.RolPermisoEntity;
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
                sesion.close();
                rolPermiso.setTrazaRespuesta("Inserción de Permiso Exitosa");
                rolPermiso.setNumeroRespuesta(15);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rolPermiso = new RolPermisoEntity();
            rolPermiso.setNumeroRespuesta(0);
            rolPermiso.setTrazaRespuesta(e.getMessage());
        }
        return rolPermiso;
    }
    /**
     * Método para encontrar el sihuiente id de la tabla rol-permisos
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
     * @param rolPermiso 
     */
    public void actualizaRolPermiso(RolPermisoEntity rolPermiso) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                rolPermiso.setNumeroRespuesta(3);
                rolPermiso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                rolPermiso.setId_rol_permiso(maxRolPermiso());
                sesion.update(rolPermiso);
                tx.commit();
                sesion.close();
                rolPermiso.setTrazaRespuesta("Actualización de Permiso Exitosa");
                rolPermiso.setNumeroRespuesta(16);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rolPermiso = new RolPermisoEntity();
            rolPermiso.setNumeroRespuesta(0);
            rolPermiso.setTrazaRespuesta(e.getMessage());
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
                ListaAsignaPermisosModulo objLista = new ListaAsignaPermisosModulo();
                Query query1 = sesion.createQuery("FROM ModuloEntity");
                ArrayList<ModuloEntity> listaModulos = new ArrayList<>();
                listaModulos = (ArrayList<ModuloEntity>) query1.list();
                if (listaModulos != null) {                    
                    ArrayList<PermisosEntity> listan1=new ArrayList<>();
                    for (ModuloEntity item1 : listaModulos) {
                        objLista=new ListaAsignaPermisosModulo();
                        objLista.setModulo(item1);
                        Query query2=sesion.createQuery("SELECT p FROM PermisosEntity p WHERE p.asociadoMenu=0 AND p.asociadoNivel=1 AND p.id_modulo=:modulo");                        
                        query2.setParameter("modulo", item1);
                        listan1=(ArrayList<PermisosEntity>)query2.list();
                        ArrayList<PermisosEntity> listan2=new ArrayList<>();
                        for(PermisosEntity item2: listan1){
                            System.out.println("N1: "+item2.getNombre_permiso()+" - "+item2.getId_permiso());
                            Query query3=sesion.createQuery("SELECT p FROM PermisosEntity p WHERE p.asociadoMenu=:asociado AND p.asociadoNivel=2 AND p.id_modulo=:modulo");
                            query3.setParameter("modulo", item1);
                            query3.setParameter("asociado", item2.getId_permiso());
                            listan2.addAll((ArrayList<PermisosEntity>)query3.list());
                            ArrayList<PermisosEntity> listan3=new ArrayList<>();
                            for(PermisosEntity item3: listan2){
                                System.out.println("listaN2: "+item3.getNombre_permiso());
                                Query query4=sesion.createQuery("SELECT p FROM PermisosEntity p WHERE p.asociadoMenu=:asociado AND p.asociadoNivel=3 AND p.id_modulo=:modulo");
                                query4.setParameter("modulo", item1);
                                query4.setParameter("asociado", item3.getId_permiso());
                                listan3.addAll((ArrayList<PermisosEntity>)query4.list());
                                for(PermisosEntity item4: listan3){
                                    System.out.println("listan3: "+item4.getNombre_permiso());
                                }
                                objLista.setPermisoNivel3(listan3);
                            }
                            objLista.setPermisoNivel2(listan2);
                        }
                        objLista.setPermisoNivel1(listan1);
                        listaRetorna.add(objLista);
                    }                    
                }
                
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaRetorna;
    }
}
