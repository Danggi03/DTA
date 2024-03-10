package sequence;

import java.util.InputMismatchException;

public class command extends sequence_Number_Rule{
    boolean command = false;
    String[] s;

    boolean exit = false; //exit
    boolean setup = false; //setup

    void errMISMATCH() throws InputMismatchException {
        print_WrongValue();
    }
    public command(String[] s) {
        this.s = s;
        if (this.s[0].equals("Exit") || this.s[0].equals("exit") || this.s[0].equals("EXIT")) {
            command = true;
            exit = true;
        }
        if (this.s[0].equals("Setup") || this.s[0].equals("setup") || this.s[0].equals("SETUP")) {
            command = true;
            setup = true;
        }
    }
    public void exit() {
        if (exit) {
            System.out.println("Thank you for your using");
        }
    }
    public void setup() {
        if (s[0].equals("Setup") || s[0].equals("setup") || s[0].equals("SETUP")) {
            System.out.print("""
                    1. Change the min
                    2. Change the max
                    3. Turn to Main
                    ->\s""");
            int examA = 0;
            String tempString = sc.next();

            /*
            해당 와일파일은 모든 input 에다가 넣어 exception을 방지한다.
             */
            while (examA == 0) {
                if (FindOutOfWrongTyping(tempString))
                    examA = Integer.parseInt(tempString);
                else
                    tempString = sc.next();
            }

            boolean examB = true;
            while (examB) {
                if (examA == 1) {
                    examB = false;
                    System.out.print("""
                            What's the number you're going to set?
                            (There should be a difference of more than 3 from the max value or at least 3 written.)
                            ->\s""");

                    int temp = sc.nextInt();
                    boolean changeTEMP = true;
                    do {
                        if (temp == getMIN()) {
                            System.out.print("It is the same as the current value." +
                                    "\nMIN: " + getMIN() +
                                    "\nPlease try again." +
                                    "\n-> ");
                            temp = sc.nextInt();
                        }
                        else if (temp < 3) {
                            System.out.print("""
                                            The MIN value is less than 3
                                            Please try again.
                                            ->\s""");
                            temp = sc.nextInt();
                        } else if (3 > getMAX() - temp) {
                            System.out.print("The MIN value must be at least 3 different from the maximum value." +
                                    "\nPlease try again." +
                                    "(The difference between the current value and the MAX " + (getMAX() - temp) +
                                    ".)\n-> ");
                            temp = sc.nextInt();
                        }
                        else {
                            System.out.print("Are you sure you entered " + temp + "?" +
                                    "\n1.Yes, it is.\t2.No, I want to change my entered." +
                                    "\n-> ");

                            int ANSWER = sc.nextInt();
                            if (ANSWER == 1) {
                                    int before = getMIN();
                                    setupMIN(temp);
                                    changeTEMP = false;
                                    System.out.println("Changed. From " + before + " to " + getMIN() + ".");
                            } else if (ANSWER == 2) {
                                System.out.print("""
                                        What's the number you're going to set?
                                        (There should be a difference of more than 3 from the max value or at least 3 written.)
                                        ->\s""");
                                temp = sc.nextInt();
                            } else
                                print_WrongValue();
                        }
                    } while (changeTEMP);
                }
                else if (examA == 2) {
                    examB = false;
                    System.out.print("""
                            What's the number you're going to set?
                            (The number you entered must be less than or equal to 100 or greater than the MIN value + 3.)
                            ->\s""");

                    int temp = sc.nextInt();
                    boolean changeTEMP = true;
                    do {
                        if (temp == getMAX()) {
                            System.out.print("It is the same as the current value." +
                                    "\nMAX: " + getMAX() +
                                    "\nPlease try again." +
                                    "\n-> ");
                            temp = sc.nextInt();
                        }
                        else if (temp > 100) {
                            System.out.print("""
                                            The value entered exceeds 100.
                                            Please try again.
                                            ->\s""");
                            temp = sc.nextInt();
                        } else if (temp < getMIN() + 3) {
                            System.out.print("The MAX value must be greater than MIN + 3" +
                                    "\nPlease try again." +
                                    "(The difference between the current value and the MIN " + (temp - getMIN()) +
                                    ".)\n-> ");
                            temp = sc.nextInt();
                        }
                        else {
                            System.out.print("Are you sure you entered " + temp + "?" +
                                    "\n1.Yes, it is.\t2.No, I want to change my entered." +
                                    "\n-> ");

                            int ANSWER = sc.nextInt();
                            if (ANSWER == 1) {
                                int before = getMAX();
                                setupMAX(temp);
                                changeTEMP = false;
                                System.out.println("Changed. From " + before + " to " + getMAX() + ".");
                            } else if (ANSWER == 2) {
                                System.out.print("""
                            What's the number you're going to set?
                            (The number you entered must be less than or equal to 100 or greater than the MIN value + 3.)
                            ->\s""");
                                temp = sc.nextInt();
                            } else
                                print_WrongValue();
                        }
                    } while (changeTEMP);
                }
                else if (examA == 3) {
                    examB = false;
                }
                else {
                    print_WrongValue();
                    examA = sc.nextInt();
                }
            }
        }
    }

    /**
     * @return 해당 커멘드가 사용가능한지
     */
    public boolean isCommand() {
        return command;
    }

    /**
     * @return 꺼질 준비가 되었는지
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * @return 셋팅
     */
    public boolean isSetup() {
        return setup;
    }
}
