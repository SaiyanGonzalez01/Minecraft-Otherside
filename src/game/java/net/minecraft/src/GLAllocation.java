package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.internal.buffer.FloatBuffer;
import net.lax1dude.eaglercraft.internal.buffer.IntBuffer;
import net.lax1dude.eaglercraft.opengl.EaglercraftGPU;

public class GLAllocation {
	private static final Map field_74531_a = new HashMap();
	private static List<Integer> displayLists = new ArrayList<Integer>();

	public static int generateDisplayLists(int var0) {
		int var1 = EaglercraftGPU.glGenLists(var0);
		field_74531_a.put(Integer.valueOf(var1), Integer.valueOf(var0));
		return var1;
	}

	public static void deleteDisplayLists(int par0) {
		EaglercraftGPU.glDeleteLists(par0, ((Integer) field_74531_a.remove(Integer.valueOf(par0))).intValue());
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
