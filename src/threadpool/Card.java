package threadpool;

public class Card {
    private String id;
    private String rnk;

    public Card(String id, String rnk) {
        this.id = id;
        this.rnk = rnk;
    }

    public String getId() {
        return id;
    }

    public String getRnk() {
        return rnk;
    }
}
