package fullstack.oving2.model;

public class AuthorBook {
    private int authorID;
    private int bookID;

    public AuthorBook(int authorID, int bookID) {
        this.authorID = authorID;
        this.bookID = bookID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}
