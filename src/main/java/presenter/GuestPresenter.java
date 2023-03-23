package presenter;

import model.ArtWork;
import model.persistence.ArtWorkPersistence;
import view.GuestUserInterface;

import java.util.ArrayList;
import java.util.Collections;

public class GuestPresenter {
    ArtWorkPersistence artWorkPersistence;
    GuestUserInterface guestInterfaceView;
    public GuestPresenter(GuestUserInterface guestInterfaceView){
        this.guestInterfaceView=guestInterfaceView;
        artWorkPersistence=new ArtWorkPersistence();
    }
    public void showData(){
        Object[][] data=new Object[100][6];
        ArrayList<ArtWork> artWorks = (ArrayList<ArtWork>) artWorkPersistence.readAll();
        Collections.sort(artWorks);
        for (int i = 0; i < 100; i++) {
            data[i][0] = "";
            data[i][1] = "";
            data[i][2] = "";
            data[i][3] = "";
            data[i][4] = "";
            data[i][5] = "";
        }
        int index=0;
       for(ArtWork artWork: artWorks){
           data[index][0]= artWork.getIdArtWork();
           data[index][1] = artWork.getName();
           data[index][2] = artWork.getArtist();
           data[index][3] = artWork.getYear();
           data[index][4] = artWork.getType();
           data[index][5] = artWork.getArtGallery().getName();
           index++;
       }

        guestInterfaceView.paintTable(data);
    }
    public void filterData(){
        Object[][] data=new Object[100][6];
        ArrayList<ArtWork> artWorks = (ArrayList<ArtWork>) artWorkPersistence.readAll();

        for (int i = 0; i < 100; i++) {
            data[i][0] = "";
            data[i][1] = "";
            data[i][2] = "";
            data[i][3] = "";
            data[i][4] = "";
            data[i][5] = "";
        }
        int index=0;
        String filter=guestInterfaceView.accessComboBox();
        String textInfo=guestInterfaceView.accessFilterField();
        for(ArtWork artWork: artWorks){
            if(filter.equals("Type")) {
                if (artWork.getType().equals(textInfo)) {
                    data[index][0] = artWork.getIdArtWork();
                    data[index][1] = artWork.getName();
                    data[index][2] = artWork.getArtist();
                    data[index][3] = artWork.getYear();
                    data[index][4] = artWork.getType();
                    data[index][5] = artWork.getArtGallery().getName();
                    index++;
                }
            }else
            {
                if (artWork.getArtist().equals(textInfo)) {
                    data[index][0] = artWork.getIdArtWork();
                    data[index][1] = artWork.getName();
                    data[index][2] = artWork.getArtist();
                    data[index][3] = artWork.getYear();
                    data[index][4] = artWork.getType();
                    data[index][5] = artWork.getArtGallery().getName();
                    index++;
                }

            }
        }
        guestInterfaceView.paintTable(data);
    }
   public void changeToLogin(){
        this.guestInterfaceView.changeToLogin();
   }


}
