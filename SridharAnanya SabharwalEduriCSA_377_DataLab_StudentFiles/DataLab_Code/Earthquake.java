public class Earthquake {
    //time,latitude,longitude,depth,mag,magType,nst,gap,dmin,rms,net,id,updated,place,type,horizontalError,depthError,magError,magNst,status,locationSource,magSource

    private double latitude;
    private double longitude;
    private double depth;

   private String place;

   private double mag;

    Earthquake(double latitude, double longitude, double depth, String place, double mag) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.depth = depth;
        this.place = place;
        this.mag = mag;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getDepth() {
        return depth;
    }

    public String getPlace() {
        return place;
    }

    public double getMag() {
        return mag;
    }


}
