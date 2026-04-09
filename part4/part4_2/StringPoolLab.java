package part4.part4_2;

/**
 * Задание 4.2 — Исследование String Pool
 */
public class StringPoolLab {

    public static void main(String[] args) {

        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        String s4 = new String("Hello");
        String s5 = s3.intern();
        String s6 = "Hel" + "lo";
        String half = "Hel";
        String s7 = half + "lo";

        System.out.println("=== Сравнение строк ===\n");

        // Прогноз: true. Причина: оба — литералы из пула
        System.out.println("s1 == s2      : " + (s1 == s2));
        System.out.println("s1.equals(s2) : " + s1.equals(s2));
        System.out.println();

        // Прогноз: false. Причина: new String создаёт новый объект
        System.out.println("s1 == s3      : " + (s1 == s3));
        System.out.println("s1.equals(s3) : " + s1.equals(s3));
        System.out.println();

        // Прогноз: false. Причина: два разных объекта в куче
        System.out.println("s3 == s4      : " + (s3 == s4));
        System.out.println("s3.equals(s4) : " + s3.equals(s4));
        System.out.println();

        // Прогноз: true. Причина: intern() возвращает ссылку из пула
        System.out.println("s1 == s5      : " + (s1 == s5));
        System.out.println("s1.equals(s5) : " + s1.equals(s5));
        System.out.println();

        // Прогноз: true. Причина: конкатенация литералов → пул
        System.out.println("s1 == s6      : " + (s1 == s6));
        System.out.println("s1.equals(s6) : " + s1.equals(s6));
        System.out.println();

        // Прогноз: false. Причина: runtime конкатенация → новый объект
        System.out.println("s1 == s7      : " + (s1 == s7));
        System.out.println("s1.equals(s7) : " + s1.equals(s7));
        System.out.println();

        System.out.println("=== StringBuilder ===\n");
        StringBuilder sb = new StringBuilder();
        sb.append('H').append('e').append('l').append('l').append('o');
        String s8 = sb.toString();

        // Прогноз: false. Причина: toString создаёт новый объект
        System.out.println("s1 == s8      : " + (s1 == s8));
        System.out.println("s1.equals(s8) : " + s1.equals(s8));
    }
}