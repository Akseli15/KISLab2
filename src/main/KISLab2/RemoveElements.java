package main;

import javax.enterprise.inject.Default;
import java.util.Arrays;

/**
 * Реализация интерфейса main.IArrayTransformation для удаления указанных элементов из массива.
 * Этот класс позволяет удалить все вхождения заданного элемента из исходного массива.
 *
 * Пример: если целевым элементом для удаления является 5, то все пятерки будут удалены из массива.
 */
@Default
public class RemoveElements implements IArrayTransformation {

    /**
     * Элемент, который нужно удалить из массива.
     */
    private int elementToRemove;

    /**
     * Конструктор класса, принимающий элемент для удаления.
     *
     * @param elementToRemove элемент, который нужно удалить из массива.
     */
    public RemoveElements(int elementToRemove) {
        this.elementToRemove = elementToRemove;
    }

    /**
     * Метод для трансформации массива, который удаляет все вхождения элемента elementToRemove.
     *
     * @param array исходный массив, из которого нужно удалить элемент.
     * @return массив без всех вхождений элемента elementToRemove.
     */
    @Override
    public int[] transform(int[] array) {
        // Используем Stream API для фильтрации массива.
        // Метод filter пропускает только те элементы, которые не равны элементу для удаления.
        return Arrays.stream(array)
                .filter(element -> element != elementToRemove) // Оставляем только те элементы, которые не равны elementToRemove.
                .toArray(); // Преобразуем результат обратно в массив.
    }
}
