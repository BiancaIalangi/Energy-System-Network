import payment.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.InputData;
import output.OutputData;

import java.io.File;
import java.io.FileWriter;

public class Main {
    
    public static void main(final String[] args) throws Exception {

        File file = new File(args[0]);

        ObjectMapper objectMapper = new ObjectMapper();
        InputData inputData = objectMapper.treeToValue(
                objectMapper.readTree(file), InputData.class);

        Payment payment = new Payment(inputData);

        payment.basicRound();

        OutputData outputData = new OutputData(inputData);

        final String tobeWritten = new ObjectMapper().writeValueAsString(outputData);

        File outFile = new File(args[1]);
        FileWriter writer = new FileWriter(outFile);
        writer.write(tobeWritten);
        writer.close();
    }
}
