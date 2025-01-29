package service.custom;

import Model.Item;
import Model.OrderDetail;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface ItemService extends SuperService {

    boolean AddItem(Item item);

    boolean UpdateItem(Item item);

    boolean DeleteItem(String code);

    Item SearchItem(String code);

    ObservableList<Item> getAll();

    public ObservableList<String> getItemId();

    boolean updateStock(List<OrderDetail> orderDetails);

}
