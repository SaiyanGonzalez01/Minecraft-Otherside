package net.minecraft.src;

import net.lax1dude.eaglercraft.Random;

public class BlockLogBirch extends Block {
	protected BlockLogBirch(int var1) {
		super(var1, Material.wood);
		this.blockIndexInTexture = 86;
	}

	public int quantityDropped(Random var1) {
		return 1;
	}

	public int idDropped(int var1, Random var2) {
		return Block.woodBirch.blockID;
	}

	public int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? 87 : (var1 == 0 ? 87 : 86);
	}
}
