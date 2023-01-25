package level3;

import java.util.*;
import java.util.stream.Collectors;

public class 일차캐시 {
    public static void main(String[] args) {
        일차캐시_Solution solution = new 일차캐시_Solution();
        int cacheSize = 2;
        String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};
        System.out.println(solution.solution(cacheSize, cities));
    }
}

class 일차캐시_Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }

        int answer = 0;
        Queue<String> cityList = Arrays.stream(cities).map(String::toUpperCase).collect(Collectors.toCollection(LinkedList::new));
        List<String> cache = new ArrayList<>();

        while (cacheSize > 0) {
            cacheSize--;
            String city = cityList.poll();
            int cacheIdx = cache.indexOf(city);

            if (cacheIdx != -1) {
                answer++;
                cache.remove(cacheIdx);
                cacheSize++;
            } else {
                answer += 5;
            }
            cache.add(city);
        }

        while (!cityList.isEmpty()) {
            String city = cityList.poll();
            int cacheIdx = cache.indexOf(city);
            if (cacheIdx != -1) {
                answer++;
                cache.remove(cacheIdx);
            } else {
                answer += 5;
                cache.remove(0);
            }
            cache.add(city);
        }

        return answer;
    }
}
