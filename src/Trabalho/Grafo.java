package Trabalho;

import java.io.*;
import java.util.StringTokenizer;

public class Grafo {
    private BufferedReader br;
    private StringTokenizer st;
    private int[][] matGrafo;
    private int[][] matCoor;
    private int numVertices;
    private int numArestas;
    private int linha;
    private int coluna;
    private int peso;
    private String direcionado;

    public void importar() {
        try {
            br = new BufferedReader(new FileReader("grafo.txt"));
            direcionado = br.readLine();
            numVertices = Integer.parseInt(br.readLine());

            matCoor = new int[numVertices][2];

            for (int i = 0; i < this.numVertices; i++) {
                st = new StringTokenizer(br.readLine());
                int indice = Integer.parseInt(st.nextToken());
                linha = Integer.parseInt(st.nextToken());
                coluna = Integer.parseInt(st.nextToken());
                matCoor[i][0] = linha;
                matCoor[i][1] = coluna;
            }

            /*for (int i = 0; i < this.numVertices; i++) {
                System.out.println(matCoor[i][0] + " " + matCoor[i][1]);
            }
            numArestas = Integer.parseInt(br.readLine());
            System.out.println(this.numArestas);*/

            if (verificaDirecionado()) {
                criaDirecionado();
            } else {
                criaNaoDirecionado();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void criaNaoDirecionado() {
        inicializaMatriz();
        try {
            for (int i = 0; i < numArestas; i++) {
                st = new StringTokenizer(br.readLine());
                linha = Integer.parseInt(st.nextToken());
                coluna = Integer.parseInt(st.nextToken());
                peso = Integer.parseInt(st.nextToken());
                matGrafo[linha][coluna] = peso;
                matGrafo[coluna][linha] = peso;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //testes
        for (int i = 0; i < numArestas; i++) {
            for (int j = 0; j < numArestas; j++) {
                if (matGrafo[i][j] != -1){
                    System.out.println(i + " " + j + " " + matGrafo[i][j]);
                }
            }
        }

        testeImprimir();
    }

    private void criaDirecionado() {
        inicializaMatriz();
        try {
            for (int i = 0; i < numArestas; i++) {
                st = new StringTokenizer(br.readLine());
                linha = Integer.parseInt(st.nextToken());
                coluna = Integer.parseInt(st.nextToken());
                peso = Integer.parseInt(st.nextToken());
                matGrafo[linha][coluna] = peso;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //testes
        for (int i = 0; i < numArestas; i++) {
            for (int j = 0; j < numArestas; j++) {
                if (matGrafo[i][j] != -1){
                    System.out.println(i + " " + j + " " + matGrafo[i][j]);
                }
            }
        }

        testeImprimir();
    }

    public void exportar() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("novoGrafo.txt"));
            bw.write(direcionado + "\n");
            bw.write(numVertices + "\n");
            for (int i = 0; i < numVertices; i++) {
                bw.write(i + " " + matCoor[i][0] + " " + matCoor[i][1] + "\n");
            }
            bw.write(numVertices + "\n");

            if (verificaDirecionado()) {
                for (int i = 0; i < numArestas; i++) {
                    for (int j = 0; j < numArestas; j++) {
                        if (matGrafo[i][j] != -1) {
                            bw.write(i + " " + j + " " + matGrafo[i][j] + "\n");
                        }
                    }
                }
            } else {
                for (int i = 0; i < numVertices; i++) {
                    for (int j = 0; j < numVertices; j++) {
                        if ((matGrafo[i][j] != -1) && (j > i)) { // comparando para salvar so uma diagonal
                            bw.write(i + " " + j + " " + matGrafo[i][j] + "\n");
                        }
                    }
                }
            }

            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void inicializaMatriz(){ //usar só se o peso puder ser zero
        matGrafo = new int[numArestas][numArestas];

        for (int i = 0; i < numArestas; i++) {
            for (int j = 0; j < numArestas; j++) {
                matGrafo[i][j] = -1;
            }
        }
    }
    private boolean verificaDirecionado() {
        return (direcionado.equals("direcionado=sim"));
    }

    private void testeImprimir() { // apagar depois pois é so para testar a matriz
        for (int i = 0; i < numArestas; i++) {
            System.out.print("   " + i + " ");
        }
        System.out.println();
        for (int i = 0; i < numArestas; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < numArestas; j++) {

                System.out.print(matGrafo[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
