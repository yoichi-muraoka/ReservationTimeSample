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

}
