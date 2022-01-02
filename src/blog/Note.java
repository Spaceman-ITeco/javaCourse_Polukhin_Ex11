package blog;

public abstract class Note<T> {
    private String articleName;
    private String articleText;
    static public String activeArticleName;
    static public String activeArticleText;
    static public String activeArticleAuthor;
    static public String activeArticleGenre;
    static public String activeArticleClass;
    static public String activeArticleAuthorChanging;
    T classNote;

    public int getArticle_id() {
        return article_id;
    }

    public int article_id = 0;
    public static int article_count = 0;
    //columns 0 - id 1 - name 2 - text 3 - author 4 - noteClass 5 - noteGenre 6 - authorChanging
    public static String[][] articleData = new String[20][7];

    public Note(String articleName, String articleText) {
        this.articleName = articleName;
        this.articleText = articleText;
        this.classNote = (T) getClass();
        this.article_id = article_count++;
        articleData[article_id][0] = toString(article_id);
        articleData[article_id][1] = this.articleName;
        articleData[article_id][2] = this.articleText;
        articleData[article_id][4] = String.valueOf(getClass());
    }

    protected abstract String toString(int article_id);

    public String getArticleName() {
        return articleName;
    }


    public String getArticleText() {
        return articleText;
    }

}
