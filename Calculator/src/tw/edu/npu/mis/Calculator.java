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
    String mSecondNumber = "";
    String mOperator1 = "";

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
        // TODO code application logic here
    }

    public void performOperation(Operator operator) {
        // TODO code application logic here
        switch (operator) {
            case TIMES:
                mSecondNumber = mShowNumber;
                mShowNumber = "";
                mOperator1 = "*";
                break;
            case PLUS:
                mSecondNumber += mShowNumber;
                mShowNumber = "";
                mOperator1 = "+";
                break;
            case EQUAL:
                switch (mOperator1) {
                    case "+":
                        mShowNumber = String.valueOf(Integer.parseInt(mSecondNumber) + Integer.parseInt(mShowNumber));
                        break;
                    case "-":
                        mShowNumber = String.valueOf(Integer.parseInt(mSecondNumber) - Integer.parseInt(mShowNumber));
                        break;
                    case "*":
                        mShowNumber = String.valueOf(Integer.parseInt(mSecondNumber) * Integer.parseInt(mShowNumber));
                        break;
                    case "/":
                        mShowNumber = String.valueOf(Integer.parseInt(mSecondNumber) / Integer.parseInt(mShowNumber));
                        break;
                }
                break;

            //mShowNumber = String.valueOf(Integer.parseInt(mShowNumber) * Integer.parseInt(mShowNumber));
        }
        setChanged();
        notifyObservers();
    }

    public String getDisplay() {
        String anser = mShowNumber + mOperator1;
        return anser;
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
