package sequence;

import java.util.Scanner;

public class sequence_Number_Rule {
    //사용자로부터 받을 숫자
    static int[] n = new int[10];
    /*
    -에러코드
    1465: FindOutOfWrongTyping
    19151525: FindOutOfBoundsNumber
    19152990: 앞선 두가지가 모두 일어남.
     */
    static int errCode = 0;
    
    //최소값
    final static int min = 2;
    //최대값
    final static int max = 10;

    //등차수열
    arithmetical_Sequence aS = new arithmetical_Sequence();
    //등비수열
    geometric_Sequence gS = new geometric_Sequence();
    //계차수열
    difference_Sequence dS = new difference_Sequence();
    //조화수열
    harmonic_Sequence hS = new harmonic_Sequence();
    //피보나치 수열
    fibonacci_Sequence fS = new fibonacci_Sequence();

    /**
     * temp 함수에 적절한 수가 들어갔는지 판단한다.
     * @param temp 임시적인 String 객체
     */
    public static void FindOutOfWrongTyping(String[] temp) {
        try {
            for (int freq = 0; freq < temp.length; freq++)
                if (!Character.isDigit(Integer.parseInt(temp[freq]))) ;
        }
        catch (NumberFormatException e) {
            errCode += 1465; //각 철자 대문자의 알파벳 순서를 따옴
        }
    }

    /**
     * temp 함수에 앞서 설정한 'min' 값과 'max' 값의 기준을 넘었는지 판단한다.
     * @param temp 임시적인 String 객체
     * @return
     */
    public static void FindOutOfBoundsNumber(String[] temp) {
        try {
            if (!(temp.length >= min && temp.length <= max))
                temp[temp.length + 1] = null; //강제적인 오류 발생 유도
        }
        catch (ArrayIndexOutOfBoundsException e) {
            errCode += 19151525;
        }
    }

    public static int FindOutErrCode () {
        if (errCode == 1465) {
            System.out.print("\nNone of the entries you entered are numbers." +
                    "\nPlease try again." +
                    "\n->");
        } else if (errCode == 19151525) {
            System.out.print("\nThe numbers you entered are less than " + min + " or more than " + max +
                    "\nPlease try again." +
                    "\n->");
        } else if (errCode == 19152990) {
            System.out.print("None of the entries you entered are numbers and less than " + min + " or more than " + max +
                    "\nPlease try again." +
                    "\n->");
        }
        return 0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please write down the numbers." +
                "\n(These numbers are separated by spaces.)" +
                "\n->");
        String[] temp;

        do {
            temp = sc.nextLine().split(" ");
            errCode = 0;
            FindOutOfBoundsNumber(temp);
            FindOutOfWrongTyping(temp);
        } while (FindOutErrCode() != 0);

        for (int i = 0; i < temp.length; i++) {
            System.out.println(temp[i]);
        }
    }
}
