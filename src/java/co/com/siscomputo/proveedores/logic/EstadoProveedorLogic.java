package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.proveedores.persistencia.EstadoProveedorEntity;
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
public class EstadoProveedorLogic implements AutoCloseable{

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
     * Método que inserta un Estado de Proveedor nuevo
     *
     * @param objetoEstadoProveedor
     * @return
     */
    public EstadoProveedorEntity insertarEstadoProveedor(EstadoProveedorEntity objetoEstadoProveedor) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoEstadoProveedor.setNumeroRespuesta(3);
                objetoEstadoProveedor.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoEstadoProveedor.setIdEstadoProveedor(maxMetodo());
                sesion.save(objetoEstadoProveedor);
                tx.commit();

                objetoEstadoProveedor.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoEstadoProveedor.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoEstadoProveedor = new EstadoProveedorEntity();
            objetoEstadoProveedor.setNumeroRespuesta(0);
            objetoEstadoProveedor.setTrazaRespuesta(e.getMessage());
        }
        return objetoEstadoProveedor;
    }

    /**
     * Método que trae el siguiente ID de la tabla PRO_ESTA
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idEstadoProveedor) FROM EstadoProveedorEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Estado de Proveedor
     *
     * @param objetoEstadoProveedor
     * @return
     */
    public EstadoProveedorEntity actualizarEstadoProveedor(EstadoProveedorEntity objetoEstadoProveedor) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoEstadoProveedor.setNumeroRespuesta(3);
                objetoEstadoProveedor.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoEstadoProveedor);
                tx.commit();
                sesion.close();
                objetoEstadoProveedor.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoEstadoProveedor.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoEstadoProveedor = new EstadoProveedorEntity();
            objetoEstadoProveedor.setNumeroRespuesta(0);
            objetoEstadoProveedor.setTrazaRespuesta(e.getMessage());
        }
        return objetoEstadoProveedor;
    }

    /**
     * Método Método para consultar la lista de Estado de Proveedor
     */
    public ObjetoRetornaEntity listaEstadoProveedor() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(EstadoProveedorEntity.class);
                criteria.add(Restrictions.ne("estadoEstadoProveedor", "E"));
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Consulta tabla EstadoProveedor exitosa");
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
    
    @Override
    public void close() throws Exception {
        try {
            if (tx != null) {
                tx.commit();
            }
            if (sesion != null) {
                sesion.close();
                sesion = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
