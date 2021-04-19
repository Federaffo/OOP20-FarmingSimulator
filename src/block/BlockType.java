package block;

import item.Texturable;

public enum BlockType implements Texturable{

	TERRAIN(), /*BLOCCHI CAMMINABILI*/
	FIELD(), /*BLOCCHI IN CUI SI PIANTA*/
	WALL(),
	LOCKED(),
	WATER(),
	STALL();
}
