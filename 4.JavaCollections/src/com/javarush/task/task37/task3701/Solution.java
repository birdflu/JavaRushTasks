package com.javarush.task.task37.task3701;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/* 
Круговой итератор
*/
public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }
    
    public class RoundIterator implements Iterator<T>{
        Iterator itr = Solution.super.iterator();
    
        RoundIterator() {
        }
        
        @Override
        public boolean hasNext() {
            return size() != 0;
        }
    
        @Override
        public T next() {
            if (! itr.hasNext()) {
                itr = Solution.super.iterator();
            }
            return (T) itr.next();
        }
    
        @Override
        public void remove() {
            itr.remove();
        }
    
        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            itr.forEachRemaining(action);
        }
    }
}
