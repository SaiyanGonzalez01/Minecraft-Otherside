package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.internal.buffer.FloatBuffer;
import net.lax1dude.eaglercraft.internal.buffer.IntBuffer;
import net.lax1dude.eaglercraft.opengl.EaglercraftGPU;

public class GLAllocation {
	private static List<Integer> displayLists = new ArrayList<Integer>();

	public static int generateDisplayLists(int var0) {
		int var1 = EaglercraftGPU.glGenLists(var0);
		displayLists.add(var1);
		displayLists.add(var0);
		return var1;
	}

	public static synchronized void deleteDisplayLists() {
		for(int var0 = 0; var0 < displayLists.size(); var0 += 2) {
			EaglercraftGPU.glDeleteLists(((Integer)displayLists.get(var0)).intValue(), ((Integer)displayLists.get(var0 + 1)).intValue());
		}
		displayLists.clear();
	}

	/**
	 * Creates and returns a direct byte buffer with the specified capacity. Applies
	 * native ordering to speed up access.
	 */
	public static synchronized ByteBuffer createDirectByteBuffer(int capacity) {
		return EagRuntime.allocateByteBuffer(capacity);
	}

	/**
	 * Creates and returns a direct int buffer with the specified capacity. Applies
	 * native ordering to speed up access.
	 */
	public static IntBuffer createIntBuffer(int capacity) {
		return EagRuntime.allocateIntBuffer(capacity);
	}

	/**
	 * Creates and returns a direct float buffer with the specified capacity.
	 * Applies native ordering to speed up access.
	 */
	public static FloatBuffer createFloatBuffer(int capacity) {
		return EagRuntime.allocateFloatBuffer(capacity);
	}
}
