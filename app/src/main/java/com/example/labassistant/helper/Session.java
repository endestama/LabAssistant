package com.example.labassistant.helper;

// Class singleton
public final class Session {
    private static Session session;
    private String email;

    public Session() {
    }

    public static Session getSession() {
        if(session == null){
            session = new Session();
        }
        return session;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
