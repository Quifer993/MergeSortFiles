package func.Sorters;

import Constants.CompareVariants;
import func.Parser;

import java.math.BigInteger;

import static Constants.CompareVariants.*;
import static Constants.CompareVariants.SECOND_STRING;

public class SorterInt extends ISorter{
    public SorterInt(Parser parser) {
        super(parser);
    }

    @Override
    public CompareVariants compare(String s1, String s2) {
        Long long1 = parseLong(s1);
        Long long2 = parseLong(s2);

        if (long1 != null && long2 != null) {
            return chooseNext(long1.compareTo(long2));
        } else {
            BigInteger bigInteger1;
            BigInteger bigInteger2;
            try{
                bigInteger1 = new BigInteger(s1);
            }catch(Exception e){
                if(s1 != null) {
                    return FIRST_STRING_ERROR;
                }
                else{
                    try{
                        bigInteger2 = new BigInteger(s2);
                    }catch(Exception ee){
                        return SECOND_STRING_ERROR;
                    }
                    return SECOND_STRING;
                }

            }

            try{
                bigInteger2 = new BigInteger(s2);
            }catch(Exception e){
                if(s2 != null){
                    return SECOND_STRING_ERROR;
                }
                else{
                    return FIRST_STRING;
                }
            }

            return chooseNext(bigInteger1.compareTo(bigInteger2));
        }
    }

    // Метод, который пытается преобразовать строку в значение Long
    private static Long parseLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
