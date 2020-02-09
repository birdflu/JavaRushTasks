package com.javarush.task.task17.task1714;

/* 
Comparable
*/

public  class Beach implements Comparable<Beach> {
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public  Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public synchronized int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    public  static void main(String[] args) {

/*
        Beach beach1 = new Beach("beach1", 10.2f, 3);
        Beach beach2 = new Beach("beach2", 15.2f, 2);
        Beach beach3 = new Beach("beach3", 5.2f, 2);
        Beach beach4 = new Beach("beach4", 5.2f, 2);

        System.out.println(beach1.compareTo(beach2));
        System.out.println(beach2.compareTo(beach1));
        System.out.println("===============");
        System.out.println(beach2.compareTo(beach3));
        System.out.println(beach3.compareTo(beach2));
        System.out.println("===============");
        System.out.println(beach3.compareTo(beach4));
        System.out.println(beach4.compareTo(beach3));
*/
    }

    public synchronized int compareTo(Beach beach) {
        if (this.getQuality() > beach.getQuality()) return this.getQuality();
        else if (this.getQuality() < beach.getQuality()) return -beach.getQuality();
        else if (this.getDistance() < beach.getDistance()) return (int)this.getDistance();
        else if (this.getDistance() > beach.getDistance()) return -(int)this.getDistance();
        else return 0;
    }
    }

