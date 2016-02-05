package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.proveedores.persistencia.TipoDocumentoEntity;
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
public class TipoDocumentoLogic {

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
     * Método que inserta un Tipo de Documento nuevo
     *
     * @param objetoTipoDocumento
     * @return
     */
    public TipoDocumentoEntity insertarTipoDocumento(TipoDocumentoEntity objetoTipoDocumento) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoTipoDocumento.setNumeroRespuesta(3);
                objetoTipoDocumento.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoTipoDocumento.setIdTipoDocumento(maxMetodo());
                sesion.save(objetoTipoDocumento);
                tx.commit();

                objetoTipoDocumento.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoTipoDocumento.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoTipoDocumento = new TipoDocumentoEntity();
            objetoTipoDocumento.setNumeroRespuesta(0);
            objetoTipoDocumento.setTrazaRespuesta(e.getMessage());
        }
        return objetoTipoDocumento;
    }

    /**
     * Método que trae el siguiente ID de la tabla PRO_TTIDO
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idTipoDocumento) FROM TipoDocumentoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Tipo de Documento
     *
     * @param objetoTipoDocumento
     * @return
     */
    public TipoDocumentoEntity actualizarTipoDocumento(TipoDocumentoEntity objetoTipoDocumento) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoTipoDocumento.setNumeroRespuesta(3);
                objetoTipoDocumento.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoTipoDocumento);
                tx.commit();
                sesion.close();
                objetoTipoDocumento.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoTipoDocumento.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoTipoDocumento = new TipoDocumentoEntity();
            objetoTipoDocumento.setNumeroRespuesta(0);
            objetoTipoDocumento.setTrazaRespuesta(e.getMessage());
        }
        return objetoTipoDocumento;
    }

    /**
     * Método Método para consultar la lista de Tipo de Documento
     */
    public ObjetoRetornaEntity listaTipoDocumento() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(TipoDocumentoEntity.class);
                criteria.add(Restrictions.ne("estadoTipoDocumento", "E"));
                if (criteria.list().isEmpty()) {

                } else {
                    retorna.setRetorna((ArrayList<Object>) criteria.list());
                }
                retorna.setTrazaRespuesta("Consulta tabla TipoDocumento exitosa");
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
