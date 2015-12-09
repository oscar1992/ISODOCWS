/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.entites;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class MenuModuloEntity {

    private int idModulo;
    private String nombre;
    private String rutaModulo;
    private String rutaIcono;
    private int idCreador;
    private int idActualizador;
    private String estado;
    private int orden;
    private ArrayList<MenuPermisosEntity> subNivel;

    public ArrayList<MenuPermisosEntity> getSubNivel() {
        return subNivel;
    }

    public void setSubNivel(ArrayList<MenuPermisosEntity> subNivel) {
        this.subNivel = subNivel;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaModulo() {
        return rutaModulo;
    }

    public void setRutaModulo(String rutaModulo) {
        this.rutaModulo = rutaModulo;
    }

    public String getRutaIcono() {
        return rutaIcono;
    }

    public void setRutaIcono(String rutaIcono) {
        this.rutaIcono = rutaIcono;
    }

    public int getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(int idCreador) {
        this.idCreador = idCreador;
    }

    public int getIdActualizador() {
        return idActualizador;
    }

    public void setIdActualizador(int idActualizador) {
        this.idActualizador = idActualizador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

}
