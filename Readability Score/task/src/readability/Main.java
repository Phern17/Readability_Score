package readability;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        FileReader fr = new FileReader(args[0]);
        
        try {  
            int numberOfSentences = fr.countSentencesNumber();
            // store how many sentences there are in the text file.
            int numberOfWords = fr.countWordsNumber();
            int numberOfChars = fr.countCharNumber();

            int[] tempSyllablesArr = fr.countSyllables();
            int numberOfSyllables = tempSyllablesArr[0];
            int numberOfPoly = tempSyllablesArr[1];


            System.out.println("\nWords: " + numberOfWords);
            System.out.println("Sentences: " + numberOfSentences);
            System.out.println("Characters: " + numberOfChars);
            System.out.println("Syllables: " + numberOfSyllables);
            System.out.println("Polysyllables: " + numberOfPoly);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
            String userOption = scanner.nextLine().toUpperCase();
            double score = 0;
            int resultAge= 0;
            double avgAge = 0;

            switch(userOption) {
                case "ARI":
                    score =  calculateARI(numberOfChars, numberOfWords, numberOfSentences);
                    resultAge = determineAgeGroup(score);
                    displayScore(score, resultAge, userOption);
                    avgAge = (double) resultAge;
                    break;

                case "FK":
                    score = calculateFK(numberOfWords, numberOfSentences, numberOfSyllables);
                    resultAge = determineAgeGroup(score);
                    displayScore(score, resultAge, userOption);
                    avgAge = (double) resultAge;
                    break;

                case "SMOG":
                    score = calculateSMOG(numberOfPoly, numberOfSentences);
                    resultAge = determineAgeGroup(score);
                    displayScore(score, resultAge, userOption);
                    avgAge = (double) resultAge;
                    break;

                case "CL":
                    score = calculateCL(numberOfChars, numberOfWords, numberOfSentences);
                    resultAge = determineAgeGroup(score);
                    displayScore(score, resultAge, userOption);
                    avgAge = (double) resultAge;
                    break;

                case "ALL":
                    int accumulatedAge = 0;

                    double ari = calculateARI(numberOfChars, numberOfWords, numberOfSentences);
                    resultAge = determineAgeGroup(ari);
                    displayScore(ari, resultAge, "ARI");
                    accumulatedAge += resultAge;

                    double fk = calculateFK(numberOfWords, numberOfSentences, numberOfSyllables);
                    resultAge = determineAgeGroup(fk);
                    displayScore(fk, resultAge, "FK");
                    accumulatedAge += resultAge;

                    double smog = calculateSMOG(numberOfPoly, numberOfSentences);
                    resultAge = determineAgeGroup(smog);
                    displayScore(smog, resultAge, "SMOG");
                    accumulatedAge += resultAge;

                    double cl = calculateCL(numberOfChars, numberOfWords, numberOfSentences);
                    resultAge = determineAgeGroup(cl);
                    displayScore(cl, resultAge, "CL");
                    accumulatedAge += resultAge;

                    avgAge = accumulatedAge / 4.0;
                    break;
            }

            System.out.printf("\nThis text should be understood by %.2f-year-olds.", avgAge);

        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }

    }

    private static void displayScore(double score, int resultAge, String mode) {

        switch(mode) {
            case "ARI":
                if (resultAge == 25){
                    System.out.printf("%nAutomated Readability Index: %.2f (about 24+-year-olds).%n", score);
                }
                else {
                    System.out.printf("%nAutomated Readability Index: %.2f (about %s-year-olds).%n", score, resultAge);
                }
            break;
            case "FK":
                if (resultAge == 25) {
                    System.out.printf("Flesch-Kincaid readability tests: %.2f (about 24+-year-olds).%n", score);
                } else {
                    System.out.printf("Flesch-Kincaid readability tests: %.2f (about %s-year-olds).%n", score, resultAge);
                }
            break;
            case "SMOG":
                if (resultAge == 25) {
                    System.out.printf("Simple Measure of Gobbledygook: %.2f (about 24+-year-olds).%n", score);
                } else {
                    System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s-year-olds).%n", score, resultAge);
                }
            break;
            case "CL":
                if (resultAge == 25) {
                    System.out.printf("Coleman-Liau index: %.2f (about 24+-year-olds).%n", score);
                } else {
                    System.out.printf("Coleman-Liau index: %.2f (about %s-year-olds).%n", score, resultAge);
                }
        }
    }

    private static int determineAgeGroup(double score) {
        int adjustedScore = (int) Math.round(score);
        int ageGroup = 0;
        switch (adjustedScore) {
            case 1: ageGroup = 6;
                break;
            case 2: ageGroup = 7;
                break;
            case 3: ageGroup = 9;
                break;
            case 4: ageGroup = 10;
                break;
            case 5: ageGroup = 11;
                break;
            case 6: ageGroup = 12;
                break;
            case 7: ageGroup = 13;
                break;
            case 8: ageGroup = 14;
                break;
            case 9: ageGroup = 15;
                break;
            case 10: ageGroup = 16;
                break;
            case 11: ageGroup = 17;
                break;
            case 12: ageGroup = 18;
                break;
            case 13: ageGroup = 24;
                break;
            case 14: ageGroup = 25;
                break;
        }
        return ageGroup;
    }

    private static double calculateARI(int numberOfChars, int numberOfWords, int numberOfSentences) {
        return (double) (4.71 * ((double) numberOfChars / (double) numberOfWords) + 0.5 * ((double) numberOfWords / (double) numberOfSentences) - 21.43);
    }

    private static double calculateFK(int numberOfWords, int numberOfSentences, int numberOfSyllables) {
        return (double) (0.39 * (numberOfWords / (double) numberOfSentences) + 11.8 * (numberOfSyllables / (double) numberOfWords) - 15.59);

    }

    private static double calculateSMOG(int numberOfPoly, int numberOfSentences) {
        return (double) (1.043 * Math.sqrt(numberOfPoly * 30 / (double)numberOfSentences) + 3.1291);

    }

    private static double calculateCL(int numberOfChars, int numberOfWords, int numberOfSentences) {
        double L = numberOfChars / (double) numberOfWords* 100;
        double S = numberOfSentences / (double) numberOfWords * 100;
        return 0.0588 * L - 0.296 * S - 15.8;
    }

}
