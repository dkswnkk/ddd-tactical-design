package kitchenpos.menu.tobe.domain.menu;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuRepository {
    Menu save(Menu menu);

    void saveAll(List<Menu> menus);

    Optional<Menu> findById(UUID id);

    List<Menu> findAll();

    List<Menu> findAllByProductId(UUID productId);
}
