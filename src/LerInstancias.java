
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Julianny Meireles
 */


public class LerInstancias {
    
    
    static int [] clientes; 
    static int dimensao;
    static int  id;
    static int demanda;
    static int [][] coordenadas;
    static Cliente [] listaClientes;
    static Cliente C;
    static int capacidade;
   static void lerValores(String file) throws FileNotFoundException, IOException{
         
        InputStream input;
        BufferedReader br;
        
        input = new FileInputStream(file);
        InputStreamReader in = new InputStreamReader(input);
        br = new BufferedReader(in);
        
        //pula a primeira linha do arquivo com o "NAME"
        br.readLine();

        //Pega o valor do campo DIMENSION
        String dimension = br.readLine();
        dimension = dimension.replace("DIMENSION: ", "");
        dimensao = Integer.parseInt(dimension);

        //Pega o valor do campo CAPACITY
        String capacity = br.readLine();
        capacity = capacity.replace("CAPACITY: ", "");
        capacidade = Integer.parseInt(capacity);
        
        //Pega os valores do DEMAND_SECTION e os distribui num array de clientes
        //O "DEMAND_SECTION" são duas colunas que contém o "id" dos clientes e suas demandas 
        
        br.readLine(); //Pula a linha com o nome "DEMAND_SECTION:/n"
        //clientes_demanda = new int [dimensao][2];
        
        listaClientes = new Cliente [dimensao];
        
        for(int i = 0; i < dimensao; i++){
            String demand = br.readLine();
            Scanner ler = null;
            ler = new Scanner(demand);

            id = ler.nextInt();
            demanda = ler.nextInt();

            C = new Cliente (id, demanda);
            listaClientes[i] = C; 
            ler.close(); 
        }
       
        br.readLine(); //pula a linha do espaço em branco
        br.readLine(); //pula a linha com o nome EDGE_WEIGHT_SECTION
        
        
        //Pega os valores da matriz EDGE_WEIGHT_SECTION e coloca em "coordenadas"
        coordenadas = new int [dimensao][dimensao];
        int a;
        for(int i=0; i<dimensao; i++) {

            String coord = br.readLine();
            Scanner ler = new Scanner(coord);
            
                for(int j=0; j<dimensao; j++) {
                        a = ler.nextInt();
                        coordenadas[i][j] = a;

                }
            ler.close();

            }
          
        }
    
    
   /*public static void main(String[] args) throws IOException{
        
      
       lerValores("P-n20-k2.txt");
       
       //System.out.println("dimensao: " + dimensao);
       //System.out.println("capacidade: "+Veiculo.capacidade);
       //System.out.println("clientes" + Arrays.toString(clientes[10]));
       for(int i=0; i<dimensao; i++){
       System.out.println("Cliente: "+ listaClientes[i].id + "Demanda: " + listaClientes[i].demanda);
       }
    }*/
  
}
