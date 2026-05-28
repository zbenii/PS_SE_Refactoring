# Employee Management System

Plain-Java-Beispielprojekt für den Praxisteil **„IDE-Unterstützung für Refactorings"**
(PS Software Engineering). Bewusst ohne Framework gehalten, damit der Refactoring-
Vergleich über IntelliJ IDEA / Eclipse / VS Code fair bleibt (einzige Variable = IDE).

## Build, Test, Run (Maven)

```bash
mvn test          # alle JUnit-5-Tests ausführen (Behavior-Preservation-Netz)
mvn package       # baut target/employee-management-1.0-SNAPSHOT.jar
java -jar target/employee-management-1.0-SNAPSHOT.jar   # startet das CLI-Menü
```

Voraussetzung: JDK 17+ und Maven. (Java-Release im `pom.xml` ist 17 — bei Bedarf anpassen.)

## Import in die drei IDEs (identischer Stand!)

- **IntelliJ IDEA:** *Open* → das Verzeichnis (oder direkt die `pom.xml`) wählen → als Maven-Projekt importieren.
- **Eclipse:** *File → Import → Maven → Existing Maven Projects* → Projektordner wählen.
- **VS Code:** Ordner öffnen mit installiertem *Extension Pack for Java* (Red Hat) → wird automatisch als Maven-Projekt erkannt.

Wichtig fürs Experiment: vor jedem IDE-Durchlauf denselben Git-Commit frisch auschecken,
damit alle drei IDEs exakt denselben Ausgangsstand bekommen.

## Projektstruktur

```
src/main/java
  Main.java                         CLI-Menü (Aufrufstellen für Change Method Signature)
  model/Employee.java               abstrakte Oberklasse
  model/FullTimeEmployee.java       Subklasse (implements Promotable)
  model/PartTimeEmployee.java       Subklasse
  contract/Promotable.java          Interface
  service/EmployeeManagementSystem  Service mit Liste, Sortierung, Promotion
  util/SalaryComparator.java        Comparator (absteigend nach Gehalt)
  exception/*.java                  zwei Checked Exceptions
src/test/java                       JUnit-5-Tests (18 Tests)
```

## Refactoring-Ziele (für den Praxisteil)

- **R1 – Extract Method:** in `Main` den `case 1`-Block (Mitarbeiter einlesen & anlegen)
  in eine eigene Methode extrahieren — testet Umgang mit lokalen Variablen + Parametern.
- **R2 – Pull Up Method / Extract Interface:** `displayDetails()` ist in beiden Subklassen
  identisch → in `Employee` hochziehen. Alternativ ein Interface aus `Employee` extrahieren.
- **R3 – Change Method Signature:** Signatur von `promote(double)` (Interface + Override +
  Aufrufer im Service) oder `calculateBonus()` (abstrakt + 2 Overrides + Aufrufstelle) ändern
  → testet projektweite Referenz-Aktualisierung.

Nach jedem Refactoring `mvn test` laufen lassen: bleiben alle Tests grün, wurde das
Verhalten nicht verändert.
