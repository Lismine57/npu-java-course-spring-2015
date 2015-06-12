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
    String mBuffNumber = "";
    String mOperator = "";
    String mAnser = "";
    boolean mCheckDot;

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

        /*if (mAnser.equals("+")) {
         mSecondNumber = Integer.toString(digit);
         }*/
        //mFirstNumber = mShowNumber;
        //mShowNumber = "";
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
                mShowNumber = "";
                mOperator = "+";
                break;
            case MINUS:
                mFirstNumber = mShowNumber;
                mShowNumber = "";
                mOperator = "-";
                break;
            case TIMES:
                mFirstNumber = mShowNumber;
                mShowNumber = "";
                mOperator = "*";
                break;
            case OVER:
                mFirstNumber = mShowNumber;
                mShowNumber = "";
                mOperator = "/";
                break;
            case CLEAR:
                mShowNumber = "";
                mFirstNumber = "";
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
            case PLUS_MINUS:
                mShowNumber = "-" + mShowNumber;
                break;
                
            case EQUAL:
                switch (mOperator) {
                    case "+":
                        mShowNumber = String.valueOf(Double.parseDouble(mFirstNumber) + Double.parseDouble(mShowNumber));
                        break;
                    case "-":
                        mShowNumber = String.valueOf(Double.parseDouble(mFirstNumber) - Double.parseDouble(mShowNumber));
                        break;
                    case "*":
                        mShowNumber = String.valueOf(Double.parseDouble(mFirstNumber) * Double.parseDouble(mShowNumber));
                        break;
                    case "/":
                        mShowNumber = String.valueOf(Double.parseDouble(mFirstNumber) / Double.parseDouble(mShowNumber));
                        break;
                }
                mOperator = "=";
                break;
            //mShowNumber = String.valueOf(Integer.parseInt(mShowNumber) * Integer.parseInt(mShowNumber));
        }
        //mOperatorMark = true;
        setChanged();
        notifyObservers();
    }

    public String getDisplay() {

        //if(mAnser.length() > 0 && mAnser.equals("+")) mAnser = mShowNumber;
        //else mAnser = mShowNumber + mOperator;
        mAnser = mShowNumber + mOperator;
        //mAnser = mShowNumber;
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
