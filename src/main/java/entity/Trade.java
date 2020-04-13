package entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Trade", schema = "moe_db")
@IdClass(TradeIds.class)
public class Trade {
    @Id
    private Integer idCountry;
    @Id
    private Integer idTradeCategory;
    @Id
    private Integer idTradeType;
    @Id
    private Integer idYear;
    @Id
    private String idItem;

    private Double value;
}

class TradeIds implements Serializable {
    private Integer idCountry;
    private Integer idTradeCategory;
    private Integer idTradeType;
    private Integer idYear;
    private String idItem;
}
