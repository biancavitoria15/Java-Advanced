import br.com.fiap.api_rest_aula01.model;

public enum Categoria {
    BRONZE("Bronze"),
    PRATA("Prata"),
    OURO("Ouro");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
