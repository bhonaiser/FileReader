import java.util.ArrayList;

public class Venda {
    private final String cod = "003";
    private int quantidade;
    private Vendedor vendor;
    private ArrayList<String> dadosVenda;


    public Venda() {
    }

    public Venda(int quantidade, Vendedor vendor, ArrayList<String> dadosVenda) {
        this.quantidade = quantidade;
        this.vendor = vendor;
        this.dadosVenda = dadosVenda;
    }

    public String getCod() {
        return cod;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Vendedor getVendor() {
        return vendor;
    }

    public void setVendor(Vendedor vendor) {
        this.vendor = vendor;
    }

    public ArrayList<String> getDadosVenda() {
        return dadosVenda;
    }

    public void setDadosVenda(ArrayList<String> dadosVenda) {
        this.dadosVenda = dadosVenda;
    }
}
