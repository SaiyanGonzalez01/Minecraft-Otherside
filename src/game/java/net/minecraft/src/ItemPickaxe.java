package net.minecraft.src;

public class ItemPickaxe extends ItemTool {
	private static Block[] blocksEffectiveAgainst = new Block[]{Block.cobblestone, Block.stairDouble, Block.stairSingle, Block.stone, Block.cobblestoneMossy, Block.oreIron, Block.blockSteel, Block.oreCoal, Block.blockGold, Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.oreCopper, Block.blockCopper, Block.stoneOvenIdle, Block.stoneOvenActive};
	private int getHarvestLevel;

	public ItemPickaxe(int var1, int var2) {
		super(var1, 2, var2, blocksEffectiveAgainst);
		this.getHarvestLevel = var2;
	}

	public boolean canHarvestBlock(Block var1) {
		return var1 == Block.obsidian ? this.getHarvestLevel == 3 : (var1 != Block.blockDiamond && var1 != Block.oreDiamond ? (var1 != Block.blockGold && var1 != Block.oreGold ? (var1 != Block.blockSteel && var1 != Block.oreIron ? (var1 != Block.blockCopper && var1 != Block.oreCopper ? (var1.blockMaterial == Material.rock ? true : var1.blockMaterial == Material.iron) : this.getHarvestLevel >= 1) : this.getHarvestLevel >= 2) : this.getHarvestLevel >= 1) : this.getHarvestLevel >= 2);
	}
}
