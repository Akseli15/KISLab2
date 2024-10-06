package main;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

/**
 * Декоратор для замены указанных элементов массива на новый элемент.
 * Этот класс модифицирует результат работы базовой реализации интерфейса main.IArrayTransformation,
 * заменяя все элементы массива, равные заданному значению, на новое значение.
 *
 * Пример: если целевым элементом является 5, а элементом для замены — 0,
 * то все пятерки в массиве будут заменены на нули.
 */
@Decorator
public class ReplaceElementsDecorator implements IArrayTransformation {

    /**
     * Делегируемая базовая реализация интерфейса main.IArrayTransformation,
     * которая выполняет основную работу по трансформации массива.
     */
    @Inject
    @Delegate
    private IArrayTransformation arrayTransformation;

    /**
     * Целевой элемент, который нужно заменить в массиве.
     */
    private int targetElement;

    /**
     * Элемент, которым нужно заменить все вхождения targetElement.
     */
    private int replacementElement;

    /**
     * Конструктор декоратора, принимающий параметры целевого элемента и элемента замены.
     *
     * @param targetElement элемент, который нужно заменить.
     * @param replacementElement элемент, на который нужно заменить целевой элемент.
     */
    public ReplaceElementsDecorator(int targetElement, int replacementElement) {
        this.targetElement = targetElement;
        this.replacementElement = replacementElement;
    }

    /**
     * Метод трансформации массива, который сначала выполняет базовую трансформацию,
     * а затем заменяет все элементы массива, совпадающие с targetElement, на replacementElement.
     *
     * @param array исходный массив, который нужно трансформировать.
     * @return массив после замены указанных элементов.
     */
    @Override
    public int[] transform(int[] array) {
        // Выполняем базовую трансформацию через делегат.
        int[] transformedArray = arrayTransformation.transform(array);

        // Проходим по каждому элементу массива.
        for (int i = 0; i < transformedArray.length; i++) {
            // Если элемент совпадает с целевым, заменяем его.
            if (transformedArray[i] == targetElement) {
                transformedArray[i] = replacementElement;
            }
        }

        // Возвращаем массив с замененными элементами.
        return transformedArray;
    }
}
