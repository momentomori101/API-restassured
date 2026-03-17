package sd.restassured;
import java.util.List;

public class ApiResponse {
    private String status;
    private Data_pojo data;
    private Meta meta;
    // Getters and setters for all fields

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Data_pojo getData() {
        return data;
    }
    public void setData(Data_pojo data) {
        this.data = data;
    }
    public Meta getMeta() {
        return meta;
    }
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    
}
