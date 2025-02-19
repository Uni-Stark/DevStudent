package loginserver.loginserver.Module.Find;


import loginserver.loginserver.Entity.User;
import loginserver.loginserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindUserByNickname {
    @Autowired
    private UserRepository userRepository;

    public User findUserByNickname(String nickname) {
        if (userRepository.countByNickname(nickname) == 0)
            return new User();
        User user = userRepository.findByNickname(nickname);
//        user.setRefreshToken(null);
        user.setAccessToken(null);
        user.setPassword(null);
        return user;
    }
}
