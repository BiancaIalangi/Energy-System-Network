import fileio.Consumers;
import fileio.Distributor;
import fileio.Producer;
import output.OutputData;
import payment.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.InputData;
import java.io.File;
import java.io.FileWriter;

/**
 * Entry point to the simulation
 */

public final class Main {

    private Main() { }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files, parsing JSON
     */
    public static void main(final String[] args) throws Exception {
//        File file = new File("checker/resources/in/basic_7.json");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        InputData inputData = objectMapper.treeToValue(
//                objectMapper.readTree(file), InputData.class);
//
//        System.out.println("----------------------------");
//        System.out.println("before " + inputData.getInitialData().getDistributors());
//        System.out.println("----------------------------");
//        Payment payment = new Payment(inputData);
//
//        payment.noChangeRound();
//
//        for (Consumers consumers : payment.consumers)
//            System.out.println(consumers);
//
//        for (Distributor distributor : payment.distributors)
//            System.out.println(distributor);
//
//        for (Producer producer : payment.producers)
//            System.out.println(producer);

        File file = new File(args[0]);

        ObjectMapper objectMapper = new ObjectMapper();
        InputData inputData = objectMapper.treeToValue(
                objectMapper.readTree(file), InputData.class);

        Payment payment = new Payment(inputData);

        payment.noChangeRound();

        OutputData outputData = new OutputData(inputData);

        final String tobeWritten = new ObjectMapper().writeValueAsString(outputData);

        File outFile = new File(args[1]);
        FileWriter writer = new FileWriter(outFile);
        writer.write(tobeWritten);
        writer.close();
    }
}
