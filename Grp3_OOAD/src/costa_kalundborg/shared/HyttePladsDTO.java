package costa_kalundborg.shared;

import java.io.Serializable;

public class HyttePladsDTO extends PladsDTO implements Serializable{
	
	public HyttePladsDTO(){
		super();
	}
	
	public HyttePladsDTO(int id, double price, double lowprice){
		super(id, price, lowprice);
	}

}
