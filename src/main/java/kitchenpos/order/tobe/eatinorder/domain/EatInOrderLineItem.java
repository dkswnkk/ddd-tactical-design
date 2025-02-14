package kitchenpos.order.tobe.eatinorder.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Table(name = "eat_in_order_line_item")
@Entity
public class EatInOrderLineItem {
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long seq;

    @Column(name = "menu_id", columnDefinition = "binary(16)")
    private UUID menuId;

    @Embedded
    private EatInOrderLineItemQuantity quantity;

    @Embedded
    private EatInOrderLineItemPrice price;

    protected EatInOrderLineItem() {
    }

    public EatInOrderLineItem(UUID menuId, long quantity, BigDecimal price) {
        this.menuId = menuId;
        this.quantity = new EatInOrderLineItemQuantity(quantity);
        this.price = new EatInOrderLineItemPrice(price);
    }

    public UUID getMenuId() {
        return menuId;
    }

    public long getQuantity() {
        return quantity.getQuantity();
    }

    public BigDecimal getPrice() {
        return price.getPrice();
    }

    public Long getSeq() {
        return seq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EatInOrderLineItem orderLineItems)) {
            return false;
        }

        return this.getSeq() != null && Objects.equals(this.getSeq(), orderLineItems.getSeq());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeq());
    }
}
