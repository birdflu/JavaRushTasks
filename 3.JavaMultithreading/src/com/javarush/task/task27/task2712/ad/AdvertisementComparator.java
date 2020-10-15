package com.javarush.task.task27.task2712.ad;

import java.util.Comparator;
import java.util.List;

public class AdvertisementComparator implements Comparator<List> {

//    1. сумма денег, полученная от показов, должна быть максимальной из всех возможных вариантов
//    4. если существует несколько вариантов набора видео-роликов с одинаковой суммой денег, полученной от показов, то:
//    4.1. выбрать тот вариант, у которого суммарное время максимальное;
//    4.2. если суммарное время у этих вариантов одинаковое, то выбрать вариант с минимальным количеством роликов;

    @Override
    public int compare(List l1, List l2) {
        Advertisement o1 = (Advertisement) l1.get(0);
        Advertisement o2 = (Advertisement) l2.get(0);
        if (o1.getAmountPerOneDisplaying() > o2.getAmountPerOneDisplaying())
            return 1;
        else if (o1.getAmountPerOneDisplaying() < o2.getAmountPerOneDisplaying())
            return -1;
        else if (o1.getDuration() > o2.getDuration())
            return 1;
        else if (o1.getDuration() < o2.getDuration())
            return -1;
        else return l2.size()-l1.size();
    }
}
