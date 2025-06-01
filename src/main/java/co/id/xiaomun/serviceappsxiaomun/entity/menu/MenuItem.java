package co.id.xiaomun.serviceappsxiaomun.entity.menu;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private MenuCategory category;

    private String description;
    private String imageMenu;
}
