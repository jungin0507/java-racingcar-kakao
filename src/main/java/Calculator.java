import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    StringParser stringParser;

    public Calculator(StringParser stringParser) {
        this.stringParser = stringParser;
    }

    public Integer calc(String inputstring) {
        if (Objects.equals(inputstring, "") || inputstring == null) {
            return 0;
        }

        Matcher m = Pattern.compile("//(.+)\n(.*)").matcher(inputstring);
        if (m.find()) {
            String customDelimiter = m.group(1);
            String[] numbers = stringParser.split(m.group(2), customDelimiter);
            return stringArraySum(numbers);
        }
        String[] numbers = stringParser.split(inputstring);

        return stringArraySum(numbers);
    }

    public int stringArraySum(String[] stringArray) {
        int sum = 0;
        for (String number : stringArray) {
            sum += stringParser.parseInt(number);
        }
        return sum;
    }

}
