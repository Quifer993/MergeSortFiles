package func.Sorters;

import Constants.CompareVariants;
import func.ParamsSort.DirectSort;
import func.Parser;

import java.io.*;

import static Constants.CompareVariants.*;
import static Constants.Constants.*;

public class ISorter {
    Parser parser;
    File[] files = new File[3];
    String lastElement;

    public ISorter(Parser parser) { this.parser = parser; }

        public void mergeSort() throws Exception {
            int i = 0;
            files[2] = new File("src/main/resources/emptiesFiles/empty" + TXT_FORMAT);
            files[2].createNewFile();
            for (String fileName: parser.getInputFiles()) {
                files[(i+1)%3] = new File(DIRECTORY_WORKING_FILES + fileName);
                try {
                    BufferedReader reader1;
                    BufferedReader reader2;
                    try {
                        reader1 = new BufferedReader(new FileReader(files[(i+1)%3]));
                    }catch (FileNotFoundException fileNotFoundException){
                        System.out.println("File " + fileName + " not found");
                        continue;
                    }
                    reader2 = new BufferedReader(new FileReader(files[(i+2)%3]));
                    files[i%3] = new File("src/main/resources/emptiesFiles/empty" + i%3 + TXT_FORMAT);
                    files[i%3].delete();
                    files[i%3].createNewFile();
                    var writer = new BufferedWriter(new FileWriter(files[i%3]));
                    String s1 = readLineFromFile(reader1);
                    String s2 = readLineFromFile(reader2);
                    lastElement = null;

                    while(s1 != null || s2 != null){
                        CompareVariants compareVariants = compare(s1, s2);

                        if(compareVariants == FIRST_STRING || compareVariants == FIRST_STRING_ERROR){
                            if(compareVariants != FIRST_STRING_ERROR){
                                var t = compare(s1, lastElement);
                                if(compare(lastElement, s1) == FIRST_STRING || lastElement == null){
                                    lastElement = s1;
                                    writer.write(s1 + "\n");
                                }
                            }
                            s1 = readLineFromFile(reader1);
                        }
                        else if(compareVariants == SECOND_STRING || compareVariants == SECOND_STRING_ERROR){
                            if(compareVariants != SECOND_STRING_ERROR){
                                if(compare(lastElement, s2) == FIRST_STRING || lastElement == null){
                                    lastElement = s2;
                                    writer.write(s2 + "\n");
                                }
                            }
                            s2 = readLineFromFile(reader2);
                        }
                    }
                    reader1.close();
                    reader2.close();
                    files[(i+2)%3].delete();
                    writer.flush();
                    writer.close();
                    i++;
                } catch (IOException e) {
                    System.out.println("Error read file: " + fileName);
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            if(!files[(i+2)%3].renameTo(new File(DIRECTORY_OUTPUT_FILES + parser.getOutputFile()))){
                File fileOut = new File (DIRECTORY_OUTPUT_FILES + parser.getOutputFile());
                if(!fileOut.delete()){
                    throw new Exception("Can't delete old output file - " + parser.getOutputFile());
                }
                else{
                    if(!files[(i+2)%3].renameTo(new File(DIRECTORY_OUTPUT_FILES + parser.getOutputFile()))){
                        throw new Exception("Output file: " + parser.getOutputFile() + " not move in outputFiles directory." +
                                "If u get this truble, try check src/main/resources/emptiesFiles");
                    }
                }
            }
        }

    private String readLineFromFile(BufferedReader reader1) {
        String s1 = null;
        try{
            s1 = reader1.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        return s1;
    }


    public CompareVariants compare(String s1, String s2) {
        return CompareVariants.FIRST_STRING;//В ISorter не имеет смысла
    }

    CompareVariants chooseNext(int compareAnswer) {
        if(compareAnswer >= 0){
            if(parser.getDirectSort() == DirectSort.ASCENDING){
                return SECOND_STRING;
            }else{
                return FIRST_STRING;
            }
        }
        else{
            if(parser.getDirectSort() == DirectSort.ASCENDING){
                return FIRST_STRING;
            }else{
                return SECOND_STRING;
            }
        }
    }
}
