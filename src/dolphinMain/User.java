package dolphinMain;

/**
 * User class is for instantiating the object User,
 * so that during the process of logging in,
 * the system can use it to determine who the user is.
 */

public class User {

    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
            return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

}
