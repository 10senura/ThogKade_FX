package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CartTM {
    private String itemCode;
    private String description;
    private String handOnStock;
    private Integer qty;
    private Double unitPrice;
    private Double total;
}