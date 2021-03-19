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

    @Transactional(readOnly = true)
    public ResponseDTO<Object> retrieveShop(Long id){
        Shop shop = shopRepository.findById(id).orElse(null);

        return ResponseDTO.builder()
                .message("뜨개샵 조회가 완료되었습니다.")
                .content(shop)
                .build();
    }

    @Transactional(readOnly = true)
    public ResponseDTO<Object> retrieveShopList(){
        List<Shop> shopList = shopRepository.findAll();

        return ResponseDTO.builder()
                .message("뜨개샵 조회가 완료되었습니다.")
                .content(shopList)
                .build();
    }

    @Transactional()
    public ResponseDTO<Object> createShop(ShopCreateReqDTO shopCreateReqDTO){

        User user = userRepository.findById(shopCreateReqDTO.getUserId())
                                    .orElseThrow(NoSuchElementException::new);

        Shop shop = Shop.builder()
                .name(shopCreateReqDTO.getName())
                .shopNumber(shopCreateReqDTO.getShopNumber())
                .picture(shopCreateReqDTO.getPicture())
                .user(user)
                .build();

        shopRepository.save(shop);

        return ResponseDTO.builder()
                .message("뜨개샵 저장이 완료되었습니다.")
                .content(shop.getId())
                .build();
    }

    @Transactional
    public ResponseDTO<Object> updateShop(Long id, ShopUpdateReqDTO shopUpdateReqDTO){
        Shop shop = shopRepository.findById(id).orElseThrow(NoSuchElementException::new);

        shop.updateShop(shopUpdateReqDTO.getName(), shopUpdateReqDTO.getPicture(), shopUpdateReqDTO.getShopNumber(), null);

        shopRepository.save(shop);

        return ResponseDTO.builder()
                .message("뜨개샵 수정이 완료되었습니다.")
                .content(shop.getId())
                .build();
    }


}
