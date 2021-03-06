import java.util.Objects;

public class Cliente {
    private final String cod = "002";
    private String cnpj;
    private String nome;
    private String businessArea;

    public Cliente() {
    }

    public Cliente(String cnpj, String nome, String businessArea) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.businessArea = businessArea;
    }


    public String getCod() {
        return cod;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cnpj.equals(cliente.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }



}

