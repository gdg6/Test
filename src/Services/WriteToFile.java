package Services;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by denis on 25.07.16.
 * Запись в файл с синхронизацией. Вообще по хорошему
 * можно использовать очередь на запись, но пускай останется пока что так.
 */
public class WriteToFile {

    private PrintWriter writer;

    WriteToFile() throws FileNotFoundException, UnsupportedEncodingException {
        this.writer = new PrintWriter("output.txt", "UTF-8");
    }

    public void writeAnswer(int number_line, int answer) {
        synchronized (this) {
            writer.format("%d %d\n", number_line, answer);
            writer.flush();
        }
    }
}
