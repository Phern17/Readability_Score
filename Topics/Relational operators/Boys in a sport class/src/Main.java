import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int firstHeight = scanner.nextInt();
        int secondHeight = scanner.nextInt();
        int thirdHeight = scanner.nextInt();
        if (firstHeight >= secondHeight && secondHeight >= thirdHeight) {
            System.out.println("true");
        } else if (firstHeight <= secondHeight && secondHeight <= thirdHeight) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
