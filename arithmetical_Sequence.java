package sequence;

public class arithmetical_Sequence extends sequence_Number_Rule {
    /**
     * 등차수열
     */
    static int[] a;  // a: 사용자로부터 입력받은 항들
    static int d;    // d: difference; 공차
    static int k;    // k: 사용자가 추가적으로 k번째 항의 수를 알기 위해 필요시 입력할 상수
    static int m;    // m: 사용자가 만약 다른 항의 값을 알아보려고 했을 때 저장되는 상수
    static boolean available; //해당 클래스가 사용가능한지.
    public arithmetical_Sequence(int[] usersA) {
        this.a = usersA;

        if (a[1] - a[0] == a[2] - a[1]) {
            d = a[1] - a[0]; //공차 입력
            this.available = true;
            k = 0;
        }
        else
            this.available = false;
    }

        /**
         * 사용자가 입력한 수를 넘어서 n번째 항의 값은 무엇인지 찾아내는 메소드다.
         * 참고) d 값이 있어야 실행될 수 있다.
         * @param n n번째 항
         */
        public static void find_out_term (int n){
            k = n;
            if (d != 0)
                m = a[0] + (n - 1) * d;
        }

    /**
     * 이 등차수열이 사용가능한 상태인지 확인한다.
     * @return 등차수열 사용 가능 여부
     */
    public boolean getAvailable() {
            return available;
    }

    /**
     * @return 공차를 반환한다.
     */
    public static int getDifference() {
        return d;
    }

    /**
     * @return 몇 번째 항인지를 반환한다.
     */
    public static int getK() {
        return k;
    }

    /**
     * @return k번째 항을 반환한다.
     */
    public static int getM() {
        return m;
    }
}

