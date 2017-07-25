package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by namakilam on 25/07/17.
 */
public class Address implements Serializable {
    @JsonProperty("address_line")
    private String AddressLine;

    @JsonProperty("city")
    private String City;

    public String getAddressLine() {
        return AddressLine;
    }

    public void setAddressLine(String addressLine) {
        AddressLine = addressLine;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
