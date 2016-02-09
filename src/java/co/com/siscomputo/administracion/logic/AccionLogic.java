package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.persistencia.AccionEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioGrupoUsuarioEntity;
import co.com.siscomputo.gestiondocumental.persistencia.GrupoDocumentoEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class AccionLogic {

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
     * Método que inserta un Acción nuevo
     *
     * @param objetoAccion
     * @return
     */
    public AccionEntity insertarAccion(AccionEntity objetoAccion) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoAccion.setNumeroRespuesta(3);
                objetoAccion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoAccion.setIdAccion(maxMetodo());
                sesion.save(objetoAccion);
                tx.commit();

                objetoAccion.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoAccion.setNumeroRespuesta(18);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoAccion = new AccionEntity();
            objetoAccion.setNumeroRespuesta(0);
            objetoAccion.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoAccion;
    }

    /**
     * Método que trae el siguiente ID de la tabla ADM_TACCI
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idAccion) FROM AccionEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Acción
     *
     * @param objetoAccion
     * @return
     */
    public AccionEntity actualizarAccion(AccionEntity objetoAccion) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoAccion.setNumeroRespuesta(3);
                objetoAccion.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoAccion);
                tx.commit();
                
                objetoAccion.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoAccion.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoAccion = new AccionEntity();
            objetoAccion.setNumeroRespuesta(0);
            objetoAccion.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoAccion;
    }

    /**
     * Método Método para consultar la lista de Acción
     */
    public ObjetoRetornaEntity listaAccion() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query = sesion.createQuery("FROM AccionEntity d WHERE d.estadoAccion<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Accion exitosa");
                retorna.setNumeroRespuesta(22);                
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

    /**
     * Método Método para consultar la lista de Acciones asociada a un grupo de
     * usuarios
     *
     * @param idUsuario
     * @return
     */
    public ObjetoRetornaEntity listaAccionPorUsuario(int idUsuario) {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                /*Criteria criteria2=sesion.createCriteria(UsuarioGrupoUsuarioEntity.class)
                 .createCriteria("usuario")
                 .add(Restrictions.eq("idUsuario", idUsuario));
                
                 ArrayList<UsuarioGrupoUsuarioEntity> laux=(ArrayList<UsuarioGrupoUsuarioEntity>) criteria2.list();
                 ArrayList<Object> lgue=new ArrayList<>();
                 for(UsuarioGrupoUsuarioEntity ugue:laux){
                 lgue.add(ugue.getGrupoUsuario());
                 }
                 Criteria criteria=sesion.createCriteria(GrupoDocumentoEntity.class);
                 criteria.add(Property.forName("grupousuariosGrupoDocumento").in(lgue));
                 criteria.setProjection(Projections.distinct(Projections.property("grupousuariosGrupoDocumento")));
                 ArrayList<GrupoUsuariosEntity> gpe=(ArrayList<GrupoUsuariosEntity>) criteria.list();
                
                 Criteria criteria3=sesion.createCriteria(GrupoDocumentoEntity.class)
                 .add(Property.forName("grupousuariosGrupoDocumento").in(lgue));
                 criteria3.setProjection(Projections.groupProperty("grupousuariosGrupoDocumento"));
                 ArrayList<GrupoDocumentoEntity> gde=(ArrayList<GrupoDocumentoEntity>) criteria3.list();
                
                 ArrayList<Object> acciones=new ArrayList<>();
                 for(GrupoDocumentoEntity grupo:gde){
                 acciones.add(grupo.getAccionGrupoDocumento());
                 }*/
                Criteria criteria = sesion.createCriteria(UsuarioGrupoUsuarioEntity.class)
                        .add(Restrictions.eq("usuario.idUsuario", idUsuario))
                        .setProjection(Projections.groupProperty("grupoUsuario"));                
                Criteria criteria1 = sesion.createCriteria(GrupoDocumentoEntity.class);
                criteria1.createAlias("accionGrupoDocumento", "agd").add(Restrictions.eq("agd.estadoAccion", "A"));//ALIAS!!!!!!!
                criteria1.add(Property.forName("grupousuariosGrupoDocumento").in(criteria.list()));                
                criteria1.setProjection(Projections.groupProperty("accionGrupoDocumento"));
                //criteria1.add(Property.forName("accionGrupoDocumento.estadoAccion").eq("A"));
                //ArrayList<Object> listaR=(ArrayList<Object>) criteria1.list();
                ArrayList<Object> listaR = (ArrayList<Object>) criteria1.list();
                retorna.setRetorna(listaR);
                retorna.setTrazaRespuesta("Consulta tabla Accion exitosa");
                retorna.setNumeroRespuesta(22);
                
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

    /**
     * Método que retorna una acción de un usuario
     *
     * @param idAccion
     * @return
     */
    public AccionEntity AccionPorId(Integer idAccion) {
        AccionEntity accionEntity = new AccionEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                accionEntity.setNumeroRespuesta(3);
                accionEntity.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(AccionEntity.class);
                criteria.add(Restrictions.eq("idAccion", idAccion));

                accionEntity = (AccionEntity) criteria.uniqueResult();
                accionEntity.setNumeroRespuesta(22);
                accionEntity.setTrazaRespuesta("Consulta Accion por ID exitosa");
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            accionEntity = new AccionEntity();
            accionEntity.setNumeroRespuesta(0);
            accionEntity.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return accionEntity;
    }

    /**
     * Método que retorna una lista de acciones de un usuario
     *
     * @param idUsuario
     * @return
     */
    public ObjetoRetornaEntity accionesPorUsuario(Integer idUsuario) {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {      
                Query query=sesion.createQuery("select distinct(a) from AccionEntity a, GrupoUsuariosEntity gue, GrupoProcesoEntity gpe, UsuarioGrupoUsuarioEntity ugue Where a=gpe.accionGrupoProceso AND gue=gpe.grupoUsuarioProceso AND ugue.usuario.idUsuario=:idU AND ugue.grupoUsuario=gue AND a.estadoAccion<>'E'");
                query.setParameter("idU", idUsuario);
                retorna.setRetorna((ArrayList<Object>) query.list());
                System.out.println("TAMA: "+query.list().size());
                retorna.setNumeroRespuesta(22);
                retorna.setTrazaRespuesta("Consulta Accion por ID exitosa");                
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna = new AccionEntity();
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
