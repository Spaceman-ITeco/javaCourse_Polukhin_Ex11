package blog;

import java.util.Scanner;
import java.util.Objects;

import static blog.Main.login;
import static blog.Main.words;
import static blog.Note.*;

public abstract class User{

    private String login;
    private String password;
    public int user_id = 0;
    public static int count = 0;
    public static String [][]userCredentials = new String[3][2];

    public int getUser_id() {
        return user_id;
    }

    public User(String login, String password) {
        this.user_id = count++;
        this.login = login;
        this.password = password;
        this.role=getRole();
        userCredentials[getUser_id()][0]=getLogin();
        userCredentials[getUser_id()][1]=getPassword();
    }

    enum Role
    {ADMIN("admin"),MODERATOR("moderator"),READER("user");
        private String userRole;
        Role(String userRole)
        {this.userRole=userRole;}
        public String getUserRole() {
            return userRole;
        }
        public void setUserRole(String userRole) {
            this.userRole = userRole;
        }
    };
    Role role;
    protected User(String login,String password,Role role)
    {   this.login=login;
        this.password=password;
        this.role=role;
    }
    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }



    protected  Object userMenu()  {
        System.out.println("1. Поиск заметки по названию");
        System.out.println("2. Изменить пользователя");
        System.out.println("0. Выход");
        Scanner sc = new Scanner(System.in);
        String choice=sc.nextLine();
        if (Objects.equals(choice, "1"))
        {    searchNote();
            return null;
            }
        if (Objects.equals(choice, "2"))
        {   logOut();
            return null;
            }
        if (Objects.equals(choice, "0"))
        {
            System.exit(0);
            return null;
        }
        else
        {  System.out.println("Введены некорректные данные. Повторите ввод");
            return userMenu();}
    }

    protected void logOut()  {
        login();
        if(Objects.equals(words[0],"admin"))
      {   count = 0;
          Admin admin = new Admin(words[0],words[1]);
       admin.userMenu();}
        if(Objects.equals(words[0],"moder"))
        {   count = 1;
            Moderator moder = new Moderator(words[0],words[1]);
            moder.userMenu();}
        if(Objects.equals(words[0],"user"))
        {  count = 2;
            Reader user = new Reader(words[0],words[1]);
            user.userMenu();}

  }

    protected void searchNote()
    {   System.out.println("Введите название статьи.");
        System.out.println("3. Вернуться в основное меню");
        System.out.println("0. Выход");
        Scanner sn = new Scanner(System.in);
        String choice=sn.nextLine();
        for (int i = 0; i < articleData.length; i++)
        {  String searchArticle = articleData[i][1];
        if (Objects.equals(choice, searchArticle))
        {
            activeArticleName = articleData[i][1];
            activeArticleText = articleData[i][2];
            activeArticleAuthor = articleData[i][3];
            activeArticleClass =  articleData[i][4];
            activeArticleGenre = articleData[i][5];
            activeArticleAuthorChanging = articleData[i][6];
            readNote();
        }}
        if (Objects.equals(choice, "3"))
        { userMenu();
        }
        if (Objects.equals(choice, "0"))
        {
            System.exit(0);
        }
        else
        {  System.out.println("Такой статьи нет. Повторите ввод");
            searchNote();}
    };

    protected void readNote()
    {   System.out.println("Название статьи: " + activeArticleName);
        System.out.println("Текст: " + activeArticleText);
        if(Objects.equals(activeArticleClass, "class blog.ExtendedNote"))
        {System.out.println("Жанр: " + activeArticleGenre);}
        if(Objects.equals(activeArticleClass, "class blog.ExtendedNote")&&activeArticleAuthorChanging!=null)
        {System.out.println("Статья была изменена: " + activeArticleAuthorChanging);}
        System.out.println("1. Поиск новой статьи");
        System.out.println("4. Вывести автора статьи");
        System.out.println("3. Вернуться в основное меню");
        System.out.println("10. Изменить название статьи");
        System.out.println("11. Изменить слово в статье");
        System.out.println("12. Заменить текст статьи");
        System.out.println("0. Выход");
        Scanner sc = new Scanner(System.in);
        String choice=sc.nextLine();
        if (Objects.equals(choice, "1"))
        { searchNote();
        }
        if (Objects.equals(choice, "4"))
        { seeAuthor();
        }
        if (Objects.equals(choice, "3"))
        { userMenu();
        }
        if (Objects.equals(choice, "10"))
        {   if(getRole()!=Role.ADMIN && getRole()!=Role.MODERATOR)
            System.out.println("У вас нет прав менять название статьи.");
            userMenu();
        }
        if (Objects.equals(choice, "11"))
        {
            if(getRole()!=Role.ADMIN && getRole()!=Role.MODERATOR)
                System.out.println("У вас нет прав менять слова в статье.");
            userMenu();
        }
        if (Objects.equals(choice, "12"))
        {    if(getRole()!=Role.ADMIN && getRole()!=Role.MODERATOR)
            System.out.println("У вас нет прав менять текст статьи.");
            userMenu();
        }
        if (Objects.equals(choice, "0"))
        {
            System.exit(0);
        }
        else
        {  System.out.println("Введены некорректные данные. Повторите ввод");
            userMenu();}

    };

    protected void seeAuthor()
    {System.out.println("Автор статьи: " + activeArticleAuthor);
        System.out.println("1. Поиск новой статьи");
        System.out.println("5. Вернуться к статье");
        System.out.println("0. Выход");
        Scanner sc = new Scanner(System.in);
        String choice=sc.nextLine();
        if (Objects.equals(choice, "1"))
        { searchNote();
        }
        if (Objects.equals(choice, "5"))
        { readNote();
        }
        if (Objects.equals(choice, "0"))
        {
            System.exit(0);
        }
        else
        {  System.out.println("Введены некорректные данные. Повторите ввод");
            userMenu();}

    };
}
