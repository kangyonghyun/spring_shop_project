package project.shop.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Food extends Item {

    String manufacturer;
    String shelfLife;

}
