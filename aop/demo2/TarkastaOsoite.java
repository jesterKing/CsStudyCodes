import java.util.Scanner;

public class TarkastaOsoite {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // tulosta valikko
        System.out.println("1. tarkista sähköpostiosoite");
        System.out.println("2. tarkista verkkotunnus");
        System.out.println("Anna valinta:");

        // odota käyttäjän valinta
        int choice = scanner.nextInt();

        // lopetetaan jos ei ole 1 tai 2. Tässä kohtaa voisi vielä tulostaa
        // ilmoituksen, että väärin meni. Lisäksi voisi while-silmukassa
        // toista valikkoa kunnes sopiva numero on annettu.
        if(choice<1 || choice>2) return;

        // Tehdään merkkijono, jolla merkata kumpaa halutaan. Siten meidän
        // tulostekoodista tulee mahdollisimman suoraviivainen. Tämä tertiaari
        // operaattori (?:) on siihen oikein kätevää:
        // ehto ? ekajostosi : tokajosepätosi
        String uritype = choice==1 ? "osoite" : "tunnus";

        // pyydetään sitten sitä inputtia.
        System.out.println("Syötä tarkistettava" + uritype + ":");
        String syöte = scanner.next();
        boolean res = false;

        // alkuvalikon valinnan perusteella tarkistetaan syötteen kelvollisuus
        switch(choice) {
            case 1: {
                res = MerkkijonoApuvälineet.onkoSähköpostiosoite(syöte);
                break;
            }
            case 2: {
                res = MerkkijonoApuvälineet.onkoVerkkotunnus(syöte);
                break;
            }
        }

        // Tuloksen valinta merkkijonomuotoon.
        String result = res ? "kelvollinen" : "kelvoton";

        // Halutaan isolla alkukirjaimella, joten tehdäänpäs äkkiä.
        uritype = uritype.substring(0,1).toUpperCase()+uritype.substring(1);

        System.out.println(uritype + " on " + result);
    }
}
