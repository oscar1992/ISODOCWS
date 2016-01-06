
package co.com.siscomputo.gestiondocumental.logic;

import co.com.siscomputo.gestiondocumental.persistencia.DocumentoRolEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class DocumentoRolLogic {
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
     * Método que inserta un Documentos Rol nuevo
     * @param objetoDocumentoRol
     * @return 
     */    public DocumentoRolEntity insertarDocumentoRol(DocumentoRolEntity objetoDocumentoRol){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoDocumentoRol.setNumeroRespuesta(3);
                objetoDocumentoRol.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoDocumentoRol.setIdDocumentoRol(maxMetodo());
                sesion.save(objetoDocumentoRol);
                tx.commit();

                objetoDocumentoRol.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoDocumentoRol.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoDocumentoRol = new DocumentoRolEntity();
            objetoDocumentoRol.setNumeroRespuesta(0);
            objetoDocumentoRol.setTrazaRespuesta(e.getMessage());
        }
        return objetoDocumentoRol;
    }

     /**
     * Método que trae el siguiente ID de la tabla GDO_TDORO
     * @return 
     */    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idDocumentoRol) FROM DocumentoRolEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un Documentos Rol
     * @param objetoDocumentoRol
     * @return 
     */ public DocumentoRolEntity actualizarDocumentoRol(DocumentoRolEntity objetoDocumentoRol){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoDocumentoRol.setNumeroRespuesta(3);
                objetoDocumentoRol.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoDocumentoRol);
                tx.commit();
                sesion.close();
                objetoDocumentoRol.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoDocumentoRol.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoDocumentoRol = new DocumentoRolEntity();
            objetoDocumentoRol.setNumeroRespuesta(0);
            objetoDocumentoRol.setTrazaRespuesta(e.getMessage());
        }
        return objetoDocumentoRol;
    }
     /**
     * Método Método para consultar la lista de Documentos Rol
     */public ObjetoRetornaEntity listaDocumentoRol(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria=sesion.createCriteria(DocumentoRolEntity.class);
                
                if(!criteria.list().isEmpty()){
                    retorna.setRetorna((ArrayList<Object>) criteria.list());
                }
                retorna.setTrazaRespuesta("Consulta tabla DocumentoRol exitosa");
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