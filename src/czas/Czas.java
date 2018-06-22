package czas;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Bartosz Piatek on 22/06/2018
 */
public class Czas
{
    //pola klasy
    private int godz;
    private int minuty;


    // konstruktory
    public Czas(int godz, int minuty) {
        this.godz = godz;

        //jak ktos poda minuty zlosliwie np. 123 min to musimy dodac 2h do godzin
        //oraz 3 minuty do minut
        if (minuty >= 60) {
            this.godz += (minuty / 60);
            this.minuty = minuty % 60;
        } else {
            this.minuty = minuty;
        }
    }


    public Czas(String czasString) {
        //wez wszystkie znaki ktore nie sa liczbami i je zamien na spacje " "
        String str = czasString.replaceAll("[^-?0-9]+", " ");

        //wez stringa z wynikiem i zamien go na liste stringow, gdzie kazdy element
        //listy to osobna liczba np. 12jhk38 da liste -> [12, 38]
        List<String> list = Arrays.asList(str.trim().split(" "));

        //zamien stringi na integery i przypisz do pol klasy
        this.godz = Integer.parseInt(list.get(0));
        this.minuty = Integer.parseInt(list.get(1));

        //tu to samo co wyzej z minutami
        if (minuty >= 60) {
            this.godz += (minuty / 60);
            this.minuty = minuty % 60;
        } else {
            this.minuty = minuty;
        }
    }


    //gettery
    public int getGodz() {
        return godz;
    }

    public int getMinuty() {
        return minuty;
    }


    //metody
    public Czas dodaj(Czas t) {
        //wez ilosc godzin i minut czasu przekazanego go metody i dodaj do czasu obiektu
        int iloscGodzin = t.getGodz() + this.godz;
        int iloscMinut = t.getMinuty() + this.minuty;

        //jesli ilosc minut jest wieksza niz 60 to trzeba je przerobic na godziny
        //a reszte minut dodac
        int tempGodz = 0;
        if(iloscMinut >= 60) {

            //dzielimy ilosc minut przez 60 i dostajemy godziny np. 134 / 60 = 2
            tempGodz = iloscMinut / 60;

            //ilosc minut teraz zmniejszamy o wielokrotnosc 60 np. 134 % 60 = 14
            iloscMinut = iloscMinut % 60;
        }

        //zwracamy nowy obiekt z iloscia godzin + minutami przerobionymi na godziny, oraz minutami
        return new Czas(iloscGodzin + tempGodz, iloscMinut);
    }

    public Czas odejmij(Czas t) {
        //zamieniam godziny na minuty i odejmuje os siebie
        int iloscGodzinWMin = (this.godz*60) - (t.getGodz()*60);
        int iloscMinut = this.getMinuty() - t.getMinuty();

        //jezeli ilosc minut jest < 0  np. 15min - 45min = -30min
        if (iloscMinut < 0) {

            //to do tych -30 dodajemy 60, zebyśmy mieli 30min na plusie a nie na minusie
            // (jak cofniesz zegarek z 11:15 o 45 min to trafisz na 10:30)
            iloscMinut = 60 + iloscMinut;

            //musimy tez odjac od godzin 60min bo sie cofnelismy na blacie zegarka o godzine
            iloscGodzinWMin = iloscGodzinWMin - 60;
        }


        if(iloscGodzinWMin < 0) {
            iloscGodzinWMin += 60;
            iloscMinut -= 60;
        }

        return new Czas(iloscGodzinWMin/60, iloscMinut);
    }

    public Czas pomnoz(int ile) {
        //zamien minuty na godziny
        int iloscMinut = this.getMinuty() + (this.getGodz()*60);

        //pomnoz te minuty razy podana wartosc ile
        int wynik = ile * iloscMinut;

        //wyciagnij z wyniku ilosc godzin i ilosc minut
        int iloscGodzin =  wynik / 60;
        wynik = wynik % 60;

        //stworz obiekt do zwrotu
        return new Czas(iloscGodzin, wynik);
    }


    //tak na prawde nie wiem o co mu chodzi w tym zadaniu, co tu oznacza zmienna n?
    //zakladam, ze jest to rozmiar tablicy i tak pisze funkcje
    static Czas sumuj(Czas[] tab, int n) {

        //w petli przechodzimy przez wszystkie obiekty z listy i zliczamy z kazdego
        //godziny oraz minuty, przypisujac je do zmiennych iloscMinut i iloscGodzin
        int iloscMinut = 0;
        int iloscGodzin = 0;
        for(int i = 0; i < n; i++) {
            iloscGodzin += tab[i].getGodz();
            iloscMinut += tab[i].getMinuty();
        }

        //jesli ilosc minut jest wieksza niz 60 to trzeba je przerobic na godziny
        //a reszte minut dodac
        if(iloscMinut >= 60) {

            //dzielimy ilosc minut przez 60 i dostajemy godziny np. 134 / 60 = 2
            //dodajemy od razu te otrzymane godziny do naszych godzin z petli
            iloscGodzin += (iloscMinut / 60);

            //ilosc minut teraz zmniejszamy o wielokrotnosc 60 np. 134 % 60 = 14
            iloscMinut = iloscMinut % 60;
        }


        return new Czas(iloscGodzin, iloscMinut);
    }

    //wypisywanie czasu
    @Override
    public String toString() {
        return godz + " h " + minuty + " min";
    }

}
