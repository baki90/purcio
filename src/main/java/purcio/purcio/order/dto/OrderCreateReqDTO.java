package purcio.purcio.order.dto;

import lombok.Data;
import purcio.purcio.user.domain.Address;

import javax.validation.constraints.NotBlank;

@Data
public class OrderCreateReqDTO {
    public Address address;

    @NotBlank(message = "수량을 입력해 주세요.")
    public int count;
}
