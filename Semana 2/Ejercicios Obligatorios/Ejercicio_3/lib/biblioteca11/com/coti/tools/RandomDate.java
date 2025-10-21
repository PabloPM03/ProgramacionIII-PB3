package com.coti.tools;
import java.time.LocalDate;
import java.util.Random;
// Now has package and git
//
// Usage:
//
// RandomDate rd = new RandomDate(LocalDate.of(1900, 1, 1),
//                                LocalDate.of(2010, 1, 1));
//  * Test rig is ln-based
/**
 * Class <b>RandomDate</b> generates random a random LocalDate between two given
 * ones;<br>methods are not static hence an instance of RandomDate must be 
 * created before <br>using the one method in this class, nextDate().
 * @author coti
 */

public class RandomDate {
  private final LocalDate minDate;
  private final LocalDate maxDate;
  private final Random random;

    /**
     * This constructor gives back an instance of RandomDate the minimum and maximum<br>RandomDates passed as arguments.
     */
     /**
     * @param minDate - The minimum date of the segment inside which instances of<br> RandomDate are to be produced.
     * @param maxDate - The maximum date of the segment inside which instances of<br> RandomDate are to be produced.
     */
  public RandomDate(LocalDate minDate, LocalDate maxDate) {
    this.minDate = minDate;
    this.maxDate = maxDate;
    this.random = new Random();
  }

    /**
     *
     * @return
     */
    public LocalDate nextDate() {
    int minDay = (int) minDate.toEpochDay();
    int maxDay = (int) maxDate.toEpochDay();
    long randomDay = minDay + random.nextInt(maxDay - minDay);
    return LocalDate.ofEpochDay(randomDay);
  }

  @Override
  public String toString() {
    return "RandomDate{" +
    "maxDate=" + maxDate +
    ", minDate=" + minDate +
    '}';
  }
}
