package net.minecraft.src;

import net.lax1dude.eaglercraft.Random;

public class BlockPath extends Block {
	protected BlockPath(int var1) {
		super(var1, Material.ground);
		this.blockIndexInTexture = 46;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 15.0F / 16.0F, 1.0F);
	}

	public int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? 71 : (var1 == 71 ? 2 : 70);
	}

	public int idDropped(int var1, Random var2) {
		return Block.dirt.idDropped(0, var2);
	}
}
