package com.example.labassistant.helper;

// Class singleton
public class Session {
    private static Session session;
    private String usernm;

    public Session() {
    }

    public static Session getSession() {
        if(session == null){
            session = new Session();
        }
        return session;
    }

    public String getUsernm() {
        return usernm;
    }

    public void setUsernm(String usernm) {
        this.usernm = usernm;
    }

}
