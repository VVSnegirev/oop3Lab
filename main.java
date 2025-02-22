/*Реализовать паттерн «Фабрика», реализующую Унаследованный класс 1 и Унаследованный класс 2. (Удалить Класс 1, следовательно реализациию перенести в наследуемые классы)*/
import java.util.Scanner;

// Интерфейс обработчика текста
interface TextProcessor {
    String processText(String text, int n);
}

// Унаследованный класс 1
class InheritedTextProcessor1 implements TextProcessor {
    // Операция 1: Шифрование текста шифром Цезаря
    @Override
    public String processText(String text, int n) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + n) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    // Операция 2: Вывод символа с самым большим порядковым номером
    public char findMaxChar(String text) {
        char maxChar = 0;
        for (char c : text.toCharArray()) {
            if (c > maxChar) {
                maxChar = c;
            }
        }
        return maxChar;
    }
}

// Унаследованный класс 2
class InheritedTextProcessor2 implements TextProcessor {
    // Переопределенная операция 1: Расшифровка текста шифром Цезаря
    @Override
    public String processText(String text, int n) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition - n + 26) % 26; // Обратный сдвиг
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}

// Фабрика для создания экземпляров классов
class TextProcessorFactory {
    public static TextProcessor createProcessor(int choice) {
        if (choice == 1) {
            return new InheritedTextProcessor1();
        } else if (choice == 2) {
            return new InheritedTextProcessor2();
        } else {
            throw new IllegalArgumentException("Неверный выбор");
        }
    }
}

// Основной класс
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите вариант: 1 (шифрование) или 2 (расшифровка)");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Поглощаем оставшийся символ новой строки

        // Создаем процессор с помощью фабрики
        TextProcessor processor = TextProcessorFactory.createProcessor(choice);

        System.out.println("Введите текст:");
        String text = scanner.nextLine();

        if (choice == 1) {
            System.out.println("Введите сдвиг для шифра Цезаря:");
            int shift = scanner.nextInt();
            System.out.println("Зашифрованный текст: " + processor.processText(text, shift));

            // Демонстрация операции 2 (только для InheritedTextProcessor1)
            if (processor instanceof InheritedTextProcessor1) {
                System.out.println("Символ с самым большим порядковым номером: " +
                        ((InheritedTextProcessor1) processor).findMaxChar(text));
            }
        } else if (choice == 2) {
            System.out.println("Введите сдвиг для расшифровки:");
            int shift = scanner.nextInt();
            System.out.println("Расшифрованный текст: " + processor.processText(text, shift));
        }
    }
}
