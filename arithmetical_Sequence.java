package sequence;

public class arithmetical_Sequence {
    /**
     * 등차수열
     */
    static int[] a;  // a: 첫째항
    static int d;    // d: difference; 공차
    static int k;    // k: 사용자가 입력한 수들 중 항에 쉽게 접근하기 위한 상수
    static int n;    // n: 사용자가 추가적으로 n번째 항의 수를 알기 위해 필요시 입력할 상수


    public static int common_term(int[] a) {
        if (a[1] - a[0] == a[2] - a[1]) {
            d = a[1] - a[0]; //공차 입력

        }
        return 0;

    }
}
