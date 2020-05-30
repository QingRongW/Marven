package others;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Class to store the play result.
 */
class PlayResult {
    int win;
    int lose;
    int draw;

    public PlayResult() {
        this.win = 0;
        this.lose = 0;
        this.draw = 0;
    }

}

/**
 * Class to play the game Rock-Paper-Scissor.
 */
public class RockPaperScissor {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int ROUND = 10;

    //Construct hashMap with the game action: 1-Rock, 2-Paper, 3-Scissor
    private static final HashMap<Integer, String> playActionMap;

    static {
        playActionMap = new HashMap();
        playActionMap.put(1, "Rock");
        playActionMap.put(2, "Paper");
        playActionMap.put(3, "Scissor");
    }

    //Construct hashMap with the rule of game
    // Map Key: players' action
    // Map Value: Win action
    private static final HashMap<Pair<Integer, Integer>, Integer> playRuleMap;

    static {
        playRuleMap = new HashMap();
        // Rock against Paper, Paper win
        playRuleMap.put(new Pair<>(1, 2), 2);
        playRuleMap.put(new Pair<>(2, 1), 2);
        // Rock against Scissor, Rock win
        playRuleMap.put(new Pair<>(1, 3), 1);
        playRuleMap.put(new Pair<>(3, 1), 1);
        // Paper against Scissor, Scissor win
        playRuleMap.put(new Pair<>(2, 3), 3);
        playRuleMap.put(new Pair<>(3, 2), 3);
        // Draw
        playRuleMap.put(new Pair<>(1, 1), 0);
        playRuleMap.put(new Pair<>(2, 2), 0);
        playRuleMap.put(new Pair<>(3, 3), 0);

    }

    /**
     * Method to play the game.
     *
     * @return playResult
     */
    private static PlayResult playGame() {
        PlayResult playResult = new PlayResult();
        int round = 1;
        while (round <= ROUND) {
            System.out.println("Round " + round);
            System.out.println(" Please select the play mode: 1: Fair, 2: UnFair, 3: Remote Fair");
            int playMode = scanner.nextInt();
            // fair Mode
            if (playMode == 1) {
                fairModePlay(playResult);
                round++;
            }
            // unfair Mode
            else if (playMode == 2) {
                unFairModePlay(playResult);
                round++;
            }
            // remote fair mode
            else if (playMode == 3) {
                remoteModePlay(playResult);
                round++;
            }
            // unrecognized input
            else {
                System.out.println("Invalid selection");
            }
        }

        return playResult;
    }

    /**
     * Fair Mode Play, generate two int randomly.
     *
     * @param playResult
     */
    private static void fairModePlay(PlayResult playResult) {
        Random random = new Random();
        int playerAction = random.nextInt(3) + 1;
        int counterPlayerAction = random.nextInt(3) + 1;
        play(playerAction, counterPlayerAction, playResult);

    }

    /**
     * UnFair Mode Play, generate one int randomly only, the other int will always be 1-Rock
     *
     * @param playResult
     */
    private static void unFairModePlay(PlayResult playResult) {
        Random random = new Random();
        int playerAction = random.nextInt(3) + 1;
        int counterPlayerAction = 1;
        play(playerAction, counterPlayerAction, playResult);
    }

    /**
     * Remote fair mode play. One local random int, one remote random int.
     *
     * @param playResult
     */
    private static void remoteModePlay(PlayResult playResult) {
        Random random = new Random();
        int playerAction = random.nextInt(3) + 1;

        // get the remote play action by http call
        int counterPlayerAction = getRemotePlayAction();
        if (counterPlayerAction < 1 || counterPlayerAction > 3) {
            System.out.println("Invalid play action: " + counterPlayerAction);
            return;
        }
        play(playerAction, counterPlayerAction, playResult);
    }

    /**
     * Get the remote play action from an URL.
     *
     * @return a random int between 1 and 3 from the website.
     */
    private static int getRemotePlayAction() {
        BufferedReader reader;
        StringBuffer responseBody = new StringBuffer();
        String line;
        // get the play action through http call
        try {
            URL url = new URL("https://www.random.org/integers/?num=1&min=1&max=3&col=1&base=10&format=plain");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int connectStatus = connection.getResponseCode();
            if (connectStatus > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }
                reader.close();
                return Integer.valueOf(responseBody.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Play the game, get the game result from predefined hashMap.
     * If not found from map, draw scenario detected.
     * Update the playResult
     *
     * @param playerA    playerA action
     * @param playerB    playerB action
     * @param playResult
     */
    private static void play(int playerA, int playerB, PlayResult playResult) {
        System.out.println(
                "Player A action: " + playActionMap.get(playerA) + " &  Player B action: " + playActionMap.get(playerB));
        // invalid play action
        if (!playRuleMap.containsKey(new Pair<>(playerA, playerB))) {
            System.out.println("----------------Invalid play action.---------------");
            return;
        }

        int result = playRuleMap.get(new Pair<>(playerA, playerB));
        // player win, update the win value.
        if (playerA == result) {
            playResult.win++;
            System.out.println("Player A with " + playActionMap.get(result) + " Win.");
        }
        //player lose, update the lose value.
        else if (playerB == result) {
            playResult.lose++;
            System.out.println("Player B with " + playActionMap.get(result) + " Win.");
        }
        // draw
        else if (0 == result) {
            playResult.draw++;
            System.out.println("Draw.");
        }
        System.out.println("-------------------------------");
    }

    /**
     * Main method of the class.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // play the game
        PlayResult playResult = playGame();

        // construct the output string for player
        String playerOutPut =
                "Result of player A: " + " Win: " + playResult.win + " Lose: " + playResult.lose + " Draw: " + playResult.draw;
        // construct the output string for counter player
        String counterPlayerOutPut =
                "Result of player B: " + " Win: " + playResult.lose + " Lose: " + playResult.win + " Draw: " + playResult.draw;

        System.out.println("Please choose the output destination: 1: Console, 2: File");
        int outputDest = scanner.nextInt();
        // output to console
        if (outputDest == 1) {
            System.out.println(playerOutPut);
            System.out.println(counterPlayerOutPut);
        }
        //output to a file
        else if (outputDest == 2) {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out/output.txt"));
            bufferedWriter.write(playerOutPut);
            bufferedWriter.newLine();
            bufferedWriter.write(counterPlayerOutPut);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        // unrecognized input
        else {
            System.out.println("Invalid selection.");
        }
        scanner.close();
    }
}
