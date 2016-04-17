import java.util.Arrays;
import net.coderodde.util.MergeHeapsort;

public class Demo {

    public static void main(String[] args) {
        Integer[] array = new Integer[]{ 2, 3, 2, 1, 5, 4, 3, 9, 7 };
        MergeHeapsort.sort(array, 1, array.length);
        System.out.println(Arrays.toString(array));
    }
}
