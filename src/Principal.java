import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Principal {

    private static List<Vendedor> lst_vendedores;
    private static List<Venda> lst_vendas;
    private static List<Cliente> lst_cLientes;

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
        String[] output = null;

        while (scanForNewFiles) {
            File filesList[] = dirPathInObj.toFile().listFiles();
            if (filesList.length > 0) {
                for (File file : filesList) {
                    // read whole file as String in JDK 7
                    String data = "";
                    try {
                        data = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        output =  data.split("ç");
                        for(int i=0; i<output.length; i++){
                            System.out.println(output[i]);
                        }
                        System.out.print("FIM DE ARQUIVO");
                        System.out.print("FIM DE ARQUIVO");
                        System.out.println("FIM DE ARQUIVO");
                        file.delete();
                    }
                }
                scanForNewFiles = false;
            }
        }
        encaminhaArray(output);
       // System.out.println(lst_cLientes.toString());
    }

    private static void encaminhaArray(String[] texto){
        if(texto != null && texto.length >0){
            String[] aux;
            switch(texto[0]){
                case COD_VENDOR: trataArrayVendor(Arrays.copyOfRange(texto, 0, tamanhoArrayVendor -1));
                                 encaminhaArray(Arrays.copyOfRange(texto, tamanhoArrayVendor -1, texto.length - tamanhoArrayVendor));

                case COD_CLIENT: trataArrayClient(Arrays.copyOfRange(texto, 0, tamanhoArrayClient -1));
                                 encaminhaArray(Arrays.copyOfRange(texto, tamanhoArrayClient -1, texto.length - tamanhoArrayClient));

                case COD_VENDAS: trataArrayVendas(Arrays.copyOfRange(texto, 0, tamanhoArrayVendas -1));
                                 encaminhaArray(Arrays.copyOfRange(texto, tamanhoArrayVendas -1, texto.length - tamanhoArrayVendas));

            }
        }
    }


    private static void trataArrayVendor(String[] vendedor){
        System.out.println("Tratando Vendor");
        System.out.println(vendedor[1]);
        System.out.println(vendedor[2]);
        System.out.println(vendedor[3]);
        lst_vendedores.add(new Vendedor(vendedor[1], vendedor[2], Float.valueOf(vendedor[3])));
    }
    private static void trataArrayClient(String[] cliente){
        System.out.println("Tratando Cliente");
        lst_cLientes.add(new Cliente(cliente[1], cliente[2], cliente[3]));
    }

    private static void trataArrayVendas(String[] vendas){
        System.out.println("Tratando Vendas");
        //lst_vendas.add(new Venda(vendas[1], vendas[2], vendas[3]));
    }

}












