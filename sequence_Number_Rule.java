package sequence;

import java.util.Scanner;

public class sequence_Number_Rule {
    //사용자로부터 받을 숫자
    static int[] num;

    /*
    -에러코드
    1465: FindOutOfWrongTyping
    19151525: FindOutOfBoundsNumber
    19152990: 앞선 두가지가 모두 일어남.
     */
    static int errCode = 0;
    final static int MIN = 3;     //최소값
    final static int MAX = 10;    //최대값

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
     * temp 함수에 앞서 설정한 'min' 값과 'MAX' 값의 기준을 넘었는지 판단한다.
     * @param temp 임시적인 String 객체
     * @return
     */
    public static void FindOutOfBoundsNumber(String[] temp) {
        try {
            if (!(temp.length >= MIN && temp.length <= MAX))
                temp[temp.length + 1] = null; //강제적인 오류 발생 유도
        }
        catch (ArrayIndexOutOfBoundsException e) {
            errCode += 19151525;
        }
    }

    public static int FindOutErrCode () {
        if (errCode == 1465) {
            System.out.print("\nNone of the entries you entered are numbers or beyond the limits of Integer." +
                    "\nPlease try again." +
                    "\n-> ");
        } else if (errCode == 19151525) {
            System.out.print("\nThe numbers you entered are less than " + MIN + " or more than " + MAX +
                    "\nPlease try again." +
                    "\n-> ");
        } else if (errCode == 19152990) {
            System.out.print("None of the entries you entered are numbers or beyond the limits of Integer " +
                    "and less than " + MIN + " or more than " + MAX +
                    "\nPlease try again." +
                    "\n-> ");
        }
        return errCode;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please write down the numbers." +
                "\n(These numbers are separated by spaces.)" +
                "\n-> ");
        String[] temp;

        do {
            errCode = 0;
            temp = sc.nextLine().split(" ");
            FindOutOfBoundsNumber(temp);
            FindOutOfWrongTyping(temp);
        } while (FindOutErrCode() != 0);

        num = new int[temp.length]; //재정의

        for (int i = 0; i < temp.length; i++)
            num[i] = Integer.parseInt(temp[i]);

        //등차수열
        arithmetical_Sequence aS = new arithmetical_Sequence(num);
        if (aS.getAvailable()) {
            System.out.print("The numbers you entered are arithmetical sequence with a difference value " + aS.getDifference() +
                    ".\nDo you find the other term?" +
                    "\n1. Yes\t2. No" +
                    "\n-> ");
            int examA = sc.nextInt();
            boolean examB = true;
            while (examB) {
            if (examA == 1) {
                System.out.print("How many terms so far?" +
                        "\n(You don't have to worry about the numbers you entered before.)" +
                        "\n-> ");
                aS.find_out_term(sc.nextInt());
                System.out.println("The value of the " + aS.getK() + " term is " + aS.getM());
                examB = false;
            }
            else if (examA == 2) {
                System.out.print("The numbers you entered is a arithmetical sequence in which the first-term is " +
                        num[0] + " and the difference is " + aS.getDifference());
                examB = false;
            }
            else {
                System.out.print("This isn't correct value." +
                        "\n please try again." +
                        "\n-> ");
                examA = sc.nextInt();
            }

            }
        }

        //등비수열
        geometric_Sequence gS = new geometric_Sequence(num);
        if (gS.getAvailable()) {
            System.out.print("The numbers you entered are geometric sequence with a ratio value " + gS.getRatio() +
                    ".\nDo you find the other term?" +
                    "\n1. Yes\t2. No" +
                    "\n-> ");
            int examA = sc.nextInt();
            boolean examB = true;
            while (examB) {
                if (examA == 1) {
                    System.out.print("How many terms so far?" +
                            "\n(You don't have to worry about the numbers you entered before.)" +
                            "\n-> ");
                    gS.find_out_term(sc.nextInt());
                    System.out.println("The value of the " + gS.getK() + " term is " + gS.getM());
                    examB = false;
                }
                else if (examA == 2) {
                    System.out.print("The numbers you entered is a arithmetical sequence in which the first-term is " +
                            num[0] + " and the difference is " + gS.getRatio());
                    examB = false;
                }
                else {
                    System.out.print("This isn't correct value." +
                            "\n please try again." +
                            "\n-> ");
                    examA = sc.nextInt();
                }

            }
        }

        //계차수열
        difference_Sequence dS = new difference_Sequence();
        //조화수열
        harmonic_Sequence hS = new harmonic_Sequence();
        //피보나치 수열
        fibonacci_Sequence fS = new fibonacci_Sequence();

    }
}
