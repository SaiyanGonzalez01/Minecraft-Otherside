package net.minecraft.src;

public class BlockIronOre extends Block {
	public BlockIronOre(int var1, int var2, Material var3) {
		super(var1, var2, var3);
	}
  
	public int idDropped(int var1, Random var2) {
		return Item.rawIron.shiftedIndex;
	}
}
