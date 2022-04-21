package service;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import domain.Reservation;
import provider.ReservationProvider;

public class ReservationService {
	
	private static Logger logger = LogManager.getLogger();
	
	private List<Reservation> reservationList;
	private LocalTime openingTime;
	private LocalTime closingTime;
	
	public ReservationService() {
		List<Reservation> list = ReservationProvider.provideAll();
		// 予約リストを利用開始時刻順(昇順)に並び替え
		this.reservationList = list.stream()
				.sorted(Comparator.comparing(Reservation::getFrom))
				.toList();
		
		// 予約可能な時間を8:00～22:00に設定
		openingTime = LocalTime.of(8, 0);
		closingTime = LocalTime.of(22, 0);
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
	 * 指定された時刻で予約可能か判定する
	 * @param reservationToAdd 追加予定の予約オブジェクト
	 * @return 予約可能な場合はtrueを返す
	 */
	public boolean isAbleToReserve(Reservation reservationToAdd) {
		if(reservationToAdd == null) {
			logger.debug("引数がnull");
			return false;
		}
		
		LocalTime from = reservationToAdd.getFrom();
		LocalTime to = reservationToAdd.getTo();
		
		if(from == null || to == null) {
			logger.debug("開始時刻、または終了時刻が未設定");
			return false;
		}
		
		/*
		 *  開始時刻のチェック
		 */

		// 開始時刻よりも以前に終了時刻が設定されている
		if (!from.isBefore(to)) {
			logger.debug("終了時刻が開始時刻よりも前");
			return false;
		}
		
		// 開始時刻が開場時刻よりも前に設定されている
		if(from.isBefore(openingTime)) {
			logger.debug("開始時刻がオープン前");
			return false;
		}
		
		// 開始時刻が閉場時刻以降に設定されている
		if (!from.isBefore(closingTime)) {
			logger.debug("開始時刻がクローズ後");
			return false;
		}
		
		int position; // 開始時刻がどの予約の前に入っているのかを保持するための変数

		// 各予約時間との比較
		for (position = 0; position < reservationList.size(); position++) {
			Reservation rv = reservationList.get(position);

			// 次の予定の開始時刻の前
			if (from.isBefore(rv.getFrom())) {
				logger.debug("開始時刻は問題ない");
				break;
			}

			// 次の予定の開始時刻より後で終了時刻よりも前
			if (from.isBefore(rv.getTo())) {
				logger.debug("開始時刻が" + rv.getName() + "の予約時間中");
				return false;
			}
		}
		
		/*
		 * 終了時刻のチェック
		 */

		// 開始時刻が全ての予約よりも後の場合
		if (position == reservationList.size()) {
			// 閉場時刻を過ぎている
			if (to.isAfter(closingTime)) {
				logger.debug("終了時刻が閉場時刻を過ぎている");
				return false;
			}
		}
		
		// 終了時刻が次の予約者の開始時間よりも後
		Reservation rv = reservationList.get(position); // 比較対象の予約
		if (to.isAfter(rv.getFrom())) {
			logger.debug("終了時刻が" + rv.getName() + "の開始時刻後");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 予約１件分を表示
	 * @param rv １件分の予約
	 */
	private void showReservation(Reservation rv) {
		System.out.println(rv.getFrom() + "～" + rv.getTo() + "　" + rv.getName() + "様");
	}

}
