package dise;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        Service service = new Service();

        String p1 = "60";

        String p2 = "5B";

        //what you are looking for in text - P3 anti-pattern
        String p3 ="1550,1950,3150,3550,3B50,4050,5150,5450";

        String p4 = "0150,0250,0350,0450,0550,0650,0750,0850,0950,0A50, 0B50,1050,1150,1250,1350,1450,1650,1750,1850,1A50,2050";

        String p5 = "0850,0950";

        String p6 = "3050,3250,3350,3450,3550,3650,3850,3A50,4150,4350,4450,4550,5850,5950,5A50";

        //p11
        String p7 = "9050,915050,92505050,9350505050,945050505050,95505050505050,96505050505050,975050505050505050,98505050505050505050,9950505050505050505050";

        //p15
        String p8 = "8090,8191,8292,8393,8494,8595,8696,8797,8898,8999,8A9A,8B9B,8C9C,8D9D,8E9E,8F9F";

        //p23
        String p9 = "80908190, 80918190,80928190,80938190,80948190,80958190,80958190,80968190,80978190,80988190,80998190,809A8190,809B8190,809C8190,809D8190,809E8190,809F8190";

        String[] patternsArray = new String[]{p1,p2,p3,p4,p5,p6,p7,p8,p9};

        int[] gasConsumption = new int[]{3,10,200,1,4};

        int[] counterByEachPatter = new int[] {0,0,0,0,0,0,0,0,0,0};

        boolean isContractSafe = true;
        int noOftestedContracts = 0;

//        TODO to be decided
        int constantScorint = 5;

        File folder = new File(args[0]);

        for (final File fileEntry : folder.listFiles()) {
            String text ="";
            if (!fileEntry.isDirectory()) {
                noOftestedContracts++;
                text = service.getContentOfTheFile(fileEntry);

                for(int i=0; i < patternsArray.length; i++) {

                    String s = patternsArray[i];
                    boolean isSimpleCounter = s.length() == 2;
                    String[] input = new String[]{s};

                    int nrOfOcc = AlgorithmService.searchWords(input,input.length,text,isSimpleCounter);

                    if(nrOfOcc > 0) {
                        isContractSafe = false;
                        counterByEachPatter[i] += 1;
                    }

                }
            }
        }
        for(int i = 0; i < counterByEachPatter.length; i++) {
            System.out.print("P" + String.valueOf(i) + ":"+ counterByEachPatter[i]);
            System.out.print("\n");
        }

        service.consolePercentage(counterByEachPatter,noOftestedContracts);

        int nrOfEncounteredPatterns = Arrays.stream(counterByEachPatter).sum();

        if(nrOfEncounteredPatterns > constantScorint) {

            int gas = 0;
            for(int i=0; i < gasConsumption.length; i++) {
                gas += counterByEachPatter[i] * gasConsumption[i];
            }
            System.out.print("\n");
            System.out.print("By optimizing your code you can save: " + gas + " gas");
        }
    }
}
