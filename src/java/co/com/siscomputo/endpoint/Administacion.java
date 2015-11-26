/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.endpoint;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.DisposicionesEntity;
import co.com.siscomputo.administracion.persistencia.ElaboradorEntity;
import co.com.siscomputo.administracion.persistencia.MetodoProteccionEntity;
import co.com.siscomputo.administracion.persistencia.MetodoRecuperacionEntity;
import co.com.siscomputo.administracion.persistencia.PlantillaEntity;
import co.com.siscomputo.administracion.persistencia.TipoAlmacenamientoEntity;
import co.com.siscomputo.administracion.persistencia.TipoControlDistribucionEntity;
import co.com.siscomputo.administracion.persistencia.TiposAccesoEntity;
import co.com.siscomputo.usuario.logic.DisposicionesLogic;
import co.com.siscomputo.usuario.logic.ElaboradorLogic;
import co.com.siscomputo.usuario.logic.MetodoProteccionLogic;
import co.com.siscomputo.usuario.logic.MetodoRecuperacionLogic;
import co.com.siscomputo.usuario.logic.PlantillaLogic;
import co.com.siscomputo.usuario.logic.TipoAlmacenamientoLogic;
import co.com.siscomputo.usuario.logic.TipoControlDistribucionLogic;
import co.com.siscomputo.usuario.logic.TiposAccesoLogic;
import co.com.siscomputo.utilidades.Valida;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author LENOVO
 */
@WebService(serviceName = "Administacion")
public class Administacion {

