package part8.part8_1;

import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Задание 8.1 — Система управления библиотекой
 *
 * Тема: объединение всех концепций частей 1–7.
 */
public class LibrarySystem {

    // ======================== enum Genre ========================

    enum Genre {
        FICTION("Художественная литература"),
        SCIENCE("Научная литература"),
        HISTORY("История"),
        PROGRAMMING("Программирование"),
        ART("Искусство");

        private final String russianName;

        Genre(String russianName) {
            this.russianName = russianName;
        }

        public String getRussianName() {
            return russianName;
        }

        public static Genre fromString(String name) {
            for (Genre g : values()) {
                if (g.russianName.equals(name)) {
                    return g;
                }
            }
            throw new IllegalArgumentException("Неизвестный жанр: " + name);
        }
    }

    // ======================== record Book ========================

    record Book(String title, String author, int year, Genre genre, double price) {
        Book {
            if (title == null || title.isBlank()) {
                throw new IllegalArgumentException("Название не может быть пустым");
            }
            if (author == null || author.isBlank()) {
                throw new IllegalArgumentException("Автор не может быть пустым");
            }
            int maxYear = Year.now().getValue();
            if (year < 1450 || year > maxYear) {
                throw new IllegalArgumentException("Год издания вне допустимого диапазона");
            }
            if (price < 0) {
                throw new IllegalArgumentException("Цена не может быть отрицательной");
            }
        }
    }

    // ======================== sealed interface LibraryItem ========================

    sealed interface LibraryItem permits PhysicalBook, EBook {
        String getInfo();
        Book book();
    }

    record PhysicalBook(Book book, String shelf) implements LibraryItem {
        @Override
        public String getInfo() {
            return "[Полка " + shelf + "] " + book.title() + " — " + book.author();
        }
    }

    record EBook(Book book, String format, double sizeMB) implements LibraryItem {
        @Override
        public String getInfo() {
            return "[" + format + ", " + sizeMB + " МБ] " + book.title() + " — " + book.author();
        }
    }

    // ======================== interface Searchable ========================

    interface Searchable {
        default boolean matches(String query) {
            return getInfo().toLowerCase().contains(query.toLowerCase());
        }

        String getInfo();

        static <T extends Searchable> List<T> search(List<T> items, String query) {
            return items.stream().filter(item -> item.matches(query)).collect(Collectors.toList());
        }
    }

    // ======================== Класс Library ========================

    static class Library {
        private final List<LibraryItem> items = new ArrayList<>();

        public void add(LibraryItem item) {
            items.add(item);
        }

        public void printCatalog() {
            for (LibraryItem item : items) {
                switch (item) {
                    case PhysicalBook pb -> System.out.println("Физ.: " + pb.getInfo());
                    case EBook eb -> System.out.println("Эл.:  " + eb.getInfo());
                }
            }
        }

        public Map<Genre, List<LibraryItem>> groupByGenre() {
            return items.stream().collect(Collectors.groupingBy(
                    i -> i.book().genre(),
                    () -> new EnumMap<>(Genre.class),
                    Collectors.toList()));
        }

        public double totalValue() {
            return items.stream().mapToDouble(i -> i.book().price()).reduce(0.0, Double::sum);
        }

        public Optional<LibraryItem> mostExpensive() {
            return items.stream().max(Comparator.comparingDouble(i -> i.book().price()));
        }

        public List<String> authorsByGenre(Genre genre) {
            return items.stream()
                    .map(LibraryItem::book)
                    .filter(b -> b.genre() == genre)
                    .map(Book::author)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    // ======================== main ========================

    public static void main(String[] args) {
        Library lib = new Library();

        // Добавление книг в библиотеку
        lib.add(new PhysicalBook(new Book("Война и мир", "Толстой", 1869, Genre.FICTION, 800), "A-12"));
        lib.add(new PhysicalBook(new Book("История России", "Соловьёв", 1851, Genre.HISTORY, 1200), "H-3"));
        lib.add(new EBook(new Book("Clean Code", "Мартин", 2008, Genre.PROGRAMMING, 1500), "PDF", 5.2));
        lib.add(new EBook(new Book("Effective Java", "Блох", 2018, Genre.PROGRAMMING, 2200), "EPUB", 3.1));
        lib.add(new PhysicalBook(new Book("Краткая история времени", "Хокинг", 1988, Genre.SCIENCE, 950), "S-7"));
        lib.add(new EBook(new Book("Искусство войны", "Сунь-цзы", 2000, Genre.ART, 400), "PDF", 1.0));
        lib.add(new PhysicalBook(new Book("Преступление и наказание", "Достоевский", 1866, Genre.FICTION, 700), "A-5"));
        lib.add(new EBook(new Book("Sapiens", "Харари", 2014, Genre.HISTORY, 1100), "MOBI", 8.0));

        // Демонстрация работы методов
        System.out.println("=== Каталог ===");
        lib.printCatalog();

        System.out.println("\n=== По жанрам ===");
        lib.groupByGenre().forEach((g, list) ->
                System.out.println(g.getRussianName() + ": " + list.size() + " элемент(ов)"));

        System.out.printf("%nОбщая стоимость: %.2f руб.%n", lib.totalValue());

        lib.mostExpensive().ifPresent(b -> System.out.println("Самая дорогая: " + b));

        System.out.println("\nАвторы программирования: " + lib.authorsByGenre(Genre.PROGRAMMING));
    }
}