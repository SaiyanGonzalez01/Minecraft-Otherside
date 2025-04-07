package net.minecraft.src;

import org.lwjgl.opengl.GL11;

import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.opengl.BufferBuilder;
import net.lax1dude.eaglercraft.opengl.RealOpenGLEnums;
import net.lax1dude.eaglercraft.opengl.Tessellator;
import net.lax1dude.eaglercraft.opengl.VertexFormat;

public class LoadingScreenRenderer implements IProgressUpdate {
	private String currentlyDisplayedProgress = "";
	private Minecraft mc;
	private String currentlyDisplayedText = "";
	private long systemTime = EagRuntime.steadyTimeMillis();
	private boolean printText = false;

	public LoadingScreenRenderer(Minecraft var1) {
		this.mc = var1;
	}

	public void printText(String var1) {
		this.printText = false;
		this.drawScreen(var1);
	}

	public void displayProgressMessage(String var1) {
		this.printText = true;
		this.drawScreen(this.currentlyDisplayedText);
	}

	public void drawScreen(String var1) {
		if(!this.mc.running) {
			if(!this.printText) {
				throw new MinecraftError();
			}
		} else {
			this.currentlyDisplayedText = var1;
			ScaledResolution var2 = new ScaledResolution(this.mc.displayWidth, this.mc.displayHeight);
			int var3 = var2.getScaledWidth();
			int var4 = var2.getScaledHeight();
			GL11.glClear(RealOpenGLEnums.GL_DEPTH_BUFFER_BIT);
			GL11.glMatrixMode(RealOpenGLEnums.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0.0D, (double)var3, (double)var4, 0.0D, 100.0D, 300.0D);
			GL11.glMatrixMode(RealOpenGLEnums.GL_MODELVIEW);
			GL11.glLoadIdentity();
			GL11.glTranslatef(0.0F, 0.0F, -200.0F);
		}
	}

	public void displayLoadingString(String var1) {
		if(!this.mc.running) {
			if(!this.printText) {
				throw new MinecraftError();
			}
		} else {
			this.systemTime = 0L;
			this.currentlyDisplayedProgress = var1;
			this.setLoadingProgress(-1);
			this.systemTime = 0L;
		}
	}

	public void setLoadingProgress(int var1) {
		if(!this.mc.running) {
			if(!this.printText) {
				throw new MinecraftError();
			}
		} else {
			long var2 = EagRuntime.steadyTimeMillis();
			if(var2 - this.systemTime >= 20L) {
				this.systemTime = var2;
				ScaledResolution var4 = new ScaledResolution(this.mc.displayWidth, this.mc.displayHeight);
				int var5 = var4.getScaledWidth();
				int var6 = var4.getScaledHeight();
				GL11.glClear(RealOpenGLEnums.GL_DEPTH_BUFFER_BIT);
				GL11.glMatrixMode(RealOpenGLEnums.GL_PROJECTION);
				GL11.glLoadIdentity();
				GL11.glOrtho(0.0D, (double)var5, (double)var6, 0.0D, 100.0D, 300.0D);
				GL11.glMatrixMode(RealOpenGLEnums.GL_MODELVIEW);
				GL11.glLoadIdentity();
				GL11.glTranslatef(0.0F, 0.0F, -200.0F);
				GL11.glClear(RealOpenGLEnums.GL_DEPTH_BUFFER_BIT | RealOpenGLEnums.GL_COLOR_BUFFER_BIT);
				Tessellator tess = Tessellator.getInstance();
				BufferBuilder var7 = tess.getWorldRenderer();
				int var8 = this.mc.renderEngine.getTexture("/dirt.png");
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, var8);
				float var9 = 32.0F;
				var7.begin(7, VertexFormat.POSITION_TEX_COLOR);
				var7.posUV(0.0D, (double)var6, 0.0D, 0.0D, (double)((float)var6 / var9)).setColorOpaque_I(4210752).endVertex();;
				var7.posUV((double)var5, (double)var6, 0.0D, (double)((float)var5 / var9), (double)((float)var6 / var9)).setColorOpaque_I(4210752).endVertex();;
				var7.posUV((double)var5, 0.0D, 0.0D, (double)((float)var5 / var9), 0.0D).setColorOpaque_I(4210752).endVertex();;
				var7.posUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D).setColorOpaque_I(4210752).endVertex();;
				tess.draw();
				if(var1 >= 0) {
					byte var10 = 100;
					byte var11 = 2;
					int var12 = var5 / 2 - var10 / 2;
					int var13 = var6 / 2 + 16;
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					var7.begin(7, VertexFormat.POSITION_COLOR);
					var7.pos((double)var12, (double)var13, 0.0D).setColorOpaque_I(8421504).endVertex();
					var7.pos((double)var12, (double)(var13 + var11), 0.0D).setColorOpaque_I(8421504).endVertex();
					var7.pos((double)(var12 + var10), (double)(var13 + var11), 0.0D).setColorOpaque_I(8421504).endVertex();
					var7.pos((double)(var12 + var10), (double)var13, 0.0D).setColorOpaque_I(8421504).endVertex();
					var7.pos((double)var12, (double)var13, 0.0D).setColorOpaque_I(8454016).endVertex();
					var7.pos((double)var12, (double)(var13 + var11), 0.0D).setColorOpaque_I(8454016).endVertex();
					var7.pos((double)(var12 + var1), (double)(var13 + var11), 0.0D).setColorOpaque_I(8454016).endVertex();
					var7.pos((double)(var12 + var1), (double)var13, 0.0D).setColorOpaque_I(8454016).endVertex();
					tess.draw();
					GL11.glEnable(GL11.GL_TEXTURE_2D);
				}

				this.mc.fontRenderer.drawStringWithShadow(this.currentlyDisplayedText, (var5 - this.mc.fontRenderer.getStringWidth(this.currentlyDisplayedText)) / 2, var6 / 2 - 4 - 16, 16777215);
				this.mc.fontRenderer.drawStringWithShadow(this.currentlyDisplayedProgress, (var5 - this.mc.fontRenderer.getStringWidth(this.currentlyDisplayedProgress)) / 2, var6 / 2 - 4 + 8, 16777215);
				this.mc.updateDisplay();
			}
		}
	}
}
