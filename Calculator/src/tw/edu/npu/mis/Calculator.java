/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.npu.mis;

import java.util.Observable;

/**
 * The model class of the calculator application.
 */
public class Calculator extends Observable {

    String mShowNumber = "";
    String mFirstNumber = "";
    String mBuffNumber = "0";
    String mMBuffNumber = "";
    String mOperator = "";
    String mAnser = "";
    boolean mCheckDot, mCheckPlusMinus, mEvenPlus, mEvenMinus,
            mEvenTimes, mEvenOver, mCheckMemPlus, mCheckMemMinus;

    void appendDigit(Operator operator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * The available operators of the calculator.
     */
    public enum Operator {

        CLEAR, // C
        CLEAR_ENTRY, // CE
        BACKSPACE, // ⌫
        EQUAL, // =
        PLUS, // +
        MINUS, // -
        TIMES, // ×
        OVER, // ⁄
        PLUS_MINUS, // ±
        RECIPROCAL, // 1/x
        PERCENT, // %
        SQRT, // √
        MEM_CLEAR, // MC
        MEM_SET, // MS
        MEM_PLUS, // M+
        MEM_MINUS, // M-
        MEM_RECALL   // MR
    }

    public void appendDigit(int digit) {
        mShowNumber += Integer.toString(digit);
        setChanged();
        notifyObservers();
    }

    public void appendDot() {
        if (!mCheckDot) {
            mShowNumber = mShowNumber + ".";
            mCheckDot = true;
        }

    }

    public void performOperation(Operator operator) {
        // TODO code application logic here
        switch (operator) {
            case PLUS:
                if (mOperator == "+") {
                    mBuffNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                            + Double.parseDouble(mShowNumber));
                    mFirstNumber = mShowNumber;
                    mEvenPlus = true;
                    //System.out.println("B: " + mBuffNumber + " F: " + mFirstNumber + " S:" + mShowNumber);
                } else {
                    mFirstNumber = mShowNumber;
                    mBuffNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                            + Double.parseDouble(mShowNumber));
                }
                //System.out.println("OB: " + mBuffNumber + " OF: " + mFirstNumber + " OS:" + mShowNumber);
                mShowNumber = "";
                mOperator = "+";
                break;
            case MINUS:
                if (mOperator == "-") {
                    mBuffNumber = String.valueOf(Double.parseDouble(mFirstNumber) - Double.parseDouble(mShowNumber));
                    mFirstNumber = mShowNumber;
                    mEvenMinus = true;
                }
                mShowNumber = "";
                mOperator = "-";
                break;
            case TIMES:
                if (mOperator == "*") {
                    mBuffNumber = String.valueOf(Double.parseDouble(mFirstNumber) * Double.parseDouble(mShowNumber));
                    mFirstNumber = mShowNumber;
                    mEvenTimes = true;
                }
                mFirstNumber = mShowNumber;
                mShowNumber = "";
                mOperator = "*";
                break;
            case OVER:
                if (mOperator == "/") {
                    mBuffNumber = String.valueOf(Double.parseDouble(mFirstNumber) / Double.parseDouble(mShowNumber));
                    mFirstNumber = mShowNumber;
                    mEvenOver = true;
                }
                mFirstNumber = mShowNumber;
                mShowNumber = "";
                mOperator = "/";
                break;
            case CLEAR:
                mShowNumber = "";
                mFirstNumber = "";
                mBuffNumber = "0";
                mBuffNumber = "";
                mOperator = "";
                mCheckDot = false;
                break;
            case BACKSPACE:
                mShowNumber = mShowNumber.substring(0, mShowNumber.length() - 1);
                break;
            case SQRT:
                mShowNumber = String.valueOf(Math.sqrt(Double.parseDouble(mShowNumber)));
                break;
            case RECIPROCAL:
                mShowNumber = String.valueOf(1 / Double.parseDouble(mShowNumber));
                break;
            case PLUS_MINUS:
                if (!mCheckPlusMinus) {
                    mShowNumber = "-" + mShowNumber;
                    mCheckPlusMinus = true;
                } else {
                    mShowNumber = mShowNumber.substring(1, mShowNumber.length());
                    mCheckPlusMinus = false;
                }
                break;
            case PERCENT:
                if (mOperator == "+" || mOperator == "-" || mOperator == "*" || mOperator == "/") {
                    mShowNumber = String.valueOf(Double.parseDouble(mShowNumber) / 100);
                }
                break;
            case EQUAL:
                switch (mOperator) {
                    case "+":
                        if (mEvenPlus) {
                            mShowNumber = String.valueOf(Double.parseDouble(mBuffNumber)
                                    + Double.parseDouble(mShowNumber));
                        } else {
                            mShowNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                                    + Double.parseDouble(mShowNumber));
                        }
                        mEvenPlus = false;
                        break;
                    case "-":
                        if (mEvenMinus) {
                            mShowNumber = String.valueOf(Double.parseDouble(mBuffNumber)
                                    - Double.parseDouble(mShowNumber));
                        } else {
                            mShowNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                                    - Double.parseDouble(mShowNumber));
                        }
                        mEvenMinus = false;
                        break;
                    case "*":
                        if (mEvenTimes) {
                            mShowNumber = String.valueOf(Double.parseDouble(mBuffNumber)
                                    * Double.parseDouble(mShowNumber));
                        } else {
                            mShowNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                                    * Double.parseDouble(mShowNumber));
                        }
                        mEvenTimes = false;
                        break;
                    case "/":
                        if (mEvenOver) {
                            mShowNumber = String.valueOf(Double.parseDouble(mBuffNumber)
                                    / Double.parseDouble(mShowNumber));
                        } else {
                            mShowNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                                    / Double.parseDouble(mShowNumber));
                        }
                        mEvenOver = false;
                        break;
                }
                mOperator = "=";
                mBuffNumber = "0";
                mCheckPlusMinus = false;
                break;

            //mShowNumber = String.valueOf(Integer.parseInt(mShowNumber) * Integer.parseInt(mShowNumber));
            case MEM_PLUS:
                if (!mCheckMemPlus) {
                    mMBuffNumber = mShowNumber;
                    mFirstNumber = "";
                    mShowNumber = "";
                } else {
                    mShowNumber = String.valueOf(Double.parseDouble(mShowNumber)
                            + Double.parseDouble(mMBuffNumber));
                    mCheckMemPlus = false;
                }
                mCheckMemPlus = true;
                break;
            case MEM_MINUS:
                if (!mCheckMemMinus) {
                    mMBuffNumber = mShowNumber;
                    mFirstNumber = "";
                    mShowNumber = "";
                } else {
                    mShowNumber = String.valueOf(Double.parseDouble(mMBuffNumber)
                            - Double.parseDouble(mShowNumber));
                    mCheckMemMinus = false;
                }
                mCheckMemMinus = true;
                break;
            case MEM_CLEAR:
                mMBuffNumber = "";
                break;
            case MEM_SET:
                mMBuffNumber = mShowNumber;
                break;
            case MEM_RECALL:
                mShowNumber = mBuffNumber;
                break;
        }
        //mOperatorMark = true;
        setChanged();
        notifyObservers();

    }

    /**
     *
     * @return
     */
    public String getDisplay() {
        mAnser = mShowNumber + mOperator;
        return mAnser;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalculatorView().setVisible(true);
            }
        });
    }
}
