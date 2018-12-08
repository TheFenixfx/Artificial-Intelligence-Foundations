/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spanish_Dijkstra;

import java.util.ArrayList;

/**
 *
 * @author Fenix
 * numVertices se explica solo XD
 * adyacentes, es una lista q contiene 
 * Info: Recordando que esta representacion de Grafo, la estoy haciendo mediante una lista de adyacencia, osea que
 * puede tener distinto numero de hijos por nodo, y los "hijos" estan referidos como indices numericos que refieren a
 * el numero de fila de otro grafo. Cada nodo del arreglo, tiene una sola lista, por lo tanto, para guardar en cada posicion de 
 * la lista aparte de los indices integer, debe contener un object. Osea un modificar la lista a ArrayNode
 * 
 * En esta implementacion se crea el grafo de una vez, y pueden haber nodos sueltos. Se crean nodos primero, luego se conectan con los arcos.
 */
public class Grafo {
        int numHijos;
        ArrayNode<Integer>[] indice_de_nodos; // el indice numera a los nodos existentes y es igual al numero de nodos. EL arrayNode contiene data
        int numArcos;
            
    public Grafo(int numHijos){
        this.numHijos = numHijos;
        this.numArcos = 0;
        indice_de_nodos =  new ArrayNode[numHijos];
        for(int verticeVacio = 0; verticeVacio < numHijos; verticeVacio++ ){
                indice_de_nodos[numHijos] = new ArrayNode<>();
        }
    }
        private void validarNodo(int indice) {
        if (indice < 0 || indice >= numHijos)
            throw new IllegalArgumentException("vertex " + indice + " is not between 0 and " + (numHijos-1));
    }
        public void agregarArco(int v, int w) {
        validarNodo(v);
        validarNodo(w);
        numArcos++;
        indice_de_nodos[v].add(w);
        indice_de_nodos[w].add(v);
    }
    
     public Iterable<Integer> consultarAdyacentes(int v) {
        validarNodo(v);
        return indice_de_nodos[v];
    }
    
    public int degree(int v) {
        validarNodo(v);
        return indice_de_nodos[v].size();
    }
    
    public int numArcos(){
        return numArcos;
    }
    
}
