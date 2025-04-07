package br.com.ms.utils.enums;

public enum Mensagens {

    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada no banco de dados.");

    private final String descricao;

    Mensagens(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
