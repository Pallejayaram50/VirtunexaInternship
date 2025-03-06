import java.util.Arrays;
import java.util.Scanner;

public class AnagramChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Anagram Checker =====");

        // Get user input
        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine().toLowerCase().replaceAll("\\s+", ""); // Remove spaces & convert to lowercase

        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine().toLowerCase().replaceAll("\\s+", ""); // Remove spaces & convert to lowercase

        // Check for empty input
        if (str1.isEmpty() || str2.isEmpty()) {
            System.out.println("Error: Strings cannot be empty!");
        } else {
            // Check if they are anagrams
            if (isAnagram(str1, str2)) {
                System.out.println("✅ The strings are anagrams!");
            } else {
                System.out.println("❌ The strings are NOT anagrams.");
            }
        }
        
        scanner.close();
    }

    // Function to check if two strings are anagrams
    private static boolean isAnagram(String str1, String str2) {
        // If lengths are different, they can't be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Convert to char arrays and sort
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        // Compare sorted arrays
        return Arrays.equals(arr1, arr2);
    }
}
