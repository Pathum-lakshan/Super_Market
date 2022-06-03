/**
 * @author : ALE_IS_TER
 * Project Name: Layered Architecture
 * Date        : 6/3/2022
 * Time        : 2:31 AM
 * @Since : 0.1.0
 */

package lk.ijse.pos.view;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class Util {
    public static void notifications(String text, String title){
        Notifications notifications=Notifications.create();
        notifications.darkStyle();
        notifications.text(text);
        notifications.title(title);
        notifications.hideAfter(Duration.seconds(4));
        notifications.showInformation();
    }
}
