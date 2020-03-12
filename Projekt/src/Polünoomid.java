import java.lang.reflect.Array;
import java.util.Arrays;

public class Polünoomid {

    private int moodul; //jäägiklass üle mille polünoomide ring luuakse

    public Polünoomid(int moodul) {
        this.moodul = moodul;
    } //konstruktor

    //polünoomide liitmine
    public int[] liida(int[] a, int[] b) {
        //loome jäägiklassi mooduli järgi
        Jäägiklass Zn = new Jäägiklass(moodul);
        int pikkus = Math.max(a.length, b.length);
        int[] polüA = new int[pikkus];
        int[] polüB = new int[pikkus];

        //teisendame polünoomid ühe pikkusega polünoomdeks, lisades vajadusel nulle
        if (a.length != pikkus) {
            for (int i = 0; i < a.length; i++) {
                polüA[i + pikkus - a.length] = a[i];
            }
        }
        else{
            polüA = a;
        }
        if (b.length != pikkus) {
            for (int i = 0; i < b.length; i++) {
                polüB[i + pikkus - b.length] = b[i];
            }
        }
        else{
            polüB = b;
        }
        int[] väljund = new int[pikkus];

        //liidame vastavad elemendid kokku
        for (int i = 0; i < pikkus ; i++) {
            väljund[i]=Zn.liitmine(polüA[i], polüB[i]);
        }
        return väljund;
    }

    //polünoomide lahutamine
    public int[] lahuta(int[] a, int[] b) {
        //loome jäägiklassi
        Jäägiklass Zn = new Jäägiklass(moodul);
        int pikkus = Math.max(a.length, b.length);
        int[] polüA = new int[pikkus];
        int[] polüB = new int[pikkus];

        //teisendame polünoomid ühe pikkuseks, lisades vajadusel nulle
        if (a.length != pikkus) {
            for (int i = 0; i < a.length; i++) {
                polüA[i + pikkus - a.length] = a[i];
            }
        }
        else{
            polüA = a;
        }
        if (b.length != pikkus) {
            for (int i = 0; i < b.length; i++) {
                polüB[i + pikkus - b.length] = b[i];
            }
        }
        else{
            polüB = b;
        }
        int[] väljund = new int[pikkus];

        //lahutame vastavaid liikmeid
        for (int i = 0; i < pikkus ; i++) {
            väljund[i]=Zn.lahutamine(polüA[i], polüB[i]);
        }
        //vajadusel eemaldame nullid
        return eemaldaNullid(väljund);
    }

    //abimeetod, eemaldab esimese nulli, kui see eksisteerib
    public int[] eemaldaNull(int[] a){
        int[] b = new int[a.length-1];
        if (a[0]==0 & a.length != 1){
            for (int i = 1; i < a.length ; i++) {
                b[i-1] = a[i];
            }
        }
        return b;
    }

    //eemaldab kõik esimesed nullid peale vabaliikme
    public int[] eemaldaNullid(int[] a){
        while (a[0] == 0 && a.length != 1){
            a = eemaldaNull(a);
        }
        return a;
    }

    //teisendab jäägiklassid positiivseteks (Java lubab jääke vahemikus [-d+1,d-1])
    public int[] ilusaleKujule(int[] a){
        for (int i = 0; i < a.length; i++) {
            while (a[i]<0){
                a[i] += moodul;
            }
        }
        return a;
    }

    //abimeetod: leiab kõik võimalikud indeksipaarid, mille summa on otsitav aste
    private int[][] summad(int pikkusA, int pikkusB, int aste){
        int pikem = Math.max(pikkusA, pikkusB);
        int[][] väljund = new int[Math.min(pikem,aste+1)][2];
        for (int i = 0; i <= pikem; i++) {
            if (aste - i >= 0){
                väljund[i][0] = i;
                väljund[i][1] = aste - i;
            }
        }
        return väljund;
    }

    //polünoomide korrutamine
    public int[] korruta(int[] a, int[] b){
        //loome jäägiklassi ja väljundmassiivi
        Jäägiklass Zn = new Jäägiklass(moodul);
        int[] väljund = new int[a.length+b.length-1];
        int pikkus = väljund.length;

        //teisendame polünoomid väljundiga ühe pikkuseks, lisades ette nulle.
        int[] polüA = new int[pikkus];
        int[] polüB = new int[pikkus];

        if (a.length != pikkus) {
            for (int i = 0; i < a.length; i++) {
                polüA[i + pikkus - a.length] = a[i];
            }
        }
        else{
            polüA = a;
        }
        if (b.length != pikkus) {
            for (int i = 0; i < b.length; i++) {
                polüB[i + pikkus - b.length] = b[i];
            }
        }
        else {
            polüB = b;
        }
        //korrutame kõik võimalikud indeksite paarid, mille summa on otsitav aste, kokku ja liidame korrutised
        for (int i = 1; i <= pikkus; i++) {
            int[][] paarid = summad(polüA.length,polüB.length,pikkus-i);
            int summa = 0;
            for (int j = 0; j < paarid.length; j++) {
                summa = Zn.liitmine(summa,Zn.korrutamine(polüA[pikkus-1-paarid[j][0]],polüB[pikkus-1-paarid[j][1]]));
            }
            väljund[i-1] = summa;
        }
        //igaks juhuks eemaldame nullid (ehkki üle korpuste ei saa pealiige null tulla)
        return eemaldaNullid(väljund);
    }

    //polünoomide jagamine
    public int[][] jaga(int[] jagatav, int[] jagaja){
        //loome jäägiklassi mooduli järgi
        Jäägiklass Zn = new Jäägiklass(moodul);
        Zn.looTsükkel();
        //loome väljundi kujul [jagatis, jääk]
        int[][] väljund = new int[2][2];
        int[] jagatis = new int[Math.max(jagatav.length-jagaja.length + 1,1)];
        int pikkus = jagatis.length;

        //jagamisprotsess on tavaline polünoomide jäägiga jagamine
        int[] eelmineJagatis = {0};
        while ((jagatav.length >= jagaja.length)) {
            jagatis[pikkus - (jagatav.length - jagaja.length + 1)] = Zn.jagamine(jagatav[0], jagaja[0]);
            jagatav = lahuta(jagatav, korruta(lahuta(jagatis, eelmineJagatis), jagaja));
            eelmineJagatis = jagatis.clone();
        }
        väljund[0] = jagatis;
        väljund[1] = jagatav;
        return väljund;
    }
}
