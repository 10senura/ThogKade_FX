package service.custom;

import Model.User;
import javafx.collections.ObservableList;

public interface UserService {

        boolean AddUser(User user);

        boolean UpdateUser(User user);

        boolean DeleteUser(String id);

        User SearchUser(String id);

        ObservableList<User> getAll();

        public ObservableList<String> getUserId();

    }


