package func;

import Exceptions.ErrorTypeException;
import Exceptions.ErrorTypeFileException;
import Exceptions.LengthException;
import func.ParamsSort.DirectSort;
import func.ParamsSort.TypeSort;
import lombok.Getter;

import static Constants.Constants.TXT_FORMAT;
import static Constants.ProcessingType.*;
import static func.ParamsSort.DirectSort.*;
import static func.ParamsSort.TypeSort.INTEGER_TYPE;
import static func.ParamsSort.TypeSort.STRING_TYPE;
/**
 * This class parses the input string into an object with sort options
 * @param directSort determines the direction
 * @param typeSort defines the file type of input information
 * @param outputFile
 * @param inputFiles
 */
@Getter
public class Parser {
    private int nextPoint = 0;
    private DirectSort directSort;
    private TypeSort typeSort;
    private String outputFile;
    private String[] inputFiles;

    public Parser(String[] args) throws Exception {
        parseDirectParam(args);
        parseTypeParam(args);
        parseOutputFileParam(args);
        parseInputFileParam(args);
    }

    private void parseInputFileParam(String[] args) throws Exception {
        if(nextPoint < args.length){
            inputFiles = new String[args.length - nextPoint];
            for(int i = 0; i < inputFiles.length; i++){
                if(args[nextPoint].toLowerCase().endsWith(TXT_FORMAT)){
                    inputFiles[i] = args[nextPoint];
                }
                else{
                    throw new ErrorTypeFileException(TXT_FORMAT, args[nextPoint]);
                }
                nextPoint++;
            }
        }else{
            throw new LengthException("No one input file");
        }
    }

    private void parseOutputFileParam(String[] args) throws ErrorTypeFileException {
        if(nextPoint < args.length && args[nextPoint].toLowerCase().endsWith(TXT_FORMAT)){
            outputFile = args[nextPoint];
            nextPoint++;
        }else{
            throw new ErrorTypeFileException(TXT_FORMAT, args[nextPoint]);
        }
    }

    private void checkLength(int length) throws LengthException{
        if(length == nextPoint){
            throw new LengthException("You need to build your query with more parameters");
        }
    }

    private void parseTypeParam(String[] args) throws Exception {
        checkLength(args.length);
        String secondArg = args[nextPoint];
        chooseType(secondArg);
        nextPoint++;
    }

    private void parseDirectParam(String[] args) throws LengthException{
        checkLength(args.length);
        String firstArg = args[nextPoint];
        chooseDirect(firstArg);
    }

    private void chooseType(String arg) throws ErrorTypeException {
        if(arg.equals(INTEGER_PROCESSING)){
            typeSort = INTEGER_TYPE;
        }
        else if(arg.equals(STRING_PROCESSING)){
            typeSort = STRING_TYPE;
        }
        else{
            throw new ErrorTypeException("Error type of sort (MUST BE -s or -i). You set: ", arg);
        }
    }

    private void chooseDirect(String arg) {
        nextPoint++;
        if(arg.equals(DESCENDING_DIRECT)){
            directSort = DESCENDING;
        }
        else if(arg.equals(ASCENDING_DIRECT)){
            directSort = ASCENDING;
        }
        else{
            nextPoint--;
            directSort = ASCENDING;
        }
    }
}
