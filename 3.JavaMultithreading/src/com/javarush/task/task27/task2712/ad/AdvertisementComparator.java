package com.javarush.task.task27.task2712.ad;

import java.util.Comparator;

public class AdvertisementComparator implements Comparator<Advertisement> {

    // 2.4. Отобразить все рекламные ролики, отобранные для показа, в порядке уменьшения стоимости показа
    // одного рекламного ролика в копейках. Вторичная сортировка - по увеличению стоимости показа
    // одной секунды рекламного ролика в тысячных частях копейки.

    @Override
    public int compare(Advertisement o1, Advertisement o2) {
        int l = (int) (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
        if (l != 0) return l;
        else {
            int p = (int) (o1.getPricePerSecond() - o2.getAmountPerOneDisplaying());
            return p;
        }
    }
}
