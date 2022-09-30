package Trabalho;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Grafo grafo = new Grafo();
        int opcao, origem, destino, peso;

        do {
            menu();
            opcao = scan.nextInt();
            switch (opcao) {
                case 1:
                    grafo.importar();
                    break;
                case 2:
                    //usuario vai criar as coordenadas também ??
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.print("Informe a origem: ");
                    origem = scan.nextInt();
                    System.out.print("Informe o destino: ");
                    destino = scan.nextInt();
                    System.out.print("Informe o peso: ");
                    peso = scan.nextInt();
                    grafo.inserirAresta(origem, destino, peso);
                    break;
                case 6:
                    System.out.print("Informe a origem: ");
                    origem = scan.nextInt();
                    System.out.print("Informe o destino: ");
                    destino = scan.nextInt();
                    grafo.removerAresta(origem, destino);
                    break;
                case 7:
                    System.out.print("Informe o vértice: ");
                    int vertice = scan.nextInt();
                    System.out.print("Informe a nova coordenada X: ");
                    int x = scan.nextInt();
                    System.out.print("Informe a nova coordenada Y: ");
                    int y = scan.nextInt();
                    grafo.editarCoordenada(vertice,x,y);
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    grafo.exportar();
                    break;
            }
        } while (opcao != 12);

    }
//para ter o controle de acesso dos menus é preciso trabalhar os booleans importado e criado
    static void menu() {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* 1 - Importar um grafo                             *");
        System.out.println("* 2 - Criar um grafo vazio                          *");
        System.out.println("* 3 - Exibir as adjacências                         *");
        System.out.println("* 4 - Consultar se um vértice é adjacente a outro   *");
        System.out.println("* 5 - Inserir novas arestas                         *");
        System.out.println("* 6 - Remover arestas                               *");
        System.out.println("* 7 - Editar coordenada dos vértices                *");
        System.out.println("* 8 - Consultar o primeiro adjacente de um vértice  *");
        System.out.println("* 9 - Consultar o próximo adjacente de um vértice   *");
        System.out.println("* 10 - Consultar a lista completa de adjacentes     *");
        System.out.println("* 11 - exportar o grafo para um arquivo de texto    *");
        System.out.println("* 12 - Sair                                         *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.print("Escolha uma opcao: ");
    }
}
