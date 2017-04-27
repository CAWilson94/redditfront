package reddit;

import java.util.Date;

/**
 * Created by charl on 24/04/2017.
 * <p>
 * Need to save url string for images here, load into custom list view and plop onto each item in
 * list.
 * <p>
 * Need a default image also.
 */

public class Reddit {
    private String title;
    private int score;
    private String subreddit;
    private Date date;
    private String image;

    public Reddit() {

    }

    public Reddit(String title, int score, String subreddit, String image) {
        this.title = title;
        this.score = score;
        this.subreddit = subreddit;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
