/**
 * @author : ALE_IS_TER
 * Project Name: Layered_Architecture
 * Date        : 5/28/2022
 * Time        : 9:43 AM
 * @Since : 0.1.0
 */

package lk.ijse.pos.bo;


import lk.ijse.pos.bo.custom.impl.ManageBOImpl;
import lk.ijse.pos.bo.custom.impl.ManageItemsBOImpl;
import lk.ijse.pos.bo.custom.impl.PlaceOrderBOImpl;
import lk.ijse.pos.bo.custom.impl.SystemReportBOImpl;


public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        if (boFactory == null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
        MANAGECUSTOMER,MANAGEITEM,PLACEORDER,SYSTEMREPORT
    }


    //method for hide the object creation logic and return what client wants
    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case MANAGECUSTOMER:
                return new ManageBOImpl();
            case MANAGEITEM:
                return new ManageItemsBOImpl();
            case PLACEORDER:
                return  new PlaceOrderBOImpl();
            case SYSTEMREPORT:
                return  new SystemReportBOImpl();
            default:
                return null;
        }
    }
}
