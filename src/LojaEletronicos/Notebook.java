package LojaEletronicos;

public class Notebook extends Equipamento {
    private String processador;
    private String ram;
    private static final long serialVersionUID = 1L;

    public Notebook(String marca, String modelo, int tela, String processador, String ram) {
        super(marca, modelo, tela);
        this.processador = processador;
        this.ram = ram;
    }

    public String so() {
        return "Windows";
    }

    public String getProcessador() {
        return processador;
    }

    public String getRam() {
        return ram;
    }

    public String toString() {
        return  "Nome: "                    + getNome()         + "\n" +
                "Marca: "                   + getMarca()        + "\n" +
                "Modelo: "                  + getModelo()       + "\n" +
                "Tamanho da tela: "         + getTela()         + "\n" +
                "Sistema operacional: "     + so()              + "\n" +
                "Processador: "             + getProcessador()  + "\n" +
                "Modelo de memória ram: "   + getRam()          + "\n";
    }
}
