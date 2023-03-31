package org.example;

import java.util.List;
import java.util.Random;

public class RandSelect {

    private static final Random rand = new Random();

    public static Store select(List<Store> list, int left, int right, int k) {
        if (left == right) {
            return list.get(left);
        }

        int pivotIndex = RandPartition.partition(list, left, right);
        int pivotRank = pivotIndex - left + 1;

        if (k == pivotRank) {
            return list.get(pivotIndex);
        } else if (k < pivotRank) {
            return select(list, left, pivotIndex - 1, k);
        } else {
            return select(list, pivotIndex + 1, right, k - pivotRank);
        }
    }

    private static class RandPartition {
        public static int partition(List<Store> list, int left, int right) {
            int pivotIndex = left + rand.nextInt(right - left + 1);
            Store pivotValue = list.get(pivotIndex);

            swap(list, pivotIndex, right);

            int storeIndex = left;
            for (int i = left; i < right; i++) {
                if (list.get(i).compareTo(pivotValue) < 0) {
                    swap(list, i, storeIndex);
                    storeIndex++;
                }
            }

            swap(list, storeIndex, right);
            return storeIndex;
        }
    }


        private static <T extends Comparable<T>> void swap(List<T> list, int i, int j) {
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }


