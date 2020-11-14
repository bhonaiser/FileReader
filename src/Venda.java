

public class Venda {
    private final  String cod = "003";
    private int    idDaVenda;
    private String dadosVenda;
    private String nomeVendedor;

    private int   idDoProduto;
    private int   qntdProduto;
    private float precoProduto;
    private float valorTotalDaVenda;

    public Venda() {
    }

    public Venda(int id, String dadosVenda, String nomeVendedor) {
        this.idDaVenda = id;
        this.setDadosVenda(dadosVenda);
        this.nomeVendedor = nomeVendedor;
    }

    public void setDadosVenda(String dadosVenda) {

        //this.dadosVenda = dadosVenda;
        dadosVenda = dadosVenda.replaceAll("\\[", "");
        dadosVenda = dadosVenda.replaceAll("]", "");
        String[] aux = dadosVenda.split(",");

        if (aux.length > 0) {
            String[] aux2 = null;
            for (int i = 0; i < aux.length; i++)
                aux2 = aux[i].split("-");

            this.idDoProduto = Integer.valueOf(aux2[0]);
            this.qntdProduto = Integer.valueOf(aux2[1]);
            this.precoProduto = Float.valueOf(aux2[2]);
            this.valorTotalDaVenda = qntdProduto * precoProduto;
        }
    }

    public String getCod() {
        return cod;
    }

    public int getIdDoProduto() {
        return idDoProduto;
    }

    public void setIdDoProduto(int idDoProduto) {
        this.idDoProduto = idDoProduto;
    }

    public int getQntdProduto() {
        return qntdProduto;
    }

    public void setQntdProduto(int qntdProduto) {
        this.qntdProduto = qntdProduto;
    }

    public float getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(float precoProduto) {
        this.precoProduto = precoProduto;
    }

    public float getValorTotalDaVenda() {
        return valorTotalDaVenda;
    }

    public void setValorTotalDaVenda(float valorTotalDaVenda) {
        this.valorTotalDaVenda = valorTotalDaVenda;
    }

    public int getId() {
        return idDaVenda;
    }

    public void setId(int id) {
        this.idDaVenda = id;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }



}
