package sequence;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class sequence_Number_Rule {
    static Scanner sc = new Scanner(System.in);
    //사용자로부터 받을 숫자
    static long[] num;

    // 업데이트 사항: 123 같은 것을 썼을 때 피보나치와 등차수열과 둘 다 같은 수열일 수 있음. 해당 고려작업해야함.

    /*
    -에러코드
    1465: FindOutOfWrongTyping
    19151525: FindOutOfBoundsNumber
    19152990: 앞선 두가지가 모두 일어남.
    -2: 설정
     */
    static int errCode = 0;
    static int MIN = 3;     //최소값
    static int MAX = 10;    //최대값
    private static boolean exit = false;

    /**
     * temp 함수에 적절한 수가 들어갔는지 판단한다.
     * @param temp 임시적인 String 객체
     */

    public static void FindOutOfWrongTyping(String[] temp) {
        try {
            for (String s : temp)
                Character.isDigit(Integer.parseInt(s));
        }
        catch (NumberFormatException e) {
            errCode += 1465; //각 철자 대문자의 알파벳 순서를 따옴
        }
    }

    public static boolean FindOutOfWrongTyping(String temp) {
        try {
            Character.isDigit(Integer.parseInt(temp));
            return true;
        }
        catch (Exception e) {
            print_WrongValue();
            return false;
        }
    }

    /**
     * temp 함수에 앞서 설정한 'min' 값과 'MAX' 값의 기준을 넘었는지 판단한다.
     * @param temp 임시적인 String 객체
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
            System.out.print("""

                    None of the entries you entered are numbers or beyond the limits of Integer.
                    Please try again.
                    ->\s""");
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

    /**
     * @return MIN 값을 불러온다.
     */
    public static int getMIN() {
        return MIN;
    }

    /**
     * @return MAX 값을 불러온다.
     */
    public static int getMAX() {
        return MAX;
    }

    /**
     * @param min MIN 값을 바꾼다.
     */
    public static void setupMIN(int min) {
        MIN = min;
    }

    /**
     * @param max MAX 값을 바꾼다.
     */
    public static void setupMAX(int max) {
        MAX = max;
    }



    public static void main(String[] args) {
        System.out.println("Welcome my the sequence reader." +
                "\nMake by DangGi.\t\t\trelease. 2024-03-10." +
                "\n");
        print_WriteDown();
        String[] temp;
        do {
            errCode = 0;
            temp = sc.nextLine().split(" ");
            command cmd = new command(temp);
            if (cmd.isCommand()) {
                if (cmd.isExit()) {
                    exit = true;
                    cmd.exit();
                }
                if (cmd.isSetup()) {
                    errCode = -2;
                    cmd.setup();
                    print_WriteDown();
                    temp = sc.nextLine().split(" ");
                }
            }
            else {
                FindOutOfBoundsNumber(temp);
                FindOutOfWrongTyping(temp);
            }
        } while (FindOutErrCode() != 0);

        if (!exit) {
            num = new long[temp.length]; //재정의

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
                        print_UserK();
                        aS.find_out_term(sc.nextLong());
                        while (aS.getM() == -1 || aS.getM() == Long.MAX_VALUE) {
                            if (aS.getM() == -1) {
                                print_WrongValue();
                                aS.find_out_term(sc.nextLong());
                            } else {
                                print_Overflow();
                                aS.find_out_term(sc.nextLong());
                            }
                        }
                        print_kTerm_answer(aS.getK(), aS.getM());
                        examB = false;
                    } else if (examA == 2) {
                        System.out.print("The numbers you entered is a arithmetical sequence in which the first-term is " +
                                num[0] + " and the difference is " + aS.getDifference() +
                                "\n-> " +
                                Arrays.toString(num));
                        examB = false;
                    } else {
                        print_WrongValue();
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
                        print_UserK();
                        gS.find_out_term(sc.nextLong());
                        while (gS.getM() == -1 || gS.getM() == Long.MAX_VALUE) {
                            if (gS.getM() == -1) {
                                print_WrongValue();
                                gS.find_out_term(sc.nextLong());
                            } else {
                                print_Overflow();
                                gS.find_out_term(sc.nextLong());
                            }
                        }
                        print_kTerm_answer(gS.getK(), gS.getM());
                        examB = false;
                    } else if (examA == 2) {
                        System.out.print("The numbers you entered is a arithmetical sequence in which the first-term is " +
                                num[0] + " and the difference is " + gS.getRatio() +
                                "\n-> " +
                                Arrays.toString(num));
                        examB = false;
                    } else {
                        print_WrongValue();
                        examA = sc.nextInt();
                    }

                }
            }
            //피보나치 수열
            fibonacci_Sequence fS = new fibonacci_Sequence(num);
            if (fS.getAvailable()) {
                System.out.print("""
                        The numbers you entered are fibonacci sequence.
                        Do you find the other term?
                        1. Yes\t2. No
                        ->\s""");
                int examA = sc.nextInt();
                boolean examB = true;
                while (examB) {
                    if (examA == 1) {
                        print_UserK();
                        fS.find_out_term(sc.nextLong());
                        while (fS.getM() == -1 || fS.getM() == Long.MAX_VALUE) {
                            if (fS.getM() == -1) {
                                print_WrongValue();
                                fS.find_out_term(sc.nextLong());
                            } else {
                                print_Overflow();
                                fS.find_out_term(sc.nextLong());
                            }
                        }
                        System.out.print("When first-term is " + 1 + ", ");
                        print_kTerm_answer(fS.getK(), fS.getM());
                        examB = false;
                    } else if (examA == 2) {
                        System.out.print("The numbers you entered is a fibonacci sequence." +
                                "\n-> " +
                                Arrays.toString(num));
                        examB = false;
                    } else {
                        print_WrongValue();
                        examA = sc.nextInt();
                    }

                }
            }
        }
    }

    public static void print_WriteDown() {
        System.out.print("""
                Please write down the numbers.
                (These numbers are separated by spaces.)
                (Enter "Exit" and "Setup" in the corresponding space and proceed to the corresponding command)
                ->\s""");
    }
    public static void print_Overflow() {
        System.out.print("""
                This term is out of integer range. (\t
                9,223,372,036,854,775,808 ++)
                Please try again.
                ->\s""");
    }
    public static void print_UserK() {
        System.out.print("""
                How many terms so far?
                (You don't have to worry about the numbers you entered before.)
                ->\s""");
    }
    public static void print_WrongValue() {
        System.out.print("""
                This isn't correct value.
                 please try again.
                ->\s""");
    }
    public static void print_kTerm_answer(long k, long m) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        System.out.println("The value of the " + decimalFormat.format(k) + " term is " + decimalFormat.format(m));
    }

}
