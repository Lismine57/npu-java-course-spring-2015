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

    String mShowNumber = "";  //顯示的數字
    String mFirstNumber = ""; //第一個輸入的數字
    String mBuffNumber = "0"; //Buff佔存數字
    String mMBuffNumber = ""; //mem佔存的數字
    String mOperator = ""; //運算符號
    String mAnser = ""; // 顯示在Text的 數字和符號
    boolean mCheckDot //檢查小數點有無重複
            , mCheckPlusMinus //檢查正負符號是否啟用
            , mEvenPlus //檢查連加事件
            , mEvenMinus //檢查連減事件
            , mEvenTimes //檢查連乘事件
            , mEvenOver //檢查連除事件
            , mCheckMemPlus //檢查M+是否啟動
            , mCheckMemMinus; ////檢查M-是否啟動

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

    /**
     * 把秀出來的字串ShowNumber 加上 接下來案的數字 例如: 123 → 123456 設定監聽和變動 若有等於 按按鈕時 會顯示按下的按鈕
     *
     * @param digit
     */
    public void appendDigit(int digit) {
        if (mOperator.equals("=")) {
            mShowNumber = String.valueOf(digit);
            mOperator = "";
            setChanged();
            notifyObservers();
        } else {
            mShowNumber += Integer.toString(digit);
            setChanged();
            notifyObservers();
        }

    }

    /**
     * 把小數點加在數字字串後面的方法
     */
    public void appendDot() {
        if (!mCheckDot) {
            mShowNumber = mShowNumber + ".";
            mCheckDot = true;
        }

    }

    /**
     * 運算的方法與計算 用Switch的方法撰寫
     *
     * @param operator
     */
    public void performOperation(Operator operator) {
        // TODO code application logic here
        switch (operator) {
            case PLUS:
                if (mOperator.equals("+")) {
                    if (mEvenPlus) {
                        mBuffNumber = String.valueOf(Double.parseDouble(mBuffNumber)
                                + Double.parseDouble(mShowNumber));
                    } else {
                        mBuffNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                                + Double.parseDouble(mShowNumber));
                        mFirstNumber = mShowNumber;
                        mEvenPlus = true;
                    }
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
                if (mOperator.equals("-")) {
                    if (mEvenMinus) {
                        mBuffNumber = String.valueOf(Double.parseDouble(mBuffNumber)
                                - Double.parseDouble(mShowNumber));
                    } else {
                        mBuffNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                                - Double.parseDouble(mShowNumber));
                        mFirstNumber = mShowNumber;
                        mEvenMinus = true;
                    }
                    //System.out.println("B: " + mBuffNumber + " F: " + mFirstNumber + " S:" + mShowNumber);
                } else {
                    mFirstNumber = mShowNumber;
                    mBuffNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                            - Double.parseDouble(mShowNumber));
                }
                mShowNumber = "";
                mOperator = "-";
                break;
            case TIMES:
                if (mOperator.equals("*")) {
                    if (mEvenTimes) {
                        mBuffNumber = String.valueOf(Double.parseDouble(mBuffNumber)
                                * Double.parseDouble(mShowNumber));
                    } else {
                        mBuffNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                                * Double.parseDouble(mShowNumber));
                        mFirstNumber = mShowNumber;
                        mEvenTimes = true;
                    }
                    //System.out.println("B: " + mBuffNumber + " F: " + mFirstNumber + " S:" + mShowNumber);
                } else {
                    mFirstNumber = mShowNumber;
                    mBuffNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                            * Double.parseDouble(mShowNumber));
                }
                mShowNumber = "";
                mOperator = "*";
                break;
            case OVER:
                if (mOperator.equals("/")) {
                    if (mEvenOver) {
                        mBuffNumber = String.valueOf(Double.parseDouble(mBuffNumber)
                                / Double.parseDouble(mShowNumber));
                    } else {
                        mBuffNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                                / Double.parseDouble(mShowNumber));
                        mFirstNumber = mShowNumber;
                        mEvenOver = true;
                    }
                    //System.out.println("B: " + mBuffNumber + " F: " + mFirstNumber + " S:" + mShowNumber);
                } else {
                    mFirstNumber = mShowNumber;
                    mBuffNumber = String.valueOf(Double.parseDouble(mFirstNumber)
                            / Double.parseDouble(mShowNumber));
                }
                mShowNumber = "";
                mOperator = "/";
                break;
            case CLEAR_ENTRY:
                mShowNumber = "";
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
                if (mOperator.equals("+") || mOperator.equals("-")
                        || mOperator.equals("*") || mOperator.equals("/")) {
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
        mCheckDot = false;
        setChanged();
        notifyObservers();
    }

    /**
     * 把運算符號加在字串後方 以便判別 加在一起後並回傳
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
