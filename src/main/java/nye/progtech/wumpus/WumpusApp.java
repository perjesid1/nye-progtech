package nye.progtech.wumpus;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
/**
 *
 */
public class WumpusApp {
    /**
     *
     */
    private static final int TOP_LIST_LIMIT = 10;
    /**
     *
     */
    private static final long MILLIS_TO_SECONDS = 1000L;
    /**
     *
     */
    private static final int SMALL_SLEEP_TIME = 1;
    /**
     *
     */
    private static final int MEDIUM_SLEEP_TIME = 3;
    /**
     *
     */
    private static final int LONG_SLEEP_TIME = 5;
    /**
     *
     */
    private UserSaveHandler users = new UserSaveHandler();
    /**
     *
     */
    private MapSaveHandler map = new MapSaveHandler();
    /**
     *
     */
    public final void run() {
        clearConsole();
        greetThePlayer();
        sleepFor(MEDIUM_SLEEP_TIME);
        clearConsole();
        gameIntro();
        clearConsole();
        try {
            users.loadAllUsers();
        } catch (Exception e) {
            System.out.println("Unable to load users - corrupted save file.");
        }
        askForUser();
        try {
            users.saveAllUsers();
        } catch (Exception e) {
            System.out.println("Unable to save users - corrupted save file.");
        }
        Throwable throwable = null;
        try {
            map.setFileName("current_map_" + users.getCurrentUser()
                    .getUserName());
            map.loadMap();
        } catch (FileNotFoundException e) {
            throwable = e;
            System.out.println("Seems like you don't have any "
                    + "pending games saved.");
        } catch (Exception e) {
            throwable = e;
            System.out.println("Your pending game is not able "
                    + "to load - corrupted save file.");
        }
        if (throwable == null) {
            System.out.println("Your pending game has been loaded.");
        }
        do {
            sleepFor(SMALL_SLEEP_TIME);
            clearConsole();
            displayMainMenu();
        } while (true);
    }
    /**
     *
     */
    private static void greetThePlayer() {
        System.out.println("\n");
        System.out.println("░█░█░█░█░█▀█░▀█▀░░░▀█▀░█░█░█▀▀░░░"
                + "█░█░█░█░█▄█░█▀█░█░█░█▀▀");
        System.out.println("░█▀█░█░█░█░█░░█░░░░░█░░█▀█░█▀▀░░░"
                + "█▄█░█░█░█░█░█▀▀░█░█░▀▀█");
        System.out.println("░▀░▀░▀▀▀░▀░▀░░▀░░░░░▀░░▀░▀░▀▀▀░░░"
                + "▀░▀░▀▀▀░▀░▀░▀░░░▀▀▀░▀▀▀");
        System.out.println("\n");
    }
    /**
     *
     */
    private static void gameIntro() {
        System.out.println("Welcome to the \"Hunt the Wumpus\" game.");
        System.out.println("The game is made by Dániel \"perjesid1\" Perjési,"
                + " as an assignment for his Computer Science studies.");
        System.out.println("The original game was created "
                + "by Gregory Yob in 1973.");
        System.out.println("The assignment is a modified version"
                + "of the original game.");
        sleepFor(LONG_SLEEP_TIME);
    }
    /**
     *
     */
    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } else {
                System.out.print("\033[2J\033[1;1H");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param seconds Number of seconds to wait.
     */
    public static void sleepFor(final int seconds) {
        try {
            Thread.sleep(seconds * MILLIS_TO_SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    /**
     *
     */
    private void askForUser() {
        Scanner scn = new Scanner(System.in);
        String userName;
        String answer;
        do {
            System.out.println("Please type in your user name.");
            try {
                userName = scn.nextLine();
            } catch (Exception e) {
                userName = "unknownUser";
            }
            System.out.println("Your user name will be \"" + userName
                    + "\".\nContinue? (Y/N)");
            try {
                answer = scn.nextLine();
            } catch (Exception e) {
                answer = "N";
            }
        } while (!answer.equalsIgnoreCase("Y"));
        users.setCurrentUser(userName);
    }
    /**
     *
     */
    private void displayMainMenu() {
        System.out.println("Map Editor [M]");
        System.out.println("Load Map From File [L]");
        System.out.println("Save Database [S]");
        System.out.println("Play Current Map [P]");
        System.out.println("Score Board [B]");
        System.out.println("Exit [X]");
        Scanner scn = new Scanner(System.in);
        switch (scn.nextLine().toUpperCase()) {
            case "X" -> System.exit(0);
            case "S" -> {
                Throwable throwable = null;
                try {
                    users.saveAllUsers();
                } catch (Exception e) {
                    System.out.println("Unable to save users - "
                            + "corrupted save file.");
                    throwable = e;
                }
                if (throwable == null) {
                    System.out.println("Data saved successfully!");
                }
            }
            case "M" -> System.out.println("Not implemented in current "
                    + "version!");
            case "P" -> {
                if (map != null) {
                    clearConsole();
                    System.out.println("You have found the labyrinth "
                            + "where the terrifying monster lives...");
                    System.out.println("You have only one task... "
                            + "And only one chance...");
                    sleepFor(MEDIUM_SLEEP_TIME);
                    clearConsole();
                    greetThePlayer();
                    sleepFor(MEDIUM_SLEEP_TIME);
                    Hunt newGame = new Hunt(users.getCurrentUser()
                            .getUserName());
                    if (newGame.run(map)) {
                        users.getCurrentUser().setHighScore(users
                                .getCurrentUser().getHighScore() + 1);
                        map = null;
                        try {
                            users.saveAllUsers();
                        } catch (Exception e) {
                            System.out.println("Unable to save users - "
                                    + "corrupted save file.");
                        }
                    }
                } else {
                    System.out.println("No map loaded. Please load a map "
                            + "to play the game!");
                }
            }
            case "L" -> {
                clearConsole();
                System.out.println("Please input the file's name without the "
                        + "file extension which you want to load.");
                System.out.println("The file extension must be .txt!");
                Throwable throwable = null;
                try {
                    map.setFileName(scn.nextLine());
                    map.loadNewMap();
                } catch (FileNotFoundException e) {
                    throwable = e;
                    System.out.println("There's no file with such name in "
                            + "the import folder!");
                } catch (Exception e) {
                    throwable = e;
                    System.out.println("Invalid import file!");
                }
                if (throwable == null) {
                    System.out.println("Map successfully loaded!");
                }

            }
            case "B" -> {
                List<User> temList = users.getAllUsers().getAllUsers();
                temList.sort((a, b) -> {
                    if (a.getHighScore() == b.getHighScore()) {
                        return a.getUserName()
                                .compareToIgnoreCase(b.getUserName());
                    } else {
                        return Integer.compare(b.getHighScore(),
                                a.getHighScore());
                    }
                });
                if (temList.size() > TOP_LIST_LIMIT) {
                    for (int i = 0; i < TOP_LIST_LIMIT; i++) {
                        System.out.println(i + SMALL_SLEEP_TIME + ". "
                                + temList
                                .get(i).getUserName() + " [" + temList
                                .get(i).getHighScore() + " win(s)]");
                        sleepFor(SMALL_SLEEP_TIME);
                    }
                } else {
                    for (int i = 0; i < temList.size(); i++) {
                        System.out.println(i + SMALL_SLEEP_TIME + ". "
                                + temList.get(i).getUserName()
                                + " [" + temList.get(i).getHighScore()
                                + " win(s)]");
                        sleepFor(SMALL_SLEEP_TIME);
                    }
                }
                sleepFor(SMALL_SLEEP_TIME);
            }
            default -> System.out.println("Unknown command.");
        }
    }
}
