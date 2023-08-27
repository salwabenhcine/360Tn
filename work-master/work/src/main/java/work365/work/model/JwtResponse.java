package work365.work.model;



public class JwtResponse {
    private Clienttt user;
    private String jwtToken;
    public JwtResponse(Clienttt user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }
    public Clienttt getUser() {
        return user;
    }

    public void setUser(Clienttt user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
