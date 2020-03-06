public class Algarv {
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
}