package Services;

import ServicesThreads.AckRunnable;
import ServicesThreads.FactRunnable;
import ServicesThreads.FibRunnable;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by denis on 25.07.16.
 */
public class ExecutorProcessor {

    private ExecutorService executor;
    private WriteToFile writeToFile;

    public ExecutorProcessor() throws FileNotFoundException, UnsupportedEncodingException {
        int cores = Runtime.getRuntime().availableProcessors();
        cores = cores < 2 ? 4 : cores;
        this.executor = Executors.newFixedThreadPool(cores);
        writeToFile = new WriteToFile();
    }

    public void addFunction(int number_string, String line) {

        Runnable worker = null;
        String [] words = line.split("\\s+");
        int m;

        if (words.length < 2){
            System.out.format("line %d errors argument. Count %d", number_string, words.length);
            return;
        }
        int n = Integer.parseInt(words[1]);
        if(words[0].equals("F")) {
            worker = new FactRunnable(number_string, n, writeToFile);
        } else if (words[0].equals("ACK")) {
            m = Integer.parseInt(words[1]);
            n = Integer.parseInt(words[2]);
            worker = new AckRunnable(number_string, m, n, writeToFile);
        } else if (words[0].equals("FIB")) {
            worker = new FibRunnable(number_string, n, writeToFile);
        }
        if (worker != null) {
            this.executor.execute(worker);
        }
    }

    public void finished() {
        this.executor.shutdown();
        while (!this.executor.isTerminated()) {
        }
    }

}
