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
@Table(name = "Trade_Type_Name", schema = "moe_db")
public class TradeType implements TinyDatable {
    @Id
    private Integer idTradeType;

    private String tradeType;

    @Override
    @JsonIgnore
    public TinyData toTinyData() {
        return new TinyData(idTradeType, tradeType);
    }
}
