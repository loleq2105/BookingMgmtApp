import java.util.Scanner;

public class Field {
    private String name;
    private Validator validator;

    public Field(String name, Validator validator) {
        this.name = name;
        this.validator = validator;
    }

    // Interface for validation
    @FunctionalInterface
    public interface Validator {
        boolean validate(String value);
    }

    // Prompt user for input and validate it
    public String inputAndValidate() {
        Scanner scanner = new Scanner(System.in);
        String value;

        while (true) {
            System.out.print("Wprowadź pole " + name + ": ");
            value = scanner.nextLine();

            if (validator.validate(value)) {
                return value;
            } else {
                System.out.println("Nieprawidłowe pole " + name + ". Proszę spróbować ponownie.");
            }
        }
    }

    // Get field name
    public String getName() {
        return name;
    }
}