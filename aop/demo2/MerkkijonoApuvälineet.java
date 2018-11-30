import java.util.Arrays;

/**
 * Luokka, joka tarjoaa työkaluja.
 */
public class MerkkijonoApuvälineet{

    /**
     *
     * Laskee parametrina saadusta merkkijonosta, kuinka merkki -esiintymää merkkijonossa on ja palauttaa kyseisen määrän.
     *
     * @param mjono Merkkijono, josta etsiä merkkiä.
     * @param merkki laskee tätä merkkiä.
     *
     * @return merkkimäärä
     */
    public static int laskeMerkki(String mjono, char merkki){
        int sum = 0;
        for(int i=0; i<mjono.length(); i++) {
            if(mjono.charAt(i)==merkki) sum++;

        }
        return sum;
    }

    /**
     * Tarkista onko annettu merkkijono palindromi
     *
     * @param s Merkkijono
     * @return true jos merkkijono on palindromi
     */
    public static boolean onkoPalindromi(String s)
    {
        boolean res = true;

        // pieniksi kirjaimiksi kaikki, helpottaa huomattavasti työt
        s = s.toLowerCase();

        // Otetaan ensin kaikki muut merkit kuin kirjaimet pois
        s = s.replaceAll("[^a-z]", "");

        // Riittää, että tarkistamme puoliväliin asti.
        // Huomaa, että näin parittomien pituuksien merkkijonoista jää
        // keskimmäinen merkki tarkistamatta. Voimmekin jättää, sillä
        // se olisi tarkistus itsensä kanssa.
        int tarkistusAlue = s.length()/2;
        int loppu = s.length()-1;

        // Juoksemme siis alusta puoliväliin (tarkistusAlue)
        for(int i=0; i<tarkistusAlue; i++) {
            // ja tarkistetaan peilikuvallisesti alkua ja loppua
            // toisiaan vastaan.
            if(s.charAt(i)!=s.charAt(loppu-i)) {
                res = false; // epäonni iski.
                break;
            }
        }
        return res;
    }

    /**
     * Tarkista onko annettu merkkijono sähköpostiosoite.
     *
     * Tarkistus ei ole tyhjentävä, eikä varsinkaan validoiva, vaan riittää,
     * että merkkijono on jotakuinkin muotoa:
     *
     *   nimi@domain.tld
     *
     * Nimi voi sisältää pisteenkin, muttei kaksi pistettä peräkkäin.
     *
     * @param s merkkijono, joka esittää mahdollisesti sähköpostiosoitetta
     * @return true jos annettu merkkijono esittää sähköpostiosoitetta
     */
    public static boolean onkoSähköpostiosoite(String s) {
        boolean res = true;
        int atcount = laskeMerkki(s, '@');

        String[] parts = s.split("@");
        if(parts.length == 2) {
            boolean eka = true;
            for(String p: parts) {
                String[] dotCheck = p.split("\\.");
                // eka osanen, yksi alkio olisi riittävä, muttei tyhjä (esimerkiks
                // pelkkä pistä ennen @-merkkiä
                if (eka && dotCheck.length<1) {
                    res = false;
                }
                // tokassa osassa pitää olla vähintään kaksi palaa
                else if(!eka && dotCheck.length<2) {
                    res = false;
                } else {
                    for (String dotPart : dotCheck) {
                        // tyhjiä merkkijonoja ei suvaita se tarkoittaisi
                        // pisteen alussa, kaksi tai useampi pistettä peräkkäin,
                        // tai pisteen lopussa.
                        if (dotPart.isEmpty()) {
                            res = false;
                            break;
                        }
                    }
                }
                eka = false; // nyt ensimmäinen osa handlattu, ei enää siis olla ekoja tämän jälkeen.
            }
        } else {
            // ei riittävästi, tai liikaa osia
            res = false;
        }
        return res;
    }

    /**
     * URI scheme käytöstä tässä enemmän:
     * https://en.wikipedia.org/wiki/Uniform_Resource_Identifier#Generic_syntax
     *
     * Verkkotunnus nyt kuitenkin vain
     *
     * [protokolla]?[//]?(host[/path]?)
     *
     * host muotoa [subdomain.]domain.tld
     *
     * Tarkistus vaatii vähintään yhden pisteen domain.tld osalta.
     *
     * @param s tarkistettava merkkijono
     * @return true jos merkkijono mukailee verkkotunnuksen muotoa.
     */
    public static boolean onkoVerkkotunnus(String s) {
        boolean res = true;

        // Tässä funktiossa käytetty kahteen kertaan regexpejä, niistä voi
        // lukea lisää:
        // https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#sum

        // Split protokolla+authority-kenoja. Jos protokollaa on annettu protParts on
        // kahden alkion pituinen, jolloin ensimmäinen alkio on tyhjä ja toinen alkio
        // on loppuosa, josta olemme kiinnostuneita.
        // Jos taas on annettu merkkijono, josta puuttuu protokolla+authority-kenot
        // protParts on vain yhden alkion pituinen - ihan alkuperäinen merkkijono
        // ainoassa alkiossa.
        String[] protParts = s.split(".*://");

        String hostPath;
        String host;
        // ei protokollaa - haluamaamme on ekassa (ainoassa) alkiossa, muuten
        // oletamme, että toisessa alkiossa on mitä haluamme jatkotyöstää.
        if(protParts.length == 1) hostPath = protParts[0];
        else hostPath = protParts[1];

        // meillä voi olla merkkijonossa keno polkua varten, joko
        // hostname/, tai hostname/joku/polku. Eli katsomme ihan vaan
        // ensimmäistä, me emme sen enempää tee mitään polkuosalle.
        int slashIdx = hostPath.indexOf('/');

        // -1 tarkoittaa ei kenoa lainkaan, muuten otetaan
        // substringina alusta ensimmäiseen kenoon.
        if(slashIdx<0) host = hostPath;
        else host = hostPath.substring(0, slashIdx+1);

        // katsotaan nyt pisteiden määrä. Haluamme siis vähintään yhden pisteen.
        String[] hostParts = host.split("\\.");

        // ei ollut tai host muutenkin vaan ollut tyhjänä
        if(hostParts.length<2) {
            res = false;
        }
        else {
            // varmistamme vielä että kaikissa osissa on vähintään joku merkki.
            // tässä voisi oikeastaan vielä tehdä lisätarkastus, ettei verkkotunnuksessa
            // ole kielettyjä merkkejä - mitä sitä nyt ei tehdä.
            for (String hp : hostParts) {
                if (hp.isEmpty()) {
                    res = false; // oh noes
                    break; // pikapoista, koska tarkastus jo epäonnistui.
                }
            }
        }

        return res;
    }

}