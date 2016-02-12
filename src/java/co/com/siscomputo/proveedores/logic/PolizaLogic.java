/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.proveedores.persistencia.PolizasEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class PolizaLogic {

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
                Query query = sesion.createQuery("SELECT MAX(idPoliza) FROM PolizasEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo para insertar una poliza
     *
     * @param objPoliza
     * @return
     */
    public PolizasEntity insertarPoliza(PolizasEntity objPoliza) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                objPoliza.setIdPoliza(maxDocumento());
                sesion.save(objPoliza);
                tx.commit();
                objPoliza.setNumeroRespuesta(23);
                objPoliza.setTrazaRespuesta("Insercion correcta");
            } else {
                objPoliza.setNumeroRespuesta(0);
                objPoliza.setTrazaRespuesta("Error de conexion :" + conexion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objPoliza = new PolizasEntity();
            objPoliza.setNumeroRespuesta(0);
            objPoliza.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objPoliza;
    }

    /**
     * Metodo para actualizar las polizas
     *
     * @param objPoliza
     * @return
     */
    public PolizasEntity actualizarPoliza(PolizasEntity objPoliza) {
        try {
            String conex = initOperation();
            if ("OK".equalsIgnoreCase(conex)) {
                sesion.update(objPoliza);
                tx.commit();
                objPoliza.setNumeroRespuesta(23);
                objPoliza.setTrazaRespuesta("Actualización exitosa");
            } else {
                objPoliza.setNumeroRespuesta(0);
                objPoliza.setTrazaRespuesta("Error de conexion : " + conex);
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
        return objPoliza;
    }

    /**
     * Metodo para traer todas las polizas
     *
     * @return
     */
    public ObjetoRetornaEntity listaPolizas() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                Query sentencia = sesion.createQuery("FROM PolizasEntity p WHERE p.estadopoliza<>'E'");
                retorno.setRetorna((ArrayList<Object>) sentencia.list());
                retorno.setTrazaRespuesta("Consulta tabla Poliza exitosa");
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
                retorno = new ObjetoRetornaEntity();
                retorno.setNumeroRespuesta(0);
                retorno.setTrazaRespuesta(e.getMessage());
            }

        }
        return retorno;
    }

}
