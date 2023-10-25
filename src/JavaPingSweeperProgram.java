import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JavaPingSweeperProgram {
    public static void main(String[] args) throws IOException {
        JavaPingSweeperProgram ping = new JavaPingSweeperProgram();
        List<String> commands = new ArrayList<String>();
        String startIP = "127.0.0.1";
        String endIP = "127.0.0.5";
        for (String ipAddress : getIPRange(startIP, endIP)) {
            commands.clear();
            commands.add("ping");
            commands.add("-c");
            commands.add("3");
            commands.add(ipAddress);
            doCommand(commands);
        }
    }
    public static void doCommand(List<String> command) throws IOException{
        String s = null;
        ProcessBuilder pb= new ProcessBuilder(command);
        Process process = pb.start();
        BufferedReader stdInput = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );
        BufferedReader stdError = new BufferedReader(
                new InputStreamReader(process.getErrorStream())
        );
        System.out.println("Here is the standard output of the command:\n");
        while((s= stdInput.readLine() )!=null){
            System.out.println(s);
        }
        System.out.println("Here is the standard error of the command (if any):\n");
        while((s= stdError.readLine()) != null){
            System.out.println(s);
        }
    }
    private static int ipToInt(String ip) {
        String[] parts = ip.split("\\.");
        int ipNumbers = 0;
        for(int i=0; i<4; i++) {
            ipNumbers += Integer.parseInt(parts[i]) << (24 - (8 * i));
        }
        return ipNumbers;
    }
    private static String intToIp(int ipNumbers) {
        return ((ipNumbers >> 24 ) & 0xFF) + "." +
                ((ipNumbers >> 16 ) & 0xFF) + "." +
                ((ipNumbers >> 8 ) & 0xFF) + "." +
                (ipNumbers & 0xFF);
    }
    private static List<String> getIPRange(String startIP, String endIP) {
        List<String> ipRange = new ArrayList<>();
        int startIPInt = ipToInt(startIP);
        int endIPInt = ipToInt(endIP);
        for (int i = startIPInt; i <= endIPInt; i++) {
            ipRange.add(intToIp(i));
        }
        return ipRange;
    }
}
