package costa_kalundborg.shared;

import java.io.Serializable;

public class LillePladsDTO extends PladsDTO implements Serializable{
	
	public LillePladsDTO(){
		super();
	}

	public LillePladsDTO(int id, double price, double lowprice){
		super(id, price, lowprice);
	}

}
