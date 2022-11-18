import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Teste {
    public static void main(String[] args) {
        List<Integer> num = new ArrayList<>();
        num.add(10);
        num.add(1);
        num.add(20);
        num.add(7);
        num.add(50);

        num.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1 + " " + o2 + " r: " + (o1 - o2));
                return o1 - o2;
            }
        });

    }
}
