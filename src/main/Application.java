package main;

import service.ReservationService;

public class Application {

	public static void main(String[] args) {
		ReservationService service = new ReservationService();
		
		// 現在の予約状況を表示
		System.out.println("[現在の予約状況]--------------");
		service.showSchedule();
		System.out.println("------------------------------\n");
		
		// 名前、利用開始時刻、終了時刻を入力してもらう
		System.out.println("お名前、利用開始時刻、終了時刻を入力してください。");
		System.out.println("8:00～22:00の間で予約を受け付けています。\n");
		System.out.println("お名前　 >>> 山田太郎");
		System.out.println("開始時刻 >>> 14:00");
		System.out.println("終了時刻 >>> 15:00");
		
		// 入力時刻のバリデーション
		System.out.println("\n入力、ありがとうございました。ご予約可能か確認します。\n");
		System.out.println("... 確認中 ...\n");
		
		// 予約受付の完了、または予約できない旨を表示
		System.out.println("14:00～15:00で、山田太郎様のご予約を承りました。");
		System.out.println("申し訳ありませんが、ご希望の時間では予約できません。");
	}

}
