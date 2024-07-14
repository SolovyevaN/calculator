package calculator;
import java.util.Scanner;

public class Arabian {
    public static void main(String[] args) {
        Converted converted = new Converted();
        String[] array = {"+", "-", "*", "/"};
        String[] array2 = {"\\+", "-", "\\*", "/"};
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println("Введите пример: ");
            String exp = scn.nextLine();
            if (exp.equalsIgnoreCase(" ")) {
                System.out.println("Выход из калькулятора. До свидания!");
                break;
            }

            int arrayIndex = -1;
            for (int i = 0; i < array.length; i++) {
                if (exp.contains(array[i])) {
                    arrayIndex = i;
                    break;
                }
            }
            if (arrayIndex == -1) {
                System.out.println("Некорректное выражение!");
                return;
            }
            //разделяем строку и проверяем оба ли числа одного формата
            String[] format = exp.split(array2[arrayIndex]);
            if (converted.chislo(format[0]) == converted.chislo(format[1])) {
                int a, b;
                boolean chislo = converted.chislo(format[0]);
                if (chislo) {
                    a = converted.calcKeyMap(format[0]);
                    b = converted.calcKeyMap(format[1]);
                } else {
                    //конвертируем из стоки в число
                    a = Integer.parseInt(format[0]);
                    b = Integer.parseInt(format[1]);


                    if (a < 1 || a > 10 || b < 1 || b > 10) {
                        System.out.println("Числа должны быть в диапазоне от 1 до 10.");
                        return;
                    }
                }
                //приступаем к арифметическим действиям

                int result;
                switch(array[arrayIndex]) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    default:
                        result = a / b;
                        break;
                }
                if (chislo) {
                    System.out.println(converted.arabicToRoman(result));
                } else {
                    System.out.println(result);
                }

            } else {
                System.out.println("Числа имеют разные форматы");
                return;
            }
        }

    }

    static class Converted {
        // Метод определяет является ли строка римским числом
        public boolean chislo(String s) {

            return s.matches("[IVXLCDM]+");
        }

        // Метод для преобразования римского числа в арабское
        public int calcKeyMap(String roman) {
            switch (roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
                case "XX":
                    return 20;
                case "XXX":
                    return 30;
                case "XL":
                    return 40;
                case "L":
                    return 50;
                case "LX":
                    return 60;
                case "LXX":
                    return 70;
                case "LXXX":
                    return 80;
                case "XC":
                    return 90;
                case "C":
                    return 100;
                default:
                    throw new IllegalArgumentException("Некорректное римское число: " + roman);
            }
        }

        public String arabicToRoman(int number) {
            if (number < 1) {
                throw new IllegalArgumentException("Римские числа не могут быть меньше 1.");
            }
            if (number > 100) {
                throw new IllegalArgumentException("Римские числа не могут быть больше 100.");
            }
            String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
            String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
            String[] hundreds = {"", "C"};
            return hundreds[number / 100] + tens[(number%100)/10] + units[number % 10];
        }
    }
}
