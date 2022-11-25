package trabalho;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Grafo grafo = new Grafo();
        int opcao, vtOrigem, vtDestino, peso, verticeAdj;

        do {
            menu();
            opcao = scan.nextInt();
            switch (opcao) {
                case 1:
                    grafo.importar();
                    break;
                case 2:
                    System.out.print("Informe a quantidade de vértices: ");
                    int numVertices = scan.nextInt();
                    System.out.print("1 - Direcionado\n2 - Não direcionado\nEscolha o tipo do grafo: ");
                    int tipo = scan.nextInt();
                    grafo.criaGrafoVazio(numVertices, (tipo == 1));
                    break;
                case 3:
                    grafo.exibirAdjacencias();
                    break;
                case 4:
                    System.out.print("Informe o vértice de origem: ");
                    vtOrigem = scan.nextInt();
                    System.out.print("Informe o vértice de destino: ");
                    vtDestino = scan.nextInt();
                    if (grafo.consultarSeAdjacente(vtOrigem, vtDestino)){
                        System.out.println("É adjacente!\n");
                    } else {
                        System.out.println("Não é adjacente!\n");
                    }
                    break;
                case 5:
                    System.out.print("Informe a origem: ");
                    vtOrigem = scan.nextInt();
                    System.out.print("Informe o destino: ");
                    vtDestino = scan.nextInt();
                    System.out.print("Informe o peso: ");
                    peso = scan.nextInt();
                    grafo.inserirAresta(vtOrigem, vtDestino, peso);
                    break;
                case 6:
                    System.out.print("Informe a origem: ");
                    vtOrigem = scan.nextInt();
                    System.out.print("Informe o destino: ");
                    vtDestino = scan.nextInt();
                    grafo.removerAresta(vtOrigem, vtDestino);
                    break;
                case 7:
                    grafo.exibirMatCoor();
                    System.out.print("Informe o número do vértice: ");
                    vtOrigem = scan.nextInt();
                    System.out.print("Informe a nova coordenada X: ");
                    int x = scan.nextInt();
                    System.out.print("Informe a nova coordenada Y: ");
                    int y = scan.nextInt();
                    grafo.editarCoordenada(vtOrigem,x,y);
                    break;
                case 8:
                    grafo.exibirMatCoor();
                    System.out.println("Informe o número do vértice: ");
                    vtOrigem = scan.nextInt();
                    grafo.editarNomeVertice(vtOrigem);
                    break;
                case 9:
                    System.out.print("Informe o número do vértice: ");
                    vtOrigem = scan.nextInt();
                    verticeAdj = grafo.primeiroAdjacenteDoVertice(vtOrigem);
                    if (verticeAdj >= 0) {
                        System.out.println("O primeiro adjacente do vértice: " +
                        vtOrigem + " é: " + verticeAdj + "\n");
                    } else if (verticeAdj == -1){
                        System.out.println("O vértice " + vtOrigem + " não tem adjacente!\n");
                    } else {
                        System.out.println("Vértice não existe!\n");
                    }
                    break;
                case 10:
                    System.out.print("Informe o número do vértice: ");
                    vtOrigem = scan.nextInt();
                    System.out.print("Informe o número do vértice atual: ");
                    int atual = scan.nextInt();
                    verticeAdj = grafo.proximoAdjacenteDoVertice(vtOrigem,atual);
                    if (verticeAdj >= 0) {
                        System.out.println("O proximo adjacente do vértice " +
                        vtOrigem + " iniciando no " + "vértice: " + atual + " é: " + verticeAdj + "\n");
                    } else if (verticeAdj == -1){
                        System.out.println("O vértice atual " + atual + " não tem adjacente!\n");
                    } else {
                        System.out.println("Vértice não existe!\n");
                    }
                    break;
                case 11:
                    System.out.print("Informe o número do vértice: ");
                    vtOrigem = scan.nextInt();
                    grafo.listaCompletaDeAdjacentesDoVertice(vtOrigem);
                    break;
                case 12:
                    grafo.arvoreGeradoraMinima();
                    break;
                case 13:
                    grafo.calculaMenorCaminho();
                    break;
                case 14:
                    grafo.exportar();
                    break;
                case 15:
                    break;
                default:
                    System.out.println("Opção inválida!\n");
                    break;
            }
        } while (opcao != 15);
    }
    static void menu() {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* 1 - Importar um grafo                             *");
        System.out.println("* 2 - Criar um grafo vazio                          *");
        System.out.println("* 3 - Exibir as adjacências                         *");
        System.out.println("* 4 - Consultar se um vértice é adjacente a outro   *");
        System.out.println("* 5 - Inserir novas arestas                         *");
        System.out.println("* 6 - Remover arestas                               *");
        System.out.println("* 7 - Editar coordenada dos vértices                *");
        System.out.println("* 8 - Editar nome dos vértices                      *");
        System.out.println("* 9 - Consultar o primeiro adjacente de um vértice  *");
        System.out.println("* 10 - Consultar o próximo adjacente de um vértice  *");
        System.out.println("* 11 - Consultar a lista completa de adjacentes     *");
        System.out.println("* 12 - Calcular árvore geradora mínima              *");
        System.out.println("* 13 - Calcular o menor caminho                     *");
        System.out.println("* 14 - exportar o grafo para um arquivo de texto    *");
        System.out.println("* 15 - Sair                                         *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.print("Escolha uma opcao: ");
    }
}
