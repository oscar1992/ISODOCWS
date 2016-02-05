package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.proveedores.persistencia.LineaEntity;
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
public class LineaLogic {

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
     * Método que inserta un Linea del Proveedor nuevo
     *
     * @param objetoLinea
     * @return
     */
    public LineaEntity insertarLinea(LineaEntity objetoLinea) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoLinea.setNumeroRespuesta(3);
                objetoLinea.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoLinea.setIdLinea(maxMetodo());
                sesion.save(objetoLinea);
                tx.commit();

                objetoLinea.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoLinea.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoLinea = new LineaEntity();
            objetoLinea.setNumeroRespuesta(0);
            objetoLinea.setTrazaRespuesta(e.getMessage());
        }
        return objetoLinea;
    }

    /**
     * Método que trae el siguiente ID de la tabla PRO_TLINE
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idLinea) FROM LineaEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Linea del Proveedor
     *
     * @param objetoLinea
     * @return
     */
    public LineaEntity actualizarLinea(LineaEntity objetoLinea) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoLinea.setNumeroRespuesta(3);
                objetoLinea.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoLinea);
                tx.commit();
                sesion.close();
                objetoLinea.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoLinea.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoLinea = new LineaEntity();
            objetoLinea.setNumeroRespuesta(0);
            objetoLinea.setTrazaRespuesta(e.getMessage());
        }
        return objetoLinea;
    }

    /**
     * Método Método para consultar la lista de Linea del Proveedor
     */
    public ObjetoRetornaEntity listaLinea() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(LineaEntity.class);
                criteria.add(Restrictions.ne("estadoLinea", "E"));
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Consulta tabla Linea exitosa");
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
