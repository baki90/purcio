package purcio.purcio.order.dto.order;

import lombok.Data;
import purcio.purcio.user.domain.Address;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
public class OrderCreateReqDTO {
    public Address address;
    public Map<Long, Integer> orderProductDTOs;
}
