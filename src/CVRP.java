


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Julianny Meireles
 */
public class CVRP {
    static Cliente []clienteslista;
    static int [][] coord;
    static int numClientes;
    static int custo;
    
    LerInstancias instancias = new LerInstancias();
    
  
    static Rota rota;   
    static Veiculo veiculo;
     
    static void cvrp(){
    
        
        coord = LerInstancias.coordenadas;
        numClientes = LerInstancias.dimensao;
        clienteslista = LerInstancias.listaClientes; 
       
     }
    
    
    public static void main(String[] args) throws IOException{
        
      LerInstancias ler = new LerInstancias();
       ler.lerValores("P-n16-k8.txt");
    
       
       cvrp();
       Guloso();
       print();
       
      
    }
    
   
    
    static void Guloso(){
       
        Cliente deposito = clienteslista[0];
        Cliente candidato;
        Cliente c;
        
        int distCandidato;
        int menorDistancia = Integer.MAX_VALUE;
        int posicaoVizinhoProx = -1; //posição do vizinho mais proximo
        
        
        int semRota = numClientes; //quantidade de clientes que estão fora de rota
        int demandaAtual;
        coord = LerInstancias.coordenadas;
       
        
        //Dizer que todos os clientes da lista não estão em rotas
		for (int i = 0 ; i < clienteslista.length; i++)
		{
			clienteslista[i].IsRouted = false;
		}
                
         
         rota = new Rota(); //cria uma rota
         veiculo = new Veiculo(); //cria um novo veiculo
         
         ArrayList <Cliente> listaClientesNaRota = rota.clientesNaRota;
         listaClientesNaRota.add(deposito); //adiciona o depósito na lista de clientes em rota
        
        
        //enquanto houver clientes sem rota
        while(semRota != 0){
        
          
                        //pega o último cliente que está na lista de clientes que estão na rota
                       Cliente ultimoNaRota =  listaClientesNaRota.get(listaClientesNaRota.size()-1);
                       
                       //Identifica quem é o vizinho mais próximo fora de rota
                        for ( int j = 0 ; j < clienteslista.length ; j++){
                       
                            //O candidato é o cliente que pode vir a entrar na rota
                            candidato = clienteslista[j];
                            //System.out.println("candidato: ID"+clienteslista[j].id);
                           
                            //Se o candidato não tiver entrado na rota de nenhum veículo
                            if(candidato.IsRouted == false){
                      
                            // A distância entre o cliente atual na rota e o candidato    
                            distCandidato = coord[ultimoNaRota.id][candidato.id];
                              
                            //System.out.println("coord x:"+ultimoNaRota.id+ "y"+candidato.id);
                               
                                //Se a distância do candidato for menor que a MENORDISTANCIA atual
                                if (menorDistancia > distCandidato && distCandidato != 0) {
                                
                                //guarda a posicao do vizinho mais proximo
                                posicaoVizinhoProx = j;
                                //a menor distância vira a distancia do candidato
                                menorDistancia =  distCandidato;
                                
                           
                     
                           }
                }
                       
                        } 
                     
                       
                        demandaAtual = 0;
                    
                   // for que incrementa a demanda com a nova demanda do cliente que foi adicionado por último
                   for (int a = 0; a < listaClientesNaRota.size(); a++) {
			demandaAtual = demandaAtual+listaClientesNaRota.get(a).demanda;
                    }
                    
                                              
                    //Se a demanda atual não passar a capacidade
                    if(clienteslista[posicaoVizinhoProx].demanda+demandaAtual <= LerInstancias.capacidade){
                        //adiciona o cliente mais proximo na lista de clientes que estão na rota
                        c = clienteslista[posicaoVizinhoProx];                      
                        listaClientesNaRota.add(c);
                        //diz que ele ja está em rota
                        c.IsRouted = true;
                        semRota = semRota-1;//decrementa o total de clientes sem rota
                       
                        
                    }else{//Se a demanda atual ultrapassar a capacidade, encerra a rota
                        
                        
                        listaClientesNaRota.add(deposito); //adiciona o depósito no final da rota
                        
                        veiculo.listaRotas.add(rota); //adiciona a rota concluída no veículo atual
                        //inicializa uma nova rota e adiciona o deposito no seu início
                        rota = new Rota();
                        listaClientesNaRota = rota.clientesNaRota;
                        listaClientesNaRota.add(deposito);
                    }
//brecar}
                    //Se acabar a quantidade de clientes que ficou sem rota 
                    if(semRota == 0){
                        //pega o ultimo cliente da rota atual e guarda
                        ultimoNaRota = listaClientesNaRota.get(listaClientesNaRota.size()-1);
                        listaClientesNaRota.add(deposito);//adiciona o depósito no final
                        veiculo.listaRotas.add(rota); //adiciona a rota no veículo
                        
                    }
                    
                    
                    
             
           
        }
            
    
 

        
            
        
    }

static void print() {
       
   int DemandaRota;

		System.out.println("Número de Veículos usados: "+veiculo.listaRotas.size());
	
		for(int l = 0; l < veiculo.listaRotas.size(); l++){
		
			System.out.println("Veiculo "+(l+1)+" ");
			
			DemandaRota=0;
			for(int k = 0; k<veiculo.listaRotas.get(l).clientesNaRota.size(); k++) 
			{
			if (k == 0)
			System.out.print("Sequência de clientes:"+veiculo.listaRotas.get(l).clientesNaRota.get(k).id);
			else// ids dos clientes de cada rota
			System.out.print("->"+veiculo.listaRotas.get(l).clientesNaRota.get(k).id);
			DemandaRota = DemandaRota+veiculo.listaRotas.get(l).clientesNaRota.get(k).demanda;
			}
			System.out.println("");
			System.out.println("Demanda da Rota:"+DemandaRota);
		}
   
    
    
    
    
    
    
    
    }

static class Veiculo 
{
	
	ArrayList<Rota> listaRotas;
        
	
	Veiculo ()
	{
		listaRotas = new ArrayList<Rota>();
		
	}
}

static class Rota 
{
	ArrayList <Cliente> clientesNaRota;
	
	Rota() 
	{
		// A new arraylist of nodes is created
		clientesNaRota = new ArrayList<Cliente>();
	}



}



}