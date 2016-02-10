/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.gestiondocumental.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.gestiondocumental.persistencia.DocumentoEntity;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author LENOVO
 */
public class FiltroLogic {
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
    
    public ObjetoRetornaEntity documentosFiltrado(Integer idTipoDocumental, Integer idPlantilla, Integer idAccion, String fecha1, String fecha2){
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                System.out.println("F1: "+idTipoDocumental);
                System.out.println("F2: "+idPlantilla);
                System.out.println("F3: "+idAccion);
                System.out.println("F4: "+fecha1);
                System.out.println("F5: "+fecha2);
                Criteria criteria=sesion.createCriteria(DocumentoEntity.class);
                if (idTipoDocumental==0) {
                    System.out.println("TipoDocumental Nulo");
                }else{
                    System.out.println("");
                    criteria.add(Restrictions.eq("tipoDocumentalDocumento.idTipoDocumental", idTipoDocumental));
                }
                if (idPlantilla==0) {
                    System.out.println("Plantilla Nulo");
                }else{
                    criteria.add(Restrictions.eq("plantilla.idPlantilla", idPlantilla));
                }
                if(idAccion==0){
                    System.out.println("accion nula");
                }else{
                    criteria.add(Restrictions.eq("accionDocumento.idAccion", idAccion));
                }
                
                if(fecha1==null){
                    System.out.println("Fecha1 Nula");
                }
                if(fecha2==null){
                    System.out.println("Fecha2 Nula");
                }
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Carga exitosa de documentos filtrados");
                retorna.setNumeroRespuesta(99);
                System.out.println("UAB: "+criteria.list().size());
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna = new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna;
    }
}
