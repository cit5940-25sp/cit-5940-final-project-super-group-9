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