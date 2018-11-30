public class Alkuluku {
    /**
     * Tämä ohjelma tarkastaa, onko parametrina saatu luku alkuluku.
     * Luku on alkuluku, jos se on jaollinen vain itsellään ja ykkösellä
     */
    public static void main(String[] args) {
        int testattava = Integer.parseInt(args[0]);
        boolean alkuluku = true; //oletetaan että luku on alkuluku.
        if (testattava % 2 == 0) {
            System.out.println(testattava + " ei ole alkuluku!");
            return; //lopetetaan ohjelma, luku ei ole alkuluku
        }
        //jos pariton luku, jatketaan testaamista kaikilla eri jakajilla testattavan neliöjuureen asti.
        for (int a = 3; a <= Math.sqrt(testattava); a = a + 2) {
            if (testattava % a == 0) {
                alkuluku = false; //luku on alkuluku koska se on jaollinen luvulla a
                break; //lopetetaan silmukka.

            }
            //silmukan jälkeen tulostettaan tieto siitä, onko kyseessä alkuluku.
            if (alkuluku) {
                System.out.println(testattava + " on alkuluku");
            } else {
                System.out.println(testattava + " ei ole alkuluku");
            }
            //ohjelma päätyy
        }
    }
}
