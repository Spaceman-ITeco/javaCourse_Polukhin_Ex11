package blog;

public class ExtendedNote extends Note{
    public String getNoteGenre() {
        return noteGenre;
    }

    private String noteGenre;
    private String authorChanging;
    public ExtendedNote(String articleName, String articleText, String noteGenre) {
        super(articleName, articleText);
        this.classNote = getClass();
        this.noteGenre = noteGenre;
        this.authorChanging = authorChanging;
        this.article_id=article_count++;
        articleData[article_id][0]=toString(article_id);
        articleData[article_id][1]=getArticleName();
        articleData[article_id][2]=getArticleText();
        articleData[article_id][4]= String.valueOf(getClass());
        articleData[article_id][5]=getNoteGenre();
        articleData[article_id][6]=this.authorChanging;
    }

    @Override
    protected String toString(int article_id) {
        return null;
    }
}
