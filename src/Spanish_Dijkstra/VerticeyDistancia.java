/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spanish_Dijkstra;

import java.util.Objects;
/**
 *
 * @author Fenix
 * Esta clase implementa un comparador para usar la libreria GENERICA priorityQueue
 */
public class VerticeyDistancia implements Comparable <VerticeyDistancia>{
    
    public int vertice;
    private double distancia;

    public VerticeyDistancia(int vertice,double nuevaDistancia ) {
        this.vertice = vertice;
        this.distancia = nuevaDistancia;    
    }
    
    public void agregarVertice(int vertice,double distancia){
        this.vertice = vertice;
        this.distancia = distancia;
    }

    @Override
    public int compareTo(VerticeyDistancia another) {
        
        if(this.distancia > another.distancia) {
            return 1;
        } else if (this.distancia < another.distancia) {
            return -1;
        } else {
            return 0;
        }
    }
    @Override
    public boolean equals(Object o){
        if (o == null || getClass() != o.getClass()) return false;
        VerticeyDistancia temp = (VerticeyDistancia)o;
        return this.vertice == temp.vertice;
    }
    
 
   
}
