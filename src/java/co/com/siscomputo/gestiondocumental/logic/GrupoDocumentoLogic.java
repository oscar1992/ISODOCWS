
package co.com.siscomputo.gestiondocumental.logic;

import co.com.siscomputo.gestiondocumental.persistencia.GrupoDocumentoEntity;
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
public class GrupoDocumentoLogic {
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
     * Método que inserta un Grupos por Documento nuevo
     * @param objetoGrupoDocumento
     * @return 
     */    public GrupoDocumentoEntity insertarGrupoDocumento(GrupoDocumentoEntity objetoGrupoDocumento){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoGrupoDocumento.setNumeroRespuesta(3);
                objetoGrupoDocumento.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoGrupoDocumento.setIdGrupoDocumento(maxMetodo());
                sesion.save(objetoGrupoDocumento);
                tx.commit();

                objetoGrupoDocumento.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoGrupoDocumento.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoGrupoDocumento = new GrupoDocumentoEntity();
            objetoGrupoDocumento.setNumeroRespuesta(0);
            objetoGrupoDocumento.setTrazaRespuesta(e.getMessage());
        }
        return objetoGrupoDocumento;
    }

     /**
     * Método que trae el siguiente ID de la tabla GDO_TGRDO
     * @return 
     */    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idGrupoDocumento) FROM GrupoDocumentoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un Grupos por Documento
     * @param objetoGrupoDocumento
     * @return 
     */ public GrupoDocumentoEntity actualizarGrupoDocumento(GrupoDocumentoEntity objetoGrupoDocumento){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoGrupoDocumento.setNumeroRespuesta(3);
                objetoGrupoDocumento.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoGrupoDocumento);
                tx.commit();
                sesion.close();
                objetoGrupoDocumento.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoGrupoDocumento.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoGrupoDocumento = new GrupoDocumentoEntity();
            objetoGrupoDocumento.setNumeroRespuesta(0);
            objetoGrupoDocumento.setTrazaRespuesta(e.getMessage());
        }
        return objetoGrupoDocumento;
    }
     /**
     * Método Método para consultar la lista de Grupos por Documento
     */public ObjetoRetornaEntity listaGrupoDocumento(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria=sesion.createCriteria(GrupoDocumentoEntity.class);
                criteria.add(Restrictions.eq("estadoGrupoDocumento", "E"));      
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Consulta tabla GrupoDocumento exitosa");
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