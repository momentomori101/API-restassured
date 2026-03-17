package sd;

public class UserRequest {
    private String id;
    private String name;
    private String email;
    private String gender;
    private String status;

    // No-args constructor
    public UserRequest() {
    }

    // All-args constructor
    public UserRequest(String name, String email, String gender, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    // Getters and Setters

     public String getId() {
        return id;
    }

    // public String setId(String id) {
    //     return id;
    // }
        

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString() implementation
    @Override
    public String toString() {
        return "UserRequest{" +
                "id='" + id + '\''+
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
