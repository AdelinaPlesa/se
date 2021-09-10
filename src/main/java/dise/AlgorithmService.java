package dise;


import java.util.*;


public class AlgorithmService {
    static int MAX_NO_OF_CHARACTERS = 200;

    static int MAX_NO_OF_STATES = 10000;

    static int[] outFunction = new int[MAX_NO_OF_STATES];

    static int[] failureFunction = new int[MAX_NO_OF_STATES];


    static int[][] gotoFunction = new int[MAX_NO_OF_STATES][MAX_NO_OF_CHARACTERS];

    static int buildMatchingMachine(String arr[], int k) {

        Arrays.fill(outFunction, 0);

        for (int i = 0; i < MAX_NO_OF_STATES; i++)
            Arrays.fill(gotoFunction[i], -1);

        int states = 1;


        for (int i = 0; i < k; ++i) {
            String word = arr[i];
            int currentState = 0;


            for (int j = 0; j < word.length(); ++j) {
                int ch = word.charAt(j);

                if (gotoFunction[currentState][ch] == -1)
                    gotoFunction[currentState][ch] = states++;

                currentState = gotoFunction[currentState][ch];
            }

            outFunction[currentState] |= (1 << i);
        }

        for (int ch = 0; ch < MAX_NO_OF_CHARACTERS; ++ch)
            if (gotoFunction[0][ch] == -1)
                gotoFunction[0][ch] = 0;


        Arrays.fill(failureFunction, -1);


        Queue<Integer> q = new LinkedList<>();

        for (int ch = 0; ch < MAX_NO_OF_CHARACTERS; ++ch) {

            if (gotoFunction[0][ch] != 0) {
                failureFunction[gotoFunction[0][ch]] = 0;
                q.add(gotoFunction[0][ch]);
            }
        }

        while (!q.isEmpty()) {

            int state = q.peek();
            q.remove();

            for (int ch = 0; ch < MAX_NO_OF_CHARACTERS; ++ch) {

                if (gotoFunction[state][ch] != -1) {

                    int failure = failureFunction[state];

                    while (gotoFunction[failure][ch] == -1)
                        failure = failureFunction[failure];

                    failure = gotoFunction[failure][ch];
                    failureFunction[gotoFunction[state][ch]] = failure;

                    outFunction[gotoFunction[state][ch]] |= outFunction[failure];

                    q.add(gotoFunction[state][ch]);
                }
            }
        }
        return states;
    }

    static int findNextState(int currentState, char nextInput) {
        int answer = currentState;
        int ch = nextInput;

        while (gotoFunction[answer][ch] == -1)
            answer = failureFunction[answer];

        return gotoFunction[answer][ch];
    }


    public static int searchWords(String[] arr, int k,
                                  String text, boolean isSimpleCounter) {

        buildMatchingMachine(arr, k);

        List<Integer> list = new ArrayList<>();

        int currentState = 0;

        for (int i = 0; i < text.length(); ++i) {
            currentState = findNextState(currentState,
                    text.charAt(i));

            if (outFunction[currentState] == 0)
                continue;

            for (int j = 0; j < k; ++j) {
                if ((outFunction[currentState] & (1 << j)) > 0) {
                    if(i % 2 != 0) {
//                        System.out.print("Word " + arr[j] +
//                                " appears from " +
//                                (i - arr[j].length() + 1) +
//                                " to " + i + "\n");
                        list.add(i - 1);
                    }
                }
            }
        }

//        for (int i : list ){
//            System.out.print(i + " ");
//        }
//        System.out.println();
        return countNumberOfDuplicatedOperations(list, isSimpleCounter);
    }
    private static int countNumberOfDuplicatedOperations(List<Integer> list, boolean isSimpleCounter) {
        int nrOfMatches = 0;

//        for(int element : list) {
//            if (element == list.indexOf(element) - 2) {
//                nrOfMatches++;
//            }
//        }

        if(isSimpleCounter) {
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) == list.get(i + 1) - 2) {
                    nrOfMatches++;
                }
            }
        } else {
            nrOfMatches = list.size();
        }
        return nrOfMatches;
    }
}
