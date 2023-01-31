package queue;

import java.util.Stack;

public class V1 {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.add(11);
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }
}

class Queue<T> {
    Stack<T> stack1 = new Stack<>();
    Stack<T> stack2 = new Stack<>();

    // 새로 들어온 데이터를 맨 뒤로 보내는 것이 핵심
    public T add(T data) {
        while (!stack2.isEmpty()) {
            stack1.add(stack2.pop());
        }
        stack2.add(data);

        while (!stack1.isEmpty()) {
            stack2.add(stack1.pop());
        }
        return data;
    }

    public T poll() {
        return stack2.pop();
    }

    public T peek() {
        return stack2.peek();
    }
}