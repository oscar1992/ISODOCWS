/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.entites.MenuModuloEntity;
import co.com.siscomputo.administracion.entites.MenuPermisosEntity;
import co.com.siscomputo.administracion.persistencia.ModuloEntity;
import co.com.siscomputo.administracion.persistencia.PermisosEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class MenuLogic {

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
     * Genera la lista de permisos filtrados por Usuario
     *
     * @param idUsuario
     * @return
     */
    public ArrayList<MenuModuloEntity> datosMenu(int idUsuario) {
        ArrayList<ModuloEntity> listaModulos = new ArrayList<>();
        ArrayList<MenuModuloEntity> listaMenuModulo = null;
        System.out.println("IDDD: "+idUsuario);
        try {
            UsuarioLogic usuarioLogic = new UsuarioLogic();
            ArrayList<Object> listaObjetoModulo=usuarioLogic.modulos(idUsuario).getRetorna();
            for(Object item: listaObjetoModulo){
                ModuloEntity modu=(ModuloEntity)item;
                listaModulos.add(modu);
            }
            
            if (listaModulos != null) {
                String validaConexion = initOperation();
                if (!"Ok".equalsIgnoreCase(validaConexion)) {

                } else {
                    for (ModuloEntity item : listaModulos) {
                        if (listaMenuModulo == null) {
                            listaMenuModulo = new ArrayList<>();

                        }
                        MenuModuloEntity mmeAuxiliar = mapeoModuloaMenu(item);
                        Query query = sesion.createQuery("SELECT p FROM PermisosEntity p, ModuloEntity m WHERE p.id_modulo=m AND m.id_modulo=:idM AND p.asociadoNivel=1");
                        query.setParameter("idM", item.getId_modulo());
                        ArrayList<PermisosEntity> listaPermisos = (ArrayList<PermisosEntity>) query.list();
                        ArrayList<MenuPermisosEntity> permPrimNivel = listaMenuPermisos(listaPermisos);
                        System.out.println("UUUUU");
                        if(permPrimNivel!=null){
                            
                            for(MenuPermisosEntity item2:permPrimNivel){
                                System.out.println("idm: "+item.getId_modulo());
                                Query query2=sesion.createQuery("SELECT p FROM PermisosEntity p, ModuloEntity m WHERE p.id_modulo=m AND m.id_modulo=:idM2 AND p.asociadoNivel=2 AND p.asociadoMenu=:aM");
                                query2.setParameter("idM2", item.getId_modulo());
                                query2.setParameter("aM", item2.getId_permiso());
                                ArrayList<PermisosEntity> subLista=(ArrayList<PermisosEntity>) query2.list();
                                ArrayList<MenuPermisosEntity> permSegNivel = listaMenuPermisos(subLista);
                                item2.setSubNivel(permSegNivel);
                            }
                            
                        }
                        mmeAuxiliar.setSubNivel(permPrimNivel);
                        listaMenuModulo.add(mmeAuxiliar);
                    }
                }
                sesion.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaMenuModulo;

    }
    /**
     * Mapea la entidad de un moduloEntity a una clase entity que no tiene anotacion de hibernate
     * @param moduloE
     * @return Retorna un objeto de Menu Modulo Entity
     */
    private MenuModuloEntity mapeoModuloaMenu(ModuloEntity moduloE) {
        MenuModuloEntity rta = null;
        try {
            rta = new MenuModuloEntity();
            rta.setEstado(moduloE.getEstado());
            rta.setIdActualizador(moduloE.getId_actualizador());
            rta.setIdCreador(moduloE.getId_creador());
            rta.setIdModulo(moduloE.getId_modulo());
            rta.setNombre(moduloE.getNombre());
            rta.setOrden(moduloE.getOrden());
            rta.setRutaIcono(moduloE.getRuta_icono());
            rta.setRutaModulo(moduloE.getRuta_modulo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * Se crea una lista de permisos que van a ser un subnivel de otra lista
     * @param listaPermisos
     * @return 
     */
    private ArrayList<MenuPermisosEntity> listaMenuPermisos(ArrayList<PermisosEntity> listaPermisos) {
        ArrayList<MenuPermisosEntity> rta = null;
        try {
            if (listaPermisos != null) {
                for (PermisosEntity item : listaPermisos) {
                    if (rta == null) {
                        rta = new ArrayList<>();
                    }
                    MenuPermisosEntity mpeAuxiliar = new MenuPermisosEntity();
                    mpeAuxiliar.setAsociadoMenu(item.getAsociadoMenu());
                    mpeAuxiliar.setAsociadoNivel(item.getAsociadoNivel());
                    mpeAuxiliar.setEstado(item.getEstado());
                    mpeAuxiliar.setId_actualizador(item.getId_actualizador());
                    mpeAuxiliar.setId_creador(item.getId_creador());
                    mpeAuxiliar.setId_permiso(item.getId_permiso());
                    mpeAuxiliar.setNombre_permiso(item.getNombre_permiso());
                    mpeAuxiliar.setRuta(item.getRuta());
                    rta.add(mpeAuxiliar);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
}
