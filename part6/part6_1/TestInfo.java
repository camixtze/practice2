package part6.part6_1;

import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * Задание 6.1 (Часть A) — Исправьте мета-аннотации
 *
 * Тема: собственные аннотации и мета-аннотации.
 */

// Исправлено: аннотация теперь доступна через рефлексию
@Retention(RetentionPolicy.RUNTIME) // Заменили SOURCE на RUNTIME
@Target(ElementType.METHOD) // Применяется к методам
@interface TestInfo {
    String author();
    String date();
    String description() default "";
    int priority() default 5;
}

/**
 * Демонстрационный класс с аннотированными методами.
 */
class Calculator {

    @TestInfo(author = "Иван", date = "2024-01-15", description = "Тест сложения", priority = 1)
    public void testAdd() {
        System.out.println("  2 + 3 = " + (2 + 3));
    }

    @TestInfo(author = "Мария", date = "2024-01-16", priority = 3)
    public void testMultiply() {
        System.out.println("  4 * 5 = " + (4 * 5));
    }

    /**
     * Точка входа. Использует Reflection для чтения аннотаций.
     */
    public static void main(String[] args) throws Exception {
        Calculator calc = new Calculator();

        System.out.println("=== Анализ аннотаций @TestInfo ===\n");

        // Используем Reflection для получения аннотаций
        for (Method method : Calculator.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(TestInfo.class)) {
                TestInfo info = method.getAnnotation(TestInfo.class);
                System.out.println("Метод: " + method.getName());
                System.out.println("  Автор:    " + info.author());
                System.out.println("  Дата:     " + info.date());
                System.out.println("  Описание: " + info.description());
                System.out.println("  Приоритет: " + info.priority());
                System.out.print("  Результат: ");
                method.invoke(calc); // Вызываем метод
                System.out.println();
            }
        }
    }
}