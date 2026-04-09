package part7.part7_1;

import java.util.*;
import java.util.function.*;

/**
 * Задание 7.1, Этап 2 — Замена лямбд на ссылки на методы
 */
public class RefactorStep2 {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Москва", "Берлин", "Токио", "Нью-Йорк", "Париж");

        // 1. Сортировка по длине
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        cities.sort(Comparator.comparingInt(String::length));
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 2. Вывод каждого элемента
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        cities.forEach(System.out::println);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 3. Преобразование в верхний регистр
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        Function<String, String> toUpper = String::toUpperCase;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 4. Проверка длины > 5
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        Predicate<String> isLong = s -> s.length() > 5; // нельзя заменить — есть сравнение
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 5. Формирование строки с восклицательным знаком
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        Function<String, String> exclaim = s -> s + "!"; // нельзя заменить — конкатенация
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 6. Создание нового списка
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        Supplier<List<String>> listFactory = ArrayList::new;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // Использование
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        List<String> result = listFactory.get();
        for (String city : cities) {
            if (isLong.test(city)) {
                result.add(toUpper.apply(city));
            }
        }
        System.out.println("Длинные города: " + result);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}