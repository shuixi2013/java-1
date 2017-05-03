package com.by.exception;

public class AuctionExceptionTest {
	private double initPrice = 30.0;

	public void bid(String bidPrice) throws AuctionException {
		double d = 0.0;
		try {
			d = Double.parseDouble(bidPrice);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AuctionException("sdff");
		}
		if (initPrice > d) {
			throw new AuctionException("dsadf");
		}
		initPrice = d;
	}

	public static void main(String[] args) {
		AuctionExceptionTest at = new AuctionExceptionTest();
		try {
			at.bid("df");
		} catch (AuctionException ae) {
			System.err.println(ae.getMessage());
		}
	}
}

/**
 * 业务异常
 * 
 * @author baoyang
 */
class AuctionException extends Exception {
	private static final long serialVersionUID = -4832304044712738987L;

	public AuctionException() {
	}

	public AuctionException(String msg) {
		super(msg);
	}
}
