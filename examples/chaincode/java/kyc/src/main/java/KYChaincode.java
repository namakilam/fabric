import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.InvalidCustomerInfoException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperledger.java.shim.ChaincodeBase;
import org.hyperledger.java.shim.ChaincodeStub;
import pojos.Customer;
import utils.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by namakilam on 25/07/17.
 */
public class KYChaincode extends ChaincodeBase {
    private static Log LOG = LogFactory.getLog(KYChaincode.class);
    private static final String CHAINCODE_NAME_ID = "KYC_CHAINCODE";
    private static final ObjectMapper mapper = new ObjectMapper();

    public String run(ChaincodeStub chaincodeStub, String function, String[] args) {
        LOG.info("In Run, Function Call : " + function);

        switch (function) {
            case "insert":
                return InsertCustomer(chaincodeStub, args);
            case "update":
                return UpdateCustomer(chaincodeStub, args);
        }

        LOG.error("No matching case for function:"+function);
        return String.format("Invalid Function Invocation. %s called Function Not Found.", function);
    }

    private String UpdateCustomer(ChaincodeStub chaincodeStub, String[] args) {
        if (args.length != 1) {
            try {
                Customer customer = mapper.readValue(args[0], Customer.class);
                if (!Customer.validateCustomer(customer)) {
                    throw new InvalidCustomerInfoException();
                }

                String customerString = chaincodeStub.getState(customer.getAadhar());
                if (customerString != null && StringUtils.NotEmpty(customerString)) {
                    Customer customer1 = mapper.readValue(customerString, Customer.class);

                    if (Objects.equals(customer.getAadhar(), customer1.getAadhar()) && Objects.equals(customer.getPan(), customer1.getPan())) {
                        chaincodeStub.putState(customer.getAadhar(), mapper.writeValueAsString(customer));
                    } else {
                        return String.format("Cannot update Immutable Details.");
                    }
                } else {
                    return String.format("Customer With Aadhar No. " + customer.getAadhar() + " is not present in the Ledger.");
                }

            } catch (IOException e) {
                return String.format("Unable to Parse Customer Argument. Please verify the JSON.");
            } catch (InvalidCustomerInfoException e) {
                return String.format("Necessary Customer Details Missing. Please Ensure Proper Details.");
            } catch (Exception e) {
                return String.format("Unknown Error. " + e.getMessage());
            }
        }

        return String.format("Invalid Number Of Arguments. Required 1.");
    }

    private String InsertCustomer(ChaincodeStub stub,String[] args) {
        if (args.length != 1) {
            try {
                Customer customer = mapper.readValue(args[0], Customer.class);
                if (!Customer.validateCustomer(customer)) {
                    throw new InvalidCustomerInfoException();
                }

                LOG.info("Validation Complete For Customer : " + customer.toString());
                stub.putState(customer.getAadhar(), mapper.writeValueAsString(customer));
            } catch (IOException e) {
                return String.format("Unable to Parse Customer Argument. Please verify the JSON.");
            } catch (InvalidCustomerInfoException e) {
                return String.format("Necessary Customer Details Missing. Please Ensure Proper Details.");
            } catch (Exception e) {
                return String.format("Unknown Error. " + e.getMessage());
            }
        }

        return String.format("Invalid Number Of Arguments. Required 1.");
    }

    public String query(ChaincodeStub chaincodeStub, String function, String[] args) {
        if (args.length != 1) {

            LOG.info("query : " + args[0]);
            String customerString = chaincodeStub.getState(args[0]);
            if (customerString != null && StringUtils.NotEmpty(customerString)) {
                LOG.trace("returning: "+ customerString);
                return customerString;
            }
        }

        return String.format("Invalid Number Of Arguments. Required 1.");
    }

    public String getChaincodeID() {
        return CHAINCODE_NAME_ID;
    }
}
