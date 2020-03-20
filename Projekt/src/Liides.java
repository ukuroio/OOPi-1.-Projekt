import java.util.Arrays;
import java.util.Scanner;

public class Liides {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Millist tegevust soovid lasta programmil teha? Sisesta vastav arv." + "\n" + "1. Jaga" + "\n" + "2. Korruta" +
                "\n" + "3. Liida" + "\n" + "4. Lahuta" + "\n" + "5. Leia polünoomide SÜT" + "\n" + "6. Leia polünoomide VÜK" +
                "\n" + "7. Leia pöördarv");
        int tegevus = scan.nextInt();
        System.out.println("Valisid tegevuse " + tegevus);
        System.out.println("Sisesta moodul, mille järgi soovid jäägiklassikorpuse luua. Moodul peab olema algarv.");
        int moodul = scan.nextInt();
        Jäägiklass Zn = new Jäägiklass(moodul);
        Zn.looTsükkel();
        Polünoomid moodul2 = new Polünoomid(moodul);
        System.out.println("Valisid mooduliks arvu " + moodul + ".");
        System.out.println("Kas soovid tehet teostada: 1. jäägiklassikorpuses või 2. polünoomide klassis?");
        int valik1 = scan.nextInt();
        if (valik1 == 1) {
            System.out.println("Sisesta täisarvud, millega soovid tehteid teha.");
            int arv1 = scan.nextInt();
            int arv2 = scan.nextInt();

            if(tegevus==1){
                System.out.println("Jagamise tulemus on " + Zn.jagamine(arv1, arv2));
            }

            if(tegevus == 3){
                System.out.println("Liitmise tulemus on " + Zn.liitmine(arv1,arv2));
            }
        }
        if (valik1 == 2) {
            System.out.println("Sisesta esimese polünoomi liikmete arv");
            int n = scan.nextInt();
            int polünoom[] = new int[n];
            System.out.println("Sisesta esimese polünoomi kordajad");
            for (int i = 0; i < n; i++) {
                polünoom[i] = scan.nextInt();
            }
            System.out.println("Sisestasid järgmise polünoomi: " + Arrays.toString(polünoom));

            System.out.println("Sisesta teise polünoomi pikkus");
            int m = scan.nextInt();
            int polünoom2[] = new int[m];
            System.out.println("Sisesta teise polünoomi kordajad");
            for (int i = 0; i < m; i++) {
                polünoom2[i] = scan.nextInt();
            }
            System.out.println("Sisestasid järgmise polünoomi: " + Arrays.toString(polünoom2));

            if(tegevus==5){
                System.out.println("Polünoomide SÜT on " + Arrays.toString(moodul2.ilusaleKujule((moodul2.SÜT(polünoom, polünoom2)))));
            }
        }
    }
}
