import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        
        long firstValue = scanner.nextLong();
        char operatorValue = scanner.next().charAt(0);
        long secondValue = scanner.nextLong();
        long output;
        switch (operatorValue) {
            case '+': 
                output = firstValue + secondValue;
                System.out.println(output);
                break;
            
            case '-':
                output = firstValue - secondValue;
                System.out.println(output);
                break;
                
            case '*':
                output = firstValue * secondValue;
                System.out.println(output);
                break;
                
            case '/':
                if (secondValue == 0) {
                    System.out.println("Division by 0!");
                } else {
                    output = firstValue / secondValue;
                    System.out.println(output);
                }
                break;
                
            default:
                System.out.println("Unknown operator");
                
        }
    }
}
