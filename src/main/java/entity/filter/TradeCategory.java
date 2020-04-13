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
@Table(name = "Trade_Category_Name", schema = "moe_db")
public class TradeCategory implements TinyDatable {
    @Id
    private Integer idTradeCategory;

    private String tradeCategory;

    @Override
    @JsonIgnore
    public TinyData toTinyData() {
        return new TinyData(idTradeCategory, tradeCategory);
    }
}
