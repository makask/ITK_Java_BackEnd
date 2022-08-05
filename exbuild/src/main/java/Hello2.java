import org.apache.commons.lang3.StringUtils;

public class Hello2 {

    public static void main(String[] args) {

        String joined = StringUtils.join(
                new String[] {"Hello", "world!", "Commons version"}, ", ");

        System.out.println(joined);
    }
}
