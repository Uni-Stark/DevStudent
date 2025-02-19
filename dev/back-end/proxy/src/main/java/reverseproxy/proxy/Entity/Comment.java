package reverseproxy.proxy.Entity;

import lombok.Getter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

@ToString
@Getter
public class Comment {
    private String _id = null; // 고유 number
    private String author = null;
    private String content = null;
    private String date = null;
    public Comment(String _id){
        this._id = _id;
    }
}
