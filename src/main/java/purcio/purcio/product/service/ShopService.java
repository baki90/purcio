package purcio.purcio.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import purcio.purcio.common.ResponseDTO;
import purcio.purcio.product.domain.Shop;
import purcio.purcio.product.dto.shop.ShopCreateReqDTO;
import purcio.purcio.product.dto.shop.ShopUpdateReqDTO;
import purcio.purcio.product.repository.ShopRepository;
import purcio.purcio.user.domain.User;
import purcio.purcio.user.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    /**
     * 뜨개샵 전체 리스트를 조회합니다.
     * @return 뜨개샵 리스트
     */
    @Transactional(readOnly = true)
    public ResponseDTO<Object> retrieveShopList(){
        List<Shop> shopList = shopRepository.findAll();
        
        return ResponseDTO.builder()
        .message("뜨개샵 리스트 조회가 완료되었습니다.")
        .content(shopList)
        .build();
    }
    
    /**
     * 뜨개샵을 생성합니다.
     * @param shopCreateReqDTO 생성할 뜨개샵 정보
     * @return 생성된 뜨개샵
     */
    @Transactional()
    public ResponseDTO<Object> createShop(ShopCreateReqDTO shopCreateReqDTO){
        
        User user = userRepository.findById(shopCreateReqDTO.getUserId())
        .orElseThrow(NoSuchElementException::new);
        
        Shop shop = Shop.createShop(shopCreateReqDTO.getName(), shopCreateReqDTO.getPicture(), shopCreateReqDTO.getShopNumber(), user);
        
        shopRepository.save(shop);
        
        return ResponseDTO.builder()
        .message("뜨개샵 저장이 완료되었습니다.")
        .content(shop.getId())
        .build();
    }
    
    /**
     * id에 해당하는 뜨개샵을 한 개 조회합니다.
     * @param id 뜨개샵 id
     * @return 조회한 뜨개샵
     */
    @Transactional(readOnly = true)
    public ResponseDTO<Object> retrieveShopById(Long id){
        Shop shop = shopRepository.findById(id).orElse(null);

        return ResponseDTO.builder()
                .message("뜨개샵 조회가 완료되었습니다.")
                .content(shop)
                .build();
    }

    /**
     * id에 해당하는 뜨개샵을 수정합니다.
     * @param id 뜨개샵 id
     * @param shopUpdateReqDTO 수정할 뜨개샵 정보
     * @return 수정된 뜨개샵
     */
    @Transactional
    public ResponseDTO<Object> updateShopById(Long id, ShopUpdateReqDTO shopUpdateReqDTO){
        Shop shop = shopRepository.findById(id).orElseThrow(NoSuchElementException::new);

        shop.updateShop(shopUpdateReqDTO.getName(), shopUpdateReqDTO.getPicture(), shopUpdateReqDTO.getShopNumber(), null);

        shopRepository.save(shop);

        return ResponseDTO.builder()
                .message("뜨개샵 수정이 완료되었습니다.")
                .content(shop.getId())
                .build();
    }

    /**
     * id에 해당하는 뜨개샵을 삭제합니다.
     * @param id 뜨개샵 id
     * @return 삭제된 뜨개샵
     */
    @Transactional
    public ResponseDTO<Object> deleteShopById(Long id){
        Shop shop = shopRepository.findById(id).orElseThrow(NoSuchElementException::new);

        shopRepository.delete(shop);

        return ResponseDTO.builder()
                .message("뜨개샵 삭제가 완료되었습니다.")
                .content(shop.getId())
                .build();
    }
}
