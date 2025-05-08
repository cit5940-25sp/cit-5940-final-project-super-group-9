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

    public String getInput(Model model){
        this.model = (GameModel) model;
        String x = null;
        try {
            TerminalWithSuggestions();
            x = run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return x;
    }

    public void TerminalWithSuggestions() throws IOException {
        terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        screen.startScreen();

        //dictionary = Arrays.asList(
        //        "java", "javascript", "python", "terminal", "program",
        //        "code", "compiler", "development", "interface", "application"
        //);

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

    public String run() throws IOException {
        boolean running = true;

        screen.clear();
        printString(0, 0, "> ");
        cursorPosition = 2;
        updateScreen();

        while (running && secondsRemaining > 0) {
            KeyStroke keyStroke = terminal.pollInput();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case Character:
                        handleCharacter(keyStroke.getCharacter());
                        break;
                    case Backspace:
                        handleBackspace();
                        break;
                    case Enter:
                    case EOF:
                    case Escape:
                        running = false;
                        //handleEnter();
                        break;
                    default:
                        break;
                }
                updateScreen();
            }

            // Small delay to prevent CPU hogging
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Shutdown timer
        scheduler.shutdown();
        screen.close();
        terminal.close();
        if (secondsRemaining > 0){
            return currentInput.toString();
        }else{
            return null;
        }
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