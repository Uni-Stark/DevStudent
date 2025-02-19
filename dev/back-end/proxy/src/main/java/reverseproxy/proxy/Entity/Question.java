package reverseproxy.proxy.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
@Setter
@Getter
public class Question {
    private String _id = null; // 고유 number
    private String title = null;
    private UserInfo author = null;
    private List<String> tags= new ArrayList<String>();
    private String date = null;
    private String content = null;
    private String previews = "";
    private int answerCount = 0;
    private int likesCount = 0;
    private String isLiked = null;
    private int views = 1;
    private String adoptedAnswerId = null;
    private List<Comment> comments = new ArrayList<Comment>();
    private List<Answer> answers = new ArrayList<Answer>();
    private List<Like> likes = new ArrayList<Like>();
    public Question(String _id) {
        this._id = _id;
    }
}