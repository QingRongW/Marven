import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Amazon {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public ArrayList<String> popularNToys(int numToys,
                                          int topToys,
                                          List<String> toys,
                                          int numQuotes,
                                          List<String> quotes) {
        HashMap<String, Integer> toyByNumber = new HashMap<>();
        HashMap<String, Boolean> toyCounted = new HashMap<>();

        if(topToys<0 || numToys<=0 || numQuotes<=0 || quotes.isEmpty() || toys.isEmpty() ){
            return new ArrayList<>();
        }

        // WRITE YOUR CODE HERE
        for (int j = 0; j < numQuotes && j < quotes.size(); j++) {
            String quote = quotes.get(j);
            String[] quoteWords = quote.split(" ");
            for (int i = 0; i < quoteWords.length; i++) {
                if (toys.contains(quoteWords[i])) {
                    String potencialToy = quoteWords[i];
                    if (toys.contains(potencialToy) && !toyCounted.get(potencialToy)) {
                        if (toyByNumber.containsKey(potencialToy)) {
                            toyByNumber.put(potencialToy, toyByNumber.get(potencialToy) + 1);
                        } else {
                            toyByNumber.put(potencialToy, 1);
                        }
                        toyCounted.put(potencialToy, true);
                    }

                }
            }
            toyCounted.clear();
        }

        // sort the number of toys
        int[] temp = new int[numToys];
        int index = 0;
        for (Integer i : toyByNumber.values()) {
            temp[index] = i * (-1);
            index++;
        }

        Arrays.sort(temp);
        ArrayList<String> toysMostFreq = new ArrayList<>();

        for (int i = 0; i < topToys; i++) {
            int number = Math.abs(temp[i]);
            for (Entry toy : toyByNumber.entrySet()) {
                if ((int) toy.getValue() == number) {
                    toysMostFreq.add((String) toy.getKey());
                }

            }
        }
        return toysMostFreq;
    }
    // METHOD SIGNATURE ENDS
}
