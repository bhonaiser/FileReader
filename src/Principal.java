import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Principal {


    public static void main(String args[]) throws IOException {

        List<Vendedor> vendedores;
        List<Venda> vendas;
        List<Cliente> cLientes;

        // Diretórios de entrada e saída
        String dirPathIn = System.getProperty("user.home") + "\\data\\in";
        String dirPathOut = System.getProperty("user.home") + "\\data\\out";

        //////////////////////////////////////////////////////////////////////////////////////////////////
        // Verifica se o diretório de entrada existe , caso não exista : cria;
        //
        Path dirPathInObj = Paths.get(dirPathIn);
        if(!Files.exists(dirPathInObj)) {
            try {
                // Creating The New Directory Structure
                Files.createDirectories(dirPathInObj);
            }catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////
        // Verifica se o diretório de saída existe , caso não exista : cria;
        //
        Path dirPathOutObj = Paths.get(dirPathOut);
        if(!Files.exists(dirPathOutObj)) {
            try {
                // Creating The New Directory Structure
                Files.createDirectories(dirPathOutObj);
            }catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //Verifica constantemente o diretório de entrada em busca de novos arquivos
        boolean scanForNewFiles = true;
        while (scanForNewFiles) {
            File filesList[] = dirPathInObj.toFile().listFiles();

            if (filesList.length > 0) {
                for (File file : filesList) {

                    List<String> result;
                    try (Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()))) {

                        result = lines.collect(Collectors.toList());
                        System.out.println(result );


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(file.delete());
                }
            }
        }
    }

    private void trataStringVendor(String vendedor){

    }
}












