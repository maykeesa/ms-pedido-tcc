package br.com.ms.config.exception.enums;

public enum MensagensException {

    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada no banco de dados.");

    private final String descricao;

    MensagensException(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
