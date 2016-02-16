/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.proveedores.persistencia.ProveedoresEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class ProveedoresLogic {

    private Session sesion;//Variable de la sesión y conexión de la base de datos
    private Transaction tx;//Variable que almacena las consultas y las transacciones de la base de datos

    /**
     * Metodo para establecer una conexion con la base de datos
     *
     * @return
     */
    private String initOperation() {
        String respuesta;
        try {
            if (sesion == null) {
                sesion = HibernateUtil.getSessionFactory().openSession();
                tx = sesion.beginTransaction();
            }
            respuesta = "Ok";

        } catch (Exception e) {
            respuesta = "Error Conexión Hibernate " + e;
        }

        return respuesta;
    }

    /**
     * Método que trae el siguiente ID de una tabla
     *
     * @return
     */
    private int maxDocumento() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idProveedor) FROM ProveedoresEntity ");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo para insertar un proveedor
     *
     * @param objProveedor
     * @return
     */
    public ProveedoresEntity InsertarProveedor(ProveedoresEntity objProveedor) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                objProveedor.setIdProveedor(maxDocumento());
                sesion.save(objProveedor);
                tx.commit();
                objProveedor.setNumeroRespuesta(23);
                objProveedor.setTrazaRespuesta("Insercion exitosa");

            } else {
                objProveedor.setNumeroRespuesta(3);
                objProveedor.setTrazaRespuesta("Error de Conexión " + conexion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            objProveedor.setNumeroRespuesta(0);
            objProveedor.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
                objProveedor.setNumeroRespuesta(0);
                objProveedor.setTrazaRespuesta(e.getMessage());
            }

        }
        return objProveedor;
    }

    /**
     * Metodo para actualziar un proveedor
     *
     * @param objProveedor
     * @return
     */
    public ProveedoresEntity actualizarProveedores(ProveedoresEntity objProveedor) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                sesion.update(objProveedor);
                tx.commit();
                objProveedor.setNumeroRespuesta(23);
                objProveedor.setTrazaRespuesta("Insercion exitosa");
            } else {
                objProveedor.setNumeroRespuesta(3);
                objProveedor.setTrazaRespuesta("Error de Conexión " + conexion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objProveedor.setNumeroRespuesta(0);
            objProveedor.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
                objProveedor.setNumeroRespuesta(0);
                objProveedor.setTrazaRespuesta(e.getMessage());
            }
            
        }
        return objProveedor;
    }

    /**
     * Metodo para mostrar todos los proveedores
     *
     * @return
     */
    public ObjetoRetornaEntity listaProveedores() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();

        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                Query sentencia = sesion.createQuery("FROM ProveedoresEntity p WHERE p.estadoProveedores<>'E'");
                retorno.setRetorna((ArrayList<Object>) sentencia.list());
                retorno.setTrazaRespuesta("Consulta tabla Proveedores exitosa");
                retorno.setNumeroRespuesta(1);
            } else {
                retorno.setNumeroRespuesta(0);
                retorno.setTrazaRespuesta("Error de conexion" + conexion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorno = new ObjetoRetornaEntity();
            retorno.setNumeroRespuesta(0);
            retorno.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  retorno;
    }
}
