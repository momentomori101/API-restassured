package sd.restassured;
//create pojo for 	

import java.util.List;

public class Data_pojo {
    private String id;
    private String name;
    private String email;
    private List<String> roles;
    private Address_pojo address;

    // Getters and setters for all fields
    public String getId() {
        return id;
    }   
    public void setId(String id) {
        this.id = id;
    }
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
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public Address_pojo getAddress() {
        return address;
    }
    public void setAddress(Address_pojo address) {
        this.address = address;
    }

}
