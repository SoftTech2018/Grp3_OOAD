package costa_kalundborg.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;



public enum Status implements Serializable, IsSerializable{
	BOOKET {
		@Override
		Status getStatus() {
			return BOOKET;
		}
	},
	CANCEL {
		@Override
		Status getStatus() {
			return CANCEL;
		}
	},
	AFHOLDT {
		@Override
		Status getStatus() {
			return AFHOLDT;
		}
	},
	AKTIV {
		@Override
		Status getStatus() {
			return AKTIV;
		}
	};
	
	abstract Status getStatus();
	
}
