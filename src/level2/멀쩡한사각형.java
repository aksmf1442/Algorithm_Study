package level2;

public class 멀쩡한사각형 {

    public static void main(String[] args) {
        멀쩡한사각형_Solution solution = new 멀쩡한사각형_Solution();
        int W = 8;
        int H = 12;
        System.out.println("solution.solution(W, H) = " + solution.solution(W, H));
    }
}

class 멀쩡한사각형_Solution {

    public long solution(int w, int h) {
        // 최대공약수 구하기
        long gcd = findGcd(w, h);

        return ((long) w * h) -  ((w / gcd) + (h / gcd) - 1) * gcd;
    }

    private int findGcd(int w, int h) {
        int gcd = 0;
        int min = Math.min(w, h);
        for (int i = 1; i <= min; i++) {
            if (w % i == 0 && h % i == 0)
                gcd = i;
        }
        return gcd;
    }
}
