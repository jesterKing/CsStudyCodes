public static int laskeViereiset(int[][] matriisi) {
    int[][]m = matriisi.clone(); // kopio, jotta alkuperäinen pysyy ehjänä
    int h = m.length-1;
    int w = m[0].length-1;
    
    int vierekkaat = 0;
    
    // Siivotaan matriisimme (huom. kopio m) ykkösistä, joilla ei
    // ole naapureita. Erakot poistetaan asettamalle alkion arvoksi
    // nolla.
    for(int i=0; i<=h; i++) { // korkeus, eli taulukot matriisista
        for(int j=0;j<=w; j++) { // leveys, eli alkiot taulukoista
            if(m[i][j]==1) {
                
                boolean hasNaapuri = false;
                // vaaka
                hasNaapuri |= j==0 && m[i][j+1]==1; // vasen laita, tarkistetaan naapuria oikealta
                hasNaapuri |= j==w && m[i][j-1]==1; // oikea laita, tarkistetaan naapuria vasemmalta
                hasNaapuri |= j>0 && j<w && (m[i][j-1]==1 || m[i][j+1]==1); // keskellä, tarkistetaan naapuria kummaltakin puolelta
                
                // pysty
                hasNaapuri |= i==0 && m[i+1][j]==1; // yläreuna, tarkistetaan naapuria alakerroksesta
                hasNaapuri |= i==h && m[i-1][j]==1; // alareuna, tarkistetaan naapuria yläkerkoksesta
                hasNaapuri |= i>0 && i<h && (m[i-1][j]==1 || m[i+1][j]==1); // keskellä, tarkistetaan naapuria sekä ylä- että alakerroksesta
                
                // jos ei ole naapureita ei välitetä - karkoitetaan asettamalla nollaksi
                if(!hasNaapuri) m[i][j] = 0;
            }
        }
    }
    
    // matriisimme on nyt siivottu ei-toivotuista ykkösistä, joten riittää, että
    // otamme summan kaikista alkioista. 
    for(int[]r: m) {
        for(int n: r) vierekkaat+=n;
    }
    
    return vierekkaat;
}
