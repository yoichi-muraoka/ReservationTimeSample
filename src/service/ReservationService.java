package service;

import java.util.Comparator;
import java.util.List;

import domain.Reservation;
import provider.ReservationProvider;

public class ReservationService {
	
	private List<Reservation> reservationList;
	
	public ReservationService() {
		List<Reservation> list = ReservationProvider.provideAll();
		// 予約リストを利用開始時刻順(昇順)に並び替え
		this.reservationList = list.stream()
				.sorted(Comparator.comparing(Reservation::getFrom))
				.toList();
	}
	
	/**
	 * 予約リストを表示
	 */
	public void showSchedule() {
		if(reservationList == null || reservationList.size() == 0) {
			System.out.println("現在、予約は入っていません。");
			return;
		}
		
		reservationList.stream()
		               .forEach(rv -> showReservation(rv));
	}
	
	/**
	 * 予約１件分を表示
	 * @param rv １件分の予約
	 */
	private void showReservation(Reservation rv) {
		System.out.println(rv.getFrom() + "～" + rv.getTo() + "　" + rv.getName() + "様");
	}

}
