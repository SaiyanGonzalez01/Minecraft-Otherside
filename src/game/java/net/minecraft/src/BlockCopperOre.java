package net.minecraft.src;

public class BlockCopperOre extends Block {
	public BlockCopperOre(int var1, int var2, Material var3) {
		super(var1, var2, var3);
	}
  
	public int idDropped(int var1, Random var2) {
		return Item.rawCopper.shiftedIndex;
	}
}
