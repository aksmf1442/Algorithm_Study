package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 주차요금계산 {

    public static void main(String[] args) {
        주차요금계산_Solution solution = new 주차요금계산_Solution();
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT",
            "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] result = solution.solution(fees, records);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }
}

/**
 * goal: 차량번호가 작은 자동차부터 청구할 주차 요금을 return하기
 */

/**
 * 알아야 할 것
 * 1. 어떤 차량이 입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주(기록이 짝수가 아닌경우)
 * 2. 누적 주차 시간이 기본 시간이하라면, 기본 요금을 청구합니다.
 * 3. 누적 주차 시간이 기본 시간을 초과하면, 기본 요금에 더해서, 초과한 시간에 대해서 단위 시간 마다 단위 요금을 청구합니다.
 * 4. 초과한 시간이 단위 시간으로 나누어 떨어지지 않으면, 올림합니다.
 */

/**
 * 문제 풀이 순서
 * 1. records 정보를 통해서 map에 어떤 차량이 언제 입차했고, 출차했는지 list로 관리한다.
 * 2. list의 길이는 무조건 2(입차, 출차)여야 하며, 길이가 1이라면 출차기록을 23:59에 한 것으로 초기화 해준다.
 * 3. map의 정보와 fees의 정보를 통해서 누적 주차 시간의 크기를 확인한다.
 */

class 주차요금계산_Solution {
    Map<String, List<String>> parkingRecords;
    int basicTime;
    int basicFee;
    int addedTime;
    int addedFee;

    public int[] solution(int[] fees, String[] records) {
        initParkingRecords(records);
        basicTime = fees[0];
        basicFee = fees[1];
        addedTime = fees[2];
        addedFee = fees[3];
        Map<String, Integer> parkingFees = calculateParkingFees();
        Object[] keys = parkingFees.keySet().toArray();
        Arrays.sort(keys);

        int[] result = new int[keys.length];

        for (int i = 0; i < result.length; i++) {
            String carNumber = (String) keys[i];
            result[i] = parkingFees.get(carNumber);
        }
        return result;
    }

    private Map<String, Integer> calculateParkingFees() {
        Map<String, Integer> parkingFees = new HashMap<>();
        for (String carNumber : parkingRecords.keySet()) {
            List<String> parkingRecord = parkingRecords.get(carNumber);
            if (parkingRecord.size() % 2 != 0) {
                parkingRecord.add("23:59");
            }
            int parkingMinute = calculateParkingRecord(parkingRecord);
            int parkingFee = calculateParkingFee(parkingMinute);
            parkingFees.put(carNumber, parkingFees.getOrDefault(carNumber, 0) + parkingFee);
        }
        return parkingFees;
    }

    private int calculateParkingFee(int parkingMinute) {
        int parkingFee = 0;
        parkingFee += basicFee;
        if (basicTime < parkingMinute) {
            parkingFee += (parkingMinute - basicTime) / addedTime  * addedFee;
            parkingFee += (parkingMinute - basicTime) % addedTime != 0 ? addedFee : 0;
        }
        return parkingFee;
    }

    private int calculateParkingRecord(List<String> parkingRecord) {
        int result = 0;
        for (int i = 0; i < parkingRecord.size(); i+=2) {
            int in = changeTimeToMinute(parkingRecord.get(i));
            int out = changeTimeToMinute(parkingRecord.get(i + 1));
            result += out - in;
        }
        return result;
    }

    private int changeTimeToMinute(String time) {
        String[] splitTime = time.split(":");
        String hour = splitTime[0];
        String minute = splitTime[1];
        return Integer.parseInt(hour) * 60 + Integer.parseInt(minute);
    }

    private void initParkingRecords(String[] records) {
        parkingRecords = new HashMap<>();
        for (String record : records) {
            String[] splitRecord = record.split(" ");
            String time = splitRecord[0];
            String carNumber = splitRecord[1];
            parkingRecords.computeIfAbsent(carNumber, k -> new ArrayList<>()).add(time);
        }
    }
}
