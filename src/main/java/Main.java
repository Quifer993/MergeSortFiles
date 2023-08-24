import func.ParamsSort.TypeSort;
import func.Parser;
import func.Sorters.ISorter;
import func.Sorters.SorterInt;
import func.Sorters.SorterString;

import java.io.File;

import static Constants.Constants.*;

public class Main {
    public static void main(String[] args) {
        try {
            //If these dir`s not exists, create them
            createDirectories(DIRECTORY_WORKING_FILES);
            createDirectories(DIRECTORY_OUTPUT_FILES);
            createDirectories(DIRECTORY_EMPTY_FILES);

            Parser parser = new Parser(args);
            ISorter sorter;
            if(parser.getTypeSort() == TypeSort.INTEGER_TYPE){
                sorter = new SorterInt(parser);
            } else if (parser.getTypeSort() == TypeSort.STRING_TYPE) {
                sorter = new SorterString(parser);
            }
            else{
                throw new Exception("Error identity type sort");
            }
            sorter.mergeSort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createDirectories(String dir) {
        File directory = new File(dir);
        if (directory.mkdir()) {
            System.out.println("Directory " + dir + " successfully created!");
        }
    }
}
