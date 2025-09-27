package net.minecraft.src;

public class ItemDrink extends ItemFood {
	public ItemDrink(int var1, int var2) {
		super(var1, var2);
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		super.onItemRightClick(var1, var2, var3);
		return new ItemStack(Item.bottle);
	}
}
