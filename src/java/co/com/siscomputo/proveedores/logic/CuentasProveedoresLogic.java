package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.proveedores.persistencia.CuentasProveedoresEntity;
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
public class CuentasProveedoresLogic implements AutoCloseable{

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
     * Método que inserta un Tipo de Cuenta de Proveedor nuevo
     *
     * @param objetoCuentasProveedores
     * @return
     */
    public CuentasProveedoresEntity insertarCuentasProveedores(CuentasProveedoresEntity objetoCuentasProveedores) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoCuentasProveedores.setNumeroRespuesta(3);
                objetoCuentasProveedores.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoCuentasProveedores.setIdCuentasProveedores(maxMetodo());
                sesion.save(objetoCuentasProveedores);
                tx.commit();

                objetoCuentasProveedores.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoCuentasProveedores.setNumeroRespuesta(18);
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoCuentasProveedores = new CuentasProveedoresEntity();
            objetoCuentasProveedores.setNumeroRespuesta(0);
            objetoCuentasProveedores.setTrazaRespuesta(e.getMessage());
        }
        return objetoCuentasProveedores;
    }

    /**
     * Método que trae el siguiente ID de la tabla PRO_TCUEN
     *
     * @return
     */
    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idCuentasProveedores) FROM CuentasProveedoresEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Método que actualiza un Tipo de Cuenta de Proveedor
     *
     * @param objetoCuentasProveedores
     * @return
     */
    public CuentasProveedoresEntity actualizarCuentasProveedores(CuentasProveedoresEntity objetoCuentasProveedores) {
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoCuentasProveedores.setNumeroRespuesta(3);
                objetoCuentasProveedores.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("JJ");
                sesion.update(objetoCuentasProveedores);
                tx.commit();
                sesion.close();
                objetoCuentasProveedores.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoCuentasProveedores.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoCuentasProveedores = new CuentasProveedoresEntity();
            objetoCuentasProveedores.setNumeroRespuesta(0);
            objetoCuentasProveedores.setTrazaRespuesta(e.getMessage());
        }
        return objetoCuentasProveedores;
    }

    /**
     * Método Método para consultar la lista de Tipo de Cuenta de Proveedor
     */
    public ObjetoRetornaEntity listaCuentasProveedores() {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Criteria criteria = sesion.createCriteria(CuentasProveedoresEntity.class);
                if (criteria.list().isEmpty()) {

                } else {
                    criteria.add(Restrictions.ne("estadoCuentasProveedores", "E"));
                }
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Consulta tabla CuentasProveedores exitosa");
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
