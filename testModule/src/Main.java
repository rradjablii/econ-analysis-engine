import java.util.LinkedList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for(int i=0;i<=9;i++){
            list.add(i+1);
        }
        System.out.println(list.get(0));
    }
}