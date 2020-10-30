package meuprimeiroapp.studio.com.frasedodia.model;

public class DadosBib {

    private String autor;
    private String imagem;
    private String biografia;

    public DadosBib() {
    }

    public DadosBib(String autor, String imagem, String biografia) {
        this.autor = autor;
        this.imagem = imagem;
        this.biografia = biografia;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}
