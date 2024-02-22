import core.data.DataSource;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.List;

public class A4work {

    private static void createChart(String title, String xlab, String ylab, List<Double> xValue, List<Double> yValue) {
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
                .title(title)
                .xAxisTitle(xlab)
                .yAxisTitle(ylab)
                .build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.addSeries(title, xValue, yValue);
        new SwingWrapper<>(chart).displayChart();
    }


    public static void main(String[] args) {
    DataSource ds = DataSource.connect("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_day.csv").load();
    ArrayList<Earthquake> earthquakeArrayList = ds.fetchList("Earthquake", "latitude", "longitude", "depth", "place", "mag");
    ArrayList<Double> longitudeList = new ArrayList<>();
    ArrayList<Double> magList = new ArrayList<>();
    ArrayList<Double> latitudeList = new ArrayList<>();
    double highestMag = 0.0;
    double highestLon = 0.0;
    double highestLat = 0.0;
    String highestPlace = "";
    double secondhighestMag = 0.0;
    double secondhighestLon = 0.0;
    double secondhighestLat = 0.0;
    String secondhighestPlace = "";
    double meanLon = 0.0;
    double meanLat = 0.0;
    for (Earthquake e : earthquakeArrayList) {
        longitudeList.add(e.getLongitude());
        magList.add(e.getMag());
        latitudeList.add(e.getLatitude());
        if (e.getMag()>highestMag) {
            highestMag = e.getMag();
            highestLon = e.getLongitude();
            highestLat = e.getLatitude();
            highestPlace = e.getPlace();
        }
    }
    for (Earthquake e : earthquakeArrayList) {
            if (e.getMag() != highestMag) {
                if (e.getMag() > secondhighestMag) {
                    secondhighestMag = e.getMag();
                    secondhighestLon = e.getLongitude();
                    secondhighestLat = e.getLatitude();
                    secondhighestPlace = e.getPlace();
                }
            }
        }
    meanLon = (highestLon+secondhighestLon)/2;
    meanLat = (highestLat+secondhighestLat)/2;
    System.out.println("The highest magnitude earthquake in the last couple of weeks was at " + highestPlace + "which is " + "at the longitude of " + highestLon + " the latitude of " + highestLat);
    System.out.println(" and had the magnitude of " + highestMag);
    System.out.println("The second highest magnitude earthquake in the last couple of weeks was at " + secondhighestPlace +
            "which " +
            "is " + "at the longitude of " + secondhighestLon + " the latitude of " + secondhighestLat);
    System.out.println(" and had the magnitude of " + secondhighestMag);
    System.out.println("This means that the average longitude that would get the highest magnitude earthquakes is " + meanLon + " and the mean latitude that would get the highest magnitude earthquakes is " + meanLat);
  createChart("Longitude v Magnitude", "longitude", "magnitude",  longitudeList,magList);

  createChart("Latitude v Magnitude", "latitude", "magnitude",  latitudeList,magList);


    /*
    ArrayList<Earthquake> allEQ = ds.fetchList("Earthquake", "station/station_name",
            "station/station_id", "station/state",
            "station/latitude", "station/longitude");
    for (WeatherStation ws : allEQ) {
        if (ws.getLat()< smallest ) {
            smallest = ws.getLat();
            stateSmallest = ws.getName();
        }

    }

     */
    }
}
