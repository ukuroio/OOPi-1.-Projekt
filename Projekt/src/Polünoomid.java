public class Polünoomid {

    private int moodul;

    public Polünoomid(int moodul) {
        this.moodul = moodul;
    }

    public int[][] summad(int pikkusA, int pikkusB, int aste){
        int pikem = Math.max(pikkusA, pikkusB);
        int lühem = Math.min(pikkusA, pikkusB);
        int[][] väljund = new int[pikem + lühem + 1 - aste][2];
        int counter = 0;
        for (int i = 0; i <= pikem; i++) {
            if (aste - i <= lühem){
                väljund[counter][0] = i;
                väljund[counter][1] = aste-i;
                counter += 1;
            }
        }
        return väljund;
    }

    public int[] liida(int[] a, int[] b) {
        Jäägiklass Zn = new Jäägiklass(moodul);
        int pikkus = Math.max(a.length, b.length);
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
        else{
            polüB = b;
        }
        int[] väljund = new int[pikkus];

        for (int i = 0; i < pikkus ; i++) {
            väljund[i]=Zn.liitmine(polüA[i], polüB[i]);

        }
        return väljund;
    }
    public int[] lahuta(int[] a, int[] b) {
        Jäägiklass Zn = new Jäägiklass(moodul);
        int pikkus = Math.max(a.length, b.length);
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
        else{
            polüB = b;
        }
        int[] väljund = new int[pikkus];

        for (int i = 0; i < pikkus ; i++) {
            väljund[i]=Zn.lahutamine(polüA[i], polüB[i]);
        }
        return väljund;
    }

    public int[] eemaldaNull(int[] a){
        int[] b = new int[a.length-1];
        if (a[0]==0){
            for (int i = 1; i < a.length ; i++) {
                b[i-1] = a[i];
            }
        }
        return b;
    }
}
