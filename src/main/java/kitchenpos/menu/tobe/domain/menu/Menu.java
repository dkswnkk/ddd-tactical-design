package kitchenpos.menu.tobe.domain.menu;

import jakarta.persistence.*;
import kitchenpos.menu.tobe.domain.menugroup.MenuGroup;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "menu")
@Entity
public class Menu {
    @Column(name = "id", columnDefinition = "binary(16)")
    @Id
    private UUID id;

    @Embedded
    private MenuName menuName;

    @Embedded
    private MenuPrice menuPrice;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "menu_group_id",
            columnDefinition = "binary(16)",
            foreignKey = @ForeignKey(name = "fk_menu_to_menu_group")
    )
    private MenuGroup menuGroup;

    @Column(name = "displayed", nullable = false)
    private boolean displayed;

    @Embedded
    private MenuProducts menuProducts;

    protected Menu() {
    }

    public Menu(UUID id, MenuName menuName, MenuPrice menuPrice, MenuGroup menuGroup, boolean displayed, MenuProducts menuProducts) {
        this.id = id;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuGroup = menuGroup;
        this.displayed = displayed;
        this.menuProducts = menuProducts;
    }

    public void changePrice(BigDecimal price) {
        MenuPrice newMenuPrice = new MenuPrice(price);
        this.menuProducts.validateMenuPriceDoesNotExceedTotalProductPrice(newMenuPrice.getPrice());
        this.menuPrice = newMenuPrice;
    }

    public void display() {
        this.menuProducts.validateMenuPriceDoesNotExceedTotalProductPrice(this.menuPrice.getPrice());
        this.displayed = true;
    }

    public void hide() {
        this.displayed = false;
    }

}