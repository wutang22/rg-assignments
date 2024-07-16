import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionalExample {
    private static Map<Integer, String> emails = new HashMap<>();

    static {
        emails.put(1, "Anna@gmail.com");
        emails.put(2, "Cindy@163.com");
    }

    public static Optional<String> getUserEmailById(int userId) {
        return Optional.ofNullable(emails.get(userId));
    }

    public static void main(String[] args) {
        Optional<String> email1 = getUserEmailById(1);
        Optional<String> email2 = getUserEmailById(3);

        System.out.println(email1.orElse("Email not found"));
        System.out.println(email2.orElse("Email not found"));
    }
}