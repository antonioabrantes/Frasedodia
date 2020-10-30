package meuprimeiroapp.studio.com.frasedodia.model;

public class Votacoes {
    private int id;
    private String email;

    public Votacoes() {
    }

    public Votacoes(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
