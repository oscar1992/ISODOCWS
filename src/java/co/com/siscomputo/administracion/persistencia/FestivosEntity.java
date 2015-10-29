/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoTraza;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ADMTFEST")
public class FestivosEntity extends ObjetoTraza implements Serializable{
    @Id
    @Column(name = "FEST_FEST")
    private int idFEstivo;
    @Column(name = "FEST_FECH")
    private String fechaFestivo;
    @Column(name = "FEST_NOMB")
    private String nombreFestivo;

    public int getIdFEstivo() {
        return idFEstivo;
    }

    public void setIdFEstivo(int idFEstivo) {
        this.idFEstivo = idFEstivo;
    }

    public String getFechaFestivo() {
        return fechaFestivo;
    }

    public void setFechaFestivo(String fechaFestivo) {
        this.fechaFestivo = fechaFestivo;
    }

    public String getNombreFestivo() {
        return nombreFestivo;
    }

    public void setNombreFestivo(String nombreFestivo) {
        this.nombreFestivo = nombreFestivo;
    }
    
    
}
