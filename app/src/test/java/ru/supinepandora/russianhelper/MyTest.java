package ru.supinepandora.russianhelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;


public class MyTest {
	@Test
	public void myTest1() {
		Activity act = Robolectric.setupActivity(MainActivity.class);
		Robolectric.buildActivity(MainActivity.class).create();
	}
}
