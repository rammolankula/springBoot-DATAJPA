package init.learn.model;


import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {
	
	@Id
	private Integer productId;
	private String productCode;
	private double productCost;
	private String productName;
	private String productVendor;
	private Date mfgDate;
    private Boolean status;
    
  
}
