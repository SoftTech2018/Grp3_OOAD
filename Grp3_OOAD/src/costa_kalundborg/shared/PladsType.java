package costa_kalundborg.shared;

import java.io.Serializable;

public enum PladsType implements Serializable{
	LILLE_TELT {
		@Override
		PladsType getType() {
			// TODO Auto-generated method stub
			return LILLE_TELT;
		}
	},
	STOR_TELT {
		@Override
		PladsType getType() {
			// TODO Auto-generated method stub
			return STOR_TELT;
		}
	},
	HYTTE {
		@Override
		PladsType getType() {
			// TODO Auto-generated method stub
			return HYTTE;
		}
	},
	LUKSUS_HYTTE {
		@Override
		PladsType getType() {
			// TODO Auto-generated method stub
			return LUKSUS_HYTTE;
		}
	};
	abstract PladsType getType();
}
