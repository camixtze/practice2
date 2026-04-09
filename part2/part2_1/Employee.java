package part2.part2_1;

/**
 * Задание 2.1 — Иерархия сотрудников (абстрактный класс)
 */
public abstract class Employee {

    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        this.name = name;
        this.baseSalary = baseSalary;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public String getName() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return name;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public double getBaseSalary() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return baseSalary;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public abstract double calculateBonus();

    public double totalCompensation() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return baseSalary + calculateBonus();
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    @Override
    public String toString() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return name + " | Оклад: " + baseSalary + " | Бонус: " + calculateBonus() + " | Итого: " + totalCompensation();
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}