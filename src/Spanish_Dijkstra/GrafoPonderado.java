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
 * la lista aparte de los indices integer, debe contener un object. 
 * 
 * En esta implementacion se crea el grafo de una vez, y pueden haber nodos sueltos. Se crean nodos primero, luego se conectan con los arcos.
 * 
 * 
 */
public class GrafoPonderado {
        int numHijos;
        ArrayNode<Arcos>[] indice_de_nodos; // el indice numera a los nodos existentes y es igual al numero de nodos. EL arrayNode contiene data
        int numArcos;
            
    public GrafoPonderado(int numHijos){
        this.numHijos = numHijos;
        this.numArcos = 0;
        indice_de_nodos =  new ArrayNode[numHijos];
        for(int verticeVacio = 0; verticeVacio < numHijos; verticeVacio++ ){
                indice_de_nodos[verticeVacio] = new ArrayNode<>();
        }
    }
        private void validarNodo(int indice) {
        if (indice < 0 || indice >= numHijos)
            throw new IllegalArgumentException("vertex " + indice + " is not between 0 and " + (numHijos-1));
    }
        /*
            Agrega un arco, que tiene dos enteros que representan los dos vertices de su extremo
        */
        
        public void agregarArco(Arcos arco) { 
        int v = arco.either();
        int w = arco.other(v);            
        validarNodo(v);
        validarNodo(w);
        numArcos++;
        indice_de_nodos[v].add(arco);
        indice_de_nodos[w].add(arco);
    }
    /*
    // Iterable devuelve una estructura que se recorre... obvio
    */
        
     public Iterable<Integer> consultarAdyacentes(int v) { 
        validarNodo(v);
        return indice_de_nodos[v];
    }
    /*
        tamaño de la lista asociada al nodo
     */
    public int grado(int v) {
        validarNodo(v); 
        return indice_de_nodos[v].size(); // la cantidad de arcos asociados a un nodo v. Que ladilla explicar esto :/
    }
    
    /*
        Esto Devuelve la lista que esta asociada al nodo.. 
    */
    public Iterable<Arcos>arcosAsociadosa( int vertice){
        validarNodo(vertice);
        return indice_de_nodos[vertice];
    }
    /*
    Esto devuelve el total de arcos del grafo, solo devuelvo un loop por nodo
    */
    
    public Iterable<Arcos>totalArcos(){
        ArrayNode<Arcos> listadeArcos = new ArrayNode<>();
        for(int vertice = 0; vertice < numHijos; vertice++){
            int ciclo = 0;
            for(Arcos arcoAsociado : arcosAsociadosa(vertice)){
                if(arcoAsociado.other(vertice) > vertice){
                    listadeArcos.add(arcoAsociado);
                }
                //Solo voy a agregar un loop por vertice
                else if(arcoAsociado.other(vertice) == vertice){
                    if(ciclo % 2 == 0) listadeArcos.add(arcoAsociado);
                    ciclo++;
                }
            }
        }
        return listadeArcos;
    }
    /*
    Una forma aparte de devolver la cantidad de vertices para mayor formalidad
    */
    
    public int V(){
        return numHijos;
    }
}
