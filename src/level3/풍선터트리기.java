package level3;

public class 풍선터트리기 {

    public static void main(String[] args) {
        풍선터트리기_Solution solution = new 풍선터트리기_Solution();
//        int[] a = {9, -1, -5};
        int[] a = {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33};
        System.out.println("solution.solution(a) = " + solution.solution(a));
    }
}

/**
 * goal: 풍선이 1개가 남을 때까지 주어진 조건에 맞게 풍선 터트려서 최후까지 남기는 것이 가능한 풍선들의 개수 구하기
 */

/**
 * 조건
 * 1. 인접한 두 풍선 중 번호가 더 작은 풍선을 터트리는 행위는 최대 1번 즉, 1번을 제외하고는 다 큰 번호의 풍선 터트림
 */

/**
 * 문제 풀이 순서
 * 1. 풍선이 든 배열을 처음부터 끝까지, 끝에서부터 처음까지 확인하면서 자신의 왼쪽, 오른쪽 작은값이 있는지를 체크한다.
 * 2. 다시 처음부터 순회하면서 왼쪽 오른쪽 모두 작은 값이 있다면 최후까지 남을 수 없는 풍선이라고 판단
 */

class 풍선터트리기_Solution {
    public int solution(int[] a) {
        Balloon[] balloons = new Balloon[a.length];
        initBalloons(a, balloons);
        int result = 0;
        for (int i = 0; i < balloons.length; i++) {
            if (balloons[i].left && balloons[i].right) {
                continue;
            }
            result++;
        }
        return result;
    }

    private void initBalloons(int[] a, Balloon[] balloons) {
        int minLeft = Integer.MAX_VALUE;
        int minRight = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            Balloon balloon = new Balloon();
            balloons[i] = balloon;
            if (minLeft > a[i]) {
                minLeft = a[i];
                continue;
            }
            balloon.left = true;
        }

        for (int i = a.length - 1; i >= 0; i--) {
            Balloon balloon = balloons[i];
            if (minRight > a[i]) {
                minRight = a[i];
                continue;
            }
            balloon.right = true;
        }
    }

}

class Balloon {
    boolean left;
    boolean right;

    public Balloon() {
        this.left = false;
        this.right = false;
    }
}


