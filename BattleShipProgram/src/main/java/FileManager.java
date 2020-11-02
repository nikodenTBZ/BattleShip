 import java.io.BufferedWriter;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
import java.time.LocalDateTime;
 import java.time.format.DateTimeFormatter;
 import java.time.format.FormatStyle;
 import java.util.Locale;
 import static Tools.Constants.*;

 public class FileManager {
    public void saveWinnerInFile(String winner) {

        LocalDateTime localDateTime = LocalDateTime.now();


        try(FileWriter fw = new FileWriter("winnerLog.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(new Locale("de"));
            String germanDateTime = localDateTime.format(formatter);
            String printout = germanDateTime.toString() + "\nWinner: " + winner + "\n";

            out.println(printout);
        } catch (IOException e) {
            //throw own exception
            throw new CouldNotWriteException();
        }

    }
}

