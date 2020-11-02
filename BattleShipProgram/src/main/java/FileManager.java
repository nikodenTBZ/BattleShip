 import java.io.BufferedWriter;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
import java.time.LocalDateTime;

public class FileManager {
    public void saveWinnerInFile(String winner) {

        LocalDateTime localDateTime = LocalDateTime.now();
        String printout = localDateTime.toString() + winner;

        try(FileWriter fw = new FileWriter("winnerLog.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(printout);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }
}

