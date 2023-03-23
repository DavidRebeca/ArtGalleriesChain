package presenter;

import model.ArtGallery;
import model.ArtWork;
import model.persistence.ArtGalleryPersistence;
import model.persistence.ArtWorkPersistence;
import view.UserInterface;

import java.util.ArrayList;

public class EmployeePresenter {
    ArtWorkPersistence artWorkPersistence;
    UserInterface userInterface;
    public EmployeePresenter(UserInterface userInterface){
        this.userInterface = userInterface;
        artWorkPersistence=new ArtWorkPersistence();
    }
    public void showData(){
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
        for(ArtWork artWork: artWorks){
            data[index][0]= artWork.getIdArtWork();
            data[index][1] = artWork.getName();
            data[index][2] = artWork.getArtist();
            data[index][3] = artWork.getYear();
            data[index][4] = artWork.getType();
            data[index][5] = artWork.getArtGallery().getName();
            index++;
        }
         this.userInterface.paintTable(data);
    }

    public void insertArtData(){
        String[] col=this.userInterface.getSelectedInfo();
        ArrayList<ArtWork> artWorks = (ArrayList<ArtWork>) artWorkPersistence.readAll();
        ArtGalleryPersistence artGalleryPersistence=new ArtGalleryPersistence();
        if(artGalleryPersistence.findGalleryByName(col[5])!=null) {
            ArtGallery artGallery = artGalleryPersistence.findGalleryByName (col[5]);
            ArtWork artWork = new ArtWork((artWorks.size() + 1), col[1], col[2], Integer.parseInt(col[3]), col[4], artGallery);
            this.artWorkPersistence.insert(artWork);
            this.userInterface.writeMessageOptionPanel("Art work was inserted!!");
        }else this.userInterface.writeMessageOptionPanel("Art Gallery invalid!");
        refresh();
    }

    public void updateArtData(){
        String[] col=this.userInterface.getSelectedInfo();
        ArtWork artWork=artWorkPersistence.findArtWorkById(Integer.parseInt(col[0]));
        artWork.setName(col[1]);
        artWork.setArtist(col[2]);
        artWork.setYear(Integer.parseInt(col[3]));
        artWork.setType(col[4]);
        ArtGalleryPersistence artGalleryPersistence=new ArtGalleryPersistence();
        if(artGalleryPersistence.findGalleryByName(col[5])!=null) {
            artWork.setArtGallery(artGalleryPersistence.findGalleryByName(col[5]));
            this.artWorkPersistence.update(artWork);
            this.userInterface.writeMessageOptionPanel("Update done!!");
        }else this.userInterface.writeMessageOptionPanel("Art Gallery invalid!");
        refresh();

    }
    public void deleteArtData(){
        String[] col=this.userInterface.getSelectedInfo();
        ArtWork artWork=artWorkPersistence.findArtWorkById(Integer.parseInt(col[0]));
        this.artWorkPersistence.delete(artWork);
        this.userInterface.writeMessageOptionPanel("Delete done!!");
        refresh();
    }

    public void changeToGuestView() {
        this.userInterface.changeViewtoGuest();
    }
    public void refresh() {
        this.userInterface.refresh();
    }
}
