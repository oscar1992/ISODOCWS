/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.proveedores.persistencia.ContratosEntity;
import co.com.siscomputo.proveedores.persistencia.ProveedoresEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class FiltroProveedores {
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
     * 
     * @param idTipoEstado
     * @param idCiudad
     * @param idLinea
     * @param idEmpresa
     * @param idResponsable
     * @param idTipoProveedor
     * @param idTibutaria
     * @param idTipoCuenta
     * @param idFormaPago
     * @return 
     */
    public ObjetoRetornaEntity filtrarProvedores(Integer idTipoEstado, Integer idCiudad, Integer idLinea, Integer idEmpresa, Integer idResponsable, Integer idTipoProveedor, Integer idTibutaria, Integer idTipoCuenta, Integer idFormaPago, Date fecha1, Date fecha2) {
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if ("Ok".equalsIgnoreCase(validaConexion)) {
                Criteria criteria = sesion.createCriteria(ProveedoresEntity.class);
                if (idTipoEstado != null) {
                    criteria.add(Restrictions.eq("estadoProveedor.idEstadoProveedor", idTipoEstado));
                }
                if (idCiudad != null) {
                    criteria.add(Restrictions.eq("ciudadProveedor", idCiudad));
                }
                if (idLinea != null) {
                    criteria.add(Restrictions.eq("lineaProveedores.idLinea", idLinea));
                }
                if (idEmpresa != null) {
                    criteria.add(Restrictions.eq("empresaProveedor.idEmpresa", idEmpresa));
                }
                if (idResponsable != null) {
                    criteria.add(Restrictions.eq("usuarioResponsable.idUsuario", idResponsable));
                }
                if (idTipoProveedor != null) {
                    criteria.add(Restrictions.eq("idTipoProveedor.idTipoProveedor", idTipoProveedor));
                }
                if (idTibutaria != null) {
                    criteria.add(Restrictions.eq("idTipoTributario.idTipoTributario", idTibutaria));
                }
                if (idTipoCuenta != null) {
                    criteria.add(Restrictions.eq("idTipocuenta.idTipoCuenta", idTipoCuenta));
                }
                if (idFormaPago != null) {
                    criteria.add(Restrictions.eq("idFormaPago.idFormasPagos", idFormaPago));
                }
                if(fecha1==null||fecha2!=null){
                    System.out.println("Fecha1 Nula");
                }else{
                    
                    SimpleDateFormat formas=new SimpleDateFormat("dd-MM-yyyy");
                    String faux=formas.format(fecha1);
                    System.out.println("No nula1: "+fecha1);
                    criteria.add(Restrictions.sqlRestriction("PROV_FECH>'"+faux+"'"));
                    //criteria.add(Restrictions.ge("fechaDocumento", fecha1));
                }
                if(fecha2==null||fecha1!=null){
                    System.out.println("Fecha2 Nula");
                }else{
                    
                    SimpleDateFormat formas=new SimpleDateFormat("dd-MM-yyyy");
                    String faux2=formas.format(fecha2);
                    System.out.println("No nula2: "+fecha2);
                    criteria.add(Restrictions.sqlRestriction("PROV_FECH<'"+faux2+"'"));
                }
                if(fecha1!=null&&fecha2!=null){                    
                    criteria.add(Restrictions.between("fechaCreacion", fecha1, fecha2));
                    
                }
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Carga exitosa de proveedores filtrados");
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
