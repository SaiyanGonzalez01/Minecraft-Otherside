package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class GuiBlastFurnace extends GuiContainer {
	private TileEntityBlastFurnace blastfurnaceInventory;

	public GuiBlastFurnace(InventoryPlayer var1, TileEntityBlastFurnace var2) {
		this.blastfurnaceInventory = var2;
		this.inventorySlots.add(new Slot(this, var2, 0, 56, 17));
		this.inventorySlots.add(new Slot(this, var2, 1, 56, 53));
		this.inventorySlots.add(new Slot(this, var2, 2, 116, 35));

		int var3;
		for(var3 = 0; var3 < 3; ++var3) {
			for(int var4 = 0; var4 < 9; ++var4) {
				this.inventorySlots.add(new Slot(this, var1, var4 + (var3 + 1) * 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for(var3 = 0; var3 < 9; ++var3) {
			this.inventorySlots.add(new Slot(this, var1, var3, 8 + var3 * 18, 142));
		}

	}

	protected void drawGuiContainerForegroundLayer() {
		this.fontRenderer.drawString("Blast Furnace", 60, 6, 4210752);
		this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float var1) {
		int var2 = this.mc.renderEngine.getTexture("/gui/furnace.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var2);
		int var3 = (this.width - this.xSize) / 2;
		int var4 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var3, var4, 0, 0, this.xSize, this.ySize);
		int var5;
		if(this.blastfurnaceInventory.isBurning()) {
			var5 = this.blastfurnaceInventory.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(var3 + 56, var4 + 36 + 12 - var5, 176, 12 - var5, 14, var5 + 2);
		}

		var5 = this.blastfurnaceInventory.getCookProgressScaled(24);
		this.drawTexturedModalRect(var3 + 79, var4 + 34, 176, 14, var5 + 1, 16);
	}
}
