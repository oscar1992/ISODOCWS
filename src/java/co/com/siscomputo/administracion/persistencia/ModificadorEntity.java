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
@Table(name = "ADM_TMODI")
public class ModificadorEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "MODI_MODI")
    private int idModificador;
    @JoinColumn(name = "MODI_USUA")
    @ManyToOne
    private UsuarioEntity usuarioModificador;
    @Column(name = "MODI_CREA")
    private int creadorModificador;
    @Column(name = "MODI_UPDA")
    private int actualizadorModificador;
    @Column(name = "MODI_ESTA")
    private String estadoModificador;

    public int getIdModificador() {
        return idModificador;
    }

    public void setIdModificador(int idModificador) {
        this.idModificador = idModificador;
    }

    public UsuarioEntity getUsuarioModificador() {
        return usuarioModificador;
    }

    public void setUsuarioModificador(UsuarioEntity usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
    }
    
    public int getCreadorModificador() {
        return creadorModificador;
    }

    public void setCreadorModificador(int creadorModificador) {
        this.creadorModificador = creadorModificador;
    }

    public int getActualizadorModificador() {
        return actualizadorModificador;
    }

    public void setActualizadorModificador(int actualizadorModificador) {
        this.actualizadorModificador = actualizadorModificador;
    }

    public String getEstadoModificador() {
        return estadoModificador;
    }

    public void setEstadoModificador(String estadoModificador) {
        this.estadoModificador = estadoModificador;
    }

}
