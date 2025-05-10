/**
 * Represents a player in the game. Each player has a name and a set of links.
 * The player can play movies, check if they are a winner, and check if they have finished the game.
 */
public class Player {
    /**
     * The name of the player.
     */
    private String name;
    /**
     * The set of links associated with the player.
     */
    private Links links;



    /**
     * Constructs a new Player object with the given name.
     * Initializes the player's links as a new Links object.
     *
     * @param name The name of the player.
     */
    public Player(String name){
        this.name = name;
        links = new Links();
    }

    public String getName() {
        return name;
    }

    /**
     * Plays a given movie. If the player's links are empty, sets the current movie in the links.
     * Otherwise, attempts to add a link for the movie.
     *
     * @param movie The movie to be played.
     * @return {@code true} if the movie is successfully played, {@code false} otherwise.
     */
    public boolean play(Movie movie){
        if(links.isEmpty()){
            links.setCurrentMovie(movie);
            return true;
        }
        return links.addLink(movie);
    }

    /**
     * Checks if the player is a winner. A player is considered a winner if they have a common genre.
     *
     * @return {@code true} if the player is a winner, {@code false} otherwise.
     */
    public boolean isWinner(){
        if (getGenre() != null){
            return true;
        }else{
            return false;
        }
    }

    public boolean hasStuff(Stuff name){
        return links.hasStuff(name);
    }

    /**
     * Retrieves the common genre of the player's links.
     *
     * @return The name of the common genre as a string, or {@code null} if there is no common genre.
     */
    public String getGenre(){
        String str = null;
        Genre genre = links.getCommonGenre();
        if (genre != null){
            str = genre.toString();
        }
        return str;
    }

    /**
     * Checks if the player has finished the game. A player has finished if their links are full.
     *
     * @return {@code true} if the player has finished, {@code false} otherwise.
     */
    public boolean isFinished(){
        return links.isFull();
    }

    @Override
    public String toString() {
        String str = "Player: " + name + "\n";
        return str + links.toString();
    }
}