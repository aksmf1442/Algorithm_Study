package level2;

import java.util.Arrays;

public class 파일명정렬 {

    public static void main(String[] args) {
        파일명정렬_Solution solution = new 파일명정렬_Solution();
//        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF",
//            "img2.JPG"};
        String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II",
            "F-14 Tomcat"};

        String[] result = solution.solution(files);
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }
}

/**
 * goal : 문자열이 담긴 files를 head, number, tail 부분으로 구분하고 주어진 조건에 맞게 정렬하기
 */

/**
 * 조건
 * 1. HEAD는 숫자가 아닌 문자
 * 2. NUMBER는 NUMBER는 한 글자에서 최대 다섯 글자 사이의 연속된 숫자로 이루어져 있으며,
 *    앞쪽에 0이 올 수 있다. 0부터 99999 사이의 숫자로, 00000이나 0101 등도 가능
 * 3. TAIL은 그 나머지 부분으로, 여기에는 숫자가 다시 나타날 수도 있으며, 아무 글자도 없을 수 있다.
 */

class 파일명정렬_Solution {

    public String[] solution(String[] files) {
        Arrays.sort(files, (file1, file2) -> {
            String[] file1Arr = extractFileArrFromFile(file1);
            String[] file2Arr = extractFileArrFromFile(file2);

            if (!file1Arr[0].equals(file2Arr[0])) {
                return file1Arr[0].compareTo(file2Arr[0]);
            }

            if (Integer.parseInt(file1Arr[1]) != Integer.parseInt(file2Arr[1])) {
                return Integer.parseInt(file1Arr[1]) - Integer.parseInt(file2Arr[1]);
            }

            return 0;
        });

        return files;
    }

    private String[] extractFileArrFromFile(String file) {
        String fileHeader = "";
        boolean fileHeaderAction = true;
        String fileNumber = "";
        boolean fileNumberAction = false;
        String fileTail = "";

        for (int i = 0; i < file.length(); i++) {
            char c = file.charAt(i);
            // 숫자 부분이 시작하면 헤더가 끝
            if ((fileNumber.length() == 0 || fileNumberAction) && c >= 48 && c <= 57) {
                fileNumber += c;
                fileNumberAction = true;
                fileHeaderAction = false;
                continue;
            }

            // 숫자 부분이 끝나면 fileNumber의 길이가 0이 아니고 위의 if 문에서 그냥 내려오기 때문에 false로 변경하여
            // 나중에 tail부분에서 숫자가 나오더라도 위의 if문이 통과되지 않도록 함.
            if (fileNumber.length() != 0) {
                fileNumberAction = false;
            }

            // header 시작
            if (fileHeaderAction) {
                fileHeader += c;
            }

            // tail 시작
            if (!fileHeaderAction) {
                fileTail += c;
            }
        }
        return new String[]{fileHeader.toUpperCase(), fileNumber, fileTail};
    }
}
