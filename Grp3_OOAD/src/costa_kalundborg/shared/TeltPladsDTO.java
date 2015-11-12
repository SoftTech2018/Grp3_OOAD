package costa_kalundborg.shared;

import java.io.Serializable;

public class TeltPladsDTO extends PladsDTO implements Serializable{
	
	public TeltPladsDTO(){
		super();
	}
	
	public TeltPladsDTO(int id, double price, double lowprice){
		super(id, price, lowprice);
	}

}
