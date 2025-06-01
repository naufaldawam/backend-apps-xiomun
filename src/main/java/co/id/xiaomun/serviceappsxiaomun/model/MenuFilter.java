package co.id.xiaomun.serviceappsxiaomun.model;

import lombok.Data;

@Data
public class MenuFilter {
    private String category; // opsional
    private String keyword;  // untuk search nama
}
