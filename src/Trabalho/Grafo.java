package Trabalho;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Grafo {
    private Scanner scan;
    private int[][] matGrafo;
    private int[][] matCoor;
    private int numVertices;
    private int numArestas;
    private int linha;
    private int coluna;
    private int peso;
    private String direcionado;
    private String[] nomeVertice;
    private boolean importado, criado;

    public void importar() {
        criado = false;
        try {
            //br = new BufferedReader(new FileReader("grafo.txt"));
            /*direcionado = br.readLine();
            numVertices = Integer.parseInt(br.readLine());

            matCoor = new int[numVertices][2];

            for (int i = 0; i < numVertices; i++) {
                st = new StringTokenizer(br.readLine()); //recebe uma linha do arquivo e separa os valores dos espaços
                int indice = Integer.parseInt(st.nextToken());
                linha = Integer.parseInt(st.nextToken());
                coluna = Integer.parseInt(st.nextToken());
                matCoor[i][0] = linha;
                matCoor[i][1] = coluna;
            }

            numArestas = Integer.parseInt(br.readLine());*/

            scan = new Scanner(new FileReader("grafoTeste.txt"));
            direcionado = scan.nextLine();
            numVertices = scan.nextInt();

            nomeVertice = new String[numVertices];
            matCoor = new int[numVertices][2];

            for (int i = 0; i < numVertices; i++) {
                int indice = scan.nextInt();
                linha = scan.nextInt();
                coluna = scan.nextInt();
                nomeVertice[indice] = scan.nextLine().trim(); //lendo e separando os espaços
                matCoor[i][0] = linha;
                matCoor[i][1] = coluna;
            }

            numArestas = scan.nextInt();

            if (verificaDirecionado()) { // verifica se o grafo é direcionado ou nao
                importado = criaDirecionado(); // retorna se o grafo foi importado ou não
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
        if (numVertices > 0) {
            importado = false;
            if (tipoGrafo) {
                direcionado = "direcionado=sim";
            } else {
                direcionado = "direcionado=nao";
            }

            this.numVertices = numVertices;
            this.numArestas = 0;
            matCoor = new int[this.numVertices][2];
            //iniciar nomevertice
            preencheMatCoor();
            //preenchernomevertice digitando

            matGrafo = new int[this.numVertices][this.numVertices];
            inicializaMatriz();

            criado = true;
            System.out.println("Grafo criado com sucesso!\n");
        } else {
            System.out.println("Valor inválido para criar o grafo!\n");
        }
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
        return (matGrafo[origem][destino] != -1); // retorna true se for adjacente
    }

    public void inserirAresta(int origem, int destino, int p) {
        if (p >= 0) { // não permite o peso ser negativo
            if (verificaPosicao(origem, destino)) {
                System.out.println("Posição inválida!\n");
            } else if (matGrafo[origem][destino] != -1) {
                System.out.println("Posição já ocupada!\n");
            } else if (verificaDirecionado()) {
                matGrafo[origem][destino] = p;
                numArestas++;
                System.out.println("Aresta inserida com sucesso!\n");
            } else { // não direcionado
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
        } else { // não direcionado
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
            exibirMatCoor();
            System.out.println("Coordenada atualizada com sucesso!\n");
        }
    }

    public void editarNomeVertice(int vertice) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Informe o novo nome do vértice: ");
        String nome = ler.nextLine().trim();

        if (vertice < 0 || vertice >= numVertices) {
            System.out.println("Vértice não existe!\n");
        } else if (nome.equals("")) { //verifica se o nome ta vazio
            System.out.println("Nome inválido!\n");
        } else {
            nomeVertice[vertice] = nome;
            exibirMatCoor();
            System.out.println("Nome atualizado com sucesso!\n");
        }
    }

    public int primeiroAdjacenteDoVertice(int vertice) {
        if (vertice >= 0 && vertice < numVertices) {
            for (int i = 0; i < numVertices; i++) {
                if (matGrafo[vertice][i] != -1) {
                    return i; // retorna o vertice adjacente
                }
            }
        } else {
            return -2; // retorno caso o vertice não exista
        }
        return -1; // retorno caso não seja adjacente
    }

    public int proximoAdjacenteDoVertice(int vertice, int atual) {
        if (vertice >= 0 && vertice < numVertices && atual >= 0 && atual < numVertices) {
            if (matGrafo[vertice][atual] != -1) { // caso o vertice atual já tenha valor, avança 1 vertice
                atual++;
            }
            for (int i = atual; i < numVertices; i++) {
                if (matGrafo[vertice][i] != -1) {
                    return i; // retorna o vertice adjacente
                }
            }
        } else {
            return -2; // retorno caso o vertice não exista
        }
        return -1; // retorno caso não seja adjacente
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
        if (importado || criado) { // so realiza a exportação se o grafo foi importado ou criado
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("grafoTeste.txt"));
                bw.write(direcionado + "\n");
                bw.write(numVertices + "\n");

                for (int i = 0; i < numVertices; i++) {
                    bw.write(i + " " + matCoor[i][0] + " " + matCoor[i][1] + " " + nomeVertice[i] + "\n");
                }

                bw.write(numArestas + "\n");

                if (verificaDirecionado()) { // exporta direcionado
                    for (int i = 0; i < numVertices; i++) {
                        for (int j = 0; j < numVertices; j++) {
                            if (matGrafo[i][j] != -1) {
                                bw.write(i + " " + j + " " + matGrafo[i][j] + "\n");
                            }
                        }
                    }
                } else { // exporta não direcionado
                    for (int i = 0; i < numVertices; i++) {
                        for (int j = 0; j < numVertices; j++) {
                            if ((matGrafo[i][j] != -1) && (j >= i)) { // sendo não direcionado, exporta só a diagonal superior
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

    public void exibirMatCoor() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " " + matCoor[i][0] + " " + matCoor[i][1] + " " + nomeVertice[i] + "\n");
        }
        System.out.println();
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
    }

    private boolean criaNaoDirecionado() {
        inicializaMatriz();
        for (int i = 0; i < numArestas; i++) {
            linha = scan.nextInt();
            coluna = scan.nextInt();
            peso = scan.nextInt();
            matGrafo[linha][coluna] = peso;
            matGrafo[coluna][linha] = peso;
        }
        return true;
    }

    private boolean criaDirecionado() {
        inicializaMatriz();
        for (int i = 0; i < numArestas; i++) {
            linha = scan.nextInt();
            coluna = scan.nextInt();
            peso = scan.nextInt();
            matGrafo[linha][coluna] = peso;
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
