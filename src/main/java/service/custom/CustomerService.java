package service.custom;

import Model.Customer;
import javafx.collections.ObservableList;
import service.SuperService;

public interface CustomerService extends SuperService {

     boolean AddCustomer(Customer customer);

     boolean UpdateCustomer(Customer customer);

     boolean DeleteCustomer(String Id);

     Customer SearchCustomer(String Name);

     ObservableList<Customer> getAll();

     public ObservableList<String> getCustomerId();

}
