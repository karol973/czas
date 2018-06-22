package czas;

import java.time.chrono.ChronoZonedDateTime;

public class Main {

    public static void main(String[] args) {


        Czas t1 = new Czas(10, 66);

        //czas z konstruktora ze stringiem w zlosliwy sposob
        Czas t2 = new Czas("0 h 123 min");

        System.out.println("t1: " + t1);
        System.out.println("t2: " + t2);

        System.out.println("t1 + t2: " + t1.dodaj(t2));
        System.out.println("t1 - t2: " + t1.odejmij(t2));

        Czas[] tab = {t1, t2, t2};
        System.out.println("Czas sumuj dla t1 + t1 + t2: " + Czas.sumuj(tab, 3));

        System.out.println("t1 * 2: " + t1.pomnoz(2));
    }
}

