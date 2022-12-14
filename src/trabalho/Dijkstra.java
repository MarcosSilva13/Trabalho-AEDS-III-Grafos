package trabalho;
public class Dijkstra {
    private int [][] matGrafo;
    private String[] nomeVertices;
    private int origem;
    private int destino;
    private int numVertices;
    private int[] dist;
    private int[] visitado;
    private boolean temCaminho = false;

    public Dijkstra(int[][] matGrafo, int origem, int destino, int numVertices, String[] nomeVertices) {
        this.matGrafo = matGrafo;
        this.origem = origem;
        this.destino = destino;
        this.numVertices = numVertices;
        this.nomeVertices = nomeVertices;
    }

    private int menorDistancia() {
        int min = Integer.MAX_VALUE;
        boolean primeiro = true;

        for (int i = 0; i < numVertices; i++) {
            //verifica se a distância do atual é menor que min e se não foi visitado
            if (dist[i] < min && visitado[i] == 0){
                if (primeiro){ //verifica se é a primeira vez que um vértice atendeu a condição
                    min = i;
                    primeiro = false;
                } else {
                    if (dist[min] > dist[i]) min = i; //verifica se a distância do min é maior que a do atual
                }
            }
        }
        return min; //retorna o vertice com a menor distância
    }
    public void menorCaminho() {
        int contVertice = numVertices, soma, verticeProcessado;
        int[] ant = new int[numVertices];
        visitado = new int[numVertices];
        dist = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            ant[i] = -1;
            dist[i] = Integer.MAX_VALUE;
            visitado[i] = 0;
        }

        dist[origem] = 0; //vertice inicial

        while (contVertice > 0) {
            verticeProcessado = menorDistancia(); //recebe o vértice com a menor distância
            if (verticeProcessado == Integer.MAX_VALUE) { //se for true não tem mais vértice para olhar
                break;
            } else {
                temCaminho = true;
            }
            visitado[verticeProcessado] = 1; //faz o vértice não ser processado novamente
            contVertice--;

            for (int vizinho = 0; vizinho < numVertices; vizinho++) {
                if (matGrafo[verticeProcessado][vizinho] != -1) { //verifica se existe uma aresta entre os vértices
                    //soma o peso da aresta + a distacia que tem no vértice
                    soma = matGrafo[verticeProcessado][vizinho] + dist[verticeProcessado];

                    if (soma < dist[vizinho]) { //verifica para decidir o caminho melhor
                        ant[vizinho] = verticeProcessado; //vértice vizinho vai ter como antecessor o vértice processado
                        dist[vizinho] = soma;
                    }
                }
            }
        }
        int caminho = destino;

        System.out.println("\n***Caminho***");

        System.out.println();
        if (temCaminho && ant[caminho] != -1) {
            while (caminho != origem) {
                System.out.print(nomeVertices[caminho] + " {" + caminho + "}" + " <-- ");
                caminho = ant[caminho];
            }
            System.out.println(nomeVertices[origem] + " {" + origem + "}");
            System.out.println("\nDistância de " + nomeVertices[origem] + " até " + nomeVertices[destino]
                    + " é " + dist[destino] + "\n");
        } else {
            System.out.println("O caminho de " + nomeVertices[origem] + " até " + nomeVertices[destino]
                    + " não existe!\n");
        }
    }
}
