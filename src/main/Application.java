package main;

import java.util.Scanner;

import domain.Reservation;
import service.ReservationService;

public class Application {

	public static void main(String[] args) {
		ReservationService service = new ReservationService();
		Scanner scanner = new Scanner(System.in);
		
		// 現在の予約状況を表示
		System.out.println("[現在の予約状況]--------------");
		service.showSchedule();
		System.out.println("------------------------------\n");
		
		// 名前、利用開始時刻、終了時刻を入力してもらう
		System.out.println("お名前、利用開始時刻、終了時刻を入力してください。");
		System.out.println("8:00～22:00の間で予約を受け付けています。\n");
		
		System.out.print("お名前　 >>> ");
		String name = scanner.nextLine();
		
		System.out.print("開始時刻 >>> ");
		String from = scanner.nextLine();
		
		System.out.print("終了時刻 >>> ");
		String to = scanner.nextLine();
		
		// 予約オブジェクトの生成
		Reservation reservationToAdd = new Reservation(name, from, to);
		
		// 入力時刻のバリデーション
		System.out.println("\n入力、ありがとうございました。ご予約可能か確認します。\n");
		System.out.println("... 確認中 ...\n");
		// 予約受付の完了、または予約できない旨を表示		
		if(service.isAbleToReserve(reservationToAdd)) {
			System.out.println(from + "～" + to + "で、" + name + "様のご予約を承りました。");
			// TODO データベースに保存する等の処理
		} else {
			System.out.println("申し訳ありませんが、ご希望の時間では予約できません。");
		}
		
		scanner.close();
	}

}