    /**
     * Método que permite insertar una disposición nueva
     *
     * @param disposicion
     * @return
     */
    @WebMethod(operationName = "insertarDisposicion")
    public DisposicionesEntity insertarDisposición(@WebParam(name = "disopsicion") DisposicionesEntity disposicion) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(disposicion.getNombreDisposiciones(), "Sede"))) {
            DisposicionesEntity ret = new DisposicionesEntity();
            ret.setTrazaRespuesta(valida.valida(disposicion.getNombreDisposiciones(), "Sede"));
            return ret;
        } else {
            DisposicionesLogic disposicionesLogic = new DisposicionesLogic();
            return disposicionesLogic.ingresarDisposición(disposicion);
        }
    }

    /**
     * Método que actualiza una disposición
     *
     * @param disposicion
     * @return
     */
    @WebMethod(operationName = "actualizarDisposicion")
    public DisposicionesEntity actualizarDisposicion(@WebParam(name = "disposicion") DisposicionesEntity disposicion) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(disposicion.getNombreDisposiciones(), "Sede"))) {
            DisposicionesEntity ret = new DisposicionesEntity();
            ret.setTrazaRespuesta(valida.valida(disposicion.getNombreDisposiciones(), "Sede"));
            return ret;
        } else {
            DisposicionesLogic disposicionesLogic = new DisposicionesLogic();
            return disposicionesLogic.actualizarDisposicion(disposicion);
        }
    }
    /**
     * Método que trae la lista de disposiciones disponibles en el sistema
     * @return 
     */
    @WebMethod(operationName = "listaDisposiciones")
    public ObjetoRetornaEntity listaDisposiciones() {
        DisposicionesLogic disposicionesLogic = new DisposicionesLogic();
        return disposicionesLogic.listaDisposicion();
    }
    /**
     * Método que permite insertar un Método de Recuperación nuevo
     * @param metodo
     * @return 
     */
    @WebMethod(operationName = "insertarMetodoRecuperacion")
    public MetodoRecuperacionEntity insertarMetodoRecuperacion(@WebParam(name = "metodo") MetodoRecuperacionEntity metodo){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(metodo.getNombreMetodoRecuperacion(), "Sede"))) {
            MetodoRecuperacionEntity ret = new MetodoRecuperacionEntity();
            ret.setTrazaRespuesta(valida.valida(metodo.getNombreMetodoRecuperacion(), "Sede"));
            return ret;
        } else {
        MetodoRecuperacionLogic metodoRecuperacionLogic=new MetodoRecuperacionLogic();
        return metodoRecuperacionLogic.insertarMetodoRecuperacion(metodo);
        }
    }
    /**
     * Método que permite actualizar un Método de recuperación
     * @param metodo
     * @return 
     */
    @WebMethod(operationName = "actualizarMetodoRecuperacion")
    public MetodoRecuperacionEntity actualizarMetodoRecuperacion(@WebParam(name = "metodo")MetodoRecuperacionEntity metodo){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(metodo.getNombreMetodoRecuperacion(), "Sede"))) {
            MetodoRecuperacionEntity ret = new MetodoRecuperacionEntity();
            ret.setTrazaRespuesta(valida.valida(metodo.getNombreMetodoRecuperacion(), "Sede"));
            return ret;
        } else {
            MetodoRecuperacionLogic metodoRecuperacionLogic=new MetodoRecuperacionLogic();
            return metodoRecuperacionLogic.actualizarMetodoRecuperacion(metodo);
        }
    }
    /**
     * Método que trae una lista de Métodos de Recuperación
     * @return 
     */
    @WebMethod(operationName = "listaMetodoRecuperacion")
    public ObjetoRetornaEntity listaMetodoRecuperacion(){
        MetodoRecuperacionLogic metodoRecuperacionLogic=new MetodoRecuperacionLogic();
        return metodoRecuperacionLogic.listaMetodosRecuperacion();
    }
    
    /**
     * Método que permite insertar un Métodos de Protección nuevo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "insertarMetodoProteccion")
    public MetodoProteccionEntity insertarMetodoProteccion(@WebParam(name = "objeto") MetodoProteccionEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreMetodoProteccion(), "Sede"))) {
            MetodoProteccionEntity ret = new MetodoProteccionEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreMetodoProteccion(), "Sede"));
            return ret;
        } else {
        MetodoProteccionLogic metodoProteccionLogic=new MetodoProteccionLogic();
        return metodoProteccionLogic.insertarMetodoProteccion(objeto);
        }
}
     /**
     * Método que permite actualizar un Métodos de Protección
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "actualizarMetodoProteccion")
    public MetodoProteccionEntity actualizarMetodoProteccion(@WebParam(name = "objeto")MetodoProteccionEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreMetodoProteccion(), "Sede"))) {
            MetodoProteccionEntity ret = new MetodoProteccionEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreMetodoProteccion(), "Sede"));
            return ret;
        } else {
            MetodoProteccionLogic metodoRecuperacionLogic=new MetodoProteccionLogic();
            return metodoRecuperacionLogic.actualizarMetodoProteccion(objeto);
        }
    }
    /**
     * Método que trae una lista de Métodos de Protección
     * @return 
     */
    @WebMethod(operationName = "listaMetodoProteccion")
    public ObjetoRetornaEntity listaMetodoProteccion(){
        MetodoProteccionLogic metodoProteccionLogic=new MetodoProteccionLogic();
        return metodoProteccionLogic.listaMetodoProteccion();
    }
    /**
     * Método que permite insertar un Tipo de Acceso nuevo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "insertarTiposAcceso")
    public TiposAccesoEntity insertarTiposAcceso(@WebParam(name = "objeto") TiposAccesoEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTiposAcceso(), "Sede"))) {
            TiposAccesoEntity ret = new TiposAccesoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTiposAcceso(), "Sede"));
            return ret;
        } else {
        TiposAccesoLogic tiposAccesoLogic=new TiposAccesoLogic();
        return tiposAccesoLogic.insertarTiposAcceso(objeto);
        }
}
     /**
     * Método que permite actualizar un Tipo de Acceso
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "actualizarTiposAcceso")
    public TiposAccesoEntity actualizarTiposAcceso(@WebParam(name = "objeto")TiposAccesoEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTiposAcceso(), "Sede"))) {
            TiposAccesoEntity ret = new TiposAccesoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTiposAcceso(), "Sede"));
            return ret;
        } else {
            TiposAccesoLogic metodoRecuperacionLogic=new TiposAccesoLogic();
            return metodoRecuperacionLogic.actualizarTiposAcceso(objeto);
        }
    }
    /**
     * Método que trae una lista de Tipo de Acceso
     * @return 
     */
    @WebMethod(operationName = "listaTiposAcceso")
    public ObjetoRetornaEntity listaTiposAcceso(){
        TiposAccesoLogic tiposAccesoLogic=new TiposAccesoLogic();
        return tiposAccesoLogic.listaTiposAcceso();
    }
    
    /**
     * Método que permite insertar un Tipo de Almacenamiento nuevo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "insertarTipoAlmacenamiento")
    public TipoAlmacenamientoEntity insertarTipoAlmacenamiento(@WebParam(name = "objeto") TipoAlmacenamientoEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoAlmacenamiento(), "Sede"))) {
            TipoAlmacenamientoEntity ret = new TipoAlmacenamientoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoAlmacenamiento(), "Sede"));
            return ret;
        } else {
        TipoAlmacenamientoLogic tipoAlmacenamientoLogic=new TipoAlmacenamientoLogic();
        return tipoAlmacenamientoLogic.insertarTipoAlmacenamiento(objeto);
        }
}
     /**
     * Método que permite actualizar un Tipo de Almacenamiento
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "actualizarTipoAlmacenamiento")
    public TipoAlmacenamientoEntity actualizarTipoAlmacenamiento(@WebParam(name = "objeto")TipoAlmacenamientoEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoAlmacenamiento(), "Sede"))) {
            TipoAlmacenamientoEntity ret = new TipoAlmacenamientoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoAlmacenamiento(), "Sede"));
            return ret;
        } else {
            TipoAlmacenamientoLogic metodoRecuperacionLogic=new TipoAlmacenamientoLogic();
            return metodoRecuperacionLogic.actualizarTipoAlmacenamiento(objeto);
        }
    }
    /**
     * Método que trae una lista de Tipo de Almacenamiento
     * @return 
     */
    @WebMethod(operationName = "listaTipoAlmacenamiento")
    public ObjetoRetornaEntity listaTipoAlmacenamiento(){
        TipoAlmacenamientoLogic tipoAlmacenamientoLogic=new TipoAlmacenamientoLogic();
        return tipoAlmacenamientoLogic.listaTipoAlmacenamiento();
    }
    
    /**
     * Método que permite insertar un Tipo de Control de distribución nuevo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "insertarTipoControlDistribucion")
    public TipoControlDistribucionEntity insertarTipoControlDistribucion(@WebParam(name = "objeto") TipoControlDistribucionEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoControlDistribucion(), "Sede"))) {
            TipoControlDistribucionEntity ret = new TipoControlDistribucionEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoControlDistribucion(), "Sede"));
            return ret;
        } else {
        TipoControlDistribucionLogic tipoControlDistribucionLogic=new TipoControlDistribucionLogic();
        return tipoControlDistribucionLogic.insertarTipoControlDistribucion(objeto);
        }
}
     /**
     * Método que permite actualizar un Tipo de Control de distribución
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "actualizarTipoControlDistribucion")
    public TipoControlDistribucionEntity actualizarTipoControlDistribucion(@WebParam(name = "objeto")TipoControlDistribucionEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoControlDistribucion(), "Sede"))) {
            TipoControlDistribucionEntity ret = new TipoControlDistribucionEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoControlDistribucion(), "Sede"));
            return ret;
        } else {
            TipoControlDistribucionLogic metodoRecuperacionLogic=new TipoControlDistribucionLogic();
            return metodoRecuperacionLogic.actualizarTipoControlDistribucion(objeto);
        }
    }
    /**
     * Método que trae una lista de Tipo de Control de distribución
     * @return 
     */
    @WebMethod(operationName = "listaTipoControlDistribucion")
    public ObjetoRetornaEntity listaTipoControlDistribucion(){
        TipoControlDistribucionLogic tipoControlDistribucionLogic=new TipoControlDistribucionLogic();
        return tipoControlDistribucionLogic.listaTipoControlDistribucion();
    }
    
    /**
     * Método que permite insertar un Plantilla de Gestión Documental nuevo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "insertarPlantilla")
    public PlantillaEntity insertarPlantilla(@WebParam(name = "objeto") PlantillaEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombrePlantilla(), "Plantilla"))) {
            PlantillaEntity ret = new PlantillaEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombrePlantilla(), "Plantilla"));
            return ret;
        } else {
        PlantillaLogic plantillaLogic=new PlantillaLogic();
        return plantillaLogic.insertarPlantilla(objeto);
        }
}
     /**
     * Método que permite actualizar un Plantilla de Gestión Documental
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "actualizarPlantilla")
    public PlantillaEntity actualizarPlantilla(@WebParam(name = "objeto")PlantillaEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombrePlantilla(), "Plantilla"))) {
            PlantillaEntity ret = new PlantillaEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombrePlantilla(), "Plantilla"));
            return ret;
        } else {
            PlantillaLogic metodoRecuperacionLogic=new PlantillaLogic();
            return metodoRecuperacionLogic.actualizarPlantilla(objeto);
        }
    }
    /**
     * Método que trae una lista de Plantilla de Gestión Documental
     * @return 
     */
    @WebMethod(operationName = "listaPlantilla")
    public ObjetoRetornaEntity listaPlantilla(){
        PlantillaLogic plantillaLogic=new PlantillaLogic();
        return plantillaLogic.listaPlantilla();
    }
    
    /**
     * Método que permite insertar un Aprobador Elaborador nuevo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "insertarElaborador")
    public ElaboradorEntity insertarElaborador(@WebParam(name = "objeto") ElaboradorEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getUsuarioElaborador(), "Elaborador"))) {
            ElaboradorEntity ret = new ElaboradorEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getUsuarioElaborador(), "Elaborador"));
            return ret;
        } else {
        ElaboradorLogic elaboradorLogic=new ElaboradorLogic();
        return elaboradorLogic.insertarElaborador(objeto);
        }
}
     /**
     * Método que permite actualizar un Aprobador Elaborador
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "actualizarElaborador")
    public ElaboradorEntity actualizarElaborador(@WebParam(name = "objeto")ElaboradorEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getUsuarioElaborador(), "Elaborador"))) {
            ElaboradorEntity ret = new ElaboradorEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getUsuarioElaborador(), "Elaborador"));
            return ret;
        } else {
            ElaboradorLogic metodoRecuperacionLogic=new ElaboradorLogic();
            return metodoRecuperacionLogic.actualizarElaborador(objeto);
        }
    }
    /**
     * Método que trae una lista de Aprobador Elaborador
     * @return 
     */
    @WebMethod(operationName = "listaElaborador")
    public ObjetoRetornaEntity listaElaborador(){
        ElaboradorLogic elaboradorLogic=new ElaboradorLogic();
        return elaboradorLogic.listaElaborador();
     }
/**
     * Método que trae un Aprobador Elaborador por ID
     *
     * @param elaborador
     * @return
     */
    @WebMethod(operationName = "deptoPorId")
    public ElaboradorEntity elaboradorPorId(@WebParam(name = "idDepto") int elaborador) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(elaborador, "Depto"))) {
            ElaboradorEntity ret = new ElaboradorEntity();
            ret.setTrazaRespuesta(valida.valida(elaborador, "Depto"));
            return ret;
        } else {
            ElaboradorLogic elaboradorLogic = new ElaboradorLogic();
            return elaboradorLogic.ElaboradorPorID(elaborador);
        }
    }      
}
