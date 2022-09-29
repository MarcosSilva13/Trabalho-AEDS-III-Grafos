package Trabalho;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Grafo {
    private BufferedReader br;
    private StringTokenizer st;
    private int[][] matGrafo;
    private int[][] matCoor;
    private int numVertices, numArestas;
    private String direcionado;

    public void importar() {
        try {
            br = new BufferedReader(new FileReader("grafo.txt"));
            this.direcionado = br.readLine();
            this.numVertices = Integer.parseInt(br.readLine());

            this.matCoor = new int[this.numVertices][2];

            for (int i = 0; i < this.numVertices; i++) {
                st = new StringTokenizer(br.readLine());
                int indice = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                //System.out.println(indice + " " + l + " " + c);
                matCoor[i][0] = l;
                matCoor[i][1] = c;
            }

            /*for (int i = 0; i < this.numVertice; i++) {
                teste = br.readLine().split(" ");
                matCoor[i][0] = Integer.parseInt(teste[1]);
                matCoor[i][1] = Integer.parseInt(teste[2]);
            }*/

            for (int i = 0; i < this.numVertices; i++) {
                System.out.println(matCoor[i][0] + " " + matCoor[i][1]);
            }

            this.numArestas =
            /*if (this.direcionado.equals("direcinado=sim")){
                System.out.println("Sim");
            } else {
                System.out.println("Não");
            }*/

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



}
