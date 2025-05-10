import java.util.Set;
import java.util.TreeSet;
public interface Reader {
    Set<Movie> readMovies();
    TreeSet<Stuff> readStuffs();
}
