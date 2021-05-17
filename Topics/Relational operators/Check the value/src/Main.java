import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input < 10){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}