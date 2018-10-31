package hu.tvarga.androidpublishingtest;

public class Beer {

	private int beersChugged;

	public void chug() {
		beersChugged++;
	}

	public int getBeersChugged() {
		return beersChugged;
	}

	public int getBeersChuggedWrong() {
		try {
			return beersChugged;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getBeersChuggedWrongToo() {
		try {
			return beersChugged;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getBeersChuggedWrongThree() {
		try {
			return beersChugged;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getBeersChuggedWrongFour() {
		try {
			return beersChugged;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getBeersChuggedWrongFive() {
		try {
			return beersChugged;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
