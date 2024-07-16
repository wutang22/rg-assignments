import java.util.Arrays;
import java.util.List;

public class MethodReferenceExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Cindy", "David");

        //Sorting using lambda
        System.out.println("Sorting using lambda expression:");
        names.stream()
                .sorted((s1, s2) -> s1.compareTo(s2))
                .forEach(s -> System.out.println(s));

        // Sorting using method reference
        System.out.println("Sorting using method reference:");
        names.stream()
                .sorted(String::compareTo)
                .forEach(System.out::println);
    }
}
