package co.id.xiaomun.serviceappsxiaomun.model.menuModel;

import lombok.Data;

@Data
public class MenuFilter {
    private String category; // ini wajib (berdasarkan name dari category_id)
    private String keyword;  // untuk search nama || bisa opsional
}
