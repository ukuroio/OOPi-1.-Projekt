import java.util.Arrays;
import java.util.Scanner;

public class Liides {

    //Teostab algarvukontrolli
    static boolean onAlgarv(int arv){
        if (arv < 2){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(arv) ; i++) {
            if(arv % i==0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Millist tegevust soovid lasta programmil teha? Sisesta vastav arv." + "\n" + "1. Jaga" + "\n" + "2. Korruta" +
                "\n" + "3. Liida" + "\n" + "4. Lahuta" + "\n" + "5. Leia polünoomide SÜT" + "\n" + "6. Leia polünoomide VÜK" +
                "\n" + "7. Leia pöördarv jäägiklassis" + "\n" + "8. Astenda");

        int tegevus = scan.nextInt();
        System.out.println("Valisid tegevuse " + tegevus);

        while(tegevus>8 || tegevus<1){
            System.out.println("Sellist tegevust ei ole. Palun vali uuesti");
            tegevus = scan.nextInt();
        }

        System.out.println("Sisesta moodul, mille järgi soovid jäägiklassikorpuse luua. Moodul peab olema algarv.");
        int moodul = scan.nextInt();

        while(!onAlgarv(moodul)){
            System.out.println("Moodul peab olema algarv. Palun sisesta uus arv.");
            moodul = scan.nextInt();
        }

        Jäägiklass Zn = new Jäägiklass(moodul);
        Zn.looTsükkel();
        Polünoomid moodul2 = new Polünoomid(moodul);
        System.out.println("Valisid mooduliks arvu " + moodul + ".");

        System.out.println("Kas soovid tehet teostada: 1. jäägiklassis või 2. polünoomide klassis?");
        int valik1 = scan.nextInt();

        while(tegevus == 5 && valik1 == 1 || tegevus == 6 && valik1 == 1){
            System.out.println("Antud tehet saab teostada ainult polünoomide klassis. Palun vali õige klass.");
            valik1 = scan.nextInt();
        }

        while(tegevus == 7 && valik1 ==2){
            System.out.println("Antud tehet saab teostada ainult jäägiklassis. Palun vali õige klass.");
            valik1 = scan.nextInt();
        }

        //Jäägiklassi tehted
        if (valik1 == 1) {
            if(tegevus==7){
                System.out.println("Sisesta täisarv, mille pöördelementi soovid leida.");
                int arv = scan.nextInt();
                System.out.println("Arvu pöördelement on " + Zn.pöördarv(arv));
            }

            else {
                System.out.println("Sisesta täisarvud, millega soovid tehteid teha.");
                int arv1 = scan.nextInt();
                int arv2 = scan.nextInt();

                if (tegevus == 1) {
                    System.out.println("Jagamise tulemus on " + Zn.jagamine(arv1, arv2));
                }

                if (tegevus == 2) {
                    System.out.println("Korrutamise tulemus on " + Zn.korrutamine(arv1, arv2));
                }

                if (tegevus == 3) {
                    System.out.println("Liitmise tulemus on " + Zn.liitmine(arv1, arv2));
                }

                if (tegevus == 4) {
                    System.out.println("Lahutamise tulemus on " + Zn.lahutamine(arv1, arv2));
                }
                
                if(tegevus==8){
                    System.out.println("Arv1 astmel arv2 on " + Zn.astendamine(arv1,arv2));
                }
            }
        }

        //Polünoomide klassi tehted
        if (valik1 == 2) {
            System.out.println("Sisesta esimese polünoomi liikmete arv");
            int n = scan.nextInt();
            int polünoom[] = new int[n];
            System.out.println("Sisesta esimese polünoomi kordajad");

            for (int i = 0; i < n; i++) {
                polünoom[i] = scan.nextInt();
            }
            System.out.println("Sisestasid järgmise polünoomi: " + Arrays.toString(polünoom));

            if (tegevus == 8) {
                System.out.println("Sisesta mittenegatiivne täisarvuline astendaja.");
                int astendaja = scan.nextInt();

                while(astendaja<0){
                    System.out.println("Astendaja peab olema mittenegatiivne täisarv. Sisesta uus astendaja.");
                    astendaja = scan.nextInt();
                }
                System.out.println("Astendamise tulemus on " + Arrays.toString((moodul2.astendaPolünoom(polünoom, astendaja))));
            }

            else {
                System.out.println("Sisesta teise polünoomi pikkus");
                int m = scan.nextInt();
                int polünoom2[] = new int[m];
                System.out.println("Sisesta teise polünoomi kordajad");
                for (int i = 0; i < m; i++) {
                    polünoom2[i] = scan.nextInt();
                }
                System.out.println("Sisestasid järgmise polünoomi: " + Arrays.toString(polünoom2));

                if (tegevus == 1) {
                    System.out.println("Polünoomide jagatis on " + Arrays.deepToString(((moodul2.jaga(polünoom, polünoom2)))));
                }

                if (tegevus == 2) {
                    System.out.println("Polünoomide korrutis on " + Arrays.toString(moodul2.korruta(polünoom, polünoom2)));
                }

                if (tegevus == 3) {
                    System.out.println("Polünoomide summa on " + Arrays.toString(moodul2.liida(polünoom, polünoom2)));
                }

                if (tegevus == 4) {
                    System.out.println("Polünoomide vahe on " + Arrays.toString(moodul2.lahuta(polünoom, polünoom2)));
                }

                if (tegevus == 5) {
                    System.out.println("Polünoomide SÜT on " + Arrays.toString(moodul2.SÜT(polünoom, polünoom2)));
                }

                if (tegevus == 6) {
                    System.out.println("Polünoomide VÜK on " + Arrays.toString(moodul2.VÜK(polünoom, polünoom2)));
                }
            }
        }
    }
}
