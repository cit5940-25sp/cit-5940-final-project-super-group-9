package src;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class SimpleIO{
    private Terminal terminal;
    private Screen screen;
    //private List<String> dictionary;
    private StringBuilder currentInput = new StringBuilder();
    private List<String> suggestions = new ArrayList<>();
    private int cursorPosition = 0;
    private GameModel model;

    // Timer variables
    private int                      secondsRemaining = 30;
    private boolean                  timerRunning = true;
    private ScheduledExecutorService scheduler;

    public SimpleIO(){
    }

    public void runInput(Model model){
        this.model = (GameModel) model;
        try {
            TerminalWithSuggestions();
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void TerminalWithSuggestions() throws IOException {
        terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        screen.startScreen();


        // Initialize timer thread
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            if (timerRunning && secondsRemaining > 0) {
                secondsRemaining--;
                try {
                    updateScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1, 1,  TimeUnit.SECONDS);
    }

    public void run() throws IOException {
        boolean running = true;

        screen.clear();
        printString(0, 0, "> ");
        cursorPosition = 2;
        updateScreen();

        /**
         * Main input handling loop. Continuously listens for user input until the time runs out or the input is valid.
         * During this process, it handles different key events, updates suggestions, and refreshes the screen.
         * After the loop ends, it cleans up resources such as the timer, screen, and terminal.
         */
        while (running && secondsRemaining > 0) {
            // Poll for user input without blocking. Returns null if no input is available.
            KeyStroke keyStroke = terminal.pollInput();
            if (keyStroke != null) {
                // Handle different types of key strokes
                switch (keyStroke.getKeyType()) {
                    case Character:
                        // If a character key is pressed, insert it into the current input and update suggestions
                        handleCharacter(keyStroke.getCharacter());
                        break;
                    case Backspace:
                        // If the backspace key is pressed, remove the last character and update suggestions
                        handleBackspace();
                        break;
                    case Enter:
                        // If the enter key is pressed, check if the current input is a valid title
                        if(model.inputMovie(currentInput.toString())){
                            // If valid, stop the input loop
                            running = false;
                        }else{
                            // If invalid, clear the input and suggestions
                            handleEnter();
                        }
                        break;
                    case EOF:
                    case Escape:
                        // If EOF or Escape key is pressed, stop the input loop
                        running = false;
                        break;
                    default:
                        break;
                }
                // Refresh the screen to reflect the changes
                updateScreen();
            }

            // Small delay to prevent high CPU usage
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // Restore the interrupted status if the sleep is interrupted
                Thread.currentThread().interrupt();
            }
        }
        // Shutdown the timer scheduler
        scheduler.shutdown();
        // Close the screen and terminal to release resources
        screen.close();
        terminal.close();
        model.setTimeOut(secondsRemaining == 0);

    }

    private void handleCharacter(char c) {
        currentInput.insert(cursorPosition - 2, c);
        cursorPosition++;
        updateSuggestions();
    }

    private void handleBackspace() {
        if (cursorPosition > 2) {
            currentInput.deleteCharAt(cursorPosition - 3);
            cursorPosition--;
            updateSuggestions();
        }
    }

    private void handleEnter() throws IOException {
        int currentRow = screen.getCursorPosition().getRow();
        currentRow += 1 + suggestions.size();
        printString(0, currentRow, "> ");
        currentInput = new StringBuilder();
        cursorPosition = 2;
        suggestions.clear();
    }

    private void updateSuggestions() {
        suggestions.clear();
        String prefix = currentInput.toString();
        List<String> dictionary = model.getSuggestions(prefix);
        if (!prefix.isEmpty()) {
            for (String word : dictionary) {
                suggestions.add(word);
            }
        }
    }

    private void updateScreen() throws IOException {
        synchronized (screen) {
            screen.clear();

            // Print timer at top right
            String timerText = "Time: " + secondsRemaining + "s";
            TerminalSize size = screen.getTerminalSize();
            printString(size.getColumns() - timerText.length(), 0, timerText);

            // Print current command line
            printString(0, 0, "> " + currentInput.toString());

            // Print suggestions
            int row = 1;
            for (String suggestion : suggestions) {
                printString(2, row++, suggestion);
            }

            screen.setCursorPosition(new TerminalPosition(cursorPosition, 0));
            screen.refresh();
        }
    }

    private void printString(int column, int row, String text) {
        for (int i = 0; i < text.length(); i++) {
            screen.setCharacter(column + i, row,
                    new TextCharacter(text.charAt(i),
                            TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
        }
    }


    public static void main(String[] args) {
        GameModel model = new GameModel();
        model.initialData();
        SimpleIO io = new SimpleIO();
        io.runInput(model);
    }
}