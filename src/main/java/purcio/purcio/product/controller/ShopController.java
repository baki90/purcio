package purcio.purcio.product.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import purcio.purcio.common.ResponseDTO;
import purcio.purcio.product.dto.shop.ShopCreateReqDTO;
import purcio.purcio.product.dto.shop.ShopUpdateReqDTO;
import purcio.purcio.product.service.ShopService;

@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;
    
    /**
     * 뜨개샵 전체 리스트를 조회합니다.
     * @return 뜨개샵 리스트
     */
    @GetMapping("/api/shop")
    public ResponseDTO<Object> retrieveShopList(){
        return shopService.retrieveShopList();
    }
    
    /**
     * 뜨개샵을 생성합니다.
     * @param userCreateReqDTO 생성할 뜨개샵 정보
     * @return 생성된 뜨개샵
     */
    @PostMapping("/api/shop")
    public ResponseDTO<Object> createShop(@RequestBody ShopCreateReqDTO userCreateReqDTO){
        return shopService.createShop(userCreateReqDTO);
    }

    /**
     * id에 해당하는 뜨개샵을 한 개 조회합니다.
     * @return 조회한 뜨개샵
     */
    @GetMapping("/api/shop/{id}")
    public ResponseDTO<Object> retrieveShopById(@PathVariable Long id){
        return shopService.retrieveShopById(id);
    }
    
    /**
     * id에 해당하는 뜨개샵을 수정합니다.
     * @param id 뜨개샵 id
     * @param userUpdateReqDTO 뜨개샵 수정 정보
     * @return 수정한 뜨개샵
     */
    @PutMapping("/api/shop/{id}")
    public ResponseDTO<Object> updateShopById(@PathVariable Long id, @RequestBody ShopUpdateReqDTO userUpdateReqDTO){
        return shopService.updateShopById(id, userUpdateReqDTO);
    }

    /**
     * id에 해당하는 뜨개샵을 삭제합니다.
     * @param id 뜨개샵 id
     * @return 삭제된 뜨개샵
     */
    @DeleteMapping("/api/shop/{id}")
    public ResponseDTO<Object> deleteShopById(@PathVariable Long id){
        return shopService.deleteShopById(id);
    }
}
