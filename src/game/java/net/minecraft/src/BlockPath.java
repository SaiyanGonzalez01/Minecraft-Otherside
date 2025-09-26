package net.minecraft.src;

import net.lax1dude.eaglercraft.Random;

public class BlockPath extends Block {
	protected BlockPath(int var1) {
		super(var1, Material.ground);
		this.blockIndexInTexture = 46;
		this.setTickOnLoad(true);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 15.0F / 16.0F, 1.0F);
		this.setLightOpacity(255);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
		return AxisAlignedBB.getBoundingBoxFromPool((double)(var2 + 0), (double)(var3 + 0), (double)(var4 + 0), (double)(var2 + 1), (double)(var3 + 1), (double)(var4 + 1));
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getBlockTextureFromSideAndMetadata(int var1, int var2) {
		return var1 == 1 && var2 > 0 ? this.blockIndexInTexture - 1 : (var1 == 1 ? this.blockIndexInTexture : 2);
	}

	public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
		super.onNeighborBlockChange(var1, var2, var3, var4, var5);
		Material var6 = var1.getBlockMaterial(var2, var3 + 1, var4);
		if(var6.isSolid()) {
			var1.setBlockWithNotify(var2, var3, var4, Block.dirt.blockID);
		}

	}

	public int idDropped(int var1, Random var2) {
		return Block.dirt.idDropped(0, var2);
	}
}
