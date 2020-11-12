import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Principal {

    private static List<Vendedor> lst_vendedores = new ArrayList<Vendedor>();
    private static List<Venda> lst_vendas = new ArrayList<Venda>();;
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

                        strAllFileBytes = strAllFileBytes.replaceAll(" ", "");
                       // System.out.println("Agora sem espaços em branco!!!!!!!!!!!!!");
                        //System.out.println(strAllFileBytes);

                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        //System.out.println(output);
                        output = output + strAllFileBytes;
                        file.delete();
                        }
                    }
                }
                scanForNewFiles = false;
            }
        //System.out.println(output);

        aux = output.split("ç");
        for(int i=0; i<aux.length;i++){
            System.out.print(aux[i] + "\n");
        }
        encaminhaArray(aux);
    }

    private static void encaminhaArray(String[] texto){
        if(texto != null && texto.length >0){
            int index = 0;
            switch(texto[index]){
                case COD_VENDOR: trataArrayVendor(Arrays.copyOfRange(texto, index, tamanhoArrayVendor));
                                 encaminhaArray(Arrays.copyOfRange(texto, tamanhoArrayVendor, texto.length - tamanhoArrayVendor));

                case COD_CLIENT: trataArrayClient(Arrays.copyOfRange(texto, index, tamanhoArrayClient));
                                 encaminhaArray(Arrays.copyOfRange(texto, tamanhoArrayClient, texto.length - tamanhoArrayClient));

                case COD_VENDAS: trataArrayVendas(Arrays.copyOfRange(texto, index, tamanhoArrayVendas ));
                                 encaminhaArray(Arrays.copyOfRange(texto, tamanhoArrayVendas, texto.length - tamanhoArrayVendas));

            }
        }
    }


    private static void trataArrayVendor(String[] vendedor){
        System.out.println("\n Tratando Vendor");
        System.out.println(vendedor[1]);
        System.out.println(vendedor[2]);
        System.out.println(vendedor[3]);
        lst_vendedores.add(new Vendedor(vendedor[1], vendedor[2], Float.valueOf(vendedor[3])));
    }
    private static void trataArrayClient(String[] cliente){
        System.out.println("Tratando Cliente");
        System.out.println(cliente[1]);
        System.out.println(cliente[2]);
        System.out.println(cliente[3]);
        lst_cLientes.add(new Cliente(cliente[1], cliente[2], cliente[3]));
    }

    private static void trataArrayVendas(String[] vendas){
        System.out.println("Tratando Vendas");
        System.out.println(vendas[1]);
        System.out.println(vendas[2]);
        System.out.println(vendas[3]);
        //lst_vendas.add(new Venda(vendas[1], vendas[2], vendas[3]));
    }

}












