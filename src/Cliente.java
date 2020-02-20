/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Julianny Meireles
 */
public class Cliente {
    public int id;
    public int demanda; 
    public boolean IsRouted;

    Cliente(int id, int demanda)
    {
        this.id = id;
        this.demanda = demanda;
        this.IsRouted = false;
    }

    
}
