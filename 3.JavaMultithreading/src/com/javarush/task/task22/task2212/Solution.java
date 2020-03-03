package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null) return false;
        String telNumberWithoutMinus = telNumber.replaceFirst("--","BLABLABLA").replaceFirst("-", "").replaceFirst("-", "").replaceFirst("-","");
        return      (telNumberWithoutMinus.matches("^\\+\\d{12}")                    // +380501234567  +38050123-45-67
                ||  telNumberWithoutMinus.matches("^\\(\\d{3}\\)\\d{7}")            // (050)1234567
                ||  telNumberWithoutMinus.matches("^\\d{10}")                       // 0501234567 050123-4567 01-2-3456789 0-1-2-3456789
                ||  telNumberWithoutMinus.matches("^\\+\\d{2}\\(\\d{3}\\)\\d{7}")   // +38(050)1234567 +38(050)123-45-67
        ) &&  //  может содержать 1 пару скобок '(' и ')' , причем если она есть, то она расположена левее знаков '-'
                (telNumber.indexOf('(') >= 0 && telNumber.indexOf('(') <  telNumber.indexOf(')') && telNumber.indexOf(')') < telNumber.indexOf('-')
                || telNumber.indexOf('(') >= 0  && telNumber.indexOf('(') <  telNumber.indexOf(')') && telNumber.indexOf('-') < 0
                || telNumber.indexOf('(') < 0  && telNumber.indexOf('(') < 0
                )
            ;
    }

    public static void main(String[] args) {

    }
}
