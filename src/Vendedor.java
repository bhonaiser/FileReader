import java.util.Objects;

public class Vendedor {

    private final String cod = "001";
    private String name;
    private String cpf;
    private Float salary;

    public Vendedor() {
    }

    public Vendedor(String cpf, String name, Float salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendedor vendedor = (Vendedor) o;
        return cpf.equals(vendedor.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    public String getCod() {
        return cod;
    }

    public String getName() {
        return name;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
