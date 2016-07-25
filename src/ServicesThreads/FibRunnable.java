package ServicesThreads;

import Services.WriteToFile;

/**
 * Created by denis on 25.07.16.
 * Поиск числа Фибоначчи для threadPool
 */
public class FibRunnable implements Runnable {

    private int number_line;
    private int n;
    private WriteToFile writer;

    public FibRunnable (int number_line, int n, WriteToFile writer) {
        this.number_line = number_line;
        this.n = n;
        this.writer = writer;
    }

    @Override
    public void run() {
        int answer = fib_easy(n);
        writer.writeAnswer(number_line, answer);
    }

    // Рекурсивный алгоритм фибоначи.
    // По хорошему для больших значений необходимо использовать
    // матричный анализ, либо формулу Бине.
    // В данном случае, кроме долгой работы, грозит переполнением стека вызовов.
    private static int fib_easy(int n)
    {
        return n > 2 ? fib_easy(n-1) + fib_easy(n-2) : 1;
    }
}
