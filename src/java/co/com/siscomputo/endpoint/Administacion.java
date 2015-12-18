/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.endpoint;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.AccionEntity;
import co.com.siscomputo.administracion.persistencia.DisposicionesEntity;
import co.com.siscomputo.administracion.persistencia.ElaboradorEntity;
import co.com.siscomputo.administracion.persistencia.GrupoProcesoEntity;
import co.com.siscomputo.administracion.persistencia.GrupoUsuariosEntity;
import co.com.siscomputo.administracion.persistencia.MetodoProteccionEntity;
import co.com.siscomputo.administracion.persistencia.MetodoRecuperacionEntity;
import co.com.siscomputo.administracion.persistencia.ModificadorEntity;
import co.com.siscomputo.administracion.persistencia.NivelEntity;
import co.com.siscomputo.administracion.persistencia.PlantillaEntity;
import co.com.siscomputo.administracion.persistencia.ProcesoEntity;
import co.com.siscomputo.administracion.persistencia.TipoAlmacenamientoEntity;
import co.com.siscomputo.administracion.persistencia.TipoControlDistribucionEntity;
import co.com.siscomputo.administracion.persistencia.TiposAccesoEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioGrupoUsuarioEntity;
import co.com.siscomputo.usuario.logic.AccionLogic;
import co.com.siscomputo.usuario.logic.DisposicionesLogic;
import co.com.siscomputo.usuario.logic.ElaboradorLogic;
import co.com.siscomputo.usuario.logic.GrupoProcesoLogic;
import co.com.siscomputo.usuario.logic.GrupoUsuariosLogic;
import co.com.siscomputo.usuario.logic.MetodoProteccionLogic;
import co.com.siscomputo.usuario.logic.MetodoRecuperacionLogic;
import co.com.siscomputo.usuario.logic.ModificadorLogic;
import co.com.siscomputo.usuario.logic.NivelLogic;
import co.com.siscomputo.usuario.logic.PlantillaLogic;
import co.com.siscomputo.usuario.logic.ProcesoLogic;
import co.com.siscomputo.usuario.logic.TipoAlmacenamientoLogic;
import co.com.siscomputo.usuario.logic.TipoControlDistribucionLogic;
import co.com.siscomputo.usuario.logic.TiposAccesoLogic;
import co.com.siscomputo.usuario.logic.UsuarioGrupoUsuarioLogic;
import co.com.siscomputo.utilidades.Valida;
import java.util.ArrayList;
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
     *
     * @return
     */
    @WebMethod(operationName = "listaDisposiciones")
    public ObjetoRetornaEntity listaDisposiciones() {
        DisposicionesLogic disposicionesLogic = new DisposicionesLogic();
        return disposicionesLogic.listaDisposicion();
    }

    /**
     * Método que permite insertar un Método de Recuperación nuevo
     *
     * @param metodo
     * @return
     */
    @WebMethod(operationName = "insertarMetodoRecuperacion")
    public MetodoRecuperacionEntity insertarMetodoRecuperacion(@WebParam(name = "metodo") MetodoRecuperacionEntity metodo) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(metodo.getNombreMetodoRecuperacion(), "Sede"))) {
            MetodoRecuperacionEntity ret = new MetodoRecuperacionEntity();
            ret.setTrazaRespuesta(valida.valida(metodo.getNombreMetodoRecuperacion(), "Sede"));
            return ret;
        } else {
            MetodoRecuperacionLogic metodoRecuperacionLogic = new MetodoRecuperacionLogic();
            return metodoRecuperacionLogic.insertarMetodoRecuperacion(metodo);
        }
    }

    /**
     * Método que permite actualizar un Método de recuperación
     *
     * @param metodo
     * @return
     */
    @WebMethod(operationName = "actualizarMetodoRecuperacion")
    public MetodoRecuperacionEntity actualizarMetodoRecuperacion(@WebParam(name = "metodo") MetodoRecuperacionEntity metodo) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(metodo.getNombreMetodoRecuperacion(), "Sede"))) {
            MetodoRecuperacionEntity ret = new MetodoRecuperacionEntity();
            ret.setTrazaRespuesta(valida.valida(metodo.getNombreMetodoRecuperacion(), "Sede"));
            return ret;
        } else {
            MetodoRecuperacionLogic metodoRecuperacionLogic = new MetodoRecuperacionLogic();
            return metodoRecuperacionLogic.actualizarMetodoRecuperacion(metodo);
        }
    }

    /**
     * Método que trae una lista de Métodos de Recuperación
     *
     * @return
     */
    @WebMethod(operationName = "listaMetodoRecuperacion")
    public ObjetoRetornaEntity listaMetodoRecuperacion() {
        MetodoRecuperacionLogic metodoRecuperacionLogic = new MetodoRecuperacionLogic();
        return metodoRecuperacionLogic.listaMetodosRecuperacion();
    }

    /**
     * Método que permite insertar un Métodos de Protección nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarMetodoProteccion")
    public MetodoProteccionEntity insertarMetodoProteccion(@WebParam(name = "objeto") MetodoProteccionEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreMetodoProteccion(), "Sede"))) {
            MetodoProteccionEntity ret = new MetodoProteccionEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreMetodoProteccion(), "Sede"));
            return ret;
        } else {
            MetodoProteccionLogic metodoProteccionLogic = new MetodoProteccionLogic();
            return metodoProteccionLogic.insertarMetodoProteccion(objeto);
        }
    }

    /**
     * Método que permite actualizar un Métodos de Protección
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarMetodoProteccion")
    public MetodoProteccionEntity actualizarMetodoProteccion(@WebParam(name = "objeto") MetodoProteccionEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreMetodoProteccion(), "Sede"))) {
            MetodoProteccionEntity ret = new MetodoProteccionEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreMetodoProteccion(), "Sede"));
            return ret;
        } else {
            MetodoProteccionLogic metodoRecuperacionLogic = new MetodoProteccionLogic();
            return metodoRecuperacionLogic.actualizarMetodoProteccion(objeto);
        }
    }

    /**
     * Método que trae una lista de Métodos de Protección
     *
     * @return
     */
    @WebMethod(operationName = "listaMetodoProteccion")
    public ObjetoRetornaEntity listaMetodoProteccion() {
        MetodoProteccionLogic metodoProteccionLogic = new MetodoProteccionLogic();
        return metodoProteccionLogic.listaMetodoProteccion();
    }

    /**
     * Método que permite insertar un Tipo de Acceso nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarTiposAcceso")
    public TiposAccesoEntity insertarTiposAcceso(@WebParam(name = "objeto") TiposAccesoEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTiposAcceso(), "Sede"))) {
            TiposAccesoEntity ret = new TiposAccesoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTiposAcceso(), "Sede"));
            return ret;
        } else {
            TiposAccesoLogic tiposAccesoLogic = new TiposAccesoLogic();
            return tiposAccesoLogic.insertarTiposAcceso(objeto);
        }
    }

    /**
     * Método que permite actualizar un Tipo de Acceso
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarTiposAcceso")
    public TiposAccesoEntity actualizarTiposAcceso(@WebParam(name = "objeto") TiposAccesoEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTiposAcceso(), "Sede"))) {
            TiposAccesoEntity ret = new TiposAccesoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTiposAcceso(), "Sede"));
            return ret;
        } else {
            TiposAccesoLogic metodoRecuperacionLogic = new TiposAccesoLogic();
            return metodoRecuperacionLogic.actualizarTiposAcceso(objeto);
        }
    }

    /**
     * Método que trae una lista de Tipo de Acceso
     *
     * @return
     */
    @WebMethod(operationName = "listaTiposAcceso")
    public ObjetoRetornaEntity listaTiposAcceso() {
        TiposAccesoLogic tiposAccesoLogic = new TiposAccesoLogic();
        return tiposAccesoLogic.listaTiposAcceso();
    }

    /**
     * Método que permite insertar un Tipo de Almacenamiento nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarTipoAlmacenamiento")
    public TipoAlmacenamientoEntity insertarTipoAlmacenamiento(@WebParam(name = "objeto") TipoAlmacenamientoEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoAlmacenamiento(), "Sede"))) {
            TipoAlmacenamientoEntity ret = new TipoAlmacenamientoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoAlmacenamiento(), "Sede"));
            return ret;
        } else {
            TipoAlmacenamientoLogic tipoAlmacenamientoLogic = new TipoAlmacenamientoLogic();
            return tipoAlmacenamientoLogic.insertarTipoAlmacenamiento(objeto);
        }
    }

    /**
     * Método que permite actualizar un Tipo de Almacenamiento
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarTipoAlmacenamiento")
    public TipoAlmacenamientoEntity actualizarTipoAlmacenamiento(@WebParam(name = "objeto") TipoAlmacenamientoEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoAlmacenamiento(), "Sede"))) {
            TipoAlmacenamientoEntity ret = new TipoAlmacenamientoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoAlmacenamiento(), "Sede"));
            return ret;
        } else {
            TipoAlmacenamientoLogic metodoRecuperacionLogic = new TipoAlmacenamientoLogic();
            return metodoRecuperacionLogic.actualizarTipoAlmacenamiento(objeto);
        }
    }

    /**
     * Método que trae una lista de Tipo de Almacenamiento
     *
     * @return
     */
    @WebMethod(operationName = "listaTipoAlmacenamiento")
    public ObjetoRetornaEntity listaTipoAlmacenamiento() {
        TipoAlmacenamientoLogic tipoAlmacenamientoLogic = new TipoAlmacenamientoLogic();
        return tipoAlmacenamientoLogic.listaTipoAlmacenamiento();
    }

    /**
     * Método que permite insertar un Tipo de Control de distribución nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarTipoControlDistribucion")
    public TipoControlDistribucionEntity insertarTipoControlDistribucion(@WebParam(name = "objeto") TipoControlDistribucionEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoControlDistribucion(), "Sede"))) {
            TipoControlDistribucionEntity ret = new TipoControlDistribucionEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoControlDistribucion(), "Sede"));
            return ret;
        } else {
            TipoControlDistribucionLogic tipoControlDistribucionLogic = new TipoControlDistribucionLogic();
            return tipoControlDistribucionLogic.insertarTipoControlDistribucion(objeto);
        }
    }

    /**
     * Método que permite actualizar un Tipo de Control de distribución
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarTipoControlDistribucion")
    public TipoControlDistribucionEntity actualizarTipoControlDistribucion(@WebParam(name = "objeto") TipoControlDistribucionEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoControlDistribucion(), "Sede"))) {
            TipoControlDistribucionEntity ret = new TipoControlDistribucionEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoControlDistribucion(), "Sede"));
            return ret;
        } else {
            TipoControlDistribucionLogic metodoRecuperacionLogic = new TipoControlDistribucionLogic();
            return metodoRecuperacionLogic.actualizarTipoControlDistribucion(objeto);
        }
    }

    /**
     * Método que trae una lista de Tipo de Control de distribución
     *
     * @return
     */
    @WebMethod(operationName = "listaTipoControlDistribucion")
    public ObjetoRetornaEntity listaTipoControlDistribucion() {
        TipoControlDistribucionLogic tipoControlDistribucionLogic = new TipoControlDistribucionLogic();
        return tipoControlDistribucionLogic.listaTipoControlDistribucion();
    }

    /**
     * Método que permite insertar un Plantilla de Gestión Documental nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarPlantilla")
    public PlantillaEntity insertarPlantilla(@WebParam(name = "objeto") PlantillaEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombrePlantilla(), "Plantilla"))) {
            PlantillaEntity ret = new PlantillaEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombrePlantilla(), "Plantilla"));
            return ret;
        } else {
            PlantillaLogic plantillaLogic = new PlantillaLogic();
            return plantillaLogic.insertarPlantilla(objeto);
        }
    }

    /**
     * Método que permite actualizar un Plantilla de Gestión Documental
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarPlantilla")
    public PlantillaEntity actualizarPlantilla(@WebParam(name = "objeto") PlantillaEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombrePlantilla(), "Plantilla"))) {
            PlantillaEntity ret = new PlantillaEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombrePlantilla(), "Plantilla"));
            return ret;
        } else {
            PlantillaLogic metodoRecuperacionLogic = new PlantillaLogic();
            return metodoRecuperacionLogic.actualizarPlantilla(objeto);
        }
    }

    /**
     * Método que trae una lista de Plantilla de Gestión Documental
     *
     * @return
     */
    @WebMethod(operationName = "listaPlantilla")
    public ObjetoRetornaEntity listaPlantilla() {
        PlantillaLogic plantillaLogic = new PlantillaLogic();
        return plantillaLogic.listaPlantilla();
    }

    /**
     * Método que permite actualizar una Plantilla de Gestión Documental
     *
     * @param idPlantilla
     * @return
     */
    @WebMethod(operationName = "plantillaPorIdlantilla")
    public PlantillaEntity plantillaPorID(@WebParam(name = "idPlantilla") int idPlantilla) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idPlantilla, "Plantilla"))) {
            PlantillaEntity ret = new PlantillaEntity();
            ret.setTrazaRespuesta(valida.valida(idPlantilla, "Plantilla"));
            return ret;
        } else {
            PlantillaLogic plantillaLogic = new PlantillaLogic();
            return plantillaLogic.plantillaPorID(idPlantilla);
        }
    }

    /**
     * Método que permite insertar un Aprobador Elaborador nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarElaborador")
    public ElaboradorEntity insertarElaborador(@WebParam(name = "objeto") ElaboradorEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getUsuarioElaborador(), "Elaborador"))) {
            ElaboradorEntity ret = new ElaboradorEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getUsuarioElaborador(), "Elaborador"));
            return ret;
        } else {
            ElaboradorLogic elaboradorLogic = new ElaboradorLogic();
            return elaboradorLogic.insertarElaborador(objeto);
        }
    }

    /**
     * Método que permite actualizar un Aprobador Elaborador
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarElaborador")
    public ElaboradorEntity actualizarElaborador(@WebParam(name = "objeto") ElaboradorEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getUsuarioElaborador(), "Elaborador"))) {
            ElaboradorEntity ret = new ElaboradorEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getUsuarioElaborador(), "Elaborador"));
            return ret;
        } else {
            ElaboradorLogic metodoRecuperacionLogic = new ElaboradorLogic();
            return metodoRecuperacionLogic.actualizarElaborador(objeto);
        }
    }

    /**
     * Método que trae una lista de Aprobador Elaborador
     *
     * @return
     */
    @WebMethod(operationName = "listaElaborador")
    public ObjetoRetornaEntity listaElaborador() {
        ElaboradorLogic elaboradorLogic = new ElaboradorLogic();
        return elaboradorLogic.listaElaborador();
    }

    /**
     * Método que trae un Aprobador Elaborador por ID
     *
     *
     * @param elaborador
     * @return
     */
    @WebMethod(operationName = "elaboradorPorId")
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

    /**
     * Método que permite insertar un Aprobador Modificador nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarModificador")
    public ModificadorEntity insertarModificador(@WebParam(name = "objeto") ModificadorEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getUsuarioModificador(), "Sede"))) {
            ModificadorEntity ret = new ModificadorEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getUsuarioModificador(), "Sede"));
            return ret;
        } else {
            ModificadorLogic modificadorLogic = new ModificadorLogic();
            return modificadorLogic.insertarModificador(objeto);
        }
    }

    /**
     * Método que permite actualizar un Aprobador Modificador
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarModificador")
    public ModificadorEntity actualizarModificador(@WebParam(name = "objeto") ModificadorEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getUsuarioModificador(), "Sede"))) {
            ModificadorEntity ret = new ModificadorEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getUsuarioModificador(), "Sede"));
            return ret;
        } else {
            ModificadorLogic metodoRecuperacionLogic = new ModificadorLogic();
            return metodoRecuperacionLogic.actualizarModificador(objeto);
        }
    }

    /**
     * Método que trae una lista de Aprobador Modificador
     *
     * @return
     */
    @WebMethod(operationName = "listaModificador")
    public ObjetoRetornaEntity listaModificador() {
        ModificadorLogic modificadorLogic = new ModificadorLogic();
        return modificadorLogic.listaModificador();
    }

    /**
     * Método que trae un Aprobador Modificador por ID
     *
     * @param modificador
     * @return
     */
    @WebMethod(operationName = "ModificadorPorId")
    public ModificadorEntity modificadorPorId(@WebParam(name = "onjeto") int modificador) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(modificador, "Modificador"))) {
            ModificadorEntity ret = new ModificadorEntity();
            ret.setTrazaRespuesta(valida.valida(modificador, "Modificador"));
            return ret;
        } else {
            ModificadorLogic modificadorLogic = new ModificadorLogic();
            return modificadorLogic.ModificadorPorID(modificador);
        }
    }

    /**
     * Método que permite insertar un Acción nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarAccion")
    public AccionEntity insertarAccion(@WebParam(name = "objeto") AccionEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreAccion(), "Sede"))) {
            AccionEntity ret = new AccionEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreAccion(), "Sede"));
            return ret;
        } else {
            AccionLogic accionLogic = new AccionLogic();
            return accionLogic.insertarAccion(objeto);
        }
    }

    /**
     * Método que permite actualizar un Acción
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarAccion")
    public AccionEntity actualizarAccion(@WebParam(name = "objeto") AccionEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreAccion(), "Sede"))) {
            AccionEntity ret = new AccionEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreAccion(), "Sede"));
            return ret;
        } else {
            AccionLogic metodoRecuperacionLogic = new AccionLogic();
            return metodoRecuperacionLogic.actualizarAccion(objeto);
        }
    }

    /**
     * Método que trae una lista de Acción
     *
     * @return
     */
    @WebMethod(operationName = "listaAccion")
    public ObjetoRetornaEntity listaAccion() {
        AccionLogic accionLogic = new AccionLogic();
        return accionLogic.listaAccion();
    }

    /**
     * Método que permite insertar un Grupo de Usuarios nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarGrupoUsuarios")
    public GrupoUsuariosEntity insertarGrupoUsuarios(@WebParam(name = "objeto") GrupoUsuariosEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreGrupoUsuarios(), "Sede"))) {
            GrupoUsuariosEntity ret = new GrupoUsuariosEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreGrupoUsuarios(), "Sede"));
            return ret;
        } else {
            GrupoUsuariosLogic grupoUsuariosLogic = new GrupoUsuariosLogic();
            return grupoUsuariosLogic.insertarGrupoUsuarios(objeto);
        }
    }

    /**
     * Método que permite actualizar un Grupo de Usuarios
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarGrupoUsuarios")
    public GrupoUsuariosEntity actualizarGrupoUsuarios(@WebParam(name = "objeto") GrupoUsuariosEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreGrupoUsuarios(), "Sede"))) {
            GrupoUsuariosEntity ret = new GrupoUsuariosEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreGrupoUsuarios(), "Sede"));
            return ret;
        } else {
            GrupoUsuariosLogic metodoRecuperacionLogic = new GrupoUsuariosLogic();
            return metodoRecuperacionLogic.actualizarGrupoUsuarios(objeto);
        }
    }

    /**
     * Método que trae un grupo de usuarios por ID
     *
     *
     * @return
     */
    @WebMethod(operationName = "grupoUsuariosPorId")
    public GrupoUsuariosEntity grupoUsuariosPorId(@WebParam(name = "objeto") int idGrupo) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idGrupo, "idGrupo"))) {
            GrupoUsuariosEntity ret = new GrupoUsuariosEntity();
            ret.setTrazaRespuesta(valida.valida(idGrupo, "idGrupo"));
            return ret;
        } else {
            GrupoUsuariosLogic metodoRecuperacionLogic = new GrupoUsuariosLogic();
            return metodoRecuperacionLogic.GrupoPorId(idGrupo);
        }
    }

    /**
     * Método que trae una lista de Grupo de Usuarios
     *
     * @return
     */
    @WebMethod(operationName = "listaGrupoUsuarios")
    public ObjetoRetornaEntity listaGrupoUsuarios() {
        GrupoUsuariosLogic grupoUsuariosLogic = new GrupoUsuariosLogic();
        return grupoUsuariosLogic.listaGrupoUsuarios();

    }

    /**
     * Método que permite insertar un relacion entre Usuarios y Grupos de
     * Usuarios nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarUsuarioGrupoUsuario")
    public ObjetoRetornaEntity insertarUsuarioGrupoUsuario(@WebParam(name = "objeto") ArrayList<UsuarioGrupoUsuarioEntity> objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto, "Sede"))) {
            UsuarioGrupoUsuarioEntity ret = new UsuarioGrupoUsuarioEntity();
            ret.setTrazaRespuesta(valida.valida(objeto, "Sede"));
            return ret;
        } else {
            UsuarioGrupoUsuarioLogic usuarioGrupoUsuarioLogic = new UsuarioGrupoUsuarioLogic();
            return usuarioGrupoUsuarioLogic.insertarUsuarioGrupoUsuario(objeto);
        }
    }

    /**
     * Método que permite actualizar un relacion entre Usuarios y Grupos de
     * Usuarios
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarUsuarioGrupoUsuario")
    public UsuarioGrupoUsuarioEntity actualizarUsuarioGrupoUsuario(@WebParam(name = "objeto") UsuarioGrupoUsuarioEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getIdUsuarioGrupoUsuario(), "Sede"))) {
            UsuarioGrupoUsuarioEntity ret = new UsuarioGrupoUsuarioEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getIdUsuarioGrupoUsuario(), "Sede"));
            return ret;
        } else {
            UsuarioGrupoUsuarioLogic metodoRecuperacionLogic = new UsuarioGrupoUsuarioLogic();
            return metodoRecuperacionLogic.actualizarUsuarioGrupoUsuario(objeto);
        }
    }

    /**
     * Método que limpia parte de la relacion entre usuario-GrupoUsuario
     *
     * @param idGrupo
     */
    @WebMethod(operationName = "limpiaUsuarioGrupoUsuario")
    public void limpiaUsuarioGrupoUsuario(@WebParam(name = "idGrupo") int idGrupo) {
        UsuarioGrupoUsuarioLogic usuarioGrupoUsuarioLogic = new UsuarioGrupoUsuarioLogic();
        usuarioGrupoUsuarioLogic.limpia(idGrupo);
    }

    /**
     * Método que trae una lista de relacion entre Usuarios y Grupos de Usuarios
     *
     * @return
     */
    @WebMethod(operationName = "listaUsuarioGrupoUsuario")
    public ObjetoRetornaEntity listaUsuarioGrupoUsuario() {
        UsuarioGrupoUsuarioLogic usuarioGrupoUsuarioLogic = new UsuarioGrupoUsuarioLogic();
        return usuarioGrupoUsuarioLogic.listaUsuarioGrupoUsuario();
    }

    /**
     * Método que trae una lista de relacion entre Usuarios y Grupos de Usuarios
     * Filtrado por id de grupo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "listaUsuarioGrupoUsuarioPorGrupo")
    public ObjetoRetornaEntity listaUsuarioGrupoUsuarioPorGrupo(@WebParam(name = "idGrupo") int idGrupo) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idGrupo, "Sede"))) {
            UsuarioGrupoUsuarioEntity ret = new UsuarioGrupoUsuarioEntity();
            ret.setTrazaRespuesta(valida.valida(idGrupo, "Sede"));
            return ret;
        } else {
            UsuarioGrupoUsuarioLogic metodoRecuperacionLogic = new UsuarioGrupoUsuarioLogic();
            return metodoRecuperacionLogic.listaUsuarioGrupoUsuarioPorGrupo(idGrupo);
        }
    }

    /**
     * Método que permite insertar un Procesos nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarProceso")
    public ProcesoEntity insertarProceso(@WebParam(name = "objeto") ProcesoEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreProceso(), "Sede"))) {
            ProcesoEntity ret = new ProcesoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreProceso(), "Sede"));
            return ret;
        } else {
            ProcesoLogic procesoLogic = new ProcesoLogic();
            return procesoLogic.insertarProceso(objeto);
        }
    }

    /**
     * Método que permite actualizar un Procesos
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarProceso")
    public ProcesoEntity actualizarProceso(@WebParam(name = "objeto") ProcesoEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreProceso(), "Sede"))) {
            ProcesoEntity ret = new ProcesoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreProceso(), "Sede"));
            return ret;
        } else {
            ProcesoLogic metodoRecuperacionLogic = new ProcesoLogic();
            return metodoRecuperacionLogic.actualizarProceso(objeto);
        }
    }

    /**
     * Método que trae una lista de Procesos
     *
     * @return
     */
    @WebMethod(operationName = "listaProceso")
    public ObjetoRetornaEntity listaProceso() {
        ProcesoLogic procesoLogic = new ProcesoLogic();
        return procesoLogic.listaProceso();

    }

    /**
     * Método que trae un proceso por su ID
     *
     * @param idProceso
     * @return
     */
    @WebMethod(operationName = "procesoPorId2")
    public ProcesoEntity procesoPorId2(@WebParam(name = "idProceso") int idProceso) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idProceso, "idGrupo"))) {
            ProcesoEntity ret = new ProcesoEntity();
            ret.setTrazaRespuesta(valida.valida(idProceso, "idGrupo"));
            return ret;
        } else {
            ProcesoLogic procesoLogic = new ProcesoLogic();
            return procesoLogic.procesoPorId(idProceso);
        }
    }

    /**
     * Método que permite insertar un Grupo de Usuarios y Procesos nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarGrupoProceso")
    public ObjetoRetornaEntity insertarGrupoProceso(@WebParam(name = "objeto") ArrayList<GrupoProcesoEntity> lista) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(lista, "GrupoProceso"))) {
            GrupoProcesoEntity ret = new GrupoProcesoEntity();
            ret.setTrazaRespuesta(valida.valida(lista, "GrupoProceso"));
            return ret;
        } else {
            GrupoProcesoLogic grupoProcesoLogic = new GrupoProcesoLogic();
            return grupoProcesoLogic.insertarGrupoProceso(lista);
        }
    }

    /**
     * Método que permite actualizar un Grupo de Usuarios y Procesos
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarGrupoProceso")
    public GrupoProcesoEntity actualizarGrupoProceso(@WebParam(name = "objeto") GrupoProcesoEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getGrupoUsuarioProceso(), "Sede"))) {
            GrupoProcesoEntity ret = new GrupoProcesoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getGrupoUsuarioProceso(), "Sede"));
            return ret;
        } else {
            GrupoProcesoLogic metodoRecuperacionLogic = new GrupoProcesoLogic();
            return metodoRecuperacionLogic.actualizarGrupoProceso(objeto);
        }
    }

    /**
     * Método que trae una lista de Grupo de Usuarios y Procesos
     *
     * @param idGrupo
     * @return
     */
    @WebMethod(operationName = "listaGrupoProceso")
    public ObjetoRetornaEntity listaGrupoProceso(@WebParam(name = "idGrupo") int idGrupo) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idGrupo, "Sede"))) {
            GrupoProcesoEntity ret = new GrupoProcesoEntity();
            ret.setTrazaRespuesta(valida.valida(idGrupo, "Sede"));
            return ret;
        } else {
            GrupoProcesoLogic grupoProcesoLogic = new GrupoProcesoLogic();
            return grupoProcesoLogic.listaGrupoProceso(idGrupo);
        }

    }

    /**
     * Método que trae una lista de Grupo de Usuarios y Procesos
     *
     * @param idGrupo
     * @param idProceso
     * @return
     */
    @WebMethod(operationName = "listaGrupoProcesoAccion")
    public ObjetoRetornaEntity listaGrupoProcesoAccion(@WebParam(name = "idGrupo") int idGrupo, @WebParam(name = "idProceso") int idProceso) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idGrupo, "Sede"))) {
            GrupoProcesoEntity ret = new GrupoProcesoEntity();
            ret.setTrazaRespuesta(valida.valida(idGrupo, "Sede"));
            return ret;
        } else {
            GrupoProcesoLogic grupoProcesoLogic = new GrupoProcesoLogic();
            return grupoProcesoLogic.listaGrupoProcesoAccion(idGrupo, idProceso);
        }
    }
    /**
     * Método que trae una lista de Grupo de Usuarios y Procesos
     * @param idAccion
     * @return
     */
    @WebMethod(operationName = "listaGrupoProcesoPorAccion")
    public ObjetoRetornaEntity listaGrupoProcesoPorAccion(@WebParam(name = "idAccion") int idAccion) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idAccion, "Sede"))) {
            GrupoProcesoEntity ret = new GrupoProcesoEntity();
            ret.setTrazaRespuesta(valida.valida(idAccion, "Sede"));
            return ret;
        } else {
            GrupoProcesoLogic grupoProcesoLogic = new GrupoProcesoLogic();
            return grupoProcesoLogic.listaGrupoProcesoPorAccion(idAccion);
        }
    }

    /**
     * Método que limpia parte de la relacion entre GrupoUsuario-Proceso
     *
     * @param idGrupo
     */
    @WebMethod(operationName = "limpiaUsuarioGrupoProceso")
    public void limpiaGrupoProceso(@WebParam(name = "idGrupo") int idGrupo, @WebParam(name = "idProceso") int idProceso) {
        GrupoProcesoLogic grupoProcesoLogic = new GrupoProcesoLogic();
        grupoProcesoLogic.limpia(idGrupo, idProceso);
    }

    /**
     * Método que permite insertar un Nivel de Proceso nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarNivel")
    public NivelEntity insertarNivel(@WebParam(name = "objeto") NivelEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreNivel(), "Sede"))) {
            NivelEntity ret = new NivelEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreNivel(), "Sede"));
            return ret;
        } else {
            NivelLogic nivelLogic = new NivelLogic();
            return nivelLogic.insertarNivel(objeto);
        }
    }

    /**
     * Método que permite actualizar un Nivel de Proceso
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarNivel")
    public NivelEntity actualizarNivel(@WebParam(name = "objeto") NivelEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreNivel(), "Sede"))) {
            NivelEntity ret = new NivelEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreNivel(), "Sede"));
            return ret;
        } else {
            NivelLogic metodoRecuperacionLogic = new NivelLogic();
            return metodoRecuperacionLogic.actualizarNivel(objeto);
        }
    }

    /**
     * Método que trae una lista de Nivel de Proceso
     *
     * @return
     */
    @WebMethod(operationName = "listaNivel")
    public ObjetoRetornaEntity listaNivel() {
        NivelLogic nivelLogic = new NivelLogic();
        return nivelLogic.listaNivel();

    }
}
