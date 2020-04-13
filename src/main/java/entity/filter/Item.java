package entity.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entity.filter.tinydata.TinyData;
import entity.filter.tinydata.TinyDatable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Item_Name", schema = "moe_db")
public class Item implements TinyDatable {
    @Id
    private Integer idItem;

    private String Item;

    @Override
    @JsonIgnore
    public TinyData toTinyData() {
        return new TinyData(idItem, Item);
    }
}
