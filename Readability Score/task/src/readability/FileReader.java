package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static readability.SyllablesCounter.countWithRegex;

public class FileReader {
    protected String filePath = null;
    protected String textFileWholeString = null;
    protected String[] separatedSentence;

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    private static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public int countSentencesNumber() throws IOException{
        this.textFileWholeString = readFileAsString(this.filePath).toLowerCase();
        System.out.println("The text is:\n" + this.textFileWholeString);
        this.separatedSentence = this.textFileWholeString.replaceAll(",", "").split("[.?!;]\\s");
        return this.separatedSentence.length;
    }

    public int countWordsNumber() {
        int numberOfWords = 0;

        for (String s : this.separatedSentence) {
            // find out the number of words inside each sentence
            numberOfWords += s.split("\\s").length;
        }
        return numberOfWords;
    }

    public int countCharNumber() {
        int numberOfChars = 0;
        // count number of chars
        for (int j = 0; j < textFileWholeString.length(); j++) {
            numberOfChars += textFileWholeString.charAt(j) != ' ' ? 1 : 0;
        }

        return numberOfChars;
    }

    public int[] countSyllables() {
        int [] syllablesContainer = new int[2];

        // count syllables and polysyllables
        for (String s : separatedSentence){
            String[] temp = s.split("\\s");

            for (String item : temp) {
                int wordSyllables = countWithRegex(item);
                syllablesContainer[0] += wordSyllables;
                if (wordSyllables > 2) {
                    syllablesContainer[1]++;
                }
            }
        }
        return syllablesContainer;
    }
}
