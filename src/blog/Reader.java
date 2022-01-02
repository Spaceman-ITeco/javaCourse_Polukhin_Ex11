package blog;

public class Reader extends User{
    public Reader(String login, String password) {
        super(login, password);
        this.role=Role.READER;
    }
}
