package entity;

public class Movie {
    private int movieId;
    private String movieName;
    private int genreId;

    public Movie(int movieId, String movieName, int genreId) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.genreId = genreId;
    }

    public Movie() {}

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}
