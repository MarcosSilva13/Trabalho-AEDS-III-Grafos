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
            numArestas = Integer.parseInt(br.readLine());

            if (verificaDirecionado()) {
                criaDirecionado();
            } else {
                criaNaoDirecionado();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void inserirAresta(int origem, int destino, int p) {
        if (p >= 0) { //mudar para > 0 caso não possa add peso zero
            if (origem < 0 || origem >= numVertices || destino < 0 || destino >= numVertices) {
                System.out.println("Posição inválida!\n");
            } else if (matGrafo[origem][destino] != -1) {
                System.out.println("Posição já ocupada!\n");
            } else if (verificaDirecionado()) {
                matGrafo[origem][destino] = p;
                numArestas++;
                System.out.println("Aresta inserida com sucesso!\n");
                testeImprimir(); //remover depois
            } else {
                matGrafo[origem][destino] = p;
                matGrafo[destino][origem] = p;
                //numArestas+= 2; usar só se for salvar a matriz espelhada
                numArestas++;
                System.out.println("Aresta inserida com sucesso!\n");
                testeImprimir(); //remover depois
            }
        } else {
            System.out.println("Peso não pode ser negativo!\n");
        }
    }

    private void criaNaoDirecionado() {
        inicializaMatriz();
        try {
            for (int i = 0; i < numVertices; i++) {
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
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (matGrafo[i][j] != -1) {
                    System.out.println(i + " " + j + " " + matGrafo[i][j]);
                }
            }
        }

        testeImprimir();
    }

    private void criaDirecionado() {
        inicializaMatriz();
        try {
            for (int i = 0; i < numVertices; i++) {
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
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (matGrafo[i][j] != -1) {
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

            bw.write(numArestas + "\n");

            if (verificaDirecionado()) {
                for (int i = 0; i < numVertices; i++) {
                    for (int j = 0; j < numVertices; j++) {
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

    private void inicializaMatriz() { //usar só se o peso puder ser zero
        matGrafo = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                matGrafo[i][j] = -1;
            }
        }
    }

    private boolean verificaDirecionado() {
        return (direcionado.equals("direcionado=sim"));
    }

    private void testeImprimir() { // apagar depois pois é so para testar a matriz
        for (int i = 0; i < numVertices; i++) {
            System.out.print("   " + i + " ");
        }
        System.out.println();
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < numVertices; j++) {

                System.out.print(matGrafo[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
