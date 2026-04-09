# Часть 9 — Эксперименты в jshell

## Как запустить jshell

Откройте терминал IntelliJ (View → Tool Windows → Terminal) и введите:
```
jshell
```
Для выхода: `/exit`

---

## Задание 9.1: Sealed-классы

### Команды (скопируйте и вставьте в jshell)

```
sealed interface Shape permits Circle, Square {}
record Circle(double r) implements Shape {}
record Square(double side) implements Shape {}
Shape s = new Circle(5)
s instanceof Circle c ? "Круг r=" + c.r() : "Не круг"
```

### Фактический вывод:

```
Круг r=5.0
```

### Вопрос: Что произойдёт при попытке создать `record Triangle(double a) implements Shape {}`?

**Ваш ответ:**
При попытке создать record Triangle(double a) implements Shape {} возникнет ошибка компиляции, так как Shape является sealed-интерфейсом, и разрешает только Circle и Square реализовывать его. Попытка создать Triangle вызовет ошибку, потому что он не указан в разрешённом списке (permits).



---

## Задание 9.2: Цепочка лямбд

### Команды

```
import java.util.function.*;
Function<String, String> trim = String::trim
Function<String, String> upper = String::toUpperCase
Function<String, String> exclaim = s -> s + "!"
var pipeline1 = trim.andThen(upper).andThen(exclaim)
var pipeline2 = exclaim.compose(upper).compose(trim)
pipeline1.apply("  hello world  ")
pipeline2.apply("  hello world  ")
```

### Фактический вывод: 

```
HELLO WORLD!
HELLO WORLD!
```

### Вопрос: Дают ли `andThen()` и `compose()` одинаковый результат? В каком случае результаты будут различаться?

**Ваш ответ:**
andThen() и compose() не дают одинаковый результат. Это различие заключается в порядке применения функций:

andThen() сначала применяет первую функцию, затем вторую.
compose() сначала применяет вторую функцию, а затем первую.

В данном случае порядок операций не влияет на результат, так как все функции действуют на строки одинаково, но в других контекстах порядок может иметь значение.


---

## Задание 9.3: Сравнение EnumSet и HashSet

### Команды

```
enum Color { RED, GREEN, BLUE, YELLOW, CYAN, MAGENTA, WHITE, BLACK }
var enumSet = java.util.EnumSet.of(Color.RED, Color.GREEN, Color.BLUE)
var hashSet = new java.util.HashSet<>(java.util.Set.of(Color.RED, Color.GREEN, Color.BLUE))
enumSet.contains(Color.RED)
hashSet.contains(Color.RED)
enumSet.getClass().getSimpleName()
hashSet.getClass().getSimpleName()
```

### Фактический вывод:

```
true
true
RegularEnumSet
HashSet
```

### Вопрос: Почему внутренний класс EnumSet называется `RegularEnumSet`? Что произойдёт, если enum будет иметь больше 64 констант?

**Ваш ответ:**

Внутренний класс EnumSet называется RegularEnumSet, потому что это класс, использующий оптимизированную структуру данных на основе битовой маски для хранения элементов, если количество элементов в enum не превышает 64. Если enum будет иметь больше 64 констант, EnumSet будет использовать другую структуру данных, например, EnumMap, для представления элементов.