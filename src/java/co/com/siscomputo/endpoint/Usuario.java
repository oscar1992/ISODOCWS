/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.endpoint;

import co.com.siscomputo.administracion.entites.ListaAsignaPermisosModulo;
import co.com.siscomputo.administracion.entites.MenuModuloEntity;
import co.com.siscomputo.administracion.entites.ObjetoLogin;
import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.AreaEntity;
import co.com.siscomputo.administracion.persistencia.CiudadEntity;
import co.com.siscomputo.administracion.persistencia.DepartamentoEntity;
import co.com.siscomputo.administracion.persistencia.EmpresaEntity;
import co.com.siscomputo.administracion.persistencia.FestivosEntity;
import co.com.siscomputo.administracion.persistencia.MacroprocesosEntity;
import co.com.siscomputo.administracion.persistencia.PaisEntity;
import co.com.siscomputo.administracion.persistencia.ProcesosEntity;
import co.com.siscomputo.administracion.persistencia.RolesEntity;
import co.com.siscomputo.administracion.persistencia.SedeEmpresaEntity;
import co.com.siscomputo.administracion.persistencia.SedeEntity;
import co.com.siscomputo.administracion.persistencia.SubprocesoEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioMacroprocesoEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioProcesoEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioSubprocesoEntity;
import co.com.siscomputo.usuario.logic.AreaLogic;
import co.com.siscomputo.usuario.logic.CiudadLogic;
import co.com.siscomputo.usuario.logic.DepartamentoLogic;
import co.com.siscomputo.usuario.logic.EmpresaLogic;
import co.com.siscomputo.usuario.logic.FestivosLogic;
import co.com.siscomputo.usuario.logic.MacroProcesoLogic;
import co.com.siscomputo.usuario.logic.MenuLogic;
import co.com.siscomputo.usuario.logic.PaisLogic;
import co.com.siscomputo.usuario.logic.PermisosLogic;
import co.com.siscomputo.usuario.logic.ProcesosLogic;
import co.com.siscomputo.usuario.logic.RolesLogic;
import co.com.siscomputo.usuario.logic.SedeEmpresaLogic;
import co.com.siscomputo.usuario.logic.SedeLogic;
import co.com.siscomputo.usuario.logic.SubProcesosLogic;
import co.com.siscomputo.usuario.logic.UsuarioLogic;
import co.com.siscomputo.usuario.logic.UsuarioMacroprocesoLogic;
import co.com.siscomputo.usuario.logic.UsuarioProcesoLogic;
import co.com.siscomputo.usuario.logic.UsuarioSubprocesoLogic;
import co.com.siscomputo.utilidades.Valida;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author LENOVO
 */
@WebService(serviceName = "Usuario")
public class Usuario {

