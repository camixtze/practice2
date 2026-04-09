package part2.part2_1;

/**
 * Задание 2.1 — Стажёр (подкласс Employee)
 */
public class Intern extends Employee {

    public Intern(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public double calculateBonus() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return 10000;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}