package kitchenpos.menu.tobe.domain.menu;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "menu_product")
@Entity
public class MenuProduct {
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long seq;

    private UUID productId;

    @Embedded
    private MenuProductQuantity menuProductQuantity;

    @Embedded
    private ProductPrice productPrice;

    protected MenuProduct() {
    }

    public MenuProduct(UUID productId, long quantity, BigDecimal price) {
        this.productId = productId;
        this.menuProductQuantity = new MenuProductQuantity(quantity);
        this.productPrice = new ProductPrice(price);
    }

    public BigDecimal getPrice() {
        return productPrice.getPrice();
    }

    public long getQuantity() {
        return this.menuProductQuantity.getQuantity();
    }

    public UUID getProductId() {
        return productId;
    }

}