package func.Sorters;

import Constants.CompareVariants;
import func.ParamsSort.DirectSort;
import func.Parser;

import java.io.*;

import static Constants.CompareVariants.*;
import static Constants.Constants.DIRECTORY_WORKING_FILES;
import static Constants.Constants.TXT_FORMAT;

public class ISorter {
    Parser parser;
    File[] files = new File[3];

    public ISorter(Parser parser) { this.parser = parser; }

        public void mergeSort() throws IOException {
            int i = 0;
            files[2] = new File("src/main/resources/emptiesFiles/empty" + TXT_FORMAT);
            files[2].createNewFile();
            for (String fileName: parser.getInputFiles()) {
                files[(i+1)%3] = new File(DIRECTORY_WORKING_FILES + fileName);
                try {
                    var reader1 = new BufferedReader(new FileReader(files[(i+1)%3]));
                    var reader2 = new BufferedReader(new FileReader(files[(i+2)%3]));
                    files[i%3] = new File("src/main/resources/emptiesFiles/empty" + i%3 + TXT_FORMAT);
                    files[i%3].delete();
                    files[i%3].createNewFile();
                    var writer = new BufferedWriter(new FileWriter(files[i%3]));
                    String s1 = reader1.readLine();
                    String s2 = reader2.readLine();
                    while(s1 != null || s2 != null){
                        CompareVariants compareVariants = compare(s1, s2);

                        if(compareVariants == FIRST_STRING){
                            writer.write(s1 + "\n");
                            s1 = reader1.readLine();
                        }
                        else if(compareVariants == SECOND_STRING){
                            writer.write(s2+ "\n");
                            s2 = reader2.readLine();
                        }
                        else if(compareVariants == FIRST_STRING_ERROR){
                            s1 = reader1.readLine();
                        }
                        else if(compareVariants == SECOND_STRING_ERROR){
                            s2 = reader2.readLine();
                        }
                    }

                    reader1.close();
                    reader2.close();
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
        }


    public CompareVariants compare(String s1, String s2) {
        return CompareVariants.FIRST_STRING;//В ISorter не имеет смысла
    }

    CompareVariants chooseNext(int compareAnswer) {
        if(compareAnswer >= 0){
            if(parser.getDirectSort() == DirectSort.ASCENDING){
                return FIRST_STRING;
            }else{
                return SECOND_STRING;
            }
        }
        else{
            if(parser.getDirectSort() == DirectSort.ASCENDING){
                return SECOND_STRING;
            }else{
                return FIRST_STRING;
            }
        }
    }
}
