/**
 * @author : ALE_IS_TER
 * Project Name: Layered Architecture
 * Date        : 6/3/2022
 * Time        : 9:03 PM
 * @Since : 0.1.0
 */

package lk.ijse.pos.controller;

import javafx.scene.chart.LineChart;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ManageItemBO;
import lk.ijse.pos.bo.custom.SystemReportBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDao;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;

public class SystemReportFormController {
    public AnchorPane WorkingContex;
    public LineChart chartIncome;
    public LineChart chartExpense;

    private final SystemReportBO systemReport =(SystemReportBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SYSTEMREPORT);

    public void initialize(){

    }
}
