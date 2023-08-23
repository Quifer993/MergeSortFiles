import func.ParamsSort.TypeSort;
import func.Parser;
import func.Sorters.ISorter;
import func.Sorters.SorterInt;
import func.Sorters.SorterString;

public class Main {
    public static void main(String[] args) {
        try {
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
}
