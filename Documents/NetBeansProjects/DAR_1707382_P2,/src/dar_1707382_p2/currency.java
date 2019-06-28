/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar_1707382_p2;

/**
 *
 * @author widad
 */
public class currency {
  private int serialNumber;
  private int value;

    public int getSerialNumber() {
        return serialNumber;
    }

    public currency(int value) {
        this.value = value;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
  
  //All abvious queue methods
}
