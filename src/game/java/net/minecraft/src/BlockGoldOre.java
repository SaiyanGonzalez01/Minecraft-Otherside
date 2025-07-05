package net.minecraft.src;

public class BlockGoldOre extends Block {
	public BlockGoldOre(int var1, int var2, Material var3) {
		super(var1, var2, var3);
	}
  
	public int idDropped(int var1, Random var2) {
		return Item.rawGold.shiftedIndex;
	}
}
