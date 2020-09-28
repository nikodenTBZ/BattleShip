import java.util.concurrent.atomic.AtomicInteger;

public class Player {

    private static AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String username;

    public Player(String username) {
        this.username = username;
        this.id = count.incrementAndGet();

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
