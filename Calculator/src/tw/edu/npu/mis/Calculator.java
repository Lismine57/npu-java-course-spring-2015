/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.npu.mis;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * The model class of the calculator application.
 */
public class Calculator extends Observable {

    String mShowNumber = "";
    String mFirstNumber = "";
    String mBuffNumber = "0";
    String mOperator = "";
    String mAnser = "";
    boolean mCheckDot, mCheckPlusMinus, mEvenPlus, mEvenMinus,
            mEvenTimes, mEvenOver;

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
                mFirstNumber = mShowNumber;
                if (mEvenPlus) {
                    mBuffNumber = mFirstNumber;
                }
                mShowNumber = "";
                mOperator = "+";
                mEvenPlus = true;
                break;
            case MINUS:
                mFirstNumber = mShowNumber;
                if (mEvenMinus) {
                    mBuffNumber = mFirstNumber;
                }
                mShowNumber = "";
                mOperator = "-";
                mEvenMinus = true;
                break;
            case TIMES:
                mFirstNumber = mShowNumber;
                if (mEvenTimes) {
                    mBuffNumber = mFirstNumber;
                }
                mShowNumber = "";
                mOperator = "*";
                mEvenTimes = true;
                break;
            case OVER:
                mFirstNumber = mShowNumber;
                if (mEvenOver) {
                    mBuffNumber = mFirstNumber;
                }
                mShowNumber = "";
                mOperator = "/";
                mEvenOver = true;
                break;
            case CLEAR:
                mShowNumber = "";
                mFirstNumber = "";
                mBuffNumber = "0";
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
                        mShowNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                                + Double.parseDouble(mShowNumber)
                                + Double.parseDouble(mBuffNumber));
                        mEvenPlus = false;
                        break;
                    case "-":
                        mShowNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                                - Double.parseDouble(mShowNumber)
                                - Double.parseDouble(mBuffNumber));
                        mEvenMinus = false;
                        break;
                    case "*":
                        mShowNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                                * Double.parseDouble(mShowNumber)
                                * Double.parseDouble(mBuffNumber));
                        mEvenTimes = false;
                        break;
                    case "/":
                        mShowNumber = String.valueOf(Double.parseDouble(mFirstNumber) / Double.parseDouble(mShowNumber));
                        mShowNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                                / Double.parseDouble(mShowNumber)
                                / Double.parseDouble(mBuffNumber));
                        mEvenOver = false;
                        break;
                }
                mOperator = "=";
                mCheckPlusMinus = false;
                break;
            //mShowNumber = String.valueOf(Integer.parseInt(mShowNumber) * Integer.parseInt(mShowNumber));
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
