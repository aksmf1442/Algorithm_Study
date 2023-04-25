package 복습;

import java.util.Stack;

public class 표편집복습 {

    public static void main(String[] args) {
        표편집_Solution solution = new 표편집_Solution();
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        String result = solution.solution(n, k, cmd);
        for (int i = 0; i < result.length(); i++) {
            System.out.print(result.charAt(i));
        }
    }
}

class 표편집_Solution {

    public String solution(int n, int k, String[] cmd) {
        int pointer = k;
        Stack<Integer> stack = new Stack<>();

        int graphSize = n;

        for (String commandStr : cmd) {
            String[] splitCommand = commandStr.split(" ");
            Command command= null;

            if (splitCommand.length == 1) {
                command = new Command(splitCommand[0], 0);
            }

            if (splitCommand.length == 2) {
                command = new Command(splitCommand[0], Integer.parseInt(splitCommand[1]));
            }

            if (command.value.equals("U")) {
                pointer-= command.move;
            }

            if (command.value.equals("D")) {
                pointer += command.move;
            }

            if (command.value.equals("C")) {
                stack.add(pointer);
                graphSize--;
                if (pointer > graphSize - 1) {
                    pointer--;
                }
            }

            if (command.value.equals("Z")) {
                if (stack.pop() <= pointer) {
                    pointer++;
                }
                graphSize++;
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < graphSize; i++) {
            answer.append("O");
        }

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            answer.insert(idx, "X");
        }

        return answer.toString();
    }

    class Command {
        String value;
        int move;

        public Command(String cmd, int move) {
            this.value = cmd;
            this.move = move;
        }
    }
}
