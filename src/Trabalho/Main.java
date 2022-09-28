package Trabalho;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.importar();
        //menu();
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
        System.out.println("* 8 - Consultar o primeiro adjacente de um vértice  *");
        System.out.println("* 9 - Consultar o próximo adjacente de um vértice   *");
        System.out.println("* 10 - Consultar a lista completa de adjacentes     *");
        System.out.println("* 11 - exportar o grafo para um arquivo de texto    *");
        System.out.println("* 12 - Sair                                         *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }
}
