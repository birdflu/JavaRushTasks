package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.combination.Combination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    int timeSeconds;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        // 2.2. Подобрать список видео из доступных, просмотр которых обеспечивает максимальную выгоду.
        // (Пока делать не нужно, сделаем позже).
        // 2.3. Если нет рекламных видео, которые можно показать посетителю, то бросить NoVideoAvailableException,
        // которое перехватить в оптимальном месте (подумать, где это место)
        // и с уровнем Level.INFO логировать фразу "No video is available for the order " + order
        if (storage.list().isEmpty()) {
            throw new NoVideoAvailableException();
        }

        // 2.4. Отобразить все рекламные ролики, отобранные для показа, в порядке уменьшения стоимости показа
        // одного рекламного ролика в копейках. Вторичная сортировка - по увеличению стоимости показа
        // одной секунды рекламного ролика в тысячных частях копейки.
        // Используйте метод Collections.sort (Тоже пока делать не нужно, сделаем позже).
        //    Пример для заказа [Water]:
        //    First Video is displaying... 50, 277
        //    где First Video - название рекламного ролика
        //    где 50 - стоимость показа одного рекламного ролика в копейках
        //    где 277 - стоимость показа одной секунды рекламного ролика в тысячных частях копейки (равно 0.277 коп)
        //  Используйте методы из класса Advertisement.
        List<Advertisement> list = getList(storage.list());
        Collections.sort(list, new AdvertisementComparator());
        for (Advertisement a : list) {
            System.out.printf("%s is displaying... %d, %d\n", a.getName(), a.getAmountPerOneDisplaying(), a.getPricePerSecond());
            a.revalidate();
        }
    }

    private List<Advertisement> getList(List list) {
//    1. сумма денег, полученная от показов, должна быть максимальной из всех возможных вариантов
//    2. общее время показа рекламных роликов НЕ должно превышать время приготовления блюд для текущего заказа;
//    3. для одного заказа любой видео-ролик показывается не более одного раза;
//    4. если существует несколько вариантов набора видео-роликов с одинаковой суммой денег, полученной от показов, то:
//    4.1. выбрать тот вариант, у которого суммарное время максимальное;
//    4.2. если суммарное время у этих вариантов одинаковое, то выбрать вариант с минимальным количеством роликов;
//    5. количество показов у любого рекламного ролика из набора - положительное число.
        Combination combination = new Combination();

        List videos = (List) list.stream()
                .filter(a -> ((Advertisement) a).getHits() > 0).collect(Collectors.toList());
        List<List<Advertisement>> result = combination.getCombinations(videos);
        result = result.subList(1, result.size());

        Stream s = result.stream().map(
                l -> new ArrayList() {{
                    add(l.stream().reduce((v1, v2) -> v1.amount(v2)).orElse(getEmptyAdvertisement()));
                    add(l);
                }})
                .filter(items -> getAmountAdvertisement(items).getDuration() <= timeSeconds);
        List<List<Advertisement>> advertisements =
                (List<List<Advertisement>>) s.max(new AdvertisementSetComparator())
                        .orElse(getEmptyAdvertisements());
        return advertisements.get(1);
    }

    private ArrayList getEmptyAdvertisements() {
        return new ArrayList() {{
            add(new ArrayList());
            add(new ArrayList());
        }};
    }

    private Advertisement getEmptyAdvertisement() {
        return new Advertisement(null, "", 0, 1, 0);
    }

    private Advertisement getAmountAdvertisement(List l) {
        return ((Advertisement) l.get(0));
    }

}
