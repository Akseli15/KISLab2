package main;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.util.Arrays;

/**
 * Класс-декоратор для выполнения медианной фильтрации одномерного массива целых чисел.
 * Декоратор модифицирует результат работы базовой реализации интерфейса main.IArrayTransformation.
 */
@Decorator
public class MedianFilterDecorator implements IArrayTransformation {

    /**
     * Поле для внедрения базовой реализации интерфейса main.IArrayTransformation.
     * @Delegate используется для указания, что именно это поле будет обернуто
     * в декоратор.
     */
    @Inject
    @Delegate
    private IArrayTransformation arrayTransformation;

    /**
     * Метод для выполнения трансформации массива с медианной фильтрацией.
     * Сначала выполняется базовая трансформация (через делегирование), после
     * чего массив обрабатывается медианным фильтром.
     *
     * @param array массив целых чисел, который нужно трансформировать.
     * @return массив после применения базовой трансформации и медианной фильтрации.
     */
    @Override
    public int[] transform(int[] array) {
        // Выполняем базовую трансформацию массива с использованием делегата.
        int[] transformedArray = arrayTransformation.transform(array);

        // Проверяем, что длина массива не меньше 3, так как медианная фильтрация
        // требует наличия как минимум 3 элементов.
        if (transformedArray.length < 3) {
            return transformedArray; // Если элементов меньше 3, фильтрация не выполняется.
        }

        // Создаем новый массив, чтобы сохранить результат медианной фильтрации.
        int[] resultArray = Arrays.copyOf(transformedArray, transformedArray.length);

        // Применяем медианную фильтрацию ко всем элементам, кроме первого и последнего.
        for (int i = 1; i < transformedArray.length - 1; i++) {
            // Берем окно из 3 элементов: текущий элемент и его соседние элементы.
            int[] window = {transformedArray[i - 1], transformedArray[i], transformedArray[i + 1]};
            // Сортируем окно и заменяем центральный элемент на медиану.
            Arrays.sort(window);
            resultArray[i] = window[1]; // Медиана — это средний элемент в отсортированном окне.
        }

        // Возвращаем массив после применения медианной фильтрации.
        return resultArray;
    }
}
