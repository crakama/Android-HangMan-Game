import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by kate on 02/01/2018.
 */

public class ServerInterfaceImpl implements ServeInterface {
    private int failedAttempts = 0;
    private int score = 0; // +=1 when user score and -=1 when server score
    private String currentWord;
    private String hiddenWord = new String();
    private LinkedList<String> guesses= new LinkedList<String>();
    ArrayList<String> dictionary = new ArrayList<>();
    private static FileReader fileReader;
    private static BufferedReader bufferedFileReader;

    ConnectionHandler connectionHandler;

    /**
     * Use the same object stream to write to the same InputStream of client to avoid streams corruption
     *
     * @param connectionHandler
     * @param
     * @throws IOException
     */
    public ServerInterfaceImpl(ConnectionHandler connectionHandler) throws IOException {
        this.connectionHandler = connectionHandler;

    }
    public ServerInterfaceImpl(){

    }
    /**
     * Randomly pick a word from a file and put in a variable wordPicked, use output stream to send it to client.
     * @throws
     */



    @Override
    public void initializeGame(Socket clientSocket) throws IOException {
        //Send game instructions
        String wordPicked = "Welcome to HangMan. I will pick a word and you will try to guess it character by character.\n\n" +
                "If you guess wrong 6 times...I WIN! If you get the word before hand...YOU WIN!.\n\n" +
                "Every time you guess a character incorrectly, the number of trials will reduce by one \n\n" +
                "Every time you guess a character correctly, the letter will be filled in all its positions in the word\n\n";
        connectionHandler.sendMessage(wordPicked);
    }


    @Override
    public void playGame(ConnectionHandler connHandler) throws IOException, ClassNotFoundException {
        System.out.println("\nGame Started !!!");
        generateNewWord();
        String s = "\n Enter a character that you think is in the word";
        connectionHandler.sendMessage("\n:::Current Game Status:::\n" + informationMessage() +"\n" + s);
        System.out.println("\nWord Picked:  " + currentWord);

        while (true) {
            String msg = connectionHandler.readMessage();
            if (msg.length() == 1) {

                guesses.add(msg);
                if (currentWord.contains(msg.toUpperCase()) || currentWord.contains(msg.toLowerCase())) { // Hit on characther
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < currentWord.length(); i++) {

                        //If i character is matching at index position
                        if (currentWord.substring(i, i + 1).equalsIgnoreCase(msg.substring(0, 1))) {

                            str.append(msg.substring(0, 1).toLowerCase());
                        } else {//No char at position i+1 after 1st round of loop
                            if (hiddenWord.charAt(i) != '-') {
                                str.append(hiddenWord.charAt(i));
                            } else {
                                str.append("-");
                            }
                        }
                    }
                    hiddenWord = str.toString();
                    if (!hiddenWord.contains("-")) {
                        ++this.score;
                        generateNewWord();
                        connectionHandler.sendMessage("You win with " + failedAttempts + " number of fail attempts"+informationMessage());

                    } else {// default presentation
                        connectionHandler.sendMessage(informationMessage() + "\n Enter a character that you think is in the word ");
                    }

                } else { // Wrong character guess
                    if (++failedAttempts >= currentWord.length()) {


                        --this.score;

                        generateNewWord();
                        String wordGuess = "You loose, the correct word was ";
                        connectionHandler.sendMessage(  wordGuess+ currentWord + "\n "+ informationMessage());
                    } else {
                        connectionHandler.sendMessage(informationMessage());
                    }
                }
            }else{

                // A guess on full word has been done
                guesses.add(msg);
                if(currentWord.equalsIgnoreCase(msg)) { // win
                    ++this.score;//increase score counter
                    generateNewWord();
                    connectionHandler.sendMessage("You win with " + failedAttempts + " number of fail attempts\n"+informationMessage());
                }
                else {
                    if(++failedAttempts>currentWord.length() && msg.length()>currentWord.length()){//loose


                        --this.score;//decrease score counter
                        generateNewWord();
                        connectionHandler.sendMessage("You loose, the correct word was " + currentWord+"\n"+informationMessage() );

                    } else{
                        connectionHandler.sendMessage(informationMessage());

                    }
                }
            }
        }// end while
    }

    /**
     * Generates a word that client shall guess on
     */
    private void generateNewWord() throws IOException {

        //guesses.clear(); //Empty guesses
        readFile();
        failedAttempts = failedAttempts;
        currentWord = pickWord();

        /*** Hide characters in word***/
        StringBuilder str = new StringBuilder();
        for(int i=0;i<currentWord.length(); i++){
            str.append("-");
        }
        hiddenWord = str.toString();
        /***                         ***/
    }

    public void readFile() throws IOException {
        try {
            File inFile = new File("words.txt");
            fileReader = new FileReader(inFile);
            bufferedFileReader = new BufferedReader(fileReader);
            String currentLine = bufferedFileReader.readLine();
            while (currentLine != null) {
                dictionary.add(currentLine);
                currentLine = bufferedFileReader.readLine();
            }
            bufferedFileReader.close();
            fileReader.close();
        } catch(IOException e) {
            System.out.println("Could not Read From File");
        }
    }

    public String pickWord(){
        Random rand = new Random();
        int wordIndex = Math.abs(rand.nextInt()) % dictionary.size();
        return dictionary.get(wordIndex);
    }

    /**
     *
     * @return - A suitable string to display in client console
     */
    private String informationMessage(){
        StringBuilder g = new StringBuilder();
        for(String str : guesses){
            g.append(str + " ");
        }
        return "\nWord : " + hiddenWord + " \n(Length=" + hiddenWord.length()  + ")"+
                "\nFailed Attempts: " + this.failedAttempts +
                "\nScore: " + this.score +
                "\nGuesses: " + g.toString();
    }
}
