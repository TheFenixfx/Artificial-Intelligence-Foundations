/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spanish_Dijkstra;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * @author Fenix
 * Version no dirigida, llevo los extremos con enteros
 */
public class Dijkstra {
    
    PriorityQueue colaPrioridad; 
    public  double[] distanciaHacia; 
    public Arcos[] historialRutas; 
    private int indiceFuente; 
    
    public Dijkstra(GrafoPonderado G,int indiceFuente) {
           distanciaHacia = new double[G.V()];
           historialRutas = new Arcos[G.V()];
           this.indiceFuente = indiceFuente;
           validarIndicesVertice(indiceFuente);
           chequearTodosVerticesPositivos(G);
           establecerTodaslasDistaciasinfinitas(G.V());
           
           distanciaHacia[indiceFuente] = 0.0; // distancia inicial al vertice arranque
           colaPrioridad = new PriorityQueue<VerticeyDistancia>(G.V());
           VerticeyDistancia verticeArranque = new VerticeyDistancia(indiceFuente,distanciaHacia[indiceFuente]); 
           colaPrioridad.add(verticeArranque);
        while(!colaPrioridad.isEmpty()){
            VerticeyDistancia este = (VerticeyDistancia)colaPrioridad.poll(); 
            for( Arcos arcoAsociado : G.arcosAsociadosa(este.vertice)){ 
                int elOtroExtremo = arcoAsociado.other(este.vertice); 
                
                if(distanciaHacia[elOtroExtremo] > distanciaHacia[este.vertice] + arcoAsociado.weight()){
                    distanciaHacia[elOtroExtremo] = distanciaHacia[este.vertice] + arcoAsociado.weight();
                    historialRutas[elOtroExtremo] = arcoAsociado;   
                    VerticeyDistancia t = new VerticeyDistancia(elOtroExtremo, distanciaHacia[elOtroExtremo]);
                    //si ya lo contiene pero
                    if(colaPrioridad.contains(t)){
                        colaPrioridad.remove(t);
                        colaPrioridad.add(t); 
                    }
                    else{ 
                        colaPrioridad.add(t);
                    }
                }
            }
        }
    }
     
       private void validarIndicesVertice(int v) {
        int V = distanciaHacia.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertice " + v + " no esta entre 0 y " + (V-1));
    }
       
      private void chequearTodosVerticesPositivos(GrafoPonderado G){
            for (Arcos e : G.totalArcos()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("arco " + e + " tiene valor negativo");
            }
      }
      
      private void establecerTodaslasDistaciasinfinitas(int sizeofGrafo){
              for(int v = 0; v < sizeofGrafo;v++){
               distanciaHacia[v] = Double.POSITIVE_INFINITY;
            }
      }
       
       /*
       Buscando el final, se van poniendo todos los predecesores hasta llegar al inicio
       */
       public Iterable<Arcos> caminoOptimoA(int v){
           validarIndicesVertice(v);
           if(!caminoPosible(v)) return null;
           Stack<Arcos> camino = new Stack<Arcos>();
           int temp = v;
           for(Arcos arco = historialRutas[v];arco != null; arco = historialRutas[temp]){
               camino.push(arco);
               temp = arco.other(temp);
           }
           return camino;
       }
       
       public boolean caminoPosible(int v){
           validarIndicesVertice(v);
           return distanciaHacia[v] < Double.POSITIVE_INFINITY; 
           //si ya se visito la distancia deberia ser menor a el infinito y ademas ser optima
       }
       
       public void imprimirTodaslasDistancias(){
                for(int  i=0;i<distanciaHacia.length;i++){
                  if(this.caminoPosible(i) && i!= indiceFuente){
                      System.out.println("desde : "+indiceFuente+" hasta "+i+" la distancia optima es : "+(int)this.distanciaHacia[i]); 
                      if((int)this.distanciaHacia[i] !=0){
                        System.out.println("Y la ruta es : ");
                      }
                      for(Arcos e : this.caminoOptimoA(i)){
                          System.out.println(e);
                      }
                      System.out.println();
                  }
                  if(!this.caminoPosible(i)) {
                      System.out.println("no hay camino");
                  }
              }
       }
       
       public void hasta(int v){
            for(int  i=0;i<distanciaHacia.length;i++){
                  if(this.caminoPosible(i) && i== v){
                      System.out.println("desde : "+indiceFuente+" hasta "+i+" la distancia optima es : "+(int)this.distanciaHacia[i]); 
                      if((int)this.distanciaHacia[i] !=0){
                        System.out.println("Y la ruta es : ");
                      }
                      for(Arcos e : this.caminoOptimoA(i)){
                          System.out.println(e);
                      }
                      System.out.println();
                  }
                  if(!this.caminoPosible(i)) {
                      System.out.println("no hay camino");
                  }
              }
       }
       
           public static void main(String[] args) {
               
              GrafoPonderado grafoTest = new GrafoPonderado(8);
              Arcos arco = new Arcos(0, 1, 5); 
              grafoTest.agregarArco(arco);
              arco = new Arcos(0, 4, 12);
              grafoTest.agregarArco(arco);
              arco = new Arcos(0, 3, 7);
              grafoTest.agregarArco(arco);
              arco = new Arcos(1, 2, 8);
              grafoTest.agregarArco(arco);
              arco = new Arcos(1, 6, 4);
              grafoTest.agregarArco(arco);
              arco = new Arcos(1, 7, 3);
              grafoTest.agregarArco(arco);
              arco = new Arcos(2, 5, 2);
              grafoTest.agregarArco(arco);
              arco = new Arcos(3, 7, 3);
              grafoTest.agregarArco(arco);
              arco = new Arcos(4, 5, 1);
              grafoTest.agregarArco(arco);
              arco = new Arcos(5, 6, 6);
              grafoTest.agregarArco(arco);
        
              Dijkstra buscador = new Dijkstra(grafoTest, 0);
            //  buscador.imprimirTodaslasDistancias();
              buscador.hasta(3);
           }
}
    
    
    

