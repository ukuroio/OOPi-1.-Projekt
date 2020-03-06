import java.util.ArrayList;

public class Tegurdus {
    static ArrayList<Integer> tegurid(int arv){
        ArrayList<Integer> list= new ArrayList<>();
        for (int i = 2; i <= arv; i++) {
            if (arv % i == 0){
                list.add(i);
                for (int j = 0; j < list.size()-1; j++) {
                    if (i % list.get(j) == 0){
                        list.remove(j);
                    }
                }
            }
        }
        return list;
    }
}
