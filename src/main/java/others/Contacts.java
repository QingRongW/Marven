package others;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

class SolutionContacts {

    /*
     * Complete the contacts function below.
     */
    static int[] contacts_v1(String[][] queries) {

        String nameDB = "";
        ArrayList<String> searchDB = new ArrayList<String>();
        for (String[] line : queries) {
            String indicator = line[0];
            String name = line[1];
            if (indicator.equals("add")) {
                nameDB = nameDB + (name) + " ";
            } else if (indicator.equals("find")) {
                searchDB.add(name);
            }
        }
        int counter = searchDB.size();
        int[] number = new int[counter];
        Arrays.fill(number, 0);
        for (int i = 0; i < counter; i++) {
            String search = searchDB.get(i);
            String tempDB = nameDB;
            while (!tempDB.isEmpty()) {
                int index = tempDB.indexOf(search);
                if (index != -1) {
                    number[i] = number[i] + 1;
                    tempDB = tempDB.substring(index + search.length());
                    int spaceInx = tempDB.indexOf(" ");
                    if (spaceInx != -1) {
                        tempDB = tempDB.substring(spaceInx + 1);
                    }
                } else {
                    break;
                }
            }
        }

        return number;
    }

    static int[] contacts_v2(String[][] queries) {
        ArrayList<String> nameDB = new ArrayList<>();
        ArrayList<String> searchDB = new ArrayList<>();
        HashMap<String, Integer> searchCounter = new HashMap<>();
        for (String[] line : queries) {
            String indicator = line[0];
            String name = line[1];
            if (indicator.equals("add")) {
                nameDB.add(name);
            } else if (indicator.equals("find")) {
                searchDB.add(name);
            }
        }
        int counter = searchDB.size();
        int[] number = new int[counter];
        searchDB.stream().parallel().forEach(search -> {
            searchCounter.put(search, nameDB.stream().filter(x -> x.startsWith(search)).collect(Collectors.toList()).size());
        });
        for (int i = 0; i < counter; i++) {
            if (searchCounter.containsKey(searchDB.get(i))) {
                number[i] = searchCounter.get(searchDB.get(i));
            } else {
                number[i] = 0;
            }
        }
        return number;
    }

    static int[] contacts_v3(String[][] queries) {
        String nameDB = " ";
        ArrayList<String> searchDB = new ArrayList<>();
        ArrayList<Integer> searchNumber = new ArrayList<>();
        for (String[] line : queries) {
            String indicator = line[0];
            String name = line[1];
            if (indicator.equals("add")) {
                nameDB = nameDB + (name) + " ";
            } else if (indicator.equals("find")) {
/*
                searchNumber.add(org.apache.commons.lang3.StringUtils.countMatches(nameDB, name));
*/
            }
        }

        int size = searchNumber.size();
        int[] number = new int[size];
        for (int i = 0; i < size; i++) {
            number[i] = searchNumber.get(i);
        }
        return number;
    }

    static int[] contacts(String[][] queries) {
        int len= queries.length;
        String nameDB = " ";
        int seperator = 0;
        ArrayList<Integer> searchNumber = new ArrayList<>();

        for(int i=0; i<len; i ++){
            if("find".equals(queries[i][0] )){
                seperator = i;
                break;
            } else{
                nameDB = nameDB + queries[i][1] + " ";
            }
        }
        for(int i=seperator; i<len; i++){
            String name = queries[i][1];
        }
        int size = searchNumber.size();
        int[] number = new int[size];
        for (int i = 0; i < size; i++) {
            number[i] = searchNumber.get(i);
        }
        return number;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/home/qingrong.wang/a_work/output/output.txt"));

        int queriesRows = Integer.parseInt(scanner.nextLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));
            System.out.println(result[resultItr]);

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
