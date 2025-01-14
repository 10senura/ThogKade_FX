package Controller.Item;

import Model.Customer;
import Model.Item;
import javafx.collections.ObservableList;

public interface ItemService {

    boolean AddItem(Item item);

    boolean UpdateItem(Item item);

    boolean DeleteItem(String code);

    Item SearchItem(String code);

    ObservableList<Item> getAll();

    public ObservableList<String> getItemId();



    }
