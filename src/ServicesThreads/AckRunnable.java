package ServicesThreads;

import Services.WriteToFile;

/**
 * Created by denis on 25.07.16.
 * Поиск значения функции Аккермана для ThreadPool
 */
public class AckRunnable implements Runnable {
    private static int N_PROCEESSED = 5;
    private static int M_PROCESSED = 3;
    //Массив заранее вычисленных значений
    private static int [][] array = {
            {1, 2, 3,  5}, //n=0
            {2, 3, 5,  13}, //n = 1
            {3, 4, 7,  29}, //n = 2
            {4, 5, 9,  61}, //n = 3
            {5, 6, 11, 125}, //n = 4
            {6, 7, 13, 253}}; //n = 5

    private int n;
    private int m;
    private int number_line;
    private WriteToFile writer;

    public AckRunnable (int number_line, int m, int n, WriteToFile writer) {
        this.m = m;
        this.n = n;
        this.number_line = number_line;
        this.writer = writer;
    }


    @Override
    public void run() {
        int answer = ack_easy(m, n);
        writer.writeAnswer(number_line, answer);
    }

    //Функция Аккермана
    //Только для положительных чисел. Для нити при "больших" значениях
    //вызывает переполнение стека. По хорошему необходимо переписать на итерацию.
    private static int ack_easy(int m, int n){
        if (m <= M_PROCESSED && n <= N_PROCEESSED ) {
            return array[n][m];
        }
        if (m == 0) {
            return n + 1;
        } else if(m > 0 && n == 0){
            return ack_easy(m-1, 1);
        }
        return  ack_easy(m-1, ack_easy(m, n-1));
    }
}
