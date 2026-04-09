package part7.part7_1;

import java.util.*;
import java.util.function.*;

public class RefactorOriginal {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Москва", "Берлин", "Токио", "Нью-Йорк", "Париж");

        // 1. Сортировка по длине
        cities.sort((a, b) -> Integer.compare(a.length(), b.length()));

        // 2. Вывод каждого элемента
        cities.forEach(city -> System.out.println(city));

        // 3. Преобразование в верхний регистр
        Function<String, String> toUpper = s -> s.toUpperCase();

        // 4. Проверка длины > 5
        Predicate<String> isLong = s -> s.length() > 5;

        // 5. Формирование строки с восклицательным знаком
        Function<String, String> exclaim = s -> s + "!";

        // 6. Создание нового списка
        Supplier<List<String>> listFactory = ArrayList::new;

        // Использование
        List<String> result = listFactory.get();
        for (String city : cities) {
            if (isLong.test(city)) {
                result.add(toUpper.apply(city));
            }
        }
        System.out.println("Длинные города: " + result);
    }
}