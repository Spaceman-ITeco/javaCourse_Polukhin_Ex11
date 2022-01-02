package blog;

public class SimpleNote extends Note{
    public SimpleNote(String articleName, String articleText) {
        super(articleName, articleText);
        this.classNote = getClass();
        this.article_id=article_count++;
        articleData[article_id][0]=toString(article_id);
        articleData[article_id][1]=getArticleName();
        articleData[article_id][2]=getArticleText();
        articleData[article_id][4]= String.valueOf(getClass());
    }

    @Override
    protected String toString(int article_id) {
        return null;
    }
    @Override
    public int getArticle_id() {
        return article_id;
    }
}
