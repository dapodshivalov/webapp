package entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Item_Id", schema = "moe_db")
public class ItemIds {
    @Id
    private Integer idItem;
    private Integer idSection;
    private Integer idChapter;
}
