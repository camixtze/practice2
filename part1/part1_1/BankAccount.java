package part1.part1_1;

/**
 * Задание 1.1 — Банковский счёт
 */
public class BankAccount {

    private String owner;
    private double balance;
    private String accountNumber;

    private static int totalAccounts;
    private static String bankName;

    static {
        // TODO: bankName = "Java Bank"; выведите "Банковская система инициализирована"
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        bankName = "Java Bank";
        System.out.println("Банковская система инициализирована");
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    {
        // TODO: totalAccounts++; выведите "Создание счёта #" + totalAccounts
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        totalAccounts++;
        System.out.println("Создание счёта #" + totalAccounts);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public BankAccount(String owner, double initialBalance) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        this.owner = owner;
        this.balance = initialBalance;
        this.accountNumber = "ACC-" + totalAccounts;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public void deposit(double amount) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        if (amount <= 0) {
            System.out.println("Ошибка: сумма должна быть положительной");
            return;
        }
        balance += amount;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public void withdraw(double amount) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        if (amount <= 0) {
            System.out.println("Ошибка: сумма должна быть положительной");
            return;
        }
        if (balance < amount) {
            System.out.println("Ошибка: недостаточно средств");
            return;
        }
        balance -= amount;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public static int getTotalAccounts() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return totalAccounts;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    @Override
    public String toString() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return String.format("[%s] %s: %.2f руб.", accountNumber, owner, balance);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public static void main(String[] args) {
        BankAccount a1 = new BankAccount("Алиса", 1000);
        BankAccount a2 = new BankAccount("Борис", 500);

        System.out.println(a1);
        System.out.println(a2);

        a1.deposit(500);
        System.out.println("После пополнения: " + a1);

        a1.withdraw(200);
        System.out.println("После снятия: " + a1);

        a1.withdraw(5000);

        a2.deposit(-100);

        System.out.println("Всего счетов: " + BankAccount.getTotalAccounts());
    }
}