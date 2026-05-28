import exception.EmployeeNotFoundException;
import exception.InvalidEmployeeDataException;
import model.FullTimeEmployee;
import model.PartTimeEmployee;
import service.EmployeeManagementSystem;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Erzeuge ein Scanner-Objekt zum Einlesen der Benutzereingaben
        Scanner scanner = new Scanner(System.in);

        // Erzeuge ein neues EmployeeManagementSystem-Objekt
        EmployeeManagementSystem ms = new EmployeeManagementSystem();

        // Endlosschleife für das Menü
        while (true) {
            // Menü anzeigen
            System.out.println("\nEmployee Management System");
            System.out.println("1. Mitarbeiter hinzufügen");
            System.out.println("2. Mitarbeiter entfernen");
            System.out.println("3. Alle Mitarbeiter anzeigen");
            System.out.println("4. Mitarbeiter nach ID sortieren");
            System.out.println("5. Mitarbeiter nach Gehalt sortieren");
            System.out.println("6. Vollzeit-Mitarbeiter befördern");
            System.out.println("7. Beenden");
            System.out.print("Wähle eine Option: ");

            // Benutzereingabe einlesen
            int choice = scanner.nextInt();

            try {
                // Auswahl verarbeiten
                switch (choice) {
                    case 1:
                        // Mitarbeiter hinzufügen
					extracted(scanner, ms);
                        break;

                    case 2:
                        // Mitarbeiter entfernen
                        System.out.print("ID des zu entfernenden Mitarbeiters eingeben: ");
                        int removeId = scanner.nextInt();
                        ms.removeEmployee(removeId);
                        System.out.println("Mitarbeiter erfolgreich entfernt!");
                        break;

                    case 3:
                        // Alle Mitarbeiter anzeigen
                        ms.displayAll();
                        break;

                    case 4:
                        // Nach ID sortieren
                        ms.sortById();
                        System.out.println("Mitarbeiter nach ID sortiert.");
                        break;

                    case 5:
                        // Nach Gehalt sortieren
                        ms.sortBySalary();
                        System.out.println("Mitarbeiter nach Gehalt sortiert.");
                        break;

                    case 6:
                        // Vollzeit-Mitarbeiter befördern
                        System.out.print("ID des Mitarbeiters zur Beförderung eingeben: ");
                        int promoteId = scanner.nextInt();

                        System.out.print("Gehaltserhöhung eingeben: ");
                        double increase = scanner.nextDouble();

                        ms.promoteFullTimeEmployee(promoteId, increase);
                        break;

                    case 7:
                        // Programm beenden
                        System.out.println("Programm wird beendet...");
                        return;

                    default:
                        // Ungültige Eingabe
                        System.out.println("Ungültige Auswahl.");
                }

            } catch (EmployeeNotFoundException | InvalidEmployeeDataException e) {
                // Fehler bei Eingaben oder Mitarbeiteroperationen behandeln
                System.out.println("Fehler: " + e.getMessage());
            }
        }
    }

	private static void extracted(Scanner scanner, EmployeeManagementSystem ms) throws InvalidEmployeeDataException {
		System.out.print("Mitarbeitertyp eingeben (1. Vollzeit, 2. Teilzeit): ");
		int type = scanner.nextInt();

		System.out.print("ID eingeben: ");
		int id = scanner.nextInt();

		System.out.print("Name eingeben: ");
		scanner.nextLine(); // Neue Zeile nach nextInt() abfangen
		String name = scanner.nextLine();

		System.out.print("Gehalt eingeben: ");
		double salary = scanner.nextDouble();

		// Mitarbeiter entsprechend dem Typ erstellen und hinzufügen
		if (type == 1) {
		    ms.addEmployee(new FullTimeEmployee(id, name, salary));
		} else {
		    ms.addEmployee(new PartTimeEmployee(id, name, salary));
		}

		System.out.println("Mitarbeiter erfolgreich hinzugefügt!");
	}
}
