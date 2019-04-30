package cis410.onlinebanking.sent;

import javax.xml.bind.annotation.XmlElement;

public class LoginAttempt {
    @XmlElement
    private String user;
    @XmlElement
    private String pass;

    public LoginAttempt(){}

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
}
