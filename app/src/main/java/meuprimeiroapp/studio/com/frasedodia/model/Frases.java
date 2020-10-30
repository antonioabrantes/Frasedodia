package meuprimeiroapp.studio.com.frasedodia.model;

import com.google.firebase.database.DatabaseReference;

import meuprimeiroapp.studio.com.frasedodia.config.ConfiguracaoFirebase;

public class Frases {
    private int id;
    private String autor;
    private String texto;
    private int curtir;

    public Frases() {
    }

    public Frases(int id, String autor, String texto, int curtir) {
        this.id = id;
        this.autor = autor;
        this.texto = texto;
        this.curtir = curtir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getCurtir() {
        return curtir;
    }

    public void setCurtir(int curtir) {
        this.curtir = curtir;
    }
}
