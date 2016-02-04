package co.com.siscomputo.administracion.logic;

import co.com.siscomputo.administracion.persistencia.PlantillaEntity;
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
public class PlantillaLogic {
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
     * Método que inserta un Plantilla de Gestión Documental nuevo
     * @param objetoPlantilla
     * @return 
     */    public PlantillaEntity insertarPlantilla(PlantillaEntity objetoPlantilla){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {                
                objetoPlantilla.setNumeroRespuesta(3);
                objetoPlantilla.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                objetoPlantilla.setIdPlantilla(maxMetodo());
                sesion.save(objetoPlantilla);
                tx.commit();

                objetoPlantilla.setTrazaRespuesta("Inserción de MetodoRecuperación exitoso");
                objetoPlantilla.setNumeroRespuesta(18);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoPlantilla = new PlantillaEntity();
            objetoPlantilla.setNumeroRespuesta(0);
            objetoPlantilla.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoPlantilla;
    }

     /**
     * Método que trae el siguiente ID de la tabla ADM_TPLAN
     * @return 
     */    private int maxMetodo() {
        int ret = -1;
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {

            } else {
                Query query = sesion.createQuery("SELECT MAX(idPlantilla) FROM PlantillaEntity");
                ret = (int) query.uniqueResult();
                ret++;
            }
        } catch (Exception e) {
            ret = 1;
        }
        return ret;
    }
    
     /**
     * Método que actualiza un Plantilla de Gestión Documental
     * @param objetoPlantilla
     * @return 
     */ public PlantillaEntity actualizarPlantilla(PlantillaEntity objetoPlantilla){
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                objetoPlantilla.setNumeroRespuesta(3);
                objetoPlantilla.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {                
                System.out.println("JJ");
                sesion.update(objetoPlantilla);
                tx.commit();
                
                objetoPlantilla.setTrazaRespuesta("Actualización de MetodoRecuperación exitoso");
                objetoPlantilla.setNumeroRespuesta(19);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objetoPlantilla = new PlantillaEntity();
            objetoPlantilla.setNumeroRespuesta(0);
            objetoPlantilla.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return objetoPlantilla;
    }
     /**
     * Método que permite actualizar un Plantilla de Gestión Documental
     * @return 
     */public ObjetoRetornaEntity listaPlantilla(){
        ObjetoRetornaEntity retorna=new ObjetoRetornaEntity();
        try {
            String validaConexion = initOperation();
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                retorna.setNumeroRespuesta(3);
                retorna.setTrazaRespuesta("Error de Conexión " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM PlantillaEntity d WHERE d.estadoPlantilla<>'E'");
                retorna.setRetorna((ArrayList<Object>) query.list());
                retorna.setTrazaRespuesta("Consulta tabla Plantilla exitosa");
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
     * Método que trae una Plantilla por ID
     * @param idPlantilla
     * @return 
     */
    public PlantillaEntity plantillaPorID(int idPlantilla) {
        PlantillaEntity pais = new PlantillaEntity();
        String validaConexion = initOperation();
        try {
            if (!"Ok".equalsIgnoreCase(validaConexion)) {
                pais.setNumeroRespuesta(3);
                pais.setTrazaRespuesta("Error de Conexión: " + validaConexion);
            } else {
                Query query=sesion.createQuery("FROM PlantillaEntity p WHERE p.idPlantilla=:idS");
                query.setParameter("idS", idPlantilla);
                pais=(PlantillaEntity) query.uniqueResult();
                pais.setTrazaRespuesta("Consulta de pais exitosa");
                pais.setNumeroRespuesta(35);
            }
        } catch (Exception e) {
            e.printStackTrace();
            pais = new PlantillaEntity();
            pais.setNumeroRespuesta(0);
            pais.setTrazaRespuesta(e.getMessage());
        }finally{
            try {
                sesion.close();  
                
            } catch (HibernateException hibernateException) {
                hibernateException.printStackTrace();
            }
        }
        return pais;
    }
}