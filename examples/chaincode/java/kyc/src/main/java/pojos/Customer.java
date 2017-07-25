package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.MaritalStatus;
import utils.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by namakilam on 25/07/17.
 */
public class Customer implements Serializable {
    @JsonProperty("aadhar")
    private String aadhar;

    @JsonProperty("name")
    private String name;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("dob")
    private String dob;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("pan")
    private String pan;

    @JsonProperty("cibil_score")
    private Integer cibilScore;

    @JsonProperty("marital_status")
    private MaritalStatus maritalStatus;

    @JsonProperty("education")
    private Map<String, String> educationMap;

    @JsonProperty("employement")
    private Map<String, String> employementMap;

    @JsonProperty("health")
    private Map<String, String> healtMap;

    @JsonProperty("possession")
    private Map<String, String> possestionMap;

    public static Boolean validateCustomer(Customer c) {
        return StringUtils.NotEmpty(c.aadhar) &&
                StringUtils.NotEmpty(c.name) &&
                StringUtils.NotEmpty(c.pan);
    }

    public String getAadhar() {
        return aadhar;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public Address getAddress() {
        return address;
    }

    public String getPan() {
        return pan;
    }

    public Integer getCibilScore() {
        return cibilScore;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public Map<String, String> getEducationMap() {
        return educationMap;
    }

    public Map<String, String> getEmployementMap() {
        return employementMap;
    }

    public Map<String, String> getHealtMap() {
        return healtMap;
    }

    public Map<String, String> getPossestionMap() {
        return possestionMap;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "aadhar='" + aadhar + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", address=" + address +
                ", pan='" + pan + '\'' +
                ", cibilScore=" + cibilScore +
                ", maritalStatus=" + maritalStatus +
                ", educationMap=" + educationMap +
                ", employementMap=" + employementMap +
                ", healtMap=" + healtMap +
                ", possestionMap=" + possestionMap +
                '}';
    }
}
