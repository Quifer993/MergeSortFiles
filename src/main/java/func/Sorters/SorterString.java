package func.Sorters;

import Constants.CompareVariants;
import func.Parser;

public class SorterString extends ISorter {
    public SorterString(Parser parser) {
        super(parser);
    }

    @Override
    public CompareVariants compare(String s1, String s2){
        if(s1 == null){
            if(isSpaceCheck(s2)){
                return CompareVariants.SECOND_STRING_ERROR;
            }else{
                return CompareVariants.SECOND_STRING;
            }
        }

        if(s2 == null){
            if(isSpaceCheck(s1)){
                return CompareVariants.FIRST_STRING_ERROR;
            }else{
                return CompareVariants.FIRST_STRING;
            }
        }

        if(isSpaceCheck(s1)){
            return CompareVariants.FIRST_STRING_ERROR;
        }
        if(isSpaceCheck(s2)){
            return CompareVariants.SECOND_STRING_ERROR;
        }

        return chooseNext(s1.compareTo(s2));
    }

    private boolean isSpaceCheck(String s){
        return s.split(" ").length > 1;
    }

}
