package sequence;

public class geometric_Sequence extends sequence_Number_Rule {
    /**
     * 등비수열
     */
    static long[] a;  // a: 사용자로부터 입력받은 항들
    static int r;    // r: ratio; 비
    static long k;    // k: 사용자가 추가적으로 k번째 항의 수를 알기 위해 필요시 입력할 상수
    static long m;    // m: 사용자가 만약 다른 항의 값을 알아보려고 했을 때 저장되는 상수
    static boolean available; //해당 클래스가 사용가능한지.
    public geometric_Sequence(long[] usersA) {
        //a[0] = a a[1] = b a[2] = c
        a = usersA;
        for (int freq = 0; freq < a.length - 2; freq++) {
            if ((a[0] != 0) && ((int) Math.pow(a[freq + 1], 2) == (a[freq] * a[freq + 2]))) {
                available = true;
                r = (int) (a[1] / a[0]); //비 입력
            } else
                available = false;
        }
    }

    /**
     * 사용자가 입력한 수를 넘어서 n번째 항의 값은 무엇인지 찾아내는 메소드다.
     * 참고) d 값이 있어야 실행될 수 있다.
     * @param n n번째 항
     */
    public static void find_out_term (long n){
        k = n;
        if (k > 0)
            m = (long) (a[0] * Math.pow(r,k - 1));
        else
            m = -1;
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
    public static long getK() {
        return k;
    }

    /**
     * @return k번째 항을 반환한다.
     */
    public static long getM() {
        return m;
    }

    /**
     * @return 비를 반환한다.
     */
    public static int getRatio() {
        return r;
    }

}
