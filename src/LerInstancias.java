
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    
    
    static int dimensao;
    
   
   static void lerValores(String file) throws FileNotFoundException, IOException{
         
        InputStream input;
        BufferedReader br;
        
        input = new FileInputStream(file);
        InputStreamReader in = new InputStreamReader(input);
        br = new BufferedReader(in);

        br.readLine();

        //Pega o valor do campo DIMENSION
        String dimension = br.readLine();
        dimension = dimension.replace("DIMENSION: ", "");
        dimensao = Integer.parseInt(dimension);

        //Pega o valor do campo CAPACITY
        String capacity = br.readLine();
        capacity = capacity.replace("CAPACITY: ", "");
        Veiculo.capacidade = Integer.parseInt(capacity);

       
        
        }
    
    
   public static void main(String[] args) throws IOException{
        
      
       lerValores("P-n20-k2.txt");
       
       System.out.println("dimensao: " + dimensao);
        System.out.println("capacidade: "+Veiculo.capacidade);
    }
}
