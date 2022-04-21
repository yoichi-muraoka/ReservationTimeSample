package domain;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
	
	private String name;    // 予約者氏名
	private LocalTime from; // 開始時刻
	private LocalTime to;   // 終了時刻
	
	/*
	 * 開始時刻、終了時刻を「9:15」のような文字列形式で
	 * セットできるように、コンストラクタ、セッターをオーバーロード
	 */
	public Reservation(String name, String from, String to) {
		this.name = name;
		this.from = convertToLocalTime(from);
		this.to = convertToLocalTime(to);
	}
	
	public void setFrom(String from) {
		this.from = convertToLocalTime(from);
	}
	
	public void setTo(String to) {
		this.from = convertToLocalTime(to);
	}
	
	/**
	 * 文字列の時間をLocalTimeに変換する
	 * @param time 「9:15」のような形式の時間
	 * @return LocalTime型の時間
	 */
	private LocalTime convertToLocalTime(String time) {
		String[] splitedTime = time.split(":");
		return LocalTime.of(Integer.parseInt(splitedTime[0]), Integer.parseInt(splitedTime[1]));
	}

}
