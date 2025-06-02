package co.id.xiaomun.serviceappsxiaomun.entity.menu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "offering")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offering {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titleOffering;
    private String descOffering;
    private String urlImageOffering;

}
