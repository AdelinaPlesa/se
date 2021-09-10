package dise;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MainClass {

    public static void main(String[] args) throws IOException, InterruptedException {

        Service service = new Service();

//        if(args.length =! 3) {
//            throw new Error("Missing parameter or larger number of param");
//        }

        String text = new String();
        String digit = "0";
        String regex = ".*?if \\(var" + digit + "[^|&\\n]*?\\) \\{.*?" +
                "if \\(var" + digit + "[^|&\\n]*?\\) \\{.*?\\}" +
                ".*?\\}.*?";

//        String regex = ".*?if\\(var[0-9]+[== > <][^|&\\n]*?\\)\\{.*?" +
//                "if\\([^|&\\n]*?\\)\\{ .*?\\}" +
//                ".*?\\}.*?";

        //String text1 = "ghvg if(a6){ ghvgh if(a<b){ 323} nbjh} 847";


        //String text1 = "ghvg if(var0==5){ ghvgh if(var0<b){ 323} nbjh} 847";
        String text1 = "if (var0 == 0x06fdde03) {\n" +
                "                            // Dispatch table entry for name()\n" +
                "                            var var1 = msg.value;\n" +
                "                        \n" +
                "                            if (var0) { revert(memory[0x00:0x00]); }\n" +
                "                        \n" +
                "                            var1 = 0x01dc;\n" +
                "                            var1 = name();\n" +
                "                        \n" +
                "                        label_01DC:\n" +
                "                            var temp0 = memory[0x40:0x60];\n" +
                "                            memory[temp0:temp0 + 0x20] = 0x20;\n" +
                "                            var temp1 = var1;\n" +
                "                            memory[temp0 + 0x20:temp0 + 0x20 + 0x20] = memory[temp1:temp1 + 0x20];\n" +
                "                            var var2 = temp0;\n" +
                "                            var var3 = var2;\n" +
                "                            var var4 = var3 + 0x40;\n" +
                "                            var var5 = temp1 + 0x20;\n" +
                "                            var var6 = memory[temp1:temp1 + 0x20];\n" +
                "                            var var7 = var6;\n" +
                "                            var var8 = var4;\n" +
                "                            var var9 = var5;\n" +
                "                            var var10 = 0x00;\n" +
                "\t\t\t\t\t\t\t}";
        boolean b = Pattern.compile(regex).matcher(text1).matches();
        System.out.println(b);


//        String text = service.getContentOfTheFile(new File(args[0]));

        String[] input = new String[]{};

        String[] myList = new String[]{};
        myList = service.getContentOfTheFile(new File(args[0])).split("(?<=\\G.)");

        List<ObjectFoundAfterPattern> myObjList = new ArrayList<>();


        for (int i = 0; i < myList.length; i += 2) {

            String tmpValue1 = "";
            String tmpValue2 = "";
            String operation = myList[i] + myList[i + 1];

            ObjectFoundAfterPattern newObj = new ObjectFoundAfterPattern();


            if (operation.equals(SimpleEnums.LT.value)) {
                String lastOperation = myList[i - 2] + myList[i - 1];
                newObj.setOperation(SimpleEnums.LT.value);
                Optional<SimpleEnums> s = Arrays.stream(SimpleEnums.values()).filter(item -> item.value.equals(lastOperation)).findFirst();
                int j = i;
                int k = 0;
                if (s.isPresent()) {
                    tmpValue1 = s.get().value;


                    for (j = i; j >= 0; j -= 2) {
                        String[] finalMyList1 = myList;
                        boolean result = true;
                        k = j;
                        for (EnumsWithTwoParams item : EnumsWithTwoParams.values()) {
                            if (item.value.equals(finalMyList1[j - 2] + finalMyList1[j - 1])) {
                                result = false;
                                break;
                            }
                        }
                        if (!result) {
                            break;
                        } else {
                            tmpValue2 = myList[k - 2] + myList[k - 1] + tmpValue2;
                        }

                    }
                } else {
                    for (j = i; j >= 0; j -= 2) {
                        String[] finalMyList1 = myList;
                        boolean result = true;
                        k = j;
                        for (EnumsWithTwoParams item : EnumsWithTwoParams.values()) {
                            if (item.value.equals(finalMyList1[j - 2] + finalMyList1[j - 1])) {
                                result = false;
                                break;
                            }
                        }


                        if (!result) {
                            break;
                        } else {
                            tmpValue1 = myList[k - 2] + myList[k - 1] + tmpValue1;
                        }

                    }
                    tmpValue2 = myList[k - 4] + myList[k - 3];
                }
            }
            if (!tmpValue1.isEmpty() && !tmpValue2.isEmpty()) {
                newObj.setFirstOperand(tmpValue1);
                newObj.setSecondOperand(tmpValue2);
                myObjList.add(newObj);

            }


        }
        myObjList.stream().forEach(item -> System.out.println(item.getFirstOperand() + "          " + item.secondOperand));
    }

}

