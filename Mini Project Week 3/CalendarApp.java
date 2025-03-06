import java.util.*;

class CalendarEvent {
    private String date;
    private String event;

    public CalendarEvent(String date, String event) {
        this.date = date;
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public String getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return date + " - " + event;
    }
}

public class CalendarApp {
    private static Map<String, List<CalendarEvent>> events = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Calendar Application =====");
            System.out.println("1. View Calendar");
            System.out.println("2. Add Event");
            System.out.println("3. View Events");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Consume invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayCurrentMonth();
                    break;
                case 2:
                    addEvent();
                    break;
                case 3:
                    viewEvents();
                    break;
                case 4:
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        cal.set(year, month, 1);

        System.out.println("\n===== " + cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + year + " =====");
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        int firstDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < firstDay; i++) {
            System.out.print("    ");
        }

        for (int day = 1; day <= daysInMonth; day++) {
            System.out.printf("%3d ", day);
            if ((day + firstDay) % 7 == 0 || day == daysInMonth) {
                System.out.println();
            }
        }
    }

    private static void addEvent() {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();
        System.out.print("Enter event description: ");
        String eventDesc = scanner.nextLine().trim();

        if (date.isEmpty() || eventDesc.isEmpty()) {
            System.out.println("Error: Date and event description cannot be empty!");
            return;
        }

        events.putIfAbsent(date, new ArrayList<>());
        events.get(date).add(new CalendarEvent(date, eventDesc));
        System.out.println("✅ Event added successfully!");
    }

    private static void viewEvents() {
        System.out.print("Enter date to view events (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();

        if (events.containsKey(date)) {
            System.out.println("\nEvents on " + date + ":");
            for (CalendarEvent event : events.get(date)) {
                System.out.println("- " + event.getEvent());
            }
        } else {
            System.out.println("❌ No events found for this date.");
        }
    }
}
