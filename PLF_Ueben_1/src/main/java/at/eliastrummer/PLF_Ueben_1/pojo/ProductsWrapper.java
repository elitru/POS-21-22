package at.eliastrummer.PLF_Ueben_1.pojo;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ProductsWrapper {
    @XmlElement(name = "Product")
    @XmlElementWrapper(name = "Wrapper")
    private List<Product> products;
}
