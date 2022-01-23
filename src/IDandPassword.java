import java.util.HashMap;

public class IDandPassword {
    HashMap<String, String> loginInfo = new HashMap<String, String>();

    IDandPassword(){
        loginInfo.put("Liza","Password");
        loginInfo.put("Guset","123");
    }

    protected HashMap getLoginInfo(){
        return loginInfo;
    }
}
