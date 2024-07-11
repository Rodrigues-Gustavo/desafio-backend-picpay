package com.gustarodrigues.picpay.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_wallet_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WalletType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public enum Enum {

        USER(1l, "user"),
        MERCHANT(2l, "merchant");

        Enum(long id, String description) {
            this.id = id;
            this.description = description;
        }

        private Long id;
        private String description;

        public WalletType get() {
            return new WalletType(id, description);
        }
    }

}
