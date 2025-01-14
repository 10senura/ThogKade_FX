package Controller.Customer;

import Model.Customer;
import javafx.collections.ObservableList;

public interface CustomerService {

     boolean AddCustomer(Customer customer);

     boolean UpdateCustomer(Customer customer);

     boolean DeleteCustomer(String Id);

     Customer SearchCustomer(String Name);

     ObservableList<Customer> getAll();

     public ObservableList<String> getCustomerId();

}
