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
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Felipe
 */
public class FiltroContratosLogic {

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
     * Metodo para filtrar contratos
     *
     * @param idTipoProveedorContrato
     * @param estadoContrato
     * @param idProveedorContrato
     * @param fechafinalContrato
     * @return
     */
    public ObjetoRetornaEntity filtrarContratos(Integer idTipoProveedorContrato, String estadoContrato, Integer idProveedorContrato, String fechafinalContrato) {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if ("Ok".equalsIgnoreCase(validaConexion)) {
                Criteria criteria = sesion.createCriteria(ContratosEntity.class);
                if (idTipoProveedorContrato == 1) {
                    criteria.add(Restrictions.eq("idTipoProveedorContrato.idTipoProveedor", idTipoProveedorContrato));
                }
                if (estadoContrato != null) {
                    criteria.add(Restrictions.eq("estadoContrato", estadoContrato));
                }
                if (idProveedorContrato == 1) {
                    criteria.add(Restrictions.eq("idProveedorContrato.idProveedor", idProveedorContrato));
                }
<<<<<<< HEAD
                if (fechafinalContrato != null) {
                    criteria.add(Restrictions.eq("fechafinalContrato", fechafinalContrato));
=======
                if (fechafinalContrato == null) {
                    Date fecha=new Date(Integer.parseInt(fechafinalContrato.substring(7, 10)), Integer.parseInt(fechafinalContrato.substring(4, 5)), Integer.parseInt(fechafinalContrato.substring(0, 2)));
                    criteria.add(Restrictions.eq("fechafinalContrato", fecha));
>>>>>>> origin/master
                }
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Carga exitosa de documentos filtrados");
                retorna.setNumeroRespuesta(99);
            } else {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna = new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        } finally {
            try {
                sesion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return retorna;
    }
}
