package blog;


import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import static blog.User.*;

public class Main {

    static String[] words;

    public static void main(String[] args) {
        Admin admin = new Admin("admin", "123");
        Moderator moderator = new Moderator("moder", "1234");
        Reader reader = new Reader("user", "12345");
        login();

            do {

                if (words[0].equals(admin.getLogin()) && words[1].equals(admin.getPassword())) {
                    admin.userMenu();
                }
                if (words[0].equals(moderator.getLogin()) && words[1].equals(moderator.getPassword())) {
                    moderator.userMenu();
                }
                if (words[0].equals(reader.getLogin()) && words[1].equals(reader.getPassword())) {
                    reader.userMenu();
                }
            }
            while (1 == 1);

        }

    static String[] login() {
        try {
            System.out.println("Введите, пожалуйста, свои логин и пароль через пробел.");
            Scanner input = new Scanner(System.in);
            String string = input.nextLine();
            words = string.split(" ");
             {
                      if ((Objects.equals(words[0], userCredentials[0][0]) && Objects.equals(words[1], userCredentials[0][1]))
                        || (Objects.equals(words[0], userCredentials[1][0]) && Objects.equals(words[1], userCredentials[1][1]))
                        || (Objects.equals(words[0], userCredentials[2][0]) && Objects.equals(words[1], userCredentials[2][1])))
                     {
                         return words;
                     } else {
                         System.out.println("Сочетание логина и пароля некорректное");
                         login();
                     }

            }

            }catch(NoSuchElementException | IndexOutOfBoundsException e)
            {
                System.out.println("Введен неправильный набор данных. Сначала вводится логин, потом пробел, потом пароль.");
                login();
            }
        return words;
    }
}
