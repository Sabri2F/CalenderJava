import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class CalenderRecord {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILENAME = "notes.by user";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    showNote();
                    break;
                case 3:
                    printCalendar();
                    break;
                case 4:
                    findDayOfWeek();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }

    private static void displayMenu() {
        System.out.println("Menu\n" +
                "1. Add Note\n" +
                "2. Show Note\n" +
                "3. Print Calendar\n" +
                "4. Find out the day\n" +
                "5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addNote() {
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
        System.out.print("Enter the month: ");
        int month = scanner.nextInt();
        System.out.print("Enter the date: ");
        int date = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the note: ");
        String note = scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true))) {
            writer.printf("%d,%d,%d,%s%n", year, month, date, note);
            System.out.println("Note added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to write to file: " + e.getMessage());
        }
    }

    private static void showNote() {
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
        System.out.print("Enter the month: ");
        int month = scanner.nextInt();
        System.out.print("Enter the date: ");
        int date = scanner.nextInt();

        try (Scanner fileScanner = new Scanner(new File(FILENAME))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                int noteYear = Integer.parseInt(parts[0]);
                int noteMonth = Integer.parseInt(parts[1]);
                int noteDate = Integer.parseInt(parts[2]);
                String note = parts[3];

                if (noteYear == year && noteMonth == month && noteDate == date) {
                    System.out.println(note);
                    return;
                }
            }

            System.out.println("No note found for this date.");
        } catch (IOException e) {
            System.out.println("Failed to read from file: " + e.getMessage());
        }
    }
        
        
        public static void findDayOfWeek() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a valid date (dd/mm/yyyy):");
    String dateString = sc.nextLine();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate date = LocalDate.parse(dateString, formatter);
    DayOfWeek dayOfWeek = date.getDayOfWeek();
    System.out.println("The day is " + dayOfWeek);
}
        
    private static void printCalendar() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the year: ");
    int year = scanner.nextInt();
    System.out.print("Enter the month: ");
    int month = scanner.nextInt();
    LocalDate startDate = LocalDate.of(year, month, 1);
    LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
    System.out.println(year + " Calendar");
    System.out.println("Sun Mon Tue Wed Thu Fri Sat");
    LocalDate date = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        while (!date.isAfter(endDate)) {
        for (int i = 0; i < 7; i++) {
            System.out.printf("%4s", date.getDayOfWeek().toString().substring(0, 3));
            date = date.plusDays(1);
        }
        System.out.println();

        while (date.getMonthValue() == month) {
            for (int i = 0; i < 7; i++) {
                if (date.getMonthValue() != month) {
                    System.out.printf("%4s", " ");
                } else {
                    System.out.printf("%4d", date.getDayOfMonth());
                }
                date = date.plusDays(1);
            }
            System.out.println();
        }
    }
}
}