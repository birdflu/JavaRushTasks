package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.Tablet;

import java.util.logging.Logger;

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
    if (storage.list().isEmpty()){
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
  }
}
