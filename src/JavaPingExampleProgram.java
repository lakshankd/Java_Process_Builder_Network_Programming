import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JavaPingExampleProgram {
    public static void main(String[] args) throws IOException {
        // Create an instance of the Task_02.JavaPingExampleProgram class
        JavaPingExampleProgram ping = new JavaPingExampleProgram();

        // Create a list to store the command and its arguments
        List<String> commands = new ArrayList<String>();

        // Add the individual components of the "ping" command to the list
        commands.add("ping");  // The command itself
        commands.add("-c");    // Option to specify the count of ping requests
        commands.add("5");     // The number of ping requests (5 in this case)
        commands.add("127.0.0.1");  // The IP address to ping

        // Call the doCommand method to execute the command
        ping.doCommand(commands);
    }

    public void doCommand(List<String> command) throws IOException {
        String s = null;

        // Create a ProcessBuilder with the specified command and arguments
        ProcessBuilder pb = new ProcessBuilder(command);

        // Start the process
        Process process = pb.start();

        // Create BufferedReader objects to read the standard output and standard error streams
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        // Print a message indicating that standard output will be displayed
        System.out.println("Here is the standard output of the command:\n");

        // Read and display the lines from the standard output of the command
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }

        // Print a message indicating that standard error will be displayed (if any)
        System.out.println("Here is the standard error of the command (if any): \n");

        // Read and display any error messages from the standard error stream
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }
}