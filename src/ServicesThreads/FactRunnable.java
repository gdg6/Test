package ServicesThreads;

import Services.WriteToFile;

/**
 * Created by denis on 25.07.16.
 * Поиск факториала для threadPool
 */
public class FactRunnable implements Runnable {

    private int number_line;
    private int n;
    private WriteToFile writer;


    public FactRunnable (int number_line, int n, WriteToFile writer) {
        this.number_line = number_line;
        this.n = n;
        this.writer = writer;
    }

    @Override
    public void run() {
        int answer =  fact_easy(n);
        writer.writeAnswer(number_line, answer);

    }

    //Итерационный поиск факториала. Долго, но нет переполнения стека.
    //Алгоритм показан, как
    //демонстация понимания перевода рекурсии в итерацию.
    private static int fact_easy(int n) {
        //return n == 0 ? 1 : fact_easy(n - 1) * n;
        int s = n;
        for (int i = n; i != 0;) {
            s *= (i--);
        }
        return s;
    }
}
