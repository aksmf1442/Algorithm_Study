package level2;

public class k진수에서소수개수구하기 {

    public static void main(String[] args) {
        k진수에서소수개수구하기_Soltuion soltuion = new k진수에서소수개수구하기_Soltuion();
        int n = 437674;
        int k = 3;
        System.out.println(soltuion.solution(n, k));
    }
}

class k진수에서소수개수구하기_Soltuion {
    public int solution(int n, int k) {
        int answer = 0, i, j;
        String s = to_Knum(n,k);
        for(i = 0; i < s.length(); i = j) {
            for(j = i + 1; j < s.length() && s.charAt(j) != '0'; j++);
            if(isprime(Long.parseLong(s.substring(i,j))))
                answer++;
        }
        return answer;
    }

    public boolean isprime(long n){
        if(n <= 1) return false;
        else if(n == 2) return true;
        for(int i = 2; i <= Math.sqrt(n); i++)
            if(n % i == 0)
                return false;
        return true;
    }
    public String to_Knum(int n, int k) {
        String res = "";
        while(n > 0) {
            res = n % k + res;
            n /= k;
        }
        return res;
    }
}
