package company.core;

/**
 * Задание 1.2 — Исправленный Employee с правильной инкапсуляцией
 */
public class EmployeeFixed {

    private String name;
    private int age;
    private double salary;
    private String password;

    public EmployeeFixed(String name, int age, double salary, String password) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.password = password;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public String getName() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return name;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public int getAge() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return age;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public double getSalary() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return salary;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public void promote(double raise) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        this.salary += raise;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public void printSummary() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        System.out.println(name + ", " + age + " лет, " + salary + " руб.");
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    private boolean validatePassword(String input) {
        return password.equals(input);
    }

    public boolean authenticate(String input) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return validatePassword(input);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}