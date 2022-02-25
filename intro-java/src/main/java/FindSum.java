import java.util.List;

public class FindSum {

    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 5, 8, 13);
        findSum(list, 16);

    }

    // simplified by just printing as side effect
    private static void findSum(List<Integer> list, int n){
        list.forEach(i -> list.stream().filter(j -> i+j == n).forEach(j -> printPair(i, j)));

    }

    private static void printPair(int i, int j){
        System.out.printf("(%d,%d)", i, j);
    }
}
