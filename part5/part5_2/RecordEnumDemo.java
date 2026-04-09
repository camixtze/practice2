package part5.part5_2;

/**
 * Задание 5.2 — Record с бизнес-логикой и Enum с абстрактным методом
 *
 * Тема: record с методами, enum с индивидуальной реализацией.
 */
public class RecordEnumDemo {

    // === Record Temperature ===

    /**
     * Температура с единицей измерения.
     *
     * Задача:
     *   1. В компактном конструкторе проверьте, что значение не ниже абсолютного нуля.
     *   2. Реализуйте convertTo() для конверсии между единицами.
     *   3. Переопределите toString(): "36.6 °C", "97.88 °F", "309.75 K".
     */
    record Temperature(double value, Unit unit) {

        enum Unit { CELSIUS, FAHRENHEIT, KELVIN }

        Temperature {
            // Переводим в Кельвины и проверяем, что температура не ниже абсолютного нуля
            double kelvin = switch (unit) {
                case CELSIUS -> value + 273.15;
                case FAHRENHEIT -> (value - 32) * 5.0/9.0 + 273.15;
                case KELVIN -> value;
            };
            if (kelvin < 0) throw new IllegalArgumentException("Ниже абсолютного нуля");
        }

        /**
         * Конвертирует температуру в другую единицу.
         *
         * Алгоритм:
         *   1. Переведите текущее значение в Цельсии (промежуточный шаг).
         *   2. Из Цельсия переведите в целевую единицу.
         */
        public Temperature convertTo(Unit targetUnit) {
            double celsius = switch (unit) {
                case CELSIUS -> value;
                case FAHRENHEIT -> (value - 32) * 5.0 / 9.0;
                case KELVIN -> value - 273.15;
            };
            double result = switch (targetUnit) {
                case CELSIUS -> celsius;
                case FAHRENHEIT -> celsius * 9.0 / 5.0 + 32;
                case KELVIN -> celsius + 273.15;
            };
            return new Temperature(result, targetUnit);
        }

        /**
         * Формат: "36.60 °C" или "97.88 °F" или "309.75 K".
         *
         * Подсказка: switch по unit: CELSIUS → "°C", FAHRENHEIT → "°F", KELVIN → "K".
         */
        @Override
        public String toString() {
            String suffix = switch (unit) {
                case CELSIUS -> "°C";
                case FAHRENHEIT -> "°F";
                case KELVIN -> "K";
            };
            return String.format("%.2f %s", value, suffix);
        }
    }

    // === Enum MathOperation ===

    /**
     * Математическая операция с абстрактным методом.
     */
    enum MathOperation {
        ADD {
            @Override
            public double apply(double a, double b) {
                return a + b; // Выполняем сложение
            }
        },
        SUBTRACT {
            @Override
            public double apply(double a, double b) {
                return a - b; // Выполняем вычитание
            }
        },
        MULTIPLY {
            @Override
            public double apply(double a, double b) {
                return a * b; // Выполняем умножение
            }
        },
        DIVIDE {
            @Override
            public double apply(double a, double b) {
                if (b == 0) throw new ArithmeticException("Деление на ноль"); // Проверка на деление на ноль
                return a / b; // Выполняем деление
            }
        };

        public abstract double apply(double a, double b);
    }

    // === Метод main (дан — НЕ ИЗМЕНЯЙТЕ) ===

    public static void main(String[] args) {
        Temperature body = new Temperature(36.6, Temperature.Unit.CELSIUS);
        System.out.println(body);
        System.out.println(body.convertTo(Temperature.Unit.FAHRENHEIT));
        System.out.println(body.convertTo(Temperature.Unit.KELVIN));

        System.out.println();

        double a = 10, b = 3;
        for (MathOperation op : MathOperation.values()) {
            System.out.printf("%s(%g, %g) = %g%n", op.name(), a, b, op.apply(a, b));
        }
    }
}