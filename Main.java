import functions.*;
import functions.basic.*;
import java.io.*;

public class Main {
    public static void main(String[] args){

        TestOverriddenMethods(); // НОВЫЙ ТЕСТ ДЛЯ ЛАБОРАТОРНОЙ 5
    }

    // НОВЫЙ МЕТОД: Тестирование переопределенных методов для лабораторной 5
    public static void TestOverriddenMethods() {
        System.out.println("=== ТЕСТИРОВАНИЕ ПЕРЕОПРЕДЕЛЕННЫХ МЕТОДОВ (ЛАБОРАТОРНАЯ 5) ===");
        
        // Создаем тестовые данные
        FunctionPoint[] points1 = {
            new FunctionPoint(0.0, 1.0),
            new FunctionPoint(1.0, 3.0),
            new FunctionPoint(2.0, 7.0),
            new FunctionPoint(3.0, 15.0)
        };
        
        FunctionPoint[] points2 = {
            new FunctionPoint(0.0, 1.0),
            new FunctionPoint(1.0, 3.0),
            new FunctionPoint(2.0, 7.0),
            new FunctionPoint(3.0, 15.0)
        };
        
        FunctionPoint[] points3 = {
            new FunctionPoint(0.0, 1.0),
            new FunctionPoint(1.0, 3.0),
            new FunctionPoint(2.0, 8.0), // Отличается от points1
            new FunctionPoint(3.0, 15.0)
        };

        // 1. Тестирование метода toString()
        System.out.println("\n1. ТЕСТИРОВАНИЕ МЕТОДА toString()");
        System.out.println("=================================");
        
        ArrayTabulatedFunction arrayFunc1 = new ArrayTabulatedFunction(points1);
        LinkedListTabulatedFunction listFunc1 = new LinkedListTabulatedFunction(points1);
        
        System.out.println("ArrayTabulatedFunction: " + arrayFunc1.toString());
        System.out.println("LinkedListTabulatedFunction: " + listFunc1.toString());

        // 2. Тестирование метода equals()
        System.out.println("\n2. ТЕСТИРОВАНИЕ МЕТОДА equals()");
        System.out.println("===============================");
        
        ArrayTabulatedFunction array1 = new ArrayTabulatedFunction(points1);
        ArrayTabulatedFunction array2 = new ArrayTabulatedFunction(points2);
        ArrayTabulatedFunction array3 = new ArrayTabulatedFunction(points3);
        
        LinkedListTabulatedFunction list1 = new LinkedListTabulatedFunction(points1);
        LinkedListTabulatedFunction list2 = new LinkedListTabulatedFunction(points2);
        
        System.out.println("array1.equals(array2): " + array1.equals(array2) + " (ожидается: true)");
        System.out.println("list1.equals(list2): " + list1.equals(list2) + " (ожидается: true)");
        System.out.println("array1.equals(list1): " + array1.equals(list1) + " (ожидается: true)");
        System.out.println("array1.equals(array3): " + array1.equals(array3) + " (ожидается: false)");
        System.out.println("array1.equals(null): " + array1.equals(null) + " (ожидается: false)");

        // 3. Тестирование метода hashCode()
        System.out.println("\n3. ТЕСТИРОВАНИЕ МЕТОДА hashCode()");
        System.out.println("=================================");
        
        System.out.println("array1.hashCode(): " + array1.hashCode());
        System.out.println("array2.hashCode(): " + array2.hashCode());
        System.out.println("list1.hashCode(): " + list1.hashCode());
        System.out.println("list2.hashCode(): " + list2.hashCode());
        
        // Проверка контракта equals-hashCode
        System.out.println("array1.equals(array2) && array1.hashCode() == array2.hashCode(): " + 
                          (array1.equals(array2) && array1.hashCode() == array2.hashCode()));
        System.out.println("array1.equals(list1) && array1.hashCode() == list1.hashCode(): " + 
                          (array1.equals(list1) && array1.hashCode() == list1.hashCode()));
        
        // Тестирование изменения объекта
        int originalHash = array1.hashCode();
        array1.setPointY(1, 3.001); // Незначительное изменение
        int modifiedHash = array1.hashCode();
        System.out.println("HashCode до изменения: " + originalHash);
        System.out.println("HashCode после изменения Y[1] на 0.001: " + modifiedHash);
        System.out.println("Хэш-код изменился: " + (originalHash != modifiedHash));

        // 4. Тестирование метода clone()
        System.out.println("\n4. ТЕСТИРОВАНИЕ МЕТОДА clone()");
        System.out.println("==============================");
        
        // Тестирование ArrayTabulatedFunction
        ArrayTabulatedFunction arrayOriginal = new ArrayTabulatedFunction(points1);
        ArrayTabulatedFunction arrayClone = (ArrayTabulatedFunction) arrayOriginal.clone();
        
        System.out.println("Array - исходная: " + arrayOriginal);
        System.out.println("Array - клон: " + arrayClone);
        System.out.println("Разные ссылки: " + (arrayOriginal != arrayClone));
        System.out.println("Равное содержимое: " + arrayOriginal.equals(arrayClone));
        
        // Проверка глубокого клонирования для Array
        arrayOriginal.setPointY(0, 100.0);
        System.out.println("После изменения исходного Array:");
        System.out.println("Исходная: " + arrayOriginal.getPoint(0));
        System.out.println("Клон: " + arrayClone.getPoint(0));
        System.out.println("Содержимое разное: " + !arrayOriginal.equals(arrayClone));
        
        // Тестирование LinkedListTabulatedFunction
        LinkedListTabulatedFunction listOriginal = new LinkedListTabulatedFunction(points1);
        LinkedListTabulatedFunction listClone = (LinkedListTabulatedFunction) listOriginal.clone();
        
        System.out.println("List - исходная: " + listOriginal);
        System.out.println("List - клон: " + listClone);
        System.out.println("Разные ссылки: " + (listOriginal != listClone));
        System.out.println("Равное содержимое: " + listOriginal.equals(listClone));
        
        // Проверка глубокого клонирования для List
        listOriginal.setPointY(2, 200.0);
        System.out.println("После изменения исходного List:");
        System.out.println("Исходная: " + listOriginal.getPoint(2));
        System.out.println("Клон: " + listClone.getPoint(2));
        System.out.println("Содержимое разное: " + !listOriginal.equals(listClone));
        
        // Тестирование через интерфейс
        TabulatedFunction interfaceOriginal = new ArrayTabulatedFunction(points1);
        TabulatedFunction interfaceClone = (TabulatedFunction) interfaceOriginal.clone();
        System.out.println("Интерфейс - тип клона: " + interfaceClone.getClass().getSimpleName());
        System.out.println("Интерфейс - клонирование корректно: " + interfaceOriginal.equals(interfaceClone));

        System.out.println("\n=== ТЕСТИРОВАНИЕ ПЕРЕОПРЕДЕЛЕННЫХ МЕТОДОВ ЗАВЕРШЕНО ===");
    }
}