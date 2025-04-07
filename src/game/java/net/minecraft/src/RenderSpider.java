package net.minecraft.src;

import org.lwjgl.opengl.GL11;

import net.lax1dude.eaglercraft.opengl.RealOpenGLEnums;

public class RenderSpider extends RenderLiving {
	public RenderSpider() {
		super(new ModelSpider(), 1.0F);
		this.setRenderPassModel(new ModelSpider());
	}

	protected float a(EntitySpider var1) {
		return 180.0F;
	}

	protected boolean a(EntitySpider var1, int var2) {
		if(var2 != 0) {
			return false;
		} else if(var2 != 0) {
			return false;
		} else {
			this.loadTexture("/mob/spider_eyes.png");
			float var3 = (1.0F - var1.getEntityBrightness(1.0F)) * 0.5F;
			GL11.glEnable(RealOpenGLEnums.GL_BLEND);
			GL11.glDisable(RealOpenGLEnums.GL_ALPHA_TEST);
			GL11.glBlendFunc(RealOpenGLEnums.GL_SRC_ALPHA, RealOpenGLEnums.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, var3);
			return true;
		}
	}

	protected float getMaxDeathRotation(EntityLiving var1) {
		return this.a((EntitySpider)var1);
	}

	protected boolean shouldRenderPass(EntityLiving var1, int var2) {
		return this.a((EntitySpider)var1, var2);
	}
}
