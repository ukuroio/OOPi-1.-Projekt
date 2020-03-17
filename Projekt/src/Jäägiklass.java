import java.util.ArrayList;
import java.util.Arrays;


public class Jäägiklass {
    private int moodul; //Moodul, mille järgi korpus luuakse
    private int[] tsükkel; //avaldab elemendid moodustaja astmete tsüklina

    public Jäägiklass(int moodul) {
        this.moodul = moodul;
    }

    public int getMoodul() {
        return moodul;
    }

    public void setMoodul(int moodul) {
        this.moodul = moodul;
    }

    public int[] getTsükkel() {
        return tsükkel;
    }

    public int taandamine(int a) { return (a % moodul + moodul) % moodul; } //taandab vähimaks mittenegatiivseks jäägiklassi esindajaks

    public int liitmine(int a, int b){
        return (a + b) % moodul;
    } //jäägiklasside liitmine

    public int lahutamine(int a, int b) { return (a - b) % moodul; } //jäägiklasside lahutamine

    public int korrutamine(int a, int b){ return (a * b) % moodul; } //jäägiklasside korrutamine

    public int astendamine(int a, int b) { //Jäägiklassi astendamine mittenegatiivse arvuga
        int muutuja = 1;
        for (int i = 0; i < b; i++) {
            muutuja = korrutamine(muutuja,a);
        }
        return muutuja;
    }

    //loome korpuse elementide tsüklilise esituse mingi moodustaja kaudu
    //seda peab alati peaprogrammis enne järgmiste meetodite kasutamist tehtud olema
    public int[] looTsükkel(){
        //leiame moodustaja (teame, et iga lõplik korpus on tsükliline)
        //põhimõtteliselt käime läbi elemendid ja kontrollime, et nad ei oleks liiga väikesel astmel võrdsed ühega
        // (kasulik on Lagrange'i teoreem)
        ArrayList<Integer> võimalikudAstmed = Tegurdus.tegurid(moodul - 1);
        int moodustaja = 1;
        boolean muutuja;
        for (int i = 1; i < moodul; i++) {
            muutuja = true;
            for (int j = 0; j < võimalikudAstmed.size(); j++) {
                if (taandamine(astendamine(i, võimalikudAstmed.get(j))) == 1 && võimalikudAstmed.get(j) != moodul - 1) {
                    muutuja = false;
                }
            }
            if (muutuja == true){
                moodustaja = i;
                break;
            }
        }
        //loome tsükli
        int[] tsükkel1 = new int[moodul-1];
        for (int i = 0; i < moodul-1; i++) {
            tsükkel1[i] = taandamine(astendamine(moodustaja, i));
        }
        tsükkel = tsükkel1;
        return tsükkel1;
    }

    public int pöördarv(int a){ //leiab antud jäägiklassi pöördelemendi
        if(taandamine(a) == 0){
            return 1; //siia erind
        }
        else {
            int positsioon = 0;
            for (int i = 0; i < tsükkel.length; i++) {
                if (tsükkel[i] == taandamine(a)) {
                    positsioon = i;
                    break;
                }
            }
            return tsükkel[(moodul - 1 - positsioon) % (moodul - 1)];
        }
    }

    public int jagamine(int a, int b ) { //jagab jäägiklassi a jäägiklassiga b (st korrutab a b pöördelemendiga)
        if (taandamine(b) == 0){
            return 1;
            //viskab erindi
        }
        else{
            return korrutamine(a,pöördarv(b));
        }
    }

    @Override
    public String toString() {
        return "Jäägiklass{" +
                "moodul=" + moodul +
                ", tsükkel=" + Arrays.toString(tsükkel) +
                '}';
    }
}
