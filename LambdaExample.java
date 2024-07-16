import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(1);
        numbers.add(3);
        numbers.add(2);
        Collections.sort(numbers, (o1, o2) -> o2.compareTo(o1));
        System.out.println("Sorted numbers (lambda): " + numbers);
    }
}
