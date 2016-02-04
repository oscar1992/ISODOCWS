package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.persistencia.ExtensionesEntity;
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
public class ExtensionesLogic {

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
     * Método que inserta un Extensiones de Archivo nuevo
     *
     * @param objetoExtensiones
     * @return
     */
    public ExtensionesEntity insertarExtensiones(ExtensionesEntity objetoExtensiones) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoExtensiones.setNumeroRespuesta(3);
                objetoExtensiones.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoExtensiones.setIdExtensiones(maxMetodo());
                sesion.save(objetoExtensiones);
                tx.commit();

                objetoExtensiones.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoExtensiones.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoExtensiones = new ExtensionesEntity();
            objetoExtensiones.setNumeroRespuesta(0);
            objetoExtensiones.setTrazaRespuesta(e.getMessage());
        }
        return objetoExtensiones;
    }

    /**
     * Método que trae el siguiente ID de la tabla ADM_TEXTE
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idExtensiones) FROM ExtensionesEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Extensiones de Archivo
     *
     * @param objetoExtensiones
     * @return
     */
    public ExtensionesEntity actualizarExtensiones(ExtensionesEntity objetoExtensiones) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoExtensiones.setNumeroRespuesta(3);
                objetoExtensiones.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoExtensiones);
                tx.commit();
                sesion.close();
                objetoExtensiones.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoExtensiones.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoExtensiones = new ExtensionesEntity();
            objetoExtensiones.setNumeroRespuesta(0);
            objetoExtensiones.setTrazaRespuesta(e.getMessage());
        }
        return objetoExtensiones;
    }

    /**
     * Método Método para consultar la lista de Extensiones de Archivo
     */
    public ObjetoRetornaEntity listaExtensiones() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(ExtensionesEntity.class);
                
                if (criteria.list().isEmpty()) {
                } else {
                    retorna.setRetorna((ArrayList<Object>) criteria.list());
                }
                retorna.setTrazaRespuesta("Consulta tabla Extensiones exitosa");
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
