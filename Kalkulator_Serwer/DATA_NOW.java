package Kalkulator_Serwer;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;   


public class DATA_NOW {
String current_date, current_hour;
public DATA_NOW() {
DateTimeFormatter  dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDateTime now = LocalDateTime.now();

DateTimeFormatter  dth = DateTimeFormatter.ofPattern("dd/MM/yyyy/HH:mm");
LocalDateTime now2 = LocalDateTime.now();

current_date = dtf.format(now);  
current_hour = dth.format(now2);
};
}
