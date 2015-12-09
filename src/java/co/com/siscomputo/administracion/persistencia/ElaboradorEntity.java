package co.com.siscomputo.administracion.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ADM_TELAB")
public class ElaboradorEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "ELAB_ELAB")
    private int idElaborador;
    @JoinColumn(name = "ELAB_USUA")
    @ManyToOne
    private UsuarioEntity usuarioElaborador;
    @Column(name = "ELAB_CREA")
    private int creadorElaborador;
    @Column(name = "ELAB_UPDA")
    private int actualizadorElaborador;
    @Column(name = "ELAB_ESTA")
    private String estadoElaborador;

    public int getIdElaborador() {
        return idElaborador;
    }

    public void setIdElaborador(int idElaborador) {
        this.idElaborador = idElaborador;
    }

    public UsuarioEntity getUsuarioElaborador() {
        return usuarioElaborador;
    }

    public void setUsuarioElaborador(UsuarioEntity usuarioElaborador) {
        this.usuarioElaborador = usuarioElaborador;
    }

    public int getCreadorElaborador() {
        return creadorElaborador;
    }

    public void setCreadorElaborador(int creadorElaborador) {
        this.creadorElaborador = creadorElaborador;
    }

    public int getActualizadorElaborador() {
        return actualizadorElaborador;
    }

    public void setActualizadorElaborador(int actualizadorElaborador) {
        this.actualizadorElaborador = actualizadorElaborador;
    }

    public String getEstadoElaborador() {
        return estadoElaborador;
    }

    public void setEstadoElaborador(String estadoElaborador) {
        this.estadoElaborador = estadoElaborador;
    }

}
