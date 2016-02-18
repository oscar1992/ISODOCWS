/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.gestiondocumental.logic;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import co.com.siscomputo.gestiondocumental.persistencia.DocumentoEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    
    public ObjetoRetornaEntity documentosFiltrado(Integer idTipoDocumental, Integer idPlantilla, Integer idAccion, Date fecha1, Date fecha2){
        ObjetoRetornaEntity retorna = new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                //System.ot.println("F1: "+idTipoDocumental);
                //System.ot.println("F2: "+idPlantilla);
                //System.ot.println("F3: "+idAccion);
                System.out.println("F4: "+fecha1);
                //System.ot.println("F5: "+fecha2);
                Criteria criteria=sesion.createCriteria(DocumentoEntity.class);
                if (idTipoDocumental==0) {
                    //System.ot.println("TipoDocumental Nulo");
                }else{
                    //System.ot.println("");
                    criteria.add(Restrictions.eq("tipoDocumentalDocumento.idTipoDocumental", idTipoDocumental));
                }
                if (idPlantilla==0) {
                    //System.ot.println("Plantilla Nulo");
                }else{
                    criteria.add(Restrictions.eq("plantilla.idPlantilla", idPlantilla));
                }
                if(idAccion==0){
                    //System.ot.println("accion nula");
                }else{
                    criteria.add(Restrictions.eq("accionDocumento.idAccion", idAccion));
                }
                
                if(fecha1==null||fecha2!=null){
                    System.out.println("Fecha1 Nula");
                }else{
                    
                    SimpleDateFormat formas=new SimpleDateFormat("dd-MM-yyyy");
                    String faux=formas.format(fecha1);
                    System.out.println("No nula1: "+fecha1);
                    criteria.add(Restrictions.sqlRestriction("DOCU_FCRE>'"+faux+"'"));
                    //criteria.add(Restrictions.ge("fechaDocumento", fecha1));
                }
                if(fecha2==null||fecha1!=null){
                    System.out.println("Fecha2 Nula");
                }else{
                    
                    SimpleDateFormat formas=new SimpleDateFormat("dd-MM-yyyy");
                    String faux2=formas.format(fecha2);
                    System.out.println("No nula2: "+fecha2);
                    criteria.add(Restrictions.sqlRestriction("DOCU_FCRE<'"+faux2+"'"));
                }
                if(fecha1!=null&&fecha2!=null){
                    SimpleDateFormat formas=new SimpleDateFormat("dd-MM-yyyy");
                    String faux=formas.format(fecha1);
                    System.out.println("No nula1: "+fecha1);
                    String faux2=formas.format(fecha2);
                    System.out.println("No nula2: "+fecha2);
                    criteria.add(Restrictions.between("fechaDocumento", fecha1, fecha2));
                    
                }
                retorna.setRetorna((ArrayList<Object>) criteria.list());
                retorna.setTrazaRespuesta("Carga exitosa de documentos filtrados");
                retorna.setNumeroRespuesta(99);
                //System.ot.println("UAB: "+criteria.list().size());
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
