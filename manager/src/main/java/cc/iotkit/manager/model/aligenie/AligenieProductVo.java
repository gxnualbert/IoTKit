package cc.iotkit.manager.model.aligenie;

import cc.iotkit.model.aligenie.AligenieProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AligenieProductVo extends AligenieProduct {

    private String productName;

    public AligenieProductVo(AligenieProduct p, String productName) {
        this.setProductId(p.getProductId());
        this.setDeviceType(p.getDeviceType());
        this.setBrand(p.getBrand());
        this.setIcon(p.getIcon());
        this.setModel(p.getModel());
        this.setProductKey(p.getProductKey());
        this.setProperties(p.getProperties());
        this.setActions(p.getActions());
        this.setTransform(p.getTransform());
        this.setCreateAt(p.getCreateAt());
        this.productName = productName;
    }
}
