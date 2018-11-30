import java.util.Random;
import java.util.Arrays;
/**
 *  Tällä ohjelmalla voi testata MerkkijonoApuvälineet -luokkaa.
 */
class MerkkijonojenTestiohjelma{

    public static void main(String[] args){
        String mjono =args[0];
        char c = args[1].charAt(0);
        int määrä = MerkkijonoApuvälineet.laskeMerkki(mjono,c);
        System.out.println(määrä);
        System.out.println(MerkkijonoApuvälineet.onkoPalindromi("saippuakauppias"));
        System.out.println(MerkkijonoApuvälineet.onkoPalindromi("lol"));
        System.out.println(MerkkijonoApuvälineet.onkoPalindromi("Aa, varo oravaa!"));
        System.out.println(MerkkijonoApuvälineet.onkoPalindromi("lool"));
        System.out.println(MerkkijonoApuvälineet.onkoPalindromi("saippualkauppias"));
        System.out.println("------");
        System.out.println(MerkkijonoApuvälineet.onkoSähköpostiosoite("nathan@letwory.net"));
        System.out.println(MerkkijonoApuvälineet.onkoSähköpostiosoite("nathan.letwory@gmail.com"));
        System.out.println(MerkkijonoApuvälineet.onkoSähköpostiosoite("nathan..letwory@gmail.com"));
        System.out.println(MerkkijonoApuvälineet.onkoSähköpostiosoite("nathan@.letwory.net"));
        System.out.println(MerkkijonoApuvälineet.onkoSähköpostiosoite("nathan@letwory@jossain.net"));
        System.out.println(MerkkijonoApuvälineet.onkoSähköpostiosoite("nathan@letworynet"));
        System.out.println(MerkkijonoApuvälineet.onkoSähköpostiosoite("nathan@letworynet."));
        System.out.println("------");
        System.out.println(MerkkijonoApuvälineet.onkoVerkkotunnus("www.letwory.net/"));
        System.out.println(MerkkijonoApuvälineet.onkoVerkkotunnus("www.letwory.net"));
        System.out.println(MerkkijonoApuvälineet.onkoVerkkotunnus("letwory.net/"));
        System.out.println(MerkkijonoApuvälineet.onkoVerkkotunnus("letwory.net"));
        System.out.println(MerkkijonoApuvälineet.onkoVerkkotunnus(".letwory.net"));
        System.out.println(MerkkijonoApuvälineet.onkoVerkkotunnus("http://www.letwory.net/"));
        System.out.println(MerkkijonoApuvälineet.onkoVerkkotunnus("http://www.letwory.net/"));
        System.out.println(MerkkijonoApuvälineet.onkoVerkkotunnus("http://www..letwory.net/"));
        System.out.println(MerkkijonoApuvälineet.onkoVerkkotunnus("https://www.letwory.net/"));
    }
}