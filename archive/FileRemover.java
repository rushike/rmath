import java.io.File;
import java.util.Arrays;

public class FileRemover{
    private String path;
    public FileRemover(String path){
        this.path = path;
    }
    public void remove(File file, String exten){
        File[] list = file.listFiles();
        //System.out.println("List : " + Arrays.toString(list));
        for(File f : list){
            if(f.isDirectory()){
                remove(f, exten);
            }else {
                int index = f.toString().lastIndexOf('.');
                String ext = f.toString().substring(index + 1);
                if(ext.equals(exten)){
                    f.delete();
                }
            }
        }
    }
}