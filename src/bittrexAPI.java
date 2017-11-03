package bittrex;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.net.*;
import java.io.*;

public class bittrexAPI {

	private static final String API_URL = "https://bittrex.com/api/v1.1/";

	static String apiKEY                = null;
	static String apiSecretKEY          = null;

	public static enum Bittrex {
		PUBLIC, MARKET, ACCOUNT
	}

	public static String BittrexRequest(String API, Bittrex options) {

		String hashedHeader2 = null;
		String Link          = API;
		String inputLine     = null;
		String bittrexData   = null;
		URL url;

		switch (options) {
		case PUBLIC:
			try {
				url = new URL(Link);
				URLConnection Conn = url.openConnection();
				BufferedReader Data2 = new BufferedReader(new InputStreamReader(Conn.getInputStream()));

				while ((inputLine = Data2.readLine()) != null) {
					bittrexData = inputLine;
				}

				Data2.close();

				return bittrexData;
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case ACCOUNT:
			try {
				hashedHeader2 = HmacSha512.ENCRYPT(Link, apiSecretKEY);
				url = new URL(Link);
				URLConnection conn = url.openConnection();

				conn.setConnectTimeout(5000);
				conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				conn.setRequestProperty("apisign", hashedHeader2);

				InputStream response = conn.getInputStream();

				BufferedReader Data = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				while ((inputLine = Data.readLine()) != null) {
					bittrexData = inputLine;
				}

				Data.close();

				return bittrexData;

			} catch (IOException | InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			break;

		}
		return "101";

	}

	public static long getUnixTime() {
		return System.currentTimeMillis() / 1000L;
	}

	public static void setAPIKeys(String apikey, String apisecretkey) {
		apiKEY = apikey;
		apiSecretKEY = apisecretkey;
	}

	// PUBLIC API

	public static String getCustom(String custom) {
		return BittrexRequest(API_URL + custom, Bittrex.PUBLIC);
	}

	public static String getMarkets() {
		return BittrexRequest(API_URL + "public/getmarkets", Bittrex.PUBLIC);
	}

	public static String getCurrencies() {
		return BittrexRequest(API_URL + "public/getcurrencies", Bittrex.PUBLIC);
	}

	public static String getMarketSummaries() {
		return BittrexRequest(API_URL + "public/getmarketsummaries", Bittrex.PUBLIC);
	}

	public static String getMarketSummary(String market) {
		return BittrexRequest(API_URL + "public/getmarketsummary?market=" + market, Bittrex.PUBLIC);
	}

	public static String getTicker(String market) {
		return BittrexRequest(API_URL + "public/getticker?market=" + market, Bittrex.PUBLIC);
	}

	public static String getOrderBook(String market) {
		return BittrexRequest(API_URL + "public/getorderbook?market=" + market, Bittrex.PUBLIC);
	}

	public static String getMarketHistory(String market) {
		return BittrexRequest(API_URL + "public/getmarkethistory?market=" + market, Bittrex.PUBLIC);
	}

	// ACCOUNT

	public static String getBalances() {
		return BittrexRequest(API_URL + "account/getbalances?apikey=" + apiKEY + "&nonce=" + getUnixTime(),
				Bittrex.ACCOUNT);
	}

	public static String getBalance(String Currency) {
		return BittrexRequest(
				API_URL + "account/getbalance?apikey=" + apiKEY + "&nonce=" + getUnixTime() + "&currency=" + Currency,
				Bittrex.ACCOUNT);
	}

	public static String getDepositAddress(String Currency) {
		return BittrexRequest(API_URL + "account/getdepositaddress?apikey=" + apiKEY + "&nonce=" + getUnixTime()
				+ "&currency=" + Currency, Bittrex.ACCOUNT);
	}

	public static String Withdraw(String Currency, String quantity, String address) {
		return BittrexRequest(API_URL + "account/withdraw?apikey=" + apiKEY + "&nonce=" + getUnixTime() + "&currency="
				+ Currency + "&quantity=" + quantity + "&address=" + address, Bittrex.ACCOUNT);
	}

}
