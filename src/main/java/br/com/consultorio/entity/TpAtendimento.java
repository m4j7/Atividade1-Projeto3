package br.com.consultorio.entity;

public enum TpAtendimento {

    plano("plano"),
    convenio("convenio");

    public final String valor;

    private TpAtendimento(String valor){

        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
