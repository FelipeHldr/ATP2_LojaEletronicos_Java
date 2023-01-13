package LojaEletronicos;

import javax.swing.JOptionPane;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LojaEletronicos {
    private ArrayList<Equipamento> equipamentos;

    public LojaEletronicos() {
        this.equipamentos = new ArrayList<Equipamento>();
    }
    public String[] leValores (String [] dadosIn){
        String [] dadosOut = new String [dadosIn.length];

        for( int i = 0; i < dadosIn.length; i++)
            dadosOut[i] = JOptionPane.showInputDialog("Entre com " + dadosIn[i] + ": ");

        return dadosOut;
    }

    public Notebook leNotebook() {
        String [] valores = new String[5];
        String [] nomeVal = {"Marca", "Modelo", "Tamanho da tela", "Processador", "Modelo de memória ram"};
        valores = leValores (nomeVal);

        int tela = this.retornaInteiro(valores[2]);

        Notebook notebook = new Notebook(valores[0], valores[1], tela, valores[3], valores[4]);
        return notebook;
    }

    public Celular leCelular() {
        String [] valores = new String[5];
        String [] nomeVal = {"Marca", "Modelo", "Tamanho da tela", "Processador", "Quantidade de memória RAM"};
        valores = leValores (nomeVal);

        int tela = this.retornaInteiro(valores[2]);

        Celular celular = new Celular(valores[0], valores[1], tela, valores[3], valores[4]);
        return celular;
    }

    public Smartwatch leSmartwatch() {
       String [] valores = new String[4];
       String [] nomeVal = {"Marca", "Modelo", "Tamanho da tela", "Material da pulseira"};
       valores = leValores (nomeVal);

        int tela = this.retornaInteiro(valores[2]);

        Smartwatch smartwatch = new Smartwatch(valores[0], valores[1], tela, valores[3]);
        return smartwatch;
    }

    private boolean intValido(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public int retornaInteiro(String entrada) {
        int numInt;

        while (!this.intValido(entrada)) {
            entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
        }
        return Integer.parseInt(entrada);
    }

    public void salvaEquipamentos (ArrayList<Equipamento> equipamentos){
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream
                    (new FileOutputStream("c:\\temp\\lojaEletronicos.dados"));
            for (int i = 0; i < equipamentos.size(); i++)
                outputStream.writeObject(equipamentos.get(i));
        }catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Impossível criar arquivo!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @SuppressWarnings("finally")
    public ArrayList<Equipamento> recuperarEquipamentos() {
        ArrayList<Equipamento> equipamentosTemp = new ArrayList<Equipamento>();

        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream
                    (new FileInputStream("c:\\temp\\lojaEletronicos.dados"));
            Object obj = null;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof Equipamento) {
                    equipamentosTemp.add((Equipamento) obj);
                }
            }
        } catch (EOFException ex) {
            System.out.println("Fim de arquivo.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo com equipamentos não existe!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
            return equipamentosTemp;
        }
    }

    public void menuLojaEletronicos() {

        String menu = "";
        String entrada;
        int    opc1, opc2;

        do {
            menu = "Controle da Loja\n" +
                    "Opções:\n" +
                    "1. Entrar Equipamentos\n" +
                    "2. Exibir Equipamentos\n" +
                    "3. Limpar Equipamentos\n" +
                    "4. Gravar Equipamentos\n" +
                    "5. Recuperar Equipamentos\n" +
                    "9. Sair";
            entrada = JOptionPane.showInputDialog(menu + "\n\n");
            opc1 = this.retornaInteiro(entrada);

            switch (opc1) {
                case 1:
                    menu = "Entrada de Produtos\n" +
                            "Opções:\n" +
                            "1. Notebook\n" +
                            "2. Celular\n" +
                            "3. Smartwatch\n" ;

                    entrada = JOptionPane.showInputDialog(menu +"\n\n");
                    opc2 = this.retornaInteiro(entrada);

                    switch (opc2) {
                        case 1: equipamentos.add((Equipamento)leNotebook());
                            break;
                        case 2: equipamentos.add((Equipamento)leCelular());
                            break;
                        case 3: equipamentos.add((Equipamento)leSmartwatch());
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Produto para entrada não escolhido!");
                    }

                    break;
                case 2:
                    if (equipamentos.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Entre com produtos primeiramente");
                        break;
                    }
                    String dados = "";
                    for (int i=0; i < equipamentos.size(); i++) {
                        dados += equipamentos.get(i).toString() + "---------------\n";
                    }
                    JOptionPane.showMessageDialog(null,dados);
                    break;
                case 3:
                    if (equipamentos.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Entre com produtos primeiramente");
                        break;
                    }
                    equipamentos.clear();
                    JOptionPane.showMessageDialog(null, "Dados deletados com sucesso!");
                case 4:
                    if(equipamentos.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Entre com produtos primeiramente");
                        break;
                    }
                    salvaEquipamentos(equipamentos);
                    JOptionPane.showMessageDialog(null,"Dados salvos com sucesso!");
                    break;
                case 5:
                    equipamentos = recuperarEquipamentos();
                    if (equipamentos.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Sem dados para apresentar.");
                        break;
                    }
                    JOptionPane.showMessageDialog(null, "Dados recuperados com sucesso!");
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null, "Fim do app LOJAELETRONICOS");
                    break;
            }
        } while (opc1 != 9);
    }

    public static void main(String[] args){

            LojaEletronicos equipamento = new LojaEletronicos();
            equipamento.menuLojaEletronicos();

    }
}