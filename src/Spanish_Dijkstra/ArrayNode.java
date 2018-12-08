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
 * Cada una de las listas que contienen los indices de los nodos a llegar, son a su vez nodos, y por eso cargan los datos
 * La data no la carga el arreglo, sino la lista modificada. Es como la pieza q se acopla, la data no esta en la base sino en el acople
 */
public class ArrayNode<T> extends ArrayList{
    
    Object data;
    public ArrayNode(){
        data = new Object();
    }
    public void agregarData(Object o){
        this.data = o;
    }
}
