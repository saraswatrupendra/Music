package Model;

public class listitem {
    private String title;
    private String desc;
    String coverimg;
    String song;
  //  boolean status;

    public listitem(String title,String desc,String coverimg,String song){//boolean status){
        this.title=title;
        this.desc=desc;
        this.coverimg=coverimg;
        this.song =song;
      //  this.status=status;
    }

  /*  public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
*/
    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }
}
