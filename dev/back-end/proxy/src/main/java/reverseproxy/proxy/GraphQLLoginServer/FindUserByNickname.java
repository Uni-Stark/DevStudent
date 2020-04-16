package reverseproxy.proxy.GraphQLLoginServer;

import com.google.gson.Gson;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.springframework.stereotype.Component;
import reverseproxy.proxy.Entity.User;

import javax.servlet.http.HttpServletRequest;

@Component
public class FindUserByNickname extends ConnectLoginServer {
    public User findUserByNickname(String token, String nickname, DataFetchingEnvironment env) throws Exception {
        String authorized = checkJwt(nickname,env);
        String invalidate = "invalidate";
        String expired = "expired";
        if(authorized.equals(invalidate))
            return new User(invalidate);
        if(authorized.equals(expired))
            return new User(expired);
        //region Query
        String query = "query{\n" +
                "    findUserByNickname(token : \"" + token + "\",nickname : \"" + nickname + "\")\n" +
                "    {\n" +
                "        email\n" +
                "        password\n" +
                "        nickname\n" +
                "        schoolName\n" +
                "        date\n" +
                "        authState\n" +
                "        token\n" +
                "    }\n" +
                "}";
        //endregion
        Gson gson = new Gson();
        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        String str = getResponse(query, name);
        User user = gson.fromJson(str, User.class);
        return user;
    }
}
