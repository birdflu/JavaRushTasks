package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static class ExceptionFactory {
        public static Throwable exception(Enum e) {
            if (e == null) return new IllegalArgumentException();
            String enumName = e.getClass().getSimpleName();
            String message = e.toString().substring(0,1) + e.toString().substring(1).toLowerCase().replace("_", " ");
            switch (enumName) {
                case "ApplicationExceptionMessage":
                    return new Exception(message);
                case "DatabaseExceptionMessage":
                    return new RuntimeException(message);
                case "UserExceptionMessage":
                    return new Error(message);
                default:
                    return new IllegalArgumentException();
            }
        }
    }
    
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    public static void main(String[] args) {
        ExceptionFactory.exception(null);
    }
    
}