    /**
     * Web service operation método de autenticación de usuario
     *
     * @param nombre
     * @param pass
     * @return
     */
    @WebMethod(operationName = "login")
    public ObjetoLogin login(@WebParam(name = "nombre") @XmlElement(required = true) String nombre, @WebParam(name = "pass") String pass) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(nombre, "nombre"))) {
            ObjetoLogin ret = new ObjetoLogin();
            ret.setAcceso(false);
            ret.setTrazaRespuesta(valida.valida(nombre, "nombre"));
            return ret;
        }
        if (!"Ok".equalsIgnoreCase(valida.valida(pass, "contraseña"))) {
            ObjetoLogin ret = new ObjetoLogin();
            ret.setAcceso(false);
            ret.setTrazaRespuesta(valida.valida(pass, "contraseña"));
            return ret;
        } else {
            System.out.println("Inten");
            UsuarioLogic usuarioLogica = new UsuarioLogic();
            return usuarioLogica.login(nombre, pass);
        }

    }

    /**
     * Web service operation Método de la lista de módulos filtrados por Usuario
     *
     * @param id_usuario
     * @return
     */
    @WebMethod(operationName = "listaModulos")
    public ObjetoRetornaEntity listaModulos(@WebParam(name = "id_usuario") int idUsuario) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idUsuario, "idUsuario"))) {
            ObjetoRetornaEntity ret = new ObjetoRetornaEntity();
            ret.setTrazaRespuesta(valida.valida(idUsuario, "idUsuario"));
            return ret;
        } else {
            UsuarioLogic usuarioLogica = new UsuarioLogic();
            return usuarioLogica.modulos(idUsuario);
        }

    }

    /**
     * Web service operation Método de la lista de permisos filtrados por
     * Usuario
     *
     * @param id_usuario
     * @return
     */
    @WebMethod(operationName = "listaPermisos")
    public ObjetoRetornaEntity listaPermisos(@WebParam(name = "id_usuario") int idUsuario) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idUsuario, "idUsuario"))) {
            ObjetoRetornaEntity ret = new ObjetoRetornaEntity();
            ret.setTrazaRespuesta(valida.valida(idUsuario, "idUsuario"));
            return ret;
        } else {
            UsuarioLogic usuarioLogica = new UsuarioLogic();
            return usuarioLogica.permisos(idUsuario);
        }
    }

    /**
     * Web service operation Método de carga de lista de usuarios
     *
     * @return
     */
    @WebMethod(operationName = "listaUsuarios")
    public ObjetoRetornaEntity listaUsuarios() {
        UsuarioLogic usuarioLogic = new UsuarioLogic();
        try {
            return usuarioLogic.listaUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Web service operation Método que permite actualizar un usario
     *
     * @param usu
     * @return
     */
    @WebMethod(operationName = "actualizarUsuario")
    public UsuarioEntity actualizarUsuario(@WebParam(name = "Usuario") UsuarioEntity usu) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(usu.getIdUsuario(), "idUsuario"))) {
            UsuarioEntity ret = new UsuarioEntity();
            ret.setTrazaRespuesta(valida.valida(usu.getIdUsuario(), "idUsuario"));
            return ret;
        } else {
            UsuarioLogic usuarioLogic = new UsuarioLogic();
            return usuarioLogic.actualizarUsuario(usu);
        }
    }

    /**
     * Método que permite ingresar un usuario nuevo
     *
     * @param usu
     * @return
     */
    @WebMethod(operationName = "ingresarUsuario")
    public UsuarioEntity ingresarUsuario(@WebParam(name = "Usuario") UsuarioEntity usu) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(usu.getNombre(), "idUsuario"))) {
            UsuarioEntity ret = new UsuarioEntity();
            ret.setTrazaRespuesta(valida.valida(usu.getNombre(), "idUsuario"));
            return ret;
        } else {
            UsuarioLogic usuarioLogic = new UsuarioLogic();
            return usuarioLogic.ingresarUsuario(usu);
        }
    }

    /**
     * Web service operation Método que carga una lista de permisos filtrados
     * por Usuario
     *
     * @param idUsuario
     * @return
     */
    @WebMethod(operationName = "listaPermisosFiltrados")
    public ObjetoRetornaEntity listaPermisosFiltrados(@WebParam(name = "idUsuario") int idUsuario) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idUsuario, "idUsuario"))) {
            ObjetoRetornaEntity ret = new ObjetoRetornaEntity();
            ret.setTrazaRespuesta(valida.valida(idUsuario, "idUsuario"));
            return ret;
        } else {
            UsuarioLogic usuarioLogic = new UsuarioLogic();
            return usuarioLogic.permisosFiltrados(idUsuario);
        }
    }

    /**
     * Método que trae las lista para crear los menus
     *
     * @param idUsuario
     * @return
     */
    @WebMethod(operationName = "menuDatosporUsuario")
    public ArrayList<MenuModuloEntity> menuDatosporUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idUsuario, "idUsuario"))) {
            return null;
        } else {
            MenuLogic menuLogic = new MenuLogic();
            return menuLogic.datosMenu(idUsuario);
        }
    }

    /**
     * Método que trae un único usuario filtrado por su ID
     *
     * @param idUsuario
     * @return
     */
    @WebMethod(operationName = "usuarioPorId")
    public UsuarioEntity usuarioPorId(@WebParam(name = "idUsuario") int idUsuario) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idUsuario, "idUsuario"))) {
            UsuarioEntity ret = new UsuarioEntity();
            ret.setTrazaRespuesta(valida.valida(idUsuario, "idUsuario"));
            return ret;
        } else {
            UsuarioLogic usuarioLogic = new UsuarioLogic();
            return usuarioLogic.usuarioPorId(idUsuario);
        }
    }

    /**
     * Método que trae la lista de roles disponibles
     *
     * @return
     */
    @WebMethod(operationName = "listaRoles")
    public ObjetoRetornaEntity listaRoles() {
        RolesLogic rolesLogic = new RolesLogic();
        return rolesLogic.listaRoles();
    }

    /**
     * Método que ingresa un Rol nuevo
     *
     * @param rol
     * @return
     */
    @WebMethod(operationName = "ingresarRol")
    public RolesEntity ingresarRol(@WebParam(name = "rol") RolesEntity rol) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(rol.getNombre_rol(), "Rol"))) {
            RolesEntity ret = new RolesEntity();
            ret.setTrazaRespuesta(valida.valida(rol.getNombre_rol(), "Rol"));
            return ret;
        } else {
            RolesLogic rolesLogic = new RolesLogic();
            return rolesLogic.ingresarRol(rol);
        }
    }

    /**
     * Metodo que permite actualizar el Rol
     *
     * @param rol
     * @return
     */
    @WebMethod(operationName = "actualizarRol")
    public RolesEntity actualizarRol(@WebParam(name = "rol") RolesEntity rol) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(rol.getId_rol(), "Rol"))) {
            RolesEntity ret = new RolesEntity();
            ret.setTrazaRespuesta(valida.valida(rol.getId_rol(), "Rol"));
            return ret;
        } else {
            RolesLogic rolesLogic = new RolesLogic();
            return rolesLogic.actualizarRol(rol);
        }
    }

    /**
     * Método que trae una lista de objetos para construir el árbol de los
     * permisos
     *
     * @return
     */
    @WebMethod(operationName = "listaPermisosDisponibles")
    public ArrayList<ListaAsignaPermisosModulo> listaPermisosDisponibles() {
        PermisosLogic permisosLogic = new PermisosLogic();
        return permisosLogic.listaRolPermiso();
    }

    /**
     * Método que permite insertar un área nueva
     *
     * @param area
     * @return
     */
    @WebMethod(operationName = "insertarArea")
    public AreaEntity ingresarArea(@WebParam(name = "area") AreaEntity area) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(area.getNombreArea(), "Area"))) {
            AreaEntity ret = new AreaEntity();
            ret.setTrazaRespuesta(valida.valida(area.getNombreArea(), "Ares"));
            return ret;
        } else {
            AreaLogic areaLogic = new AreaLogic();
            return areaLogic.ingresarArea(area);
        }
    }

    /**
     * Método que permite actualizar un área
     *
     * @param area
     * @return
     */
    @WebMethod(operationName = "actualizarArea")
    public AreaEntity actualizarArea(@WebParam(name = "area") AreaEntity area) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(area.getIdArea(), "Ares"))) {
            AreaEntity ret = new AreaEntity();
            ret.setTrazaRespuesta(valida.valida(area.getIdArea(), "Area"));
            return ret;
        } else {
            AreaLogic areaLogic = new AreaLogic();
            return areaLogic.actualizarArea(area);
        }
    }

    /**
     * Método que consulta la lista de Areas
     *
     * @return
     */
    @WebMethod(operationName = "listaAreas")
    public ObjetoRetornaEntity listaAreas() {
        AreaLogic areaLogic = new AreaLogic();
        return areaLogic.listaArea();
    }

    /**
     * Método que permite insertar un pais nuevo
     *
     * @param pais
     * @return
     */
    @WebMethod(operationName = "insertarPais")
    public PaisEntity ingresarPais(@WebParam(name = "pais") PaisEntity pais) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(pais.getNombrePais(), "Pais"))) {
            PaisEntity ret = new PaisEntity();
            ret.setTrazaRespuesta(valida.valida(pais.getNombrePais(), "Pais"));
            return ret;
        } else {
            PaisLogic paisLogic = new PaisLogic();
            return paisLogic.ingresaPais(pais);
        }
    }

    /**
     * Método que permite actualizar un pais
     *
     * @param pais
     * @return
     */
    @WebMethod(operationName = "actualizarPais")
    public PaisEntity actualizarPais(@WebParam(name = "pais") PaisEntity pais) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(pais.getIdPais(), "Pais"))) {
            PaisEntity ret = new PaisEntity();
            ret.setTrazaRespuesta(valida.valida(pais.getIdPais(), "Pais"));
            return ret;
        } else {
            PaisLogic paisLogic = new PaisLogic();
            return paisLogic.actualizaPais(pais);
        }
    }

    /**
     * Método que consulta la lista de paises
     *
     * @return
     */
    @WebMethod(operationName = "listaPais")
    public ObjetoRetornaEntity listaPais() {
        PaisLogic paisLogic = new PaisLogic();
        return paisLogic.listaPais();
    }

    @WebMethod(operationName = "paisPorId")
    public PaisEntity paisPorId(@WebParam(name = "idPais") int idPais) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idPais, "Pais"))) {
            PaisEntity ret = new PaisEntity();
            ret.setTrazaRespuesta(valida.valida(idPais, "Pais"));
            return ret;
        } else {
            PaisLogic paisLogic = new PaisLogic();
            return paisLogic.paisPorID(idPais);
        }
    }

    /**
     * Método que permite insertar un departamento nuevo
     *
     * @param departamento
     * @return
     */
    @WebMethod(operationName = "insertarDepartameno")
    public DepartamentoEntity ingresarDepartamento(@WebParam(name = "departamento") DepartamentoEntity departamento) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(departamento.getNombreDepartamento(), "Depto"))) {
            DepartamentoEntity ret = new DepartamentoEntity();
            ret.setTrazaRespuesta(valida.valida(departamento.getNombreDepartamento(), "Depto"));
            return ret;
        } else {
            DepartamentoLogic departamentoLogic = new DepartamentoLogic();
            return departamentoLogic.ingresaDepartamento(departamento);
        }
    }

    /**
     * Método que permite actualizar un departamento nuevo
     *
     * @param departamento
     * @return
     */
    @WebMethod(operationName = "actualizarDepartameno")
    public DepartamentoEntity actualizarDepartamento(@WebParam(name = "departamento") DepartamentoEntity departamento) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(departamento.getIdDepartamento(), "Depto"))) {
            DepartamentoEntity ret = new DepartamentoEntity();
            ret.setTrazaRespuesta(valida.valida(departamento.getIdDepartamento(), "Depto"));
            return ret;
        } else {
            DepartamentoLogic departamentoLogic = new DepartamentoLogic();
            return departamentoLogic.actualizaDepartamento(departamento);
        }
    }

    /**
     * Método que consulta la lista de departamentos
     *
     * @return
     */
    @WebMethod(operationName = "listaDepartameno")
    public ObjetoRetornaEntity listaDepartamento() {
        DepartamentoLogic departamentoLogic = new DepartamentoLogic();
        return departamentoLogic.listaDepartamento();
    }

    /**
     * Método que trae un departamento por ID
     *
     * @param idDepto
     * @return
     */
    @WebMethod(operationName = "deptoPorId")
    public DepartamentoEntity departamentoPorId(@WebParam(name = "idDepto") int idDepto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idDepto, "Depto"))) {
            DepartamentoEntity ret = new DepartamentoEntity();
            ret.setTrazaRespuesta(valida.valida(idDepto, "Depto"));
            return ret;
        } else {
            DepartamentoLogic departamentoLogic = new DepartamentoLogic();
            return departamentoLogic.deptoPorID(idDepto);
        }
    }

    /**
     * Método que permite insertar una ciudad nueva
     *
     * @param ciudad
     * @return
     */
    @WebMethod(operationName = "insertarCiudad")

    public CiudadEntity ingresarCiudad(@WebParam(name = "ciudad") CiudadEntity ciudad) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(ciudad.getNombreCiudad(), "Ciudad"))) {
            CiudadEntity ret = new CiudadEntity();
            ret.setTrazaRespuesta(valida.valida(ciudad.getNombreCiudad(), "Ciudad"));
            return ret;
        } else {
            CiudadLogic ciudadLogic = new CiudadLogic();
            return ciudadLogic.ingresaCiudad(ciudad);
        }
    }

    /**
     * Método que permite actualizar una ciudad
     *
     * @param ciudad
     * @return
     */
    @WebMethod(operationName = "actualizarCiudad")
    public CiudadEntity actualizarCiudad(@WebParam(name = "ciudad") CiudadEntity ciudad) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(ciudad.getIdCiudad(), "Ciudad"))) {
            CiudadEntity ret = new CiudadEntity();
            ret.setTrazaRespuesta(valida.valida(ciudad.getIdCiudad(), "Ciudad"));
            return ret;
        } else {
            CiudadLogic ciudadLogic = new CiudadLogic();
            return ciudadLogic.actualizarCiudad(ciudad);
        }
    }

    /**
     * Método que trae la lista de las ciudades disponibles
     *
     * @return
     */
    @WebMethod(operationName = "listaCiudades")
    public ObjetoRetornaEntity listaCiudad() {
        CiudadLogic ciudadLogic = new CiudadLogic();
        return ciudadLogic.listaCiudad();
    }

    /**
     * Método que permite insertar una sede nueva
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "ingresaSede")
    public SedeEntity ingresarSede(@WebParam(name = "sede") SedeEntity sede) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(sede.getNombreSede(), "Sede"))) {
            SedeEntity ret = new SedeEntity();
            ret.setTrazaRespuesta(valida.valida(sede.getNombreSede(), "Sede"));
            return ret;
        } else {
            SedeLogic sedeLogic = new SedeLogic();
            return sedeLogic.ingresarSede(sede);
        }
    }

    /**
     * Método que permite actualizar una sede
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "actualizaSede")
    public SedeEntity actualizarSede(@WebParam(name = "sede") SedeEntity sede) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(sede.getIdSede(), "Sede"))) {
            SedeEntity ret = new SedeEntity();
            ret.setTrazaRespuesta(valida.valida(sede.getIdSede(), "Sede"));
            return ret;
        } else {
            SedeLogic sedeLogic = new SedeLogic();
            return sedeLogic.actualizarSede(sede);
        }
    }

    /**
     * Método que qonculta una lista de sedes
     *
     * @return
     */
    @WebMethod(operationName = "listaSede")
    public ObjetoRetornaEntity listaSede() {
        SedeLogic sedeLogic = new SedeLogic();
        return sedeLogic.listaSedes();
    }

    @WebMethod(operationName = "sedePorId")
    public SedeEntity sedePorId(@WebParam(name = "sede") int idSede) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idSede, "Sede"))) {
            SedeEntity ret = new SedeEntity();
            ret.setTrazaRespuesta(valida.valida(idSede, "Sede"));
            return ret;
        } else {
            SedeLogic sedeLogic = new SedeLogic();
            return sedeLogic.sedePorID(idSede);
        }
    }

    /**
     * Método que inserta una empresa nueva
     *
     * @param empresa
     * @return
     */
    @WebMethod(operationName = "ingresarEmpresa")
    public EmpresaEntity ingresarEmpresa(@WebParam(name = "empresa") EmpresaEntity empresa) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(empresa.getNombreEmpresa(), "empresa"))) {
            EmpresaEntity ret = new EmpresaEntity();
            ret.setTrazaRespuesta(valida.valida(empresa.getNombreEmpresa(), "empresa"));
            return ret;
        } else {
            EmpresaLogic empresaLogic = new EmpresaLogic();
            return empresaLogic.ingresarEmpresa(empresa);
        }
    }

    /**
     * Método que actualizauna empresa
     *
     * @param empresa
     * @return
     */
    @WebMethod(operationName = "actualizarEmpresa")
    public EmpresaEntity actualizarEmpresa(@WebParam(name = "empresa") EmpresaEntity empresa) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(empresa.getIdEmpresa(), "empresa"))) {
            EmpresaEntity ret = new EmpresaEntity();
            ret.setTrazaRespuesta(valida.valida(empresa.getIdEmpresa(), "empresa"));
            return ret;
        } else {
            EmpresaLogic empresaLogic = new EmpresaLogic();
            return empresaLogic.actualizarEmpresa(empresa);
        }
    }

    /**
     * Método que consulta la lista de empresas disponibles
     *
     * @return
     */
    @WebMethod(operationName = "listaEmpresa")
    public ObjetoRetornaEntity listaEmpresa() {
        EmpresaLogic empresaLogic = new EmpresaLogic();
        return empresaLogic.listaEmpresa();
    }

    /**
     * Método que permite ingrsar un registro nuevo a la tabla Sede_Empresa
     *
     * @param sedeE
     * @return
     */
    @WebMethod(operationName = "ingresaSedeEmpresa")
    public SedeEmpresaEntity ingresarSedeEmpresa(@WebParam(name = "sedee") SedeEmpresaEntity sedeE) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(sedeE.getIdSede(), "sedeE"))) {
            SedeEmpresaEntity ret = new SedeEmpresaEntity();
            ret.setTrazaRespuesta(valida.valida(sedeE.getIdSede(), "sedeE"));
            return ret;
        } else {
            SedeEmpresaLogic sedeEmpresaLogic = new SedeEmpresaLogic();
            return sedeEmpresaLogic.ingresarSedeEmpresa(sedeE);
        }
    }

    /**
     * Método que permite actualizar un registro de la tabla Sede_Empresa
     *
     * @param sedeE
     * @return
     */
    @WebMethod(operationName = "actualizaSedeEmpresa")
    public SedeEmpresaEntity actualizaSedeEmpresa(@WebParam(name = "sedee") SedeEmpresaEntity sedeE) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(sedeE.getIdSedeEmpresa(), "sedeE"))) {
            SedeEmpresaEntity ret = new SedeEmpresaEntity();
            ret.setTrazaRespuesta(valida.valida(sedeE.getIdSedeEmpresa(), "sedeE"));
            return ret;
        } else {
            SedeEmpresaLogic sedeEmpresaLogic = new SedeEmpresaLogic();
            return sedeEmpresaLogic.actualizarsedeEmpresa(sedeE);
        }
    }

    /**
     * Método que consulta una lista de relaciones Sede_Empresa
     *
     * @return
     */
    @WebMethod(operationName = "listaSedeEmpresa")
    public ObjetoRetornaEntity listaSedeEmpresa() {
        SedeEmpresaLogic sedeEmpresaLogic = new SedeEmpresaLogic();
        return sedeEmpresaLogic.listaRoles();
    }

    /**
     * Método qye permite ingresar un festivo nuevo
     *
     * @param festivo
     * @return
     */
    @WebMethod(operationName = "ingresaFestivo")
    public FestivosEntity ingresaFestivo(@WebParam(name = "festivo") FestivosEntity festivo) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(festivo.getNombreFestivo(), "festivo"))) {
            FestivosEntity ret = new FestivosEntity();
            ret.setTrazaRespuesta(valida.valida(festivo.getNombreFestivo(), "festivo"));
            return ret;
        } else {
            FestivosLogic festivosLogic = new FestivosLogic();
            return festivosLogic.ingresaFestivo(festivo);

        }
    }

    /**
     * Método qye permite actualizar un festivo
     *
     * @param festivo
     * @return
     */
    @WebMethod(operationName = "actualizarFestivo")
    public FestivosEntity actualizarFestivo(@WebParam(name = "festivo") FestivosEntity festivo) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(festivo.getIdFEstivo(), "festivo"))) {
            FestivosEntity ret = new FestivosEntity();
            ret.setTrazaRespuesta(valida.valida(festivo.getIdFEstivo(), "festivo"));
            return ret;
        } else {
            FestivosLogic festivosLogic = new FestivosLogic();
            return festivosLogic.actualizaFestivo(festivo);
        }
    }

    /**
     * Método que consulta los festivos disponibles
     *
     * @return
     */
    @WebMethod(operationName = "listafestivos")
    public ObjetoRetornaEntity listaFestivos() {
        FestivosLogic festivosLogic = new FestivosLogic();
        return festivosLogic.listaFestivos();
    }

    /**
     * Método que trae un único festivo filtrado por el ID
     *
     * @param idFestivo
     * @return
     */
    public FestivosEntity festtivoPorId(@WebParam(name = "idFestivo") int idFestivo) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idFestivo, "festivo"))) {
            FestivosEntity ret = new FestivosEntity();
            ret.setTrazaRespuesta(valida.valida(idFestivo, "festivo"));
            return ret;
        } else {
            FestivosLogic festivosLogic = new FestivosLogic();
            return festivosLogic.festivoPorId(idFestivo);
        }
    }

    /**
     * Mátodo que ingresa un MacroProceso
     *
     * @param macro
     * @return
     */
    @WebMethod(operationName = "ingresaMacroProceso")
    public MacroprocesosEntity ingresaMacro(@WebParam(name = "macro") MacroprocesosEntity macro) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(macro.getNombreMacroproceso(), "Macro"))) {
            MacroprocesosEntity ret = new MacroprocesosEntity();
            ret.setTrazaRespuesta(valida.valida(macro.getNombreMacroproceso(), "Macro"));
            return ret;
        } else {
            MacroProcesoLogic macroProcesoLogic = new MacroProcesoLogic();
            return macroProcesoLogic.ingresaMacroproceso(macro);
        }
    }

    /**
     * Mátodo que actualiza un MacroProceso
     *
     * @param macro
     * @return
     */
    @WebMethod(operationName = "actualizaMacroProceso")
    public MacroprocesosEntity actualizaMacro(@WebParam(name = "macro") MacroprocesosEntity macro) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(macro.getIdMacroproceso(), "Macro"))) {
            MacroprocesosEntity ret = new MacroprocesosEntity();
            ret.setTrazaRespuesta(valida.valida(macro.getIdMacroproceso(), "Macro"));
            return ret;
        } else {
            MacroProcesoLogic macroProcesoLogic = new MacroProcesoLogic();
            return macroProcesoLogic.actualizarMacroproceso(macro);
        }
    }

    /**
     * Método que consulta los macroProcesos disponibles
     *
     * @return
     */
    @WebMethod(operationName = "listaMacroProcesos")
    public ObjetoRetornaEntity listaMacro() {
        MacroProcesoLogic macroProcesoLogic = new MacroProcesoLogic();
        return macroProcesoLogic.listaMacroProcesos();
    }

    /**
     * Mátodo que trae un único MacroProceso Filtrado por ID
     *
     * @param macro
     * @return
     */
    @WebMethod(operationName = "macroPorId")
    public MacroprocesosEntity macroPorId(@WebParam(name = "macro") int idMacro) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idMacro, "Macro"))) {
            MacroprocesosEntity ret = new MacroprocesosEntity();
            ret.setTrazaRespuesta(valida.valida(idMacro, "Macro"));
            return ret;
        } else {
            MacroProcesoLogic macroProcesoLogic = new MacroProcesoLogic();
            return macroProcesoLogic.macroPorID(idMacro);
        }
    }

    /**
     * Método que inserta un proceso nuevo
     *
     * @param proceso
     * @return
     */
    @WebMethod(operationName = "ingresaProceso")
    public ProcesosEntity ingresaProceso(@WebParam(name = "proceso") ProcesosEntity proceso) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(proceso.getNombrePreoceso(), "Proceso"))) {
            ProcesosEntity ret = new ProcesosEntity();
            ret.setTrazaRespuesta(valida.valida(proceso.getNombrePreoceso(), "Proceso"));
            return ret;
        } else {
            ProcesosLogic procesosLogic = new ProcesosLogic();
            return procesosLogic.ingresaProcesos(proceso);
        }
    }

    /**
     * Método que actualiza un proceso nuevo
     *
     * @param proceso
     * @return
     */
    @WebMethod(operationName = "actualizaProceso")
    public ProcesosEntity actualizaProceso(@WebParam(name = "proceso") ProcesosEntity proceso) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(proceso.getIdMacroProceso(), "Proceso"))) {
            ProcesosEntity ret = new ProcesosEntity();
            ret.setTrazaRespuesta(valida.valida(proceso.getIdMacroProceso(), "Proceso"));
            return ret;
        } else {
            ProcesosLogic procesosLogic = new ProcesosLogic();
            return procesosLogic.actualizarProcesos(proceso);
        }
    }

    /**
     * Método que inserta un proceso nuevo
     *
     * @param idProceso
     * @return
     */
    @WebMethod(operationName = "procesoPorID")
    public ProcesosEntity procesoPorID(@WebParam(name = "proceso") int idProceso) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idProceso, "Proceso"))) {
            ProcesosEntity ret = new ProcesosEntity();
            ret.setTrazaRespuesta(valida.valida(idProceso, "Proceso"));
            return ret;
        } else {
            ProcesosLogic procesosLogic = new ProcesosLogic();
            return procesosLogic.procesoPorID(idProceso);
        }
    }

    /**
     * Método que trae la lista de procesos disponibles
     *
     * @return
     */
    @WebMethod(operationName = "listaProcesos")
    public ObjetoRetornaEntity listaProceso() {
        ProcesosLogic procesosLogic = new ProcesosLogic();
        return procesosLogic.listaProcesos();
    }

    /**
     * Método que ingresa un Subproceso nuevo
     *
     * @param subproceso
     * @return
     */
    @WebMethod(operationName = "ingresaSubProceso")
    public SubprocesoEntity ingresaSubproceso(@WebParam(name = "subproceso") SubprocesoEntity subproceso) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(subproceso.getNombreSubproceso(), "Subroceso"))) {
            SubprocesoEntity ret = new SubprocesoEntity();
            ret.setTrazaRespuesta(valida.valida(subproceso.getNombreSubproceso(), "Subroceso"));
            return ret;
        } else {
            SubProcesosLogic subProcesosLogic = new SubProcesosLogic();
            return subProcesosLogic.ingresaSubProceso(subproceso);
        }
    }

    /**
     * Método que actualiza un Subproceso
     *
     * @param subproceso
     * @return
     */
    @WebMethod(operationName = "actualizaSubProceso")
    public SubprocesoEntity actualizaSubproceso(@WebParam(name = "subproceso") SubprocesoEntity subproceso) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(subproceso.getIdSubproceso(), "Subroceso"))) {
            SubprocesoEntity ret = new SubprocesoEntity();
            ret.setTrazaRespuesta(valida.valida(subproceso.getIdSubproceso(), "Subroceso"));
            return ret;
        } else {
            SubProcesosLogic subProcesosLogic = new SubProcesosLogic();
            return subProcesosLogic.actualizarSubprocesos(subproceso);
        }
    }

    /**
     * Método que trae un subproceso filtrado por ID
     *
     * @param idSubproceso
     * @return
     */
    @WebMethod(operationName = "subProcesoPorID")
    public SubprocesoEntity subprocesoPorID(@WebParam(name = "subproceso") int idSubproceso) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(idSubproceso, "Subroceso"))) {
            SubprocesoEntity ret = new SubprocesoEntity();
            ret.setTrazaRespuesta(valida.valida(idSubproceso, "Subroceso"));
            return ret;
        } else {
            SubProcesosLogic subProcesosLogic = new SubProcesosLogic();
            return subProcesosLogic.subProcesoProID(idSubproceso);
        }
    }

    /**
     * Método que consulta la lista de subprocesos disponibles
     *
     * @return
     */
    @WebMethod(operationName = "listaSubProcesos")
    public ObjetoRetornaEntity listaSubprocesos() {
        SubProcesosLogic subProcesosLogic = new SubProcesosLogic();
        return subProcesosLogic.listaSubproceso();
    }

    /**
     * Método que permite Ingresar una relación usuario-Macroproceso nuevo
     *
     * @param ususmacro
     * @return
     */
    @WebMethod(operationName = "ingresarUsuarioMacroproceso")
    public UsuarioMacroprocesoEntity ingresarUsuarioMacroproceso(@WebParam(name = "usuarioMacroproceso") UsuarioMacroprocesoEntity ususmacro) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(ususmacro.getIdUsuario().getIdUsuario(), "Usuario-MacroProceso"))) {
            UsuarioMacroprocesoEntity ret = new UsuarioMacroprocesoEntity();
            ret.setTrazaRespuesta(valida.valida(ususmacro, "Usuario-MacroProceso"));
            return ret;
        } else {
            UsuarioMacroprocesoLogic usuarioMacroprocesoLogic = new UsuarioMacroprocesoLogic();
            return usuarioMacroprocesoLogic.ingresaUsuarioMacroproceso(ususmacro);
        }
    }

    /**
     * Método que permite actulizar una relación usuario-MacroProceso
     *
     * @param usumacro
     * @return
     */
    @WebMethod(operationName = "actualizarusuarioMacroproceso")
    public UsuarioMacroprocesoEntity actualizarUsuarioMacroporceso(@WebParam(name = "usuarioMacroproceso") UsuarioMacroprocesoEntity usumacro) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(usumacro.getIdUsuarioMacroproceso(), "Usuario-MacroProceso"))) {
            UsuarioMacroprocesoEntity ret = new UsuarioMacroprocesoEntity();
            ret.setTrazaRespuesta(valida.valida(usumacro, "Usuario-MacroProceso"));
            return ret;
        } else {
            UsuarioMacroprocesoLogic usuarioMacroprocesoLogic = new UsuarioMacroprocesoLogic();
            return usuarioMacroprocesoLogic.actualizarUsuarioMacroproceso(usumacro);
        }
    }

    /**
     * Método que retorna una lista de las relaciones entre Usuarios y
     * Macroprocesos
     *
     * @return
     */
    @WebMethod(operationName = "listaUsuarioMacroproceso")
    public ObjetoRetornaEntity listausuarioMacroproceso() {
        UsuarioMacroprocesoLogic usuarioMacroprocesoLogic = new UsuarioMacroprocesoLogic();
        return usuarioMacroprocesoLogic.listaMacroProcesos();
    }

    /**
     * Método que permite Ingresar una relación usuario-Proceso nuevo
     *
     *
     * @param usuProceso
     * @return
     */
    @WebMethod(operationName = "ingresarUsuarioProcesos")
    public UsuarioProcesoEntity ingresarUsuarioProceso(@WebParam(name = "UsuarioProceso") UsuarioProcesoEntity usuProceso) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(usuProceso.getIdUsuario().getIdUsuario(), "Usuario-Proceso"))) {
            UsuarioProcesoEntity ret = new UsuarioProcesoEntity();
            ret.setTrazaRespuesta(valida.valida(usuProceso, "Usuario-Proceso"));
            return ret;
        } else {
            UsuarioProcesoLogic usuarioProcesoLogic = new UsuarioProcesoLogic();
            return usuarioProcesoLogic.ingresarUsuarioProceso(usuProceso);
        }
    }

    /**
     * Método que permite actulizar una relación usuario-Proceso
     *
     *
     * @param usuProceso
     * @return
     */
    @WebMethod(operationName = "actualizarUsuarioProceso")
    public UsuarioProcesoEntity actualizarusuarioProceso(@WebParam(name = "usuarioProceso") UsuarioProcesoEntity usuProceso) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(usuProceso.getIdUsuario().getIdUsuario(), "Usuario-Proceso"))) {
            UsuarioProcesoEntity ret = new UsuarioProcesoEntity();
            ret.setTrazaRespuesta(valida.valida(usuProceso, "Usuario-Proceso"));
            return ret;
        } else {
            UsuarioProcesoLogic usuarioProcesoLogic = new UsuarioProcesoLogic();
            return usuarioProcesoLogic.actualizarUsuarioProceso(usuProceso);
        }
    }

    /**
     * Método que retorna una lista de relaciones entre Usuarios y Procesos
     * @return
     */
    @WebMethod(operationName = "listausuarioProcesos")
    public ObjetoRetornaEntity listaUsuarioProcesos() {
        UsuarioProcesoLogic usuarioProcesoLogic = new UsuarioProcesoLogic();
        return usuarioProcesoLogic.listaProcesos();
    }

    /**
     * Método que permite Ingresar una relación usuario-Subproceso nuevo
     *
     * @param usuSubproceso
     * @return
     */
    @WebMethod(operationName = "ingresarUsuarioSubproceso")
    public UsuarioSubprocesoEntity ingresarUsuarioSubprocesos(@WebParam(name = "UsuarioSubproceso") UsuarioSubprocesoEntity usuSubproceso) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(usuSubproceso.getIdusuario().getIdUsuario(), "Usuario-SubProceso"))) {
            UsuarioSubprocesoEntity ret = new UsuarioSubprocesoEntity();
            ret.setTrazaRespuesta(valida.valida(usuSubproceso, "Usuario-SubProceso"));
            return ret;
        } else {
            UsuarioSubprocesoLogic usuarioSubprocesoLogic = new UsuarioSubprocesoLogic();
            return usuarioSubprocesoLogic.ingresaUsuarioSubproceso(usuSubproceso);
        }
    }

    /**
     * Método que permite Ingresar una relación usuario-SubProceso nuevo     *
     * @param usuSubproceso
     * @return
     */
    @WebMethod(operationName = "actualizarUsuarioSubproceso")
    public UsuarioSubprocesoEntity actualizarUsuarioSubprocesos(@WebParam(name = "UsuarioSubproceso") UsuarioSubprocesoEntity usuSubproceso) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(usuSubproceso.getIdusuario().getIdUsuario(), "Usuario-SubProceso"))) {
            UsuarioSubprocesoEntity ret = new UsuarioSubprocesoEntity();
            ret.setTrazaRespuesta(valida.valida(usuSubproceso, "Usuario-SubProceso"));
            return ret;
        } else {
            UsuarioSubprocesoLogic usuarioSubprocesoLogic = new UsuarioSubprocesoLogic();
            return usuarioSubprocesoLogic.actualizarUsuarioSubproceso(usuSubproceso);
        }
    }
    /**
     * Método que retorna una lista de relaciones entre Usuarios y Subrocesos
     * @return 
     */
    @WebMethod(operationName = "listaUsuarioSubproceso")
    public ObjetoRetornaEntity listaUsuarioSubprocesos(){
        UsuarioSubprocesoLogic usuarioSubprocesoLogic=new UsuarioSubprocesoLogic();
        return usuarioSubprocesoLogic.listaSubProcesos();
    }

}
