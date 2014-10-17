package sample.formatter;


import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;


public class formatter {

    public static void main(String[] args) {
        String sFileName = "C:\\Users\\Катрин\\IdeaProjects\\lab1\\src\\sample\\formatter\\data.txt"; // Строка для имени файла
        System.out.println("\nFILE TEST\n");
        String stroka = "\n";
        String fil = new String();

        try {
            OutputStreamWriter OSW = new OutputStreamWriter(System.out);
            PrintWriter PW = new PrintWriter(OSW, true);

            try {
                FileInputStream in = new FileInputStream(sFileName);
                FileReader FR = new FileReader(sFileName);
                BufferedReader BRf = new BufferedReader(FR);
                int c = 0;


                stroka = BRf.readLine();
                while (stroka != null) {
                    fil += stroka + " ";
                    stroka = BRf.readLine();
                }

                StringBuffer f = new StringBuffer(fil.length() * 2);
                f = f.append(fil);
                for (int i = 1; i < f.length(); i++) {
                    if(f.charAt(i-1)==' ' && f.charAt(i)==' '){
                        f.deleteCharAt(i);
                    }
                }

                PW.print(fil);
                int start = 0;
                boolean sk = false;
                // int cur = 0;

                for (int i = 0; i < f.length(); i++) {

                    switch (f.charAt(i)) {
                        case '{':
                            if (f.charAt(i + 1) != '\n') {
                                f.insert(i, "\n");
                                for (int n = 0; n < c; n++) {
                                    f.insert(i + 2, "    ");
                                    i += 4;
                                }
                                f.insert(i + 1, "\n");
                            }
                            c++;
                            for (int y = 0; y < c; y++) {
                                f.insert(i + 2, "    ");
                                i += 4;
                            }
                            break;
                        case '}':
                            if (f.charAt(i + 1) != '\n') {
                                f.insert(i + 1, "\n");
                            }
                            c--;
                            for (int y = 0; y < c; y++) {
                                f.insert(i + 2, "    ");
                                i += 4;
                            }
                            break;
                        case '\n':
                            for (int y = 0; y < c; y++) {
                                f.insert(i + 1, "    ");
                                i += 4;
                            }
                            break;
                        case ';':
                            if (f.charAt(i + 1) != '\n') {
                                f.insert(i + 1, "\n");
                            }
                            break;


                    }

                }

                FileWriter FW = new FileWriter("C:\\Users\\Катрин\\IdeaProjects\\lab1\\src\\sample\\formatter\\data2.txt");
                BufferedWriter BWf = new BufferedWriter(FW);
                fil = f.toString();
                BWf.write(fil);
                BWf.close();

                PW.println("\n\nФАЙЛ ЗАПИСАН.\n\n\n");


            } catch (FileNotFoundException e) {
                PW.println("Файл " + sFileName + " не найден.");
            }
        } catch (Exception e) {
            System.out.println("Something is wrong....");
        }
    }

}
