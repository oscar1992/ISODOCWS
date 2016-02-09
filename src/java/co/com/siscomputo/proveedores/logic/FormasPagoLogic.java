/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.proveedores.persistencia.FormasPagoEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class FormasPagoLogic {

    private Session sesion;//Variable de la sesión y conexión de la base de datos
    private Transaction tx;//Variable que almacena las consultas y las transacciones de la base de datos

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
                Query query = sesion.createQuery("SELECT MAX(idFormasPagos) FROM FormasPagoEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo para insertar una forma de pago
     *
     * @param objFormaPago
     * @return
     */
    public FormasPagoEntity insertarFormasPago(FormasPagoEntity objFormaPago) {
        try {
            String validacionCon = initOperation();
            if ("OK".equalsIgnoreCase(validacionCon)) {
                objFormaPago.setIdFormasPagos(maxDocumento());
                sesion.save(objFormaPago);
                tx.commit();
            } else {
                objFormaPago.setNumeroRespuesta(0);
                objFormaPago.setTrazaRespuesta("Error de conexión" + validacionCon);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objFormaPago = new FormasPagoEntity();
            objFormaPago.setNumeroRespuesta(0);
            objFormaPago.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objFormaPago;
    }

    /**
     * Metodo para actualizar una forma de pago
     *
     * @param objFormaPago
     * @return
     */
    public FormasPagoEntity actualizarFormasPago(FormasPagoEntity objFormaPago) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                sesion.update(objFormaPago);
                tx.commit();
            } else {
                objFormaPago.setTrazaRespuesta("Error de conexion" + conexion);
                objFormaPago.setNumeroRespuesta(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return objFormaPago;
    }

    /**
     * Metodo para traer todas las formas de pago
     *
     * @return
     */
    public ObjetoRetornaEntity listaFormaPago() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                Query sentencia = sesion.createQuery("FROM FormasPagoEntity f WHERE f.idFormasPagos<>'E'");
                retorno.setRetorna((ArrayList<Object>) sentencia.list());
                retorno.setTrazaRespuesta("Consulta tabla Documento exitosa");
                retorno.setNumeroRespuesta(1);
            } else {
                retorno.setNumeroRespuesta(0);
                retorno.setTrazaRespuesta("Error de conexion" + conexion);
            }
        } catch (Exception e) {
            retorno = new ObjetoRetornaEntity();
            retorno.setNumeroRespuesta(0);
            retorno.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                retorno = new ObjetoRetornaEntity();
                retorno.setNumeroRespuesta(0);
                retorno.setTrazaRespuesta(e.getMessage());
            }
        }
        return retorno;
    }

}
