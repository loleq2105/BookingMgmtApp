import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CLI {
    private Map<String, EntityService> entityServices;

    public CLI() {
        entityServices = new HashMap<>();
        entityServices.put("guest", new Guest());
        entityServices.put("g", new Guest());
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            handleCommand(input);
        }
    }

    private void handleCommand(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length == 0) return;

        String entityName = parts[0];
        String action = parts.length > 1 ? parts[1] : "";
        String parameter = parts.length > 2 ? parts[2] : "";

        EntityService entityService = entityServices.get(entityName);
        if (entityService == null) {
            System.out.println("Nieznana tabela: " + entityName);
            return;
        }

        switch (action) {
            case "list":
                entityService.listAll();
                break;
            case "add":
                entityService.addNew();
                break;
            case "remove":
                if (!parameter.isEmpty()) {
                    entityService.removeById(Integer.parseInt(parameter));
                } else {
                    System.out.println("Użycie: " + entityName + " remove [ID]");
                }
                break;
            default:
                if (!action.isEmpty()) {
                    try {
                        int entityId = Integer.parseInt(action);
                        entityService.viewById(entityId);
                    } catch (NumberFormatException e) {
                        System.out.println("Nieprawidłowe polecenie lub ID: " + action);
                    }
                } else {
                    System.out.println("Użycie: " + entityName + " [list|add|remove ID|ID]");
                }
                break;
        }
    }
}
