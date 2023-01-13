package LojaEletronicos;

import java.io.Serializable;

public abstract class Equipamento implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String nome;
    private String marca;
    private String modelo;
    private int tela;

    public Equipamento(String marca, String modelo, int tela) {
        this.marca = marca;
        this.modelo = modelo;
        this.tela = tela;
    }
    public abstract String so();

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getTela() {
        return tela;
    }

    public String toString() {
        return  "Nome: "                + getNome()     + "\n" +
                "Marca: "               + getMarca()    + "\n" +
                "Modelo: "              + getModelo()   + "\n" +
                "Tamanho da tela: "     + getTela()     + "\n" +
                "Sistema operacional: " + so()          + "\n";
    }
}
