package co.com.siscomputo.gestiondocumental.logic;

import co.com.siscomputo.gestiondocumental.persistencia.DocumentoProcesoEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class DocumentoProcesoLogic {

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
     * Método que inserta un Usuarios Asignados Sobre el Documento nuevo
     *
     * @param objetoDocumentoProceso
     * @return
     */
    public DocumentoProcesoEntity insertarDocumentoProceso(DocumentoProcesoEntity objetoDocumentoProceso) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoDocumentoProceso.setNumeroRespuesta(3);
                objetoDocumentoProceso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoDocumentoProceso.setIdDocumentoProceso(maxMetodo());
                sesion.save(objetoDocumentoProceso);
                tx.commit();

                objetoDocumentoProceso.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoDocumentoProceso.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoDocumentoProceso = new DocumentoProcesoEntity();
            objetoDocumentoProceso.setNumeroRespuesta(0);
            objetoDocumentoProceso.setTrazaRespuesta(e.getMessage());
        }
        return objetoDocumentoProceso;
    }

    /**
     * Método que trae el siguiente ID de la tabla GDO_TDOPR
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idDocumentoProceso) FROM DocumentoProcesoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Usuarios Asignados Sobre el Documento
     *
     * @param objetoDocumentoProceso
     * @return
     */
    public DocumentoProcesoEntity actualizarDocumentoProceso(DocumentoProcesoEntity objetoDocumentoProceso) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoDocumentoProceso.setNumeroRespuesta(3);
                objetoDocumentoProceso.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoDocumentoProceso);
                tx.commit();
                sesion.close();
                objetoDocumentoProceso.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoDocumentoProceso.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoDocumentoProceso = new DocumentoProcesoEntity();
            objetoDocumentoProceso.setNumeroRespuesta(0);
            objetoDocumentoProceso.setTrazaRespuesta(e.getMessage());
        }
        return objetoDocumentoProceso;
    }

    /**
     * Método Método para consultar la lista de Usuarios Asignados Sobre el
     * Documento
     */
    public ObjetoRetornaEntity listaDocumentoProceso() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query = sesion.createQuery("FROM DocumentoProcesoEntity d ");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla DocumentoProceso exitosa");
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
}
