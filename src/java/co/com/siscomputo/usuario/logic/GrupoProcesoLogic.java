
package co.com.siscomputo.usuario.logic;

import co.com.siscomputo.administracion.persistencia.GrupoProcesoEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class GrupoProcesoLogic {
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
     * Método que inserta un Grupo de Usuarios y Procesos nuevo
     * @param objetoGrupoProceso
     * @return 
     */
    public ObjetoRetornaEntity insertarGrupoProceso(ArrayList<GrupoProcesoEntity> objetoGrupoProceso){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                
                int siguiente=maxMetodo();
                ArrayList<Object>listaRetorna=new ArrayList<>();
                for(GrupoProcesoEntity lista:objetoGrupoProceso){
                    lista.setIdGrupoProceso(siguiente);
                    System.out.println("ACC: "+lista.getAccionGrupoProceso().getNombreAccion());
                    siguiente++;
                    sesion.save(lista);
                    listaRetorna.add(lista);
                }
                retorna.setRetorna(listaRetorna);
                
                tx.commit();

                retorna.setTrazaRespuesta("Inserción de Grupo-Procesos exitoso");
                retorna.setNumeroRespuesta(18);
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
    
    public void limpia(int idGrupo, int idProceso){
        String validaConexion = initOperation();
        
        if (!"Ok".equalsIgnoreCase(validaConexion)) {
            System.out.println("error conexión: "+validaConexion);
        } else {
            Query query = sesion.createQuery("delete GrupoProcesoEntity WHERE grupoUsuarioProceso.idGrupoUsuarios=:idGrupo AND procesoGrupoProceso.idProceso=:idProceso");
            query.setParameter("idGrupo", idGrupo);
            query.setParameter("idProceso", idProceso);
            int result = query.executeUpdate();
            tx.commit();
            
            System.out.println("LIMPIA: " + result);
        }
    }

     /**
     * Método que trae el siguiente ID de la tabla ADM_TGRPR
     * @return 
     */    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idGrupoProceso) FROM GrupoProcesoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un Grupo de Usuarios y Procesos
     * @param objetoGrupoProceso
     * @return 
     */ public GrupoProcesoEntity actualizarGrupoProceso(GrupoProcesoEntity objetoGrupoProceso){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoGrupoProceso.setNumeroRespuesta(3);
                objetoGrupoProceso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoGrupoProceso);
                tx.commit();
                sesion.close();
                objetoGrupoProceso.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoGrupoProceso.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoGrupoProceso = new GrupoProcesoEntity();
            objetoGrupoProceso.setNumeroRespuesta(0);
            objetoGrupoProceso.setTrazaRespuesta(e.getMessage());
        }
        return objetoGrupoProceso;
    }
    /**
     * Método Método para consultar la lista de Grupo de Usuarios y Procesos
     */public ObjetoRetornaEntity listaGrupoProceso(int idGrupo){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " +  validaConexion);
            } else {
                Criteria criteria=sesion.createCriteria(GrupoProcesoEntity.class).createAlias("grupoUsuarioProceso", "gup");
                criteria.createAlias("procesoGrupoProceso", "pgp");
                criteria.setProjection(Projections.distinct(Projections.property("procesoGrupoProceso")));
                criteria.add(Restrictions.eq("gup.idGrupoUsuarios", idGrupo));      
                try {
                    retorna.setRetorna((ArrayList<Object>) criteria.list());
                } catch (Exception e) {
                    System.out.println("vacio");
                }
                retorna.setTrazaRespuesta("Consulta tabla GrupoProceso exitosa");
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
     * Método Método para consultar la lista de Grupo de Usuarios y Procesos
     * @param idGrupo
     * @param idProceso
     * @return 
     */
    public ObjetoRetornaEntity listaGrupoProcesoAccion(int idGrupo, int idProceso){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " +  validaConexion);
            } else {
                Criteria criteria=sesion.createCriteria(GrupoProcesoEntity.class).createAlias("grupoUsuarioProceso", "gup");
                criteria.createAlias("procesoGrupoProceso", "pgp");
                //criteria.setProjection(Projections.distinct(Projections.property("procesoGrupoProceso")));
                criteria.add(Restrictions.eq("gup.idGrupoUsuarios", idGrupo));      
                criteria.add(Restrictions.eq("pgp.idProceso", idProceso));      
                try {
                    retorna.setRetorna((ArrayList<Object>) criteria.list());
                } catch (Exception e) {
                    System.out.println("vacio");
                    e.printStackTrace();
                }
                retorna.setTrazaRespuesta("Consulta tabla GrupoProceso exitosa");
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
     * Método Método para consultar la lista de Grupo de Usuarios y Procesos por Acción
     * @param idAccion
     * @param idProceso
     * @return 
     */
    public ObjetoRetornaEntity listaGrupoProcesoPorAccion(int idAccion, int idProceso){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " +  validaConexion);
            } else {
                Criteria criteria=sesion.createCriteria(GrupoProcesoEntity.class).createAlias("grupoUsuarioProceso", "gup");
                criteria.createAlias("procesoGrupoProceso", "pgp");
                //criteria.setProjection(Projections.distinct(Projections.property("procesoGrupoProceso")));
                criteria.add(Restrictions.eq("procesoGrupoProceso.idProceso", idProceso));                
                criteria.add(Restrictions.eq("accionGrupoProceso.idAccion", idAccion));                
                try {
                    retorna.setRetorna((ArrayList<Object>) criteria.list());
                } catch (Exception e) {
                    System.out.println("vacio");
                    e.printStackTrace();
                }
                retorna.setTrazaRespuesta("Consulta tabla GrupoProceso exitosa");
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
     
    
}