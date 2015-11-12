package costa_kalundborg.shared;

import java.io.Serializable;

public class StorPladsDTO extends PladsDTO implements Serializable{
	
	public StorPladsDTO(){
		super();
	}
	
	public StorPladsDTO(int id, double price, double lowprice){
		super(id, price, lowprice);
	}

}
