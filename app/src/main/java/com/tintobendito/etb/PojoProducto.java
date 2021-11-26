package com.tintobendito.etb;

public class PojoProducto {

    //Características del Objeto
    private int foto;
    private String nomProducto;
    private String precProducto;
    private String descProducto;


    public PojoProducto() {
    }

    public PojoProducto(int foto, String nomProducto, String precProducto, String descProducto) {
        this.foto = foto;
        this.nomProducto = nomProducto;
        this.precProducto = precProducto;
        this.descProducto = descProducto;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public String getPrecProducto() {
        return precProducto;
    }

    public void setPrecProducto(String precProducto) {
        this.precProducto = precProducto;
    }

    public String getDescProducto() {
        return descProducto;
    }

    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
    }
}
