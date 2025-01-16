package Controller.Item;

import Model.Customer;
import Model.Item;
import Model.OrderDetail;
import javafx.collections.ObservableList;

import java.util.List;

public interface ItemService {

    boolean AddItem(Item item);

    boolean UpdateItem(Item item);

    boolean DeleteItem(String code);

    Item SearchItem(String code);

    ObservableList<Item> getAll();

    public ObservableList<String> getItemId();

    boolean updateStock(List<OrderDetail> orderDetails);

}
