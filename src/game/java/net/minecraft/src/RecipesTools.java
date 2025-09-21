package net.minecraft.src;

public class RecipesTools {
	private String[][] recipePatterns = new String[][]{{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"}, {"XX", " #", " #"}};
	private Object[][] recipeItems = new Object[][]{{Block.planks, Item.ingotCopper, Item.ingotIron, Item.diamond, Item.ingotGold}, {Item.pickaxeWood, Item.pickaxeCopper, Item.pickaxeSteel, Item.pickaxeDiamond, Item.pickaxeGold}, {Item.shovelWood, Item.shovelCopper, Item.shovel, Item.shovelDiamond, Item.shovelGold}, {Item.axeWood, Item.axeCopper, Item.axeSteel, Item.axeDiamond, Item.axeGold}, {Item.hoeWood, Item.hoeCopper, Item.hoeSteel, Item.hoeDiamond, Item.hoeGold}};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				Item var5 = (Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('#'), Item.stick, Character.valueOf('X'), var3});
			}
		}

	}
}
