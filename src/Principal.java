import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Principal {

    private static List<Vendedor> lst_vendedores = new ArrayList<Vendedor>();
    private static List<Venda> lst_vendas = new ArrayList<Venda>();
    ;
    private static List<Cliente> lst_cLientes = new ArrayList<Cliente>();

    private static final int tamanhoArrayVendor = 4;
    private static final int tamanhoArrayClient = 4;
    private static final int tamanhoArrayVendas = 4;

    private static final String COD_VENDOR = "001";
    private static final String COD_CLIENT = "002";
    private static final String COD_VENDAS = "003";


    public static void main(String args[]) throws IOException {

        // Diretórios de entrada e saída
        String dirPathIn = System.getProperty("user.home") + "\\data\\in";
        String dirPathOut = System.getProperty("user.home") + "\\data\\out";

        //////////////////////////////////////////////////////////////////////////////////////////////////
        // Verifica se o diretório de entrada existe , caso não exista : cria;
        //
        Path dirPathInObj = Paths.get(dirPathIn);
        if (!Files.exists(dirPathInObj)) {
            try {
                // Creating The New Directory Structure
                Files.createDirectories(dirPathInObj);
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////
        // Verifica se o diretório de saída existe , caso não exista : cria;
        //
        Path dirPathOutObj = Paths.get(dirPathOut);
        if (!Files.exists(dirPathOutObj)) {
            try {
                // Creating The New Directory Structure
                Files.createDirectories(dirPathOutObj);
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //Verifica constantemente o diretório de entrada em busca de novos arquivos
        boolean scanForNewFiles = true;
        String output = "";
        String[] aux = null;
        String strAllFileBytes = "";

        while (scanForNewFiles) {
            File filesList[] = dirPathInObj.toFile().listFiles();
            if (filesList.length > 0) {
                for (File file : filesList) {
                    // read whole file as String in JDK 7
                    try {
                        strAllFileBytes = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                        strAllFileBytes = strAllFileBytes.replaceAll("\\r\\n|\\n", "ç");
                        //System.out.println("STRING DEPOIS DE SER RETIRADA AS QUBRAS DE LINHA !!!!!!!!!!!!!!!!!!!!");
                        //System.out.println(strAllFileBytes);

                        strAllFileBytes = strAllFileBytes.replaceAll("\\s", "");
                        //System.out.println("Agora sem espaços em branco!!!!!!!!!!!!!");
                        //System.out.println(strAllFileBytes);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        //System.out.println(output);
                        output = output + strAllFileBytes;
                        //file.delete();
                    }
                }
            }
            scanForNewFiles = false;
        }
        //System.out.println(output);

        aux = output.split("ç");
        encaminhaArray(aux, 0);



        System.out.println("\nTotal de Clientes   : " + totalDeClientes());
        System.out.println("Total de Vendedores   : " + totalDeVendedores());
        System.out.println("Id da venda mais cara : " + idVendaMaisCara());
        System.out.println("Pior vendedor  : " +  piorVendedor());

        try {
            FileWriter myWriter = new FileWriter(dirPathOut + "\\saida.txt");
            myWriter.write("Total de Clientes   : " + totalDeClientes() + "\n");
            myWriter.write("Total de Vendedores   : " + totalDeVendedores() + "\n");
            myWriter.write("Id da venda mais cara : " + idVendaMaisCara() + "\n");
            myWriter.write("Pior vendedor  : " +  piorVendedor() + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



    }

    private static void encaminhaArray(String[] texto, int index) {
        if ((texto != null) && (index < texto.length - 1)) {
            switch (texto[index]) {
                case COD_VENDOR:
                    trataArrayVendor(texto, index + 1);
                    encaminhaArray(texto, index + tamanhoArrayVendor);
                    break;

                case COD_VENDAS:
                    trataArrayVendas(texto, index + 1);
                    encaminhaArray(texto, index + tamanhoArrayVendas);
                    break;

                case COD_CLIENT:
                    trataArrayClient(texto, index + 1);
                    encaminhaArray(texto, index + tamanhoArrayClient);
                    break;
            }
        }
    }

    private static void trataArrayVendor(String[] vendedor, int index) {
        System.out.println("Tratando Vendor");
        Vendedor vendedor1 = new Vendedor(vendedor[index], vendedor[index + 1], Float.valueOf(vendedor[index + 2]));
        if (!lst_vendedores.contains(vendedor1))
            lst_vendedores.add(vendedor1);
        vendedor1 = null;
    }

    private static void trataArrayClient(String[] cliente, int index) {
        System.out.println("Tratando Cliente");
        Cliente cliente1 = new Cliente(cliente[index], cliente[index + 1], cliente[index + 2]);
        if (!lst_cLientes.contains(cliente1))
            lst_cLientes.add(cliente1);
        cliente1 = null;
    }

    private static void trataArrayVendas(String[] vendas, int index) {
        System.out.println("Tratando Vendas");
        Venda venda1 = new Venda(Integer.valueOf(vendas[index]), vendas[index + 1], vendas[index + 2]);
        if (!lst_vendas.contains(venda1))
            lst_vendas.add(venda1);
        venda1 = null;
    }

    private static int totalDeClientes() {
        if (lst_cLientes != null && lst_cLientes.size() > 0)
            return lst_cLientes.size();
        else
            return 0;
    }

    private static int totalDeVendedores() {
        if (lst_vendedores != null && lst_vendedores.size() > 0)
            return lst_vendedores.size();
        else
            return 0;
    }

    private static int idVendaMaisCara() {
        if (lst_vendas != null && lst_vendas.size() > 0) {
            int id_da_venda_mais_cara = 0;
            float valor_venda_mais_cara = 0;
            for (int i = 0; i < lst_vendas.size(); i++) {
                float valorTotalVenda = lst_vendas.get(i).getValorTotalDaVenda();
                if (valorTotalVenda > valor_venda_mais_cara) {
                    valor_venda_mais_cara = valorTotalVenda;
                    id_da_venda_mais_cara = lst_vendas.get(i).getId();
                }
            }
            return id_da_venda_mais_cara;
        } else
            return 0;
    }

    private static String piorVendedor(){
        Map<String , Float> mapaVendorsValorVenda = new HashMap<String , Float>();
        if ((lst_vendedores != null && lst_vendedores.size() > 0) && (lst_vendas.size() > 0)) {
            for (int i = 0; i < lst_vendas.size(); i++) {
                float valorTotalVenda = lst_vendas.get(i).getValorTotalDaVenda();
                String vendorsName = lst_vendas.get(i).getNomeVendedor();

                //vendor já na lista, soma-se valor de suas vendas;
                if(mapaVendorsValorVenda.containsKey(vendorsName))
                    mapaVendorsValorVenda.put(vendorsName, valorTotalVenda + mapaVendorsValorVenda.get(vendorsName));
                //vendor sendo add for the 1st time
                else
                    mapaVendorsValorVenda.put(vendorsName, valorTotalVenda);
                }


            String pior_vendor = mapaVendorsValorVenda.entrySet().stream()
                    .min(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElseThrow(RuntimeException::new);
            return pior_vendor;


        } else
            return "Nenhuma venda realizada até o momento";
    }

}













