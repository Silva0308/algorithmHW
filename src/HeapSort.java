
public class HeapSort {

    private static int LEFT(int i) {   //ищу левого потомка `A[i]`
        return (2 * i + 1);
    }


    private static int RIGHT(int i) { //ищу правого потомка `A[i]`
        return (2 * i + 2);
    }


    private static void swap(int[] sortArr, int i, int j) { //меняю два индекса в массиве
        int swap = sortArr[i];
        sortArr[i] = sortArr[j];
        sortArr[j] = swap;
    }


    private static void heapify(int[] sortArr, int i, int size) { //рекурсивный алгоритм heapify-down.
        // ищу левого и правого потомков узла
        int left = LEFT(i);
        int right = RIGHT(i);
        int largest = i;

        //сравниваем `A[i]` с его левым и правым элементами и находим наибольшее значение
        if (left < size && sortArr[left] > sortArr[i]) largest = left;
        if (right < size && sortArr[right] > sortArr[largest]) largest = right;

        //меняю местами с потомком, имеющим большее значение и вызываю рекурсию
        if (largest != i) {
            swap(sortArr, i, largest);
            heapify(sortArr, largest, size);
        }
    }

    //функция для удаления элемента с наивысшим приоритетом
    public static int pop(int[] sortArr, int size) {
        //если в куче нет элементов
        if (size <= 0) {
            return -1;
        }
        int top = sortArr[0];

        //заменяем корень кучи последним элементом массива
        sortArr[0] = sortArr[size-1];
        //опять вызов функции
        heapify(sortArr, 0, size - 1);
        return top;
    }

    //функция для выполнения сортировки кучей массива `A` размера `n`
    public static void heapSort(int[] sortArr) {
        //строим приоритетную очередь и инициализируем ее заданным массивом
        int n = sortArr.length;

        //build-heap: вызывать heapify, начиная с последнего внутреннего
        //узел до корневого узла
        int i = (n - 2) / 2;
        while (i >= 0) {
            heapify(sortArr, i--, n);
        }

        //несколько раз извлекаем из кучи, пока она не станет пустой
        while (n > 0) {
            sortArr[n - 1] = pop(sortArr, n);
            n--;
        }
    }

    public static void main(String Args[]) {
        int[] sortArr = {12, 6, 4, 1, 15, 10, 25, 78, -1};
        heapSort(sortArr);
        for (int j : sortArr) {
            System.out.print(j + "\n");
        }
    }
}
