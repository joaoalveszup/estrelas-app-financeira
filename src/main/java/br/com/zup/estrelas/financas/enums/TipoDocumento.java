package br.com.zup.estrelas.financas.enums;

public enum TipoDocumento {
    
    CPF("cpf"), RG("rg"), PASSAPORTE("passaporte"), OUTRO("outro");
    
    private TipoDocumento(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
