package tn.esprit.models;

public class Utilisateur {

    private int id , numtel ;
    private String nom , prenom, email, password, role, image;
    public static Utilisateur Current_User;

    public Utilisateur() {}

    public Utilisateur(int id, String nom, String prenom, String email, String password,int numtel, String role, String image) {
        this.id = id;
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.image = image;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getNumtel() {return numtel;}
    public void setNumtel(int numtel) {this.numtel = numtel;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}

    public String getImage() {return image;}
    public void setImage(String image) {this.image = image;}

    public static Utilisateur getCurrent_User() {return Current_User;}
    public static void setCurrent_User(Utilisateur Current_User) {Utilisateur.Current_User = Current_User;}

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", numtel=" + numtel +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
