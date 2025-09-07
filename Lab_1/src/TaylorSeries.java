public class TaylorSeries {

    private static double calculationEpsilon(int k) {
        double epsilon = 1;
        for (int i = 0; i < k; i++) {
            epsilon /= 10;
        }
        return epsilon;
    }

    private static double myPow(double x, int n) {
        double pow_x = 1;
        for (int i = 0; i < n; i++) {
            pow_x *= x;
        }
        return pow_x;
    }

    private static long calculationFactorial(int n){
        long factorial_x = 1;
        for (int i = 2; i <= n; i++) {
            factorial_x *= i;
        }
        return factorial_x;
    }

    private static double myAbs(double x) {
        if (x < 0){
            x *= (-1);
        }
        return x;
    }

    public static double mySinh(double x, int k) {
        double shx = 0;
        double epsilon = calculationEpsilon(k);
        for (int i = 0; ; i++){
            int n = i * 2 + 1;
            long factorial_n = calculationFactorial(n);
            double term = myPow(x, n) / factorial_n;

            double term_abs = myAbs(term);

            if (term_abs > Double.MAX_VALUE){
                System.out.println("Ошибка: при вычислении очередного слагаемого, его значение" +
                        "\nвышло за пределы размерности типа данных double");
                break;
            }
            if (term_abs < epsilon){
                break;
            } else {
                shx += term;
            }
        }
        return shx;
    }
}
