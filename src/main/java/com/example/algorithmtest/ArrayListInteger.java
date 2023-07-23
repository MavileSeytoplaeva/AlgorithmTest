package com.example.algorithmtest;

import java.util.*;

import static java.util.Arrays.copyOf;

public class ArrayListInteger implements IntegerList {

    private int size;
    private Integer[] integerArray;

    public ArrayListInteger() {
    }

    public ArrayListInteger(int size) {
        this.integerArray = new Integer[size];
    }

    //    Шаг 1. Реализовать приватный метод grow, который будет отвечать за расширение массива-хранилища
//    в 1,5 раза в ситуации, когда место закончилось.
    private void grow() {
        integerArray = Arrays.copyOf(integerArray, (int) (size + size / 2));
    }

    private Integer[] generateArray() {
        Integer[] integerArray = new Integer[100000];
        for (int i = 0; i < 100000; i++) {
            Random random = new Random();
            integerArray[i] = random.nextInt(1, 10);
        }
        return integerArray;
    }

    public void sort(Integer[] arr, int begin, int end) {
        quickSort(arr,0,arr.length-1);
    }

    private void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private Integer[] arraySort(Integer[] integers) {
        Arrays.sort(integers);
        return integers;
    }

    private Object[] collectionSort(Integer[] integers) {
        Integer[] generated2 = Arrays.copyOf(integers, integers.length);
        List<Integer> list = new ArrayList<>(List.of(generated2));
        Collections.sort(list);
        return list.toArray();
    }

    private Integer[] binarySort(Integer[] integers) {
        binarySort(integers);
        return integers;
    }


    @Override
    public Integer add(Integer item) {
//        for (int i = 0; i < stringArray.length; i++) {
//            if (stringArray[i] == null) {
//                stringArray[i] = item;
//                return item + i + Arrays.toString(stringArray);
//            }
//        }
        if (size >= integerArray.length) {
            grow();
        } else {
            integerArray[size++] = item;
            return item;
        }
        return 0;
//        throw new IndexOutOfBoundsException();
    }

    @Override
    public Integer add(int index, Integer item) {
        if (size >= integerArray.length) {
            grow();
        } else if (index == size) {
            integerArray[size++] = item;
            return item;
        }
        System.arraycopy(integerArray, index, integerArray, index + 1, size - index);
        integerArray[index] = item;
        size++;
//        if (item == null) {
//            throw new NullPointerException();
//        }
//        if (index > stringArray.length) {
//            throw new IndexOutOfBoundsException();
//        }
//        if (stringArray[index] == null) {
//            stringArray[index] = item;
//            return item + Arrays.toString(stringArray);
//        } else {
//            for (int i = index; i < stringArray.length-1; i++) {
//                stringArray[i + 1] = stringArray[i];
//            }
//            stringArray[index] = item;
//            return stringArray[index] + Arrays.toString(stringArray);
//        }
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        integerArray[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        int index = indexOf(item);

        if (index == -1) {
            throw new NullPointerException();
        }

        System.arraycopy(integerArray, index + 1, integerArray, index, size - index);
        size--;
        return item;
//        if (item == null) {
//            throw new NullPointerException();
//        }
//        for (int i = 0; i < stringArray.length-1; i++) {
//            if (stringArray[i].equals(item)) {
//                stringArray[stringArray.length-1] = null;
//                stringArray[i] = stringArray[i+1];
//                return Arrays.toString(stringArray);
//            }
//        }
//        throw new StringNorFoundException();
    }

    @Override
    public Integer remove(int index) {
        if (index == -1) {
            throw new NullPointerException();
        }

        System.arraycopy(integerArray, index + 1, integerArray, index, size - index);
        size--;
        return integerArray[index];
//        return item;
//        for (int i = 0; i <= stringArray.length; i++) {
//            if (i == index) {
//                stringArray[i] = stringArray[i+1];
//                stringArray[stringArray.length-1] = null;
//                return Arrays.toString(stringArray);
//            }
//        }
//        throw new StringNorFoundException();
    }

    @Override
    public boolean contains(Integer item) {
        for (Integer s : integerArray) {
            if (s.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < integerArray.length; i++) {
            if (integerArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = integerArray.length - 1; i > 0; i--) {
            if (integerArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index > integerArray.length) {
            throw new IndexOutOfBoundsException();
        }
        return integerArray[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(integerArray, size);
    }
}
