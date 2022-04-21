package provider;

import java.util.ArrayList;
import java.util.List;

import domain.Reservation;

public class ReservationProvider {
	
	private static List<Reservation> list; 
	
	// 予約状況の仮データ
	static {
		list = new ArrayList<>();
		list.add(new Reservation("本宮弘子", "15:00", "16:30"));
		list.add(new Reservation("笹川紘一", "10:00", "12:00"));
		list.add(new Reservation("藤上達也", "18:30", "20:30"));
	}
	
	/**
	 * 予約状況のリストを提供する
	 * @return 予約状況のリスト
	 */
	public static List<Reservation> provideAll() {
		return list;
	}

}
