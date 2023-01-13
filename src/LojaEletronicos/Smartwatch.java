package LojaEletronicos;

public class Smartwatch extends Equipamento {
    private String pulseira;
    private static final long serialVersionUID = 1L;

    public Smartwatch(String marca, String modelo, int tela, String pulseira) {
        super(marca, modelo, tela);
        this.nome = "Smartwatch";
        this.pulseira = pulseira;
    }

    public String so() {
        return "Não se aplica";
    }

    public String getPulseira() {
        return pulseira;
    }

    public String toString() {
        return  "Nome: "                    + getNome()         + "\n" +
                "Marca: "                   + getMarca()        + "\n" +
                "Modelo: "                  + getModelo()       + "\n" +
                "Tamanho da tela: "         + getTela()         + "\n" +
                "Sistema operacional: "     + so()              + "\n" +
                "Tipo de pulseira: "        + getPulseira()     + "\n";
    }
}
