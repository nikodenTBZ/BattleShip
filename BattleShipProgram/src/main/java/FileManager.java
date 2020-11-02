import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;


public class FileManager {
    /**
     * Writes the winner in a file
     * @param winner
     */
    public void saveWinnerInFile(String winner) {
        
        try (FileWriter fw = new FileWriter("winnerLog.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(new Locale("de"));
            LocalDateTime localDateTime = LocalDateTime.now();
            String germanDateTime = localDateTime.format(formatter);
            String printout = germanDateTime + "\nWinner: " + winner + "\n";

            //write printout into FILE
            out.println(printout);
        } catch (IOException e) {
            //throw own exception
            throw new CouldNotWriteException();
        }

    }
}

