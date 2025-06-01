package co.id.xiaomun.serviceappsxiaomun.entity.menu;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
}
