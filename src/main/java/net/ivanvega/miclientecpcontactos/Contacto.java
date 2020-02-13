package net.ivanvega.miclientecpcontactos;

import java.io.Serializable;
import java.util.Date;

public  class Contacto implements Serializable {
    int id;
    String usuario;
            String email;
            String tel;
            Date fecNac;

    public Contacto(int id, String usuario, String email, String tel) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }
}
