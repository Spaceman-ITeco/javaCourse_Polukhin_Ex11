package blog;

import java.util.Objects;
import java.util.Scanner;

import static blog.Note.*;


public class Admin extends User implements Editable, EditableExtended {

    protected Admin(String login, String password) {
        super(login,password);
        this.role = Role.ADMIN;
    }

    @Override
    protected  Object userMenu()  {
        System.out.println("1. Поиск заметки по названию");
        System.out.println("2. Изменить пользователя");
        System.out.println("6. Создать новую статью");
        System.out.println("0. Выход");
        Scanner um = new Scanner(System.in);
        String choice=um.nextLine();
        if (Objects.equals(choice, "1"))
        { searchNote();
            return null;
        }
        if (Objects.equals(choice, "2"))
        {
            logOut();
            return null;
        }
        if (Objects.equals(choice, "6"))
        { selectNoteType();
            return null;
        }
        if (Objects.equals(choice, "0"))
        {
            System.exit(0);
        }
        else
        {  System.out.println("Введены некорректные данные. Повторите ввод");
            return userMenu();}
        return null;
    }

    @Override
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
        System.out.println("9. Удалить статью");
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
        if (Objects.equals(choice, "9"))
        { deleteNote();
        }
        if (Objects.equals(choice, "10"))
        {    if(getRole()!=Role.ADMIN && getRole()!=Role.MODERATOR)
        { System.out.println("У вас нет прав менять название статьи.");
            userMenu();}
            else
            changeNoteName();
        }
        if (Objects.equals(choice, "11"))
        {
            if(getRole()!=Role.ADMIN && getRole()!=Role.MODERATOR)
            {     System.out.println("У вас нет прав менять слова в статье.");
            userMenu();}
            else
                changeNoteWord();
        }
        if (Objects.equals(choice, "12"))
        {    if(getRole()!=Role.ADMIN && getRole()!=Role.MODERATOR)
        { System.out.println("У вас нет прав менять текст статьи.");
            userMenu();}
            else
                changeNoteBody();
        }
        if (Objects.equals(choice, "0"))
        {
            System.exit(0);
        }
        else
        {  System.out.println("Введены некорректные данные. Повторите ввод");
            userMenu();}

    };

    @Override
    public void selectNoteType() {
        System.out.println("7. Создать простую статью");
        System.out.println("8. Создать статью с жанром и сохранением автора изменения");
        System.out.println("3. Вернуться в основное меню");
        System.out.println("0. Выход");
        Scanner nt = new Scanner(System.in);
        String choice=nt.nextLine();
        if (Objects.equals(choice, "7"))
        { createSimpleNote();
        }
        if (Objects.equals(choice, "8"))
        { createExtendedNote();
        }
        if (Objects.equals(choice, "3"))
        {   userMenu();
        }
        if (Objects.equals(choice, "0"))
        {
            System.exit(0);
        }
        else
        {  System.out.println("Введены некорректные данные. Повторите выбор типа статьи");
            selectNoteType();}

    }

    @Override
    public void createSimpleNote() {

        System.out.println("Введите название статьи");
        Scanner scn = new Scanner(System.in);
        String newArticleName=scn.nextLine();

        for (int i = 0; i < articleData.length; i++)
        {  String searchArticle = articleData[i][1];
            {
                try {
                    if (Objects.equals(newArticleName, searchArticle))
                        throw new DoubleArticleNameException("Статья с таким названием уже есть, введите другое");
                } catch (DoubleArticleNameException doubleArticleNameException) {
                    System.out.println(doubleArticleNameException.getMessage());
                    createSimpleNote();
                }
            }
        }
        System.out.println("Введите текст статьи");
        Scanner sct = new Scanner(System.in);
        String newArticleText=sct.nextLine();
        SimpleNote simpleNote = new SimpleNote(newArticleName,newArticleText);
        int article_id = simpleNote.getArticle_id() - 1;
        articleData[article_id][0] = String.valueOf(article_id);
        articleData[article_id][3] = getLogin();
        userMenu();
    }

    @Override
    public void deleteNote() {

        int r = articleData.length;
        int c = articleData[0].length;
        String[][] copyArticleData = new String[r][c];
        int red = 0;
        boolean s = false;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                copyArticleData[i - red][j] = articleData[i][j];
                if (Objects.equals(articleData[i][j], activeArticleName)) {
                    red += 1;
                    if(i==r-1){
                        s = true;
                    }
                    break;
                }
            }
        }
        articleData = copyArticleData;

        System.out.println("Статья удалена.");
        userMenu();
    }

    @Override
    public void changeNoteName() {
        System.out.println("Введите новое название статьи");
        Scanner sn = new Scanner(System.in);
        String newArticleName=sn.nextLine();
        for (int i = 0; i < articleData.length; i++)
        {  String searchArticle = articleData[i][1];
            if (Objects.equals(searchArticle, activeArticleName))
            {
                articleData[i][1] = newArticleName;
                activeArticleName = articleData[i][1];
                articleData[i][6] = getLogin();
                activeArticleAuthorChanging = articleData[i][6];
                break;
            }}
        readNote();
    }

    @Override
    public void changeNoteWord() {
        System.out.println("Введите слово, которое хотите заменить. Без пробелов.");
        Scanner input = new Scanner(System.in);
        String changeWord = input.nextLine();
        char[] inputArray = changeWord.toCharArray();
        char whitespace = ' ';
        String[] words = activeArticleText.split(" ");
        for (Character character : inputArray) {
            if (character == whitespace) {
                System.out.println("Строка содержит пробел");
                changeNoteWord();
                break;

            }
        }

        for (int i =0;i<words.length;i++) {
            String word = words[i];
            {
                if (Objects.equals(changeWord, word)) {
                    System.out.println("Введите текст на замену.");
                    Scanner inputUpdateWord = new Scanner(System.in);
                    String exchangeWord = inputUpdateWord.nextLine();
                    words[i] = exchangeWord;
                    String intermediate ="";
                    for (int m =0; m<words.length;m++)
                    {intermediate += words[m]+" ";}
                    activeArticleText = intermediate;
                    for (int n = 0; n < articleData.length; n++)
                    {  String searchArticle = articleData[n][1];
                        if (Objects.equals(activeArticleName, searchArticle))
                        {
                            articleData[n][2] =activeArticleText;
                            articleData[i][6] = getLogin();
                            activeArticleAuthorChanging = articleData[i][6];
                            readNote();
                        }}

                }
            }
        }
        for (int i =0;i<words.length;i++) {
            String word =words[i];
            if (!Objects.equals(changeWord, word)) {
                System.out.println("Такого слова нет в статье.");
                changeNoteWord();
            }
        }

    }

    @Override
    public void changeNoteBody() {
        System.out.println("Введите новый текст статьи");
        Scanner sn = new Scanner(System.in);
        String newArticleText=sn.nextLine();
        for (int i = 0; i < articleData.length; i++)
        {  String searchArticle = articleData[i][2];
            if (Objects.equals(searchArticle, activeArticleText))
            {
                articleData[i][2] = newArticleText;
                activeArticleText = articleData[i][2];
                articleData[i][6] = getLogin();
                activeArticleAuthorChanging = articleData[i][6];
                break;
            }}
        readNote();

    }

    @Override
    public void createExtendedNote() {
        System.out.println("Введите название статьи");
        Scanner scn = new Scanner(System.in);
        String newArticleName=scn.nextLine();

        for (int i = 0; i < articleData.length; i++)
        {  String searchArticle = articleData[i][1];
            {
                try {
                    if (Objects.equals(newArticleName, searchArticle))
                        throw new DoubleArticleNameException("Статья с таким названием уже есть, введите другое");
                } catch (DoubleArticleNameException doubleArticleNameException) {
                    System.out.println(doubleArticleNameException.getMessage());
                    createSimpleNote();
                }
            }
        }
        System.out.println("Введите текст статьи");
        Scanner sct = new Scanner(System.in);
        String newArticleText=sct.nextLine();
        System.out.println("Введите жанр статьи");
        Scanner scg = new Scanner(System.in);
        String newArticleGenre=scg.nextLine();
        ExtendedNote extendedNote = new ExtendedNote(newArticleName,newArticleText,newArticleGenre);
        int article_id = extendedNote.getArticle_id() - 1;
        articleData[article_id][0] = String.valueOf(article_id);
        articleData[article_id][3] = getLogin();
        articleData[article_id][5] = extendedNote.getNoteGenre();
        userMenu();

    }
}
