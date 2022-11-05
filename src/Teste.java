import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Teste {
    public static void main(String[] args) {
        StringTokenizer st;
        String direcionado;
        String[] nomeVertice;
        int numVertices, linha, coluna;
        int[][] matCoor;
        try {
            Scanner scan = new Scanner(new FileReader("grafoTeste.txt"));
            direcionado = scan.nextLine();
            numVertices = scan.nextInt();

            nomeVertice = new String[numVertices];
            matCoor = new int[numVertices][2];

            for (int i = 0; i < numVertices; i++) {
                int indice = scan.nextInt();
                linha = scan.nextInt();
                coluna = scan.nextInt();
                nomeVertice[indice] = scan.nextLine().trim();
                matCoor[i][0] = linha;
                matCoor[i][1] = coluna;
            }

            System.out.println(direcionado);
            System.out.println(numVertices);
            for (int i = 0; i < numVertices; i++) {
                System.out.println(i + " " + matCoor[i][0] + " " + matCoor[i][1] + " " + nomeVertice[i]);
            }

            /*for (int i = 0; i < numVertices; i++) {
                if (nomeVertice[i].equals("Coruscant")) {
                    System.out.println("achou");
                    break;
                } else {
                    System.out.println("Não achou");
                }
            }*/

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
