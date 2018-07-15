package java2.lesson3.DOP_DZ_1;

/*
Необходимо из консоли считать пароль и проверить валидность,
результат будет true или false

Требования:
1. Пароль должен быть не менее 8ми символов.
2. В пароле должно быть число
3. В пароле должна быть хотя бы 1 строчная буква
4. В пароле должна быть хотя бы 1 заглавная буква
*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class password {

    public static void main(String[] a){

        System.out.println(testPassword("aA-Fddd--4"));
        System.out.println(testPassword1("aA-Fddd--4"));
    }

    public static boolean testPassword (String pass){

        /*
        Проверяем стороку на соответствие одной из групп:
        1. >=0 любой символ + 1 цифра + >=0 любой символ + 1 большая буква + >=0 любой символ + 1 маленькая буква + >=0 любой символ;
        2. >=0 любой символ + 1 цифра + >=0 любой символ + 1 маленькая буква + >=0 любой символ + 1 большая буква + >=0 любой символ;
        3. >=0 любой символ + 1 большая буква + >=0 любой символ + 1 цифра + >=0 любой символ + 1 маленькая буква + >=0 любой символ;
        4. >=0 любой символ + 1 маленькая буква + >=0 любой символ + 1 цифра + >=0 любой символ + 1 большая буква + >=0 любой символ;
        5. >=0 любой символ + 1 большая буква + >=0 любой символ + 1 маленькая буква + >=0 любой символ + 1 цифра + >=0 любой символ;
        6. >=0 любой символ + 1 маленькая буква + >=0 любой символ + 1 большая буква + >=0 любой символ + 1 цифра + >=0 любой символ;

        Из-за того, что в каждой группе вначале, вконце или в середине может  быть ">=0 любой символ" я не понял как внутри
        Pattern проверить длину строки, поэтому в return добавил условие проверяющее длину аргемента метода: pass.length()>8.
        */
        Pattern pattern = Pattern.compile("^((.*\\d+.*[A-Z]+.*[a-z]+.*)|" +
                                            "(.*\\d+.*[a-z]+.*[A-Z]+.*)|" +
                                            "(.*[A-Z]+.*\\d+.*[a-z]+.*)|" +
                                            "(.*[a-z]+.*\\d+.*[A-Z]+.*)|" +
                                            "(.*[A-Z]+.*[a-z]+.*\\d+.*)|" +
                                            "(.*[a-z]+.*[A-Z]+.*\\d+.*))$");
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches()&&pass.length()>8;
    }

    public static boolean testPassword1 (String pass) {

        Pattern pattern1 = Pattern.compile("^.{8,}$");      //проверяем, чтобы было не меньше 8 символов
        Pattern pattern2 = Pattern.compile("^.*\\d+.*$");   //проверяем, чтобы бала мининмум 1 цифра (сдева и справа может быть любое кол-во симоволов)
        Pattern pattern3 = Pattern.compile("^.*[a-z]+.*$"); //проверяем, чтобы бала мининмум одна маленькая буква (сдева и справа может быть любое кол-во симоволов)
        Pattern pattern4 = Pattern.compile("^.*[A-Z]+.*$"); //проверяем, чтобы была мининмум одна большая буква (сдева и справа может быть любое кол-во симоволов)
        Matcher matcher1 = pattern1.matcher(pass);
        Matcher matcher2 = pattern2.matcher(pass);
        Matcher matcher3 = pattern3.matcher(pass);
        Matcher matcher4 = pattern4.matcher(pass);
        return matcher1.matches()&&matcher2.matches()&&matcher3.matches()&&matcher4.matches();
    }
}
