package Trabalho;

import java.io.*;
import java.util.Random;
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
    private boolean importado, criado;

    public boolean isImportado() {
        return this.importado;
    }

    public void importar() {
        criado = false;
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
                importado = criaDirecionado();
            } else {
                importado = criaNaoDirecionado();
            }

            if (importado) {
                System.out.println("Grafo importado com sucesso!\n");
            } else {
                System.out.println("Não foi possível importar o grafo!\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void criaGrafoVazio(int numVertices, boolean tipoGrafo) {
        importado = false;
        if (tipoGrafo) {
            direcionado = "direcionado=sim";
        } else {
            direcionado = "direcionado=nao";
        }

        this.numVertices = numVertices;
        this.numArestas = 0;
        matCoor = new int[this.numVertices][2];
        preencheMatCoor();

        matGrafo = new int[this.numVertices][this.numVertices];
        inicializaMatriz();

        criado = true;
        System.out.println("Grafo criado com sucesso!");
    }

    public void exibirAdjacencias() {
        System.out.println();
        for (int i = 0; i < numVertices; i++) {
            System.out.print("   |" + i + "|");

        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.print("|" + i + "| ");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matGrafo[i][j] + "    ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean consultarSeAdjacente(int origem, int destino) {
        if (verificaPosicao(origem,destino)) {
            System.out.println("Posição inválida!");
            return false;
        }
        return (matGrafo[origem][destino] != -1);
    }

    public void inserirAresta(int origem, int destino, int p) {
        if (p >= 0) {
            if (verificaPosicao(origem, destino)) {
                System.out.println("Posição inválida!\n");
            } else if (matGrafo[origem][destino] != -1) {
                System.out.println("Posição já ocupada!\n");
            } else if (verificaDirecionado()) {
                matGrafo[origem][destino] = p;
                numArestas++;
                System.out.println("Aresta inserida com sucesso!\n");
            } else {
                matGrafo[origem][destino] = p;
                matGrafo[destino][origem] = p;
                numArestas++;
                System.out.println("Aresta inserida com sucesso!\n");
            }
        } else {
            System.out.println("Peso não pode ser negativo!\n");
        }
    }

    public void removerAresta(int origem, int destino) {
        if (verificaPosicao(origem, destino)) {
            System.out.println("Posição inválida!\n");
        } else if (matGrafo[origem][destino] == -1) {
            System.out.println("Posição está vazia!\n");
        } else if (verificaDirecionado()) {
            matGrafo[origem][destino] = -1;
            numArestas--;
            System.out.println("Aresta removida com sucesso!\n");
        } else {
            matGrafo[origem][destino] = -1;
            matGrafo[destino][origem] = -1;
            numArestas--;
            System.out.println("Aresta removida com sucesso!\n");
        }
    }

    public void editarCoordenada(int vertice, int x, int y) {
        if (vertice < 0 || vertice >= numVertices) {
            System.out.println("Vértice não existe!\n");
        } else if (x < 0 || x > 100 || y < 0 || y > 100) {
            System.out.println("Coordenada inválida! Informe valores de 0 a 100\n");
        } else {
            matCoor[vertice][0] = x;
            matCoor[vertice][1] = y;

            //REMOVER DEPOIS
            for (int i = 0; i < numVertices; i++) {
                System.out.println(i + " " + matCoor[i][0] + " " + matCoor[i][1]);
            }

            System.out.println("Coordenada atualizada com sucesso!\n");
        }
    }

    public int primeiroAdjacenteDoVertice(int vertice) {
        if (vertice >= 0 && vertice < numVertices) {
            for (int i = 0; i < numVertices; i++) {
                if (matGrafo[vertice][i] != -1) {
                    return i;
                }
            }
        } else {
            return -2;
        }
        return -1;
    }

    public int proximoAdjacenteDoVertice(int vertice, int atual) {
        if (vertice >= 0 && vertice < numVertices && atual >= 0 && atual < numVertices) {
            if (matGrafo[vertice][atual] != -1) {
                atual++;
            }
            for (int i = atual; i < numVertices; i++) {
                if (matGrafo[vertice][i] != -1) {
                    return i;
                }
            }
        } else {
            return -2;
        }
        return -1;
    }

    public void listaCompletaDeAdjacentesDoVertice(int vertice) {
        if (vertice >= 0 && vertice < numVertices) {
            System.out.print("Vértice " + vertice + " adjacentes: ");
            for (int i = 0; i < numVertices; i++) {
                if (matGrafo[vertice][i] != -1) {
                    System.out.print(i + "  ");
                }
            }
            System.out.println("\n");
        } else {
            System.out.println("Vértice não existe!\n");
        }
    }

    public void exportar() {
        if (importado || criado) {
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
                System.out.println("Grafo exportado com sucesso!\n");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Não foi possível exportar o grafo!\n");
        }
    }
    private boolean verificaPosicao(int origem, int destino) {
        return (origem < 0 || origem >= numVertices || destino < 0 || destino >= numVertices);
    }

    private void preencheMatCoor() {
        Random random = new Random();
        for (int i = 0; i < numVertices; i++) {
            matCoor[i][0] = random.nextInt(0,101);
            matCoor[i][1] = random.nextInt(0,101);
        }

        //APENAS PARA TESTE
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " " + matCoor[i][0] + " " + matCoor[i][1] + "\n");
        }
    }

    private boolean criaNaoDirecionado() {
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
            return false;
        }
        return true;
    }

    private boolean criaDirecionado() {
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
            return false;
        }
        return true;
    }

    private void inicializaMatriz() {
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
}
