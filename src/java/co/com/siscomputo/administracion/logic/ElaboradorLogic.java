
package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.persistencia.ElaboradorEntity;
import co.com.siscomputo.conexion.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.util.ArrayList;
import org.hibernate.HibernateException;

/**
 *
 * @author LENOVO
 */
public class ElaboradorLogic {
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
     * Método que inserta un Aprobador Elaborador nuevo
     * @param objetoElaborador
     * @return 
     */    public ElaboradorEntity insertarElaborador(ElaboradorEntity objetoElaborador){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoElaborador.setNumeroRespuesta(3);
                objetoElaborador.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoElaborador.setIdElaborador(maxMetodo());
                sesion.save(objetoElaborador);
                tx.commit();

                objetoElaborador.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoElaborador.setNumeroRespuesta(18);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoElaborador = new ElaboradorEntity();
            objetoElaborador.setNumeroRespuesta(0);
            objetoElaborador.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoElaborador;
    }

     /**
     * Método que trae el siguiente ID de la tabla ADM_TELAB
     * @return 
     */    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idElaborador) FROM ElaboradorEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un Aprobador Elaborador
     * @param objetoElaborador
     * @return 
     */ public ElaboradorEntity actualizarElaborador(ElaboradorEntity objetoElaborador){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoElaborador.setNumeroRespuesta(3);
                objetoElaborador.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoElaborador);
                tx.commit();
                
                objetoElaborador.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoElaborador.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoElaborador = new ElaboradorEntity();
            objetoElaborador.setNumeroRespuesta(0);
            objetoElaborador.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoElaborador;
    }
     /**
     * Método Método para consultar la lista de Aprobador Elaborador
     */public ObjetoRetornaEntity listaElaborador(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM ElaboradorEntity d WHERE d.estadoElaborador<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Elaborador exitosa");
                retorna.setNumeroRespuesta(22);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorna=new ObjetoRetornaEntity();
            retorna.setNumeroRespuesta(0);
            retorna.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return retorna ;
    }
/**
     * Método que devuelve un Aprobador Elaborador filtrado por su Id
     * @param elaborador 
     * @return 
     */
    public ElaboradorEntity ElaboradorPorID(int elaborador) {
        ElaboradorEntity depto = new ElaboradorEntity();
        String validaConexion = initOperation();
        try {
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                depto.setNumeroRespuesta(3);
                depto.setTrazaRespuesta("Error de Conexión: " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM ElaboradorEntity p WHERE p.idElaborador=:idS");
                query.setParameter("idS", elaborador);
                depto=(ElaboradorEntity) query.uniqueResult();
                depto.setTrazaRespuesta("Consulta de elaborador exitosa");
                depto.setNumeroRespuesta(35);
            }
        } catch (Exception e) {
            e.printStackTrace();
            depto = new ElaboradorEntity();
            depto.setNumeroRespuesta(0);
            depto.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return depto;
    }
}