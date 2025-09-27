package net.minecraft.src;

import net.lax1dude.eaglercraft.Random;

public class BlockPath extends Block {
	protected BlockPath(int var1) {
		super(var1, Material.ground);
		this.blockIndexInTexture = 46;
	}

	public int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? 49 : (var1 == 47 ? 2 : 46);
	}

	public int idDropped(int var1, Random var2) {
		return Block.dirt.idDropped(0, var2);
	}
}
