package hu.tvarga.androidpublishingtestnew;

import org.junit.Before;
import org.junit.Test;

import hu.tvarga.androidpublishingtestnew.Beer;

import static org.junit.Assert.assertEquals;

public class BeerTest {

	private Beer beer;

	@Before
	public void setUp() {
		beer = new Beer();
	}

	@Test
	public void chug() {
		beer.chug();

		assertEquals(1, beer.getBeersChugged());
	}

	@Test
	public void chug_chug() {
		beer.chug();
		beer.chug();

		assertEquals(2, beer.getBeersChugged());
	}
}