package sequence;

public class fibonacci_Sequence {
    /**
     * 피보나치 수열
     */
    long[] a;  // a: 사용자로부터 입력받은 항들
    long k;    // k: 사용자가 추가적으로 k번째 항의 수를 알기 위해 필요시 입력할 상수
    long m;    // m: 사용자가 만약 다른 항의 값을 알아보려고 했을 때 저장되는 상수
    boolean available; //해당 클래스가 사용가능한지.
    String seqName = "Fibonacci-Sequence";
    public fibonacci_Sequence(long[] userA) {
        a = userA;
        for (int freq = 0; freq <= a.length - 3; freq++)
            available = a[freq] + a[freq + 1] == a[freq + 2];
    }

    /**
     * F 수열의 n번째 항을 찾아주는 메소드다.
     * @param n 번째.
     */
    public void find_out_term (long n){
        k = n;
        m = (long) ((long) 1 / Math.sqrt(5) * ((Math.pow((1 + Math.sqrt(5)) / 2, k)) - (Math.pow((1 - Math.sqrt(5)) / 2, k))));
    }

    /**
     * 이 등비수열이 사용가능한 상태인지 확인한다.
     * @return 등비수열 사용 가능 여부
     */
    public boolean getAvailable() {
        return available;
    }
    /**
     * @return 몇 번째 항인지를 반환한다.
     */
    public long getK() {
        return k;
    }

    /**
     * @return k번째 항을 반환한다.
     */
    public long getM() {
        return m;
    }

    /**
     * @return 해당 클래스가 피보나치 수열임을 알린다.
     */
    public String getSeqName() {
        return seqName;
    }
}
