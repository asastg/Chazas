package entities;

public class Admin extends User{
    private Chaza chazasAdmin;
    public Admin(String name, String username, String password, Chaza chazasAdmin) {
        super(name, username, password);
        this.chazasAdmin = chazasAdmin;
    }

    public Chaza getChazasAdmin() {
        return chazasAdmin;
    }

    public void setChazasAdmin(Chaza chazasAdmin) {
        this.chazasAdmin = chazasAdmin;
    }
}
