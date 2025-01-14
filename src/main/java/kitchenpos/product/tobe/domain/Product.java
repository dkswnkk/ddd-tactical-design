package kitchenpos.product.tobe.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kitchenpos.product.tobe.domain.validate.ProfanityValidator;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Table(name = "product")
@Entity(name = "newProduct")
public class Product {
    @Column(name = "id", columnDefinition = "binary(16)")
    @Id
    private UUID id;

    @Embedded
    private ProductName name;

    @Embedded
    private ProductPrice price;

    protected Product() {
    }

    public Product(UUID id, String name, BigDecimal price, ProfanityValidator profanityValidator) {
        this.id = id;
        this.name = new ProductName(name, profanityValidator);
        this.price = new ProductPrice(price);
    }

    public void changePrice(BigDecimal price) {
        this.price = new ProductPrice(price);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return this.name.getName();
    }

    public BigDecimal getPrice() {
        return this.price.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product product)) {
            return false;
        }

        return this.getId() != null && Objects.equals(this.getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
