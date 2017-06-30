package developer.vichit.android.com.databasefirebase;

/**
 * Created by VichitDeveloper on 6/30/17.
 */

public class Articles {

    private String id;
    private String title;

    public Articles() {
    }

    public Articles(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
