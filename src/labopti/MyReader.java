/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labopti;

import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class MyReader extends Reader{
    int numVehiculos;
    int numNodos;
    
    ArrayList<Requerimiento> LReq;
    ArrayList<Camion> Vehiculos;
    
    MyReader(String path) throws Exception{
        super(path);
    }
    
    MyReader() throws Exception{
        super();
    }
    
    public GrafoTabla AnalisarArchivo(){
        GrafoTabla Grafo = new GrafoTabla();
        ArrayList<Requerimiento> LReqAux = new ArrayList();
        ArrayList<Camion> LCamAux = new ArrayList();
        String Line = this.readLine();
        Line = Line.replaceAll("	", " ");
        Line = Line.replaceAll("  ", " ");
        System.out.println(Line);
        String[] auxS = Line.split(" ");
        if(auxS.length ==2){
            numVehiculos = Integer.parseInt(auxS[0]);
            numNodos = Integer.parseInt(auxS[1]);
        }
        else{
            ErrorMessenger.ShowError(0);
        }
        int posicion = 0;
        while(this.HasNextLine()){//Nodo 0 = nodo central.
            Line = this.readLine();
            Line = Line.replaceAll("	", " ");
            Line = Line.replaceAll("  ", " ");
            System.out.println(Line);
            auxS = Line.split(" ");
            if(auxS.length == 2){//Caso de linea con 2 valores.
                MyNodo auxNodo = new MyNodo(Integer.parseInt(auxS[0]), Integer.parseInt(auxS[1])); //crear nodo.
                auxNodo.setPosicion(posicion);
                Grafo.addNodo(auxNodo); //agregar nodo.
                posicion++;
            }
            else{
                if(auxS.length == 6){//caso de linea con 6 valores.
                    //obtener valores
                    int a = Integer.parseInt(auxS[0]);
                    int b = Integer.parseInt(auxS[1]);
                    int c = Integer.parseInt(auxS[2]);
                    int d = Integer.parseInt(auxS[3]);
                    int e = Integer.parseInt(auxS[4]);
                    int f = Integer.parseInt(auxS[5]);
                    Requerimiento auxC = new Requerimiento(Grafo.getArray().get(a),Grafo.getArray().get(b),c,d,e,f);//crear cliente
                    LReqAux.add(auxC);//agregar cliente.
                    Grafo.addNodoCarga(Grafo.getArray().get(a));
                    Grafo.addNodoDestino(Grafo.getArray().get(b));
                }
                else{
                    ErrorMessenger.ShowError(0);
                }
            }
        }
        Grafo.interconectar();
        this.LReq=LReqAux;
        
        for(int i=0;i<numVehiculos;i++){
            Camion camion_aux = new Camion(i);
            LCamAux.add(camion_aux);
        }
        this.Vehiculos=LCamAux;
        
        return Grafo;
    }
}
