package level3;

public class 광고삽입 {

    public static void main(String[] args) {
        String playTime = "02:03:55";
        String advTime = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29",
            "01:30:59-01:53:29", "01:37:44-02:02:30"};
        광고삽입_Solution solution = new 광고삽입_Solution();
        System.out.println(
            "solution.solution(playTime, advTime, logs) = " + solution.solution(playTime, advTime,
                logs));
    }
}

class 광고삽입_Solution {

    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = timeToSecond(play_time);
        int advTime = timeToSecond(adv_time);
        int[] total = new int[playTime + 1];

        for (String log : logs) {
            String[] arr = log.split("-");

            int start = timeToSecond(arr[0]);
            int end = timeToSecond(arr[1]);

            for (int j = start; j < end; j++) {
                total[j]++;
            }
        }

        long sum = 0;
        for (int i = 0; i < advTime; i++) {
            sum += total[i];
        }

        long max = sum;
        int start = 0;
        for (int i = 1, j = advTime; j < playTime; i++, j++) {
            sum += total[j] - total[i - 1];

            if (max < sum) {
                max = sum;
                start = i;
            }
        }

        return secondToTime(start);
    }

    private String secondToTime(int second) {
        StringBuilder sb = new StringBuilder();

        for (int i = 2; i >= 0; i--) {
            int t = second / (int) Math.pow(60, i);
            second %= (int) Math.pow(60, i);

            if (t < 10) {
                sb.append(0).append(t);
            } else {
                sb.append(t);
            }

            if (i != 0)
                sb.append(":");
        }

        return sb.toString();
    }

    private int timeToSecond(String time) {
        int second = 0;
        String[] arr = time.split(":");

        second += Integer.parseInt(arr[0]) * 3600;
        second += Integer.parseInt(arr[1]) * 60;
        second += Integer.parseInt(arr[2]);

        return second;
    }


}
