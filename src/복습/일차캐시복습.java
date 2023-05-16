package 복습;

import java.util.*;
import java.util.stream.Collectors;

public class 일차캐시복습 {
    public static void main(String[] args) {
        일차캐시_Solution solution = new 일차캐시_Solution();
        int cacheSize = 2;
        String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};
        System.out.println(solution.solution(cacheSize, cities));
    }
}

class 일차캐시_Solution {
    int answer = 0;

    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }

        Queue<String> cityList = Arrays.stream(cities)
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(LinkedList::new));
        List<String> cache = new ArrayList<>();

        initCache(cacheSize, cityList, cache);
        calculateRuntimeOfCache(cityList, cache);
        return answer;
    }

    private void calculateRuntimeOfCache(Queue<String> cityList, List<String> cache) {
        while (!cityList.isEmpty()) {
            String city = cityList.poll();
            int cacheIdx = cache.indexOf(city);
            
            if (cacheHit(cacheIdx)) {
                answer++;
                cache.remove(cacheIdx);
            } else {
                answer += 5;
                cache.remove(0);
            }
            cache.add(city);
        }
    }

    private static boolean cacheHit(int cacheIdx) {
        return cacheIdx != -1;
    }

    private int initCache(int cacheSize, Queue<String> cityList, List<String> cache) {
        while (cacheSize > 0) {
            cacheSize--;
            String city = cityList.poll();
            int cacheIdx = cache.indexOf(city);

            if (cacheHit(cacheIdx)) {
                answer++;
                cache.remove(cacheIdx);
                cacheSize++;
            } else {
                answer += 5;
            }
            cache.add(city);
        }
        return answer;
    }
}
