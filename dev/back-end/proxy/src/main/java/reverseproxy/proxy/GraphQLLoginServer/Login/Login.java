package reverseproxy.proxy.GraphQLLoginServer.Login;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import reverseproxy.proxy.Entity.User;
import reverseproxy.proxy.GraphQLLoginServer.ConnectLoginServer;

@Component
public class Login extends ConnectLoginServer {
    public User loginToServer(String email, String password) {
        //region Query
        String query = "mutation{\n" +
                "    loginToServer(email:\"" + email + "\",password:\"" + password + "\")\n" +
                "    {\n" +
                "        email\n" +
                "        password\n" +
                "        nickname\n" +
                "        schoolName\n" +
                "        date\n" +
                "        authState\n" +
                "        accessToken\n" +
                "        refreshToken\n" +
                "    }\n" +
                "\n" +
                "}";
        //endregion
        Gson gson = new Gson();
        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        String str = getResponse(query, name);
        System.out.println(str);
        User user = gson.fromJson(str, User.class);
        return user;
    }
}
