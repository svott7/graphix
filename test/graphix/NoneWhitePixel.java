package graphix;

import java.awt.Color;

public class NoneWhitePixel implements ObjectPixel {

	@Override
	public boolean is(int pixel) {
		return pixel != Color.WHITE.getRGB();
	}

}
