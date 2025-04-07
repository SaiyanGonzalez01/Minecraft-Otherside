package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.lwjgl.opengl.GL11;

import net.lax1dude.eaglercraft.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.opengl.EaglercraftGPU;
import net.lax1dude.eaglercraft.opengl.ImageData;
import net.lax1dude.eaglercraft.opengl.RealOpenGLEnums;

public class RenderEngine {
	private HashMap<String, Integer> textureMap = new HashMap<String, Integer>();
	private HashMap<Integer, ImageData> textureContentsMap = new HashMap<Integer, ImageData>();
	private ByteBuffer imageData = GLAllocation.createDirectByteBuffer(262144);
	private List<TextureFX> textureList = new ArrayList<TextureFX>();
	private GameSettings options;
	private boolean clampTexture = false;

	public RenderEngine(GameSettings var1) {
		this.options = var1;
	}

	public int getTexture(String var1) {
		Integer var2 = (Integer)this.textureMap.get(var1);
		if(var2 != null) {
			return var2.intValue();
		} else {
			try {
				int var4 = GL11.generateTexture();
				if(var1.startsWith("%%")) {
					this.clampTexture = true;
					this.setupTexture(ImageData.loadImageFile(var1.substring(2)), var4);
					this.clampTexture = false;
				} else {
					this.setupTexture(ImageData.loadImageFile(var1), var4);
				}

				this.textureMap.put(var1, Integer.valueOf(var4));
				return var4;
			} catch (Exception var3) {
				throw new RuntimeException("!!");
			}
		}
	}

	public int allocateAndSetupTexture(ImageData var1) {
		int var2 = GL11.generateTexture();
		this.setupTexture(var1, var2);
		this.textureContentsMap.put(Integer.valueOf(var2), var1);
		return var2;
	}

	public void setupTexture(ImageData var1, int var2) {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, var2);
		GL11.glTexParameteri(RealOpenGLEnums.GL_TEXTURE_2D, RealOpenGLEnums.GL_TEXTURE_MIN_FILTER, RealOpenGLEnums.GL_NEAREST);
		GL11.glTexParameteri(RealOpenGLEnums.GL_TEXTURE_2D, RealOpenGLEnums.GL_TEXTURE_MAG_FILTER, RealOpenGLEnums.GL_NEAREST);
		if(this.clampTexture) {
			GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10242 /* GL_TEXTURE_WRAP_S */, 10497 /* GL_REPEAT */);
			GL11.glTexParameteri(3553 /* GL_TEXTURE_2D */, 10243 /* GL_TEXTURE_WRAP_T */, 10497 /* GL_REPEAT */);
		} else {
			GL11.glTexParameteri(RealOpenGLEnums.GL_TEXTURE_2D, RealOpenGLEnums.GL_TEXTURE_WRAP_S, RealOpenGLEnums.GL_REPEAT);
			GL11.glTexParameteri(RealOpenGLEnums.GL_TEXTURE_2D, RealOpenGLEnums.GL_TEXTURE_WRAP_T, RealOpenGLEnums.GL_REPEAT);
		}

		int var3 = var1.width;
		int var4 = var1.height;
		int[] var5 = new int[var3 * var4];
		byte[] var6 = new byte[var3 * var4 * 4];
		var1.getRGB(0, 0, var3, var4, var5, 0, var3);

		for(int var7 = 0; var7 < var5.length; ++var7) {
			int var8 = var5[var7] >> 24 & 255;
			int var9 = var5[var7] >> 16 & 255;
			int var10 = var5[var7] >> 8 & 255;
			int var11 = var5[var7] & 255;
			if(this.options != null && this.options.anaglyph) {
				int var12 = (var9 * 30 + var10 * 59 + var11 * 11) / 100;
				int var13 = (var9 * 30 + var10 * 70) / 100;
				int var14 = (var9 * 30 + var11 * 70) / 100;
				var9 = var12;
				var10 = var13;
				var11 = var14;
			}

			var6[var7 * 4 + 0] = (byte)var9;
			var6[var7 * 4 + 1] = (byte)var10;
			var6[var7 * 4 + 2] = (byte)var11;
			var6[var7 * 4 + 3] = (byte)var8;
		}

		this.imageData.clear();
		this.imageData.put(var6);
		this.imageData.position(0).limit(var6.length);
		EaglercraftGPU.glTexImage2D(RealOpenGLEnums.GL_TEXTURE_2D, 0, RealOpenGLEnums.GL_RGBA8, var3, var4,
				0, RealOpenGLEnums.GL_RGBA, RealOpenGLEnums.GL_UNSIGNED_BYTE, (ByteBuffer) imageData);
	}

	public void deleteTexture(int var1) {
		this.textureContentsMap.remove(Integer.valueOf(var1));
		GL11.deleteTexture(var1);
	}

	public int getTextureForDownloadableImage(String var1, String var2) {
		return getTexture(var2);
	}

	public void registerTextureFX(TextureFX var1) {
		this.textureList.add(var1);
		var1.onTick();
	}

	public void updateDynamicTextures() {
		int var1;
		TextureFX var2;
		for(var1 = 0; var1 < this.textureList.size(); ++var1) {
			var2 = (TextureFX)this.textureList.get(var1);
			var2.anaglyphEnabled = this.options.anaglyph;
			var2.onTick();
			this.imageData.clear();
			this.imageData.put(var2.imageData);
			this.imageData.position(0).limit(var2.imageData.length);

			for(int var3 = 0; var3 < var2.tileSize; ++var3) {
				for(int var4 = 0; var4 < var2.tileSize; ++var4) {
					GL11.glTexSubImage2D(GL11.GL_TEXTURE_2D, 0, var2.iconIndex % 16 * 16 + var3 * 16, var2.iconIndex / 16 * 16 + var4 * 16, 16, 16, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)this.imageData);
				}
			}
		}

		for(var1 = 0; var1 < this.textureList.size(); ++var1) {
			var2 = (TextureFX)this.textureList.get(var1);
			if(var2.textureId > 0) {
				this.imageData.clear();
				this.imageData.put(var2.imageData);
				this.imageData.position(0).limit(var2.imageData.length);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, var2.textureId);
				GL11.glTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 16, 16, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)this.imageData);
			}
		}

	}

	public void refreshTextures() {
		Iterator<?> var1 = this.textureContentsMap.keySet().iterator();

		ImageData var3;
		while(var1.hasNext()) {
			int var2 = ((Integer)var1.next()).intValue();
			var3 = (ImageData)this.textureContentsMap.get(Integer.valueOf(var2));
			this.setupTexture(var3, var2);
		}

		var1 = this.textureMap.keySet().iterator();

		while(var1.hasNext()) {
			String var7 = (String)var1.next();

			try {
				if(var7.startsWith("%%")) {
					this.clampTexture = true;
					var3 = ImageData.loadImageFile(var7.substring(2));
					this.clampTexture = false;
				} else {
					var3 = ImageData.loadImageFile(var7);
				}

				int var4 = ((Integer)this.textureMap.get(var7)).intValue();
				this.setupTexture(var3, var4);
			} catch (Exception var5) {
				var5.printStackTrace();
			}
		}

	}

	public void bindTexture(int var1) {
		if(var1 >= 0) {
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, var1);
		}
	}
}
