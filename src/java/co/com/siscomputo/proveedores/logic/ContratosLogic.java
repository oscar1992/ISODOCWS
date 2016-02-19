/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.proveedores.persistencia.ContratosEntity;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felipe
 */
public class ContratosLogic implements AutoCloseable{

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
                Query query = sesion.createQuery("SELECT MAX(IdContrato) FROM ContratosEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }

    /**
     * Metodo Insertar un contrato
     *
     * @param objContrato
     * @return
     */
    public ContratosEntity insertarContrato(ContratosEntity objContrato) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                objContrato.setIdContrato(maxDocumento());
                sesion.save(objContrato);
                tx.commit();
                objContrato.setNumeroRespuesta(23);
                objContrato.setTrazaRespuesta("Insercion correcta");

            } else {
                objContrato = new ContratosEntity();
                objContrato.setNumeroRespuesta(0);
                objContrato.setTrazaRespuesta("Error de conexion " + conexion);
            }

        } catch (Exception e) {
            objContrato = new ContratosEntity();
            e.printStackTrace();
            objContrato.setNumeroRespuesta(0);
            objContrato.setTrazaRespuesta(e.getMessage());
        }
        return objContrato;
    }

    /**
     * Metodo para actualizar un contrato
     *
     * @param ObjContratos
     * @return
     */
    public ContratosEntity actualizarContrato(ContratosEntity ObjContratos) {
        try {
            String conexion = initOperation();
            if ("OK".equalsIgnoreCase(conexion)) {
                sesion.update(ObjContratos);
                tx.commit();
                ObjContratos.setNumeroRespuesta(23);
                ObjContratos.setTrazaRespuesta("Inserción exitosa");

            } else {
                ObjContratos = new ContratosEntity();
                ObjContratos.setNumeroRespuesta(0);
                ObjContratos.setTrazaRespuesta("Error de conexion " + conexion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ObjContratos = new ContratosEntity();
            ObjContratos.setNumeroRespuesta(0);
            ObjContratos.setTrazaRespuesta(e.getMessage());

        }
        return ObjContratos;
    }

    /**
     * Metodo para traer todos los contratos
     *
     * @return
     */
    public ObjetoRetornaEntity listaContratos() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            String Conexion = initOperation();
            if ("OK".equalsIgnoreCase(Conexion)) {
                Query sentencia = sesion.createQuery("From ContratosEntity WHERE estadoContrato<>'E'");
                retorno.setRetorna((ArrayList<Object>) sentencia.list());
                retorno.setTrazaRespuesta("Consulta tabla  de contratos exitosa");
                retorno.setNumeroRespuesta(1);
            } else {
                retorno.setNumeroRespuesta(0);
                retorno.setTrazaRespuesta("Error de conexion" + Conexion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorno = new ObjetoRetornaEntity();
            retorno.setNumeroRespuesta(0);
            retorno.setTrazaRespuesta(e.getMessage());
        }

        return retorno;
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
