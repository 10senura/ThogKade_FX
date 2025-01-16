package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private String id;
    private LocalDate date;
    private String customerId;
    List<OrderDetail> orderDetails;

}