package part1.part1_3;

import java.util.ArrayList;
import java.util.Arrays;

import part1.part1_1.BankAccount;

public class VarDemo {

    // var field = 10;
    // ↑ Не компилируется: var нельзя использовать для полей класса.

    public static void main(String[] args) {

        // === Рабочие примеры var ===

        // TODO: Пример 1 — целое число
        var number = 42;
        System.out.println(number + " -> " + ((Object) number).getClass().getSimpleName());

        // TODO: Пример 2 — строка
        var text = "Java";
        System.out.println(text + " -> " + text.getClass().getSimpleName());

        // TODO: Пример 3 — ArrayList
        var list = new ArrayList<>(Arrays.asList("один", "два"));
        System.out.println(list + " -> " + list.getClass().getSimpleName());

        // TODO: Пример 4 — массив
        var array = new int[]{1, 2, 3};
        System.out.println(Arrays.toString(array) + " -> " + array.getClass().getSimpleName());

        // TODO: Пример 5 — ваш собственный объект (например, BankAccount)
        var account = new BankAccount("Тест", 0);
        System.out.println(account.getClass().getSimpleName() + " -> " + account.getClass().getSimpleName());

        // TODO: раскомментируйте примеры выше по одному и запустите программу.

        // === Случаи, где var НЕ компилируется ===

        // 1. Без инициализации:
        // var x;
        // нельзя определить тип переменной

        // 2. Как параметр:
        // void test(var p)
        // параметры метода требуют явный тип

        // 3. Как поле класса:
        // var field = 10;
        // var нельзя использовать вне метода

        // 4. С null:
        // var nothing = null;
        // null не имеет типа → компилятор не может вывести тип
    }
}