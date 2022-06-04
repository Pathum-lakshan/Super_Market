/**
 * @author : ALE_IS_TER
 * Project Name: Layered Architecture
 * Date        : 6/3/2022
 * Time        : 9:03 PM
 * @Since : 0.1.0
 */

package lk.ijse.pos.controller;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.SystemReportBO;
import lk.ijse.pos.dto.OrderDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SystemReportFormController {
    public AnchorPane WorkingContex;
    public LineChart chartExpense;

    private final SystemReportBO systemReport =(SystemReportBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SYSTEMREPORT);

    public LineChart chartMonthly;
    public LineChart chartDaily;
    public BarChart barchartAnnual;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadAll();
    }

    private void loadAll() throws SQLException, ClassNotFoundException {
        loadAnnualIncome();
        loadMonthlyIncome();
        loadDailyIncome();
        loadItemReport();

    }

    private void loadItemReport() {

    }

    private void loadDailyIncome() throws SQLException, ClassNotFoundException {
        XYChart.Series dailyIncome = new XYChart.Series();
        ArrayList<OrderDTO> orderDTOS = systemReport.loadAllOrder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        String date_time =dtf.format(now);

        double total =0;

        int x = Integer.parseInt(date_time.substring(9,10));

        String a = date_time.substring(0,9);
        x=x-1;

        String dd =a+x;


        double total1 =0;

        for (OrderDTO order:orderDTOS
        ) {
            String nowDate= String.valueOf(order.getOrderDate());
            if (dd.equals(nowDate)){
                total1 = total1+order.getTotal();
            }
        }
        dailyIncome.getData().add(new XYChart.Data<>(dd,total1));


        for (OrderDTO order:orderDTOS
             ) {
            String nowDate= String.valueOf(order.getOrderDate());
            if (date_time.equals(nowDate)){
                total = total+order.getTotal();
            }
        }
        dailyIncome.getData().add(new XYChart.Data<>(date_time,total));


        dailyIncome.setName("Daily Report");

        chartDaily.getData().add(dailyIncome);

    }

    private void loadMonthlyIncome() throws SQLException, ClassNotFoundException {
        XYChart.Series monthlyIncome = new XYChart.Series();
        ArrayList<OrderDTO> orderDTOS = systemReport.loadAllOrder();

        String[] month = {"January","February","March","April","May","June","July","August","September","October","November","December"};

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();

        String date_time =dtf.format(now);

        for (int i = 0; i < month.length; i++) {

            if (i<10){

                double total=0;
                String date = date_time+"-0"+(i+1);

                for (OrderDTO order:orderDTOS
                     ) {
                    String dates = String.valueOf(order.getOrderDate());
                    String year = dates.substring(0,7);
                    if (date.equals(year)){
                        total=total+order.getTotal();
                    }
                }

                monthlyIncome.getData().add(new XYChart.Data<>(month[i],total));


            }else {


                double total=0;
                String date = date_time+"-"+(i+1);

                for (OrderDTO order:orderDTOS
                ) {
                    String dates = String.valueOf(order.getOrderDate());
                    String year = dates.substring(0,7);
                    if (date.equals(year)){
                        total=total+order.getTotal();
                    }
                }

                monthlyIncome.getData().add(new XYChart.Data<>(month[i],total));


            }

        }

        monthlyIncome.setName("Monthly Report");

        chartMonthly.getData().add(monthlyIncome);

    }

    private void loadAnnualIncome() throws SQLException, ClassNotFoundException {
        XYChart.Series yearlyIncome = new XYChart.Series();
        ArrayList<OrderDTO> orderDTOS = systemReport.loadAllOrder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();

        String date_time =dtf.format(now);

        double totalIncome=0;

        for (int i = 0; i < 3; i++) {
            String years = date_time.substring(0,3);

            String dates = years+i;

            for (OrderDTO orderDTO: orderDTOS
            ) {

                String date = String.valueOf(orderDTO.getOrderDate());

                int index = date.indexOf("-");

                String year = date.substring(0,index);

                if (dates.equals(year)){

                    totalIncome = totalIncome+orderDTO.getTotal();

                }
            }

            yearlyIncome.getData().add(new XYChart.Data<>(dates,totalIncome));

        }


        yearlyIncome.setName("Annual Report");
        barchartAnnual.getData().add(yearlyIncome);
    }
}
