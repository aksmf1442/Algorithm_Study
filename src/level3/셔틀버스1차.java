package level3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 셔틀버스1차 {

    public static void main(String[] args) {
        셔틀버스1차_Solution solution = new 셔틀버스1차_Solution();
        int n = 1;
        int t = 1;
        int m = 5;
        String[] timeTable = {"08:02", "08:00", "08:01", "08:03"};
        String result = solution.solution(n, t, m, timeTable);
        System.out.println("result = " + result);
    }
}

/**
 * 알아야 할 것
 * 1. 버스는 9시부터 운행한다.
 * 2. 콘은 같은 시간에 도착하더라도 가장 우선순위가 뒤로 밀린다.
 */

/**
 * 풀이 순서
 * 1. timeTable의 시간 순서대로 queue에 값을 넣어둔다.
 * 2. n이 1이 될때까지 queue의 앞에서 값을 뺴내고, queue에 값이 없더라도 일단 n을 1인 상태로 만든다.
 * 3. queue에 값이 m개 이상 있다면 콘은 queue의 m번째에 있는 값보다 1분 빨리 줄을 선다.
 */

class 셔틀버스1차_Solution {

    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        Queue<Integer> queue = initQueueOfTimeTable(timetable);

        // 마지막 운행 차량 전에 타려고 하는 인원들을 미리 보내기
        int currentBusTime = 540;
        while (n > 1) {
            n -= 1;
            if (queue.isEmpty()) {
                continue;
            }
            for (int i = 0; i < m; i++) {
                if (!queue.isEmpty() && queue.peek() > currentBusTime) {
                    break;
                }
                queue.poll();
            }
            currentBusTime += t;
        }

        // 현재 버스를 타려고 대기하고 있는 인원중에 마지막 사람전까지 버스태우기
        for (int i = 0; i < m - 1; i++) {
            if (!queue.isEmpty() && queue.peek() > currentBusTime) {
                break;
            }
            queue.poll();
        }

        // 마지막 차량 이후에 대기하는 인원들 제거
        while (!queue.isEmpty() && queue.peek() > currentBusTime) {
            queue.poll();
        }

        // 대기하는 사람이 없을 경우
        if (queue.isEmpty()) {
            answer = minuteToTime(currentBusTime);
        }

        // 대기하는 사람이 있을 경우
        if (!queue.isEmpty()) {
            Integer crewTime = queue.poll();
            answer = minuteToTime(crewTime - 1);
        }

        return answer;
    }

    private String minuteToTime(int currentBusTime) {
        String hour = String.valueOf(currentBusTime / 60);
        hour = hour.length() > 1 ? hour : "0" + hour;
        String minute = String.valueOf(currentBusTime % 60);
        minute = minute.equals("0") ? "00" : minute.length() > 1 ? minute : "0" + minute;
        return hour + ":" + minute;
    }

    private Queue<Integer> initQueueOfTimeTable(String[] timetable) {
        Arrays.sort(timetable);
        Queue<Integer> queue = new LinkedList<>();
        for (String time : timetable) {
            queue.add(timeToMinute(time));
        }
        return queue;
    }

    private int timeToMinute(String time) {
        String[] splitTime = time.split(":");
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);
        return hour * 60 + minute;
    }
}
