package part5.part5_1;

import java.util.*;

/**
 * Задание 5.1 — Система оценок (enum, record, EnumMap, EnumSet)
 */
public class GradeSystem {

    // === Enum Grade ===

    /**
     * Оценка с описанием и GPA-значением.
     *
     * Синтаксис enum с полями:
     *
     *     enum Grade {
     *         A("Отлично", 4.0),
     *         B("Хорошо", 3.0);
     *         // ...
     *         private final String description;
     *         private final double gpaValue;
     *         Grade(String description, double gpaValue) { ... }
     *     }
     */
    enum Grade {
        A("Отлично", 4.0),
        B("Хорошо", 3.0),
        C("Удовлетворительно", 2.0),
        D("Неудовлетворительно", 1.0),
        F("Провал", 0.0);

        private final String description;
        private final double gpaValue;

        Grade(String description, double gpaValue) {
            // Сохраняем описание и GPA
            this.description = description;
            this.gpaValue = gpaValue;
        }

        /** Возвращает описание оценки (например, "Отлично"). */
        public String getDescription() {
            return description;
        }

        /** Возвращает GPA-значение (например, 4.0). */
        public double getGpaValue() {
            return gpaValue;
        }

        /**
         * Возвращает true, если оценка является проходной (не D и не F).
         *
         * Подсказка: return this != F && this != D;
         */
        public boolean isPassing() {
            return this != F && this != D;
        }

        /**
         * Преобразует числовую оценку (0–100) в Grade.
         *
         * Шкала: A ≥ 90, B ≥ 80, C ≥ 70, D ≥ 60, иначе F.
         *
         * Подсказка:
         * if (score >= 90) return A; else if (score >= 80) return B; ...
         */
        public static Grade fromScore(int score) {
            if (score >= 90) return A;
            else if (score >= 80) return B;
            else if (score >= 70) return C;
            else if (score >= 60) return D;
            else return F;
        }
    }

    // === Record Student ===

    /**
     * Студент с именем и ID.
     *
     * Компактный конструктор:
     *
     *     record Student(String name, int id) {
     *         Student {   // без скобок с параметрами!
     *             if (name == null || name.isBlank()) throw new IllegalArgumentException("...");
     *             if (id <= 0) throw new IllegalArgumentException("...");
     *         }
     *     }
     */
    record Student(String name, int id) {
        Student {
            if (name == null || name.isBlank()) throw new IllegalArgumentException("Имя не может быть пустым или null.");
            if (id <= 0) throw new IllegalArgumentException("ID должен быть положительным.");
        }
    }

    // === Метод main ===

    public static void main(String[] args) {

        // Создание студентов и присваивание числовых оценок
        Student[] students = {
                new Student("Анна", 1),
                new Student("Борис", 2),
                new Student("Вера", 3),
                new Student("Глеб", 4),
                new Student("Дмитрий", 5),
                new Student("Елена", 6),
                new Student("ваш имя???", 7)
        };
        int[] scores = {95, 82, 71, 58, 88, 90, 65};

        // Создание EnumMap для хранения студентов по оценкам
        var gradeMap = new EnumMap<Grade, List<Student>>(Grade.class);
        double gpaSum = 0;

        // Присвоение оценок студентам и заполнение gradeMap
        for (int i = 0; i < students.length; i++) {
            Grade g = Grade.fromScore(scores[i]);
            gradeMap.computeIfAbsent(g, k -> new ArrayList<>()).add(students[i]);
            gpaSum += g.getGpaValue();
        }

        // Создание EnumSet для проходных оценок
        var passingGrades = EnumSet.of(Grade.A, Grade.B, Grade.C);

        // Вывод сводки по каждому виду оценок
        System.out.println("=== Студенты по оценкам (проходные: A, B, C) ===");
        for (Grade g : Grade.values()) {
            List<Student> list = gradeMap.get(g);
            if (list == null || list.isEmpty()) {
                continue;
            }
            System.out.println(g + " (" + g.getDescription() + "): " +
                    list.stream().map(Student::name).toList());
        }

        // Вывод проходных оценок
        System.out.println("\nПроходные категории EnumSet: " + passingGrades);

        // Подсчёт среднего GPA
        System.out.printf("Средний GPA по выборке: %.2f%n", gpaSum / students.length);
    }
}