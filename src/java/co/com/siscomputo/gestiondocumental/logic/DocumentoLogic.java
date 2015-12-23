
package co.com.siscomputo.gestiondocumental.logic;

import co.com.siscomputo.gestiondocumental.persistencia.DocumentoEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.AccionEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class DocumentoLogic {
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
     * Método que inserta un Documento nuevo
     * @param objetoDocumento
     * @return 
     */    public DocumentoEntity insertarDocumento(DocumentoEntity objetoDocumento){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoDocumento.setNumeroRespuesta(3);
                objetoDocumento.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoDocumento.setIdDocumento(maxDocumento());
                sesion.save(objetoDocumento);
                tx.commit();

                objetoDocumento.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoDocumento.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoDocumento = new DocumentoEntity();
            objetoDocumento.setNumeroRespuesta(0);
            objetoDocumento.setTrazaRespuesta(e.getMessage());
        }
        return objetoDocumento;
    }

     /**
     * Método que trae el siguiente ID de la tabla GDO_TDOCU
     * @return 
     */    private int maxDocumento() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idDocumento) FROM DocumentoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un Documento
     * @param objetoDocumento
     * @return 
     */ public DocumentoEntity actualizarDocumento(DocumentoEntity objetoDocumento){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoDocumento.setNumeroRespuesta(3);
                objetoDocumento.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoDocumento);
                tx.commit();
                sesion.close();
                objetoDocumento.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoDocumento.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoDocumento = new DocumentoEntity();
            objetoDocumento.setNumeroRespuesta(0);
            objetoDocumento.setTrazaRespuesta(e.getMessage());
        }
        return objetoDocumento;
    }
     /**
     * Método Método para consultar la lista de Documento
     * @return 
     */public ObjetoRetornaEntity listaDocumento(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM DocumentoEntity d WHERE d.estadoDocumento<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Documento exitosa");
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
     * Método que retorna una lista de relaciones Usuarios-MacroProcesos
     * @return 
     */
    public ObjetoRetornaEntity listaUsuariosPorDocumento(int idDocumento){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query = query=sesion.createQuery("SELECT ump FROM UsuarioMacroprocesoEntity ump, UsuarioEntity u, AccionEntity a WHERE ump.idUsuario=u AND ump.idAccion=a AND a.idAccion=:idAccion");
                
                query.setParameter("idAccion", idDocumento);
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla usuario-Macroproceso exitosa");
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
    /**
     * Método que trae una lista de documetnos por Acción
     * @param idAccion
     * @return 
     */
    public ObjetoRetornaEntity DocumentosPorAccion(AccionEntity idAccion){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria=sesion.createCriteria(DocumentoEntity.class);
                criteria.add(Restrictions.eq("accionDocumento", idAccion));
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Consulta tabla Documentos exitosa");
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