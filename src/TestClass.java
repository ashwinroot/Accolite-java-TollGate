import java.util.Arrays;
import java.util.regex.Pattern;
public class TestClass {
    public static final String PLAYER = "1||1||Abdul-Jabbar||Karim||1996||1974";
    public static void main(String[] args) {
        String[] data = PLAYER.split("\\|\\|");
        System.out.println(Arrays.toString(data));

        Pattern pattern = Pattern.compile("\\|\\|");
        data = pattern.split(PLAYER);
        System.out.println(Arrays.toString(data));

        pattern = Pattern.compile(Pattern.quote("||"));
        data = pattern.split(PLAYER);
        System.out.println(Arrays.toString(data));
    }
}
