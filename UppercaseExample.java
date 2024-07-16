import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UppercaseExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("anna", "bob", "cindy", "david");
        List<String> uppercasedWords = words.stream().map(String::toUpperCase).collect(Collectors.toList());
        uppercasedWords.forEach(System.out::println);
    }
